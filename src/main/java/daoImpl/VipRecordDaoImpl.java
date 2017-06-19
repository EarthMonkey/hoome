package daoImpl;

import dao.VipRecordDao;
import entity.VipRecord;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marioquer on 2017/3/19.
 */
@Repository
public class VipRecordDaoImpl implements VipRecordDao {
    private Session session;
    private Criteria criteria;
    @Override
    public boolean addRecord(VipRecord vipRecord) {
        return HibernateUtil.addObject(vipRecord);
    }

    @Override
    public List<VipRecord> getRecords(Integer vip_id) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.VipRecord.class);
            criteria.add(Restrictions.eq("vipId", vip_id));
            List<VipRecord> list = criteria.list();

            if (list.size() == 0) {
                System.out.println(list);
                HibernateUtil.closeSession();
                return null;
            } else {
                HibernateUtil.closeSession();
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return null;
        }
    }
}
