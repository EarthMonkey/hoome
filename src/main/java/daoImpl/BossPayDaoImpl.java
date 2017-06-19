package daoImpl;

import dao.BossPayDao;
import entity.BossPay;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marioquer on 2017/3/19.
 */
@Repository
public class BossPayDaoImpl implements BossPayDao {
    private Session session;
    private Criteria criteria;

    @Override
    public boolean addBossPay(BossPay bossPay) {
        return HibernateUtil.addObject(bossPay);
    }

    @Override
    public boolean updateBossPay(BossPay bossPay) {
        return HibernateUtil.updateObject(bossPay);
    }

    @Override
    public List<BossPay> getAllBossPay() {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.BossPay.class);
            List<BossPay> list = criteria.list();

            if (list.size() == 0) {
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
