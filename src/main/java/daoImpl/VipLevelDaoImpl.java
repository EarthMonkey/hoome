package daoImpl;

import dao.VipLevelDao;
import entity.VipLevel;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marioquer on 2017/3/17.
 */
@Repository
public class VipLevelDaoImpl implements VipLevelDao {
    private Session session;
    private Criteria criteria;
    @Override
    public VipLevel getVipLevel(byte level) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.VipLevel.class);
            criteria.add(Restrictions.eq("level", level));
            List<VipLevel> list = criteria.list();
            if (list.size()!=0){
                return list.get(0);
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return null;
        }
    }
}
