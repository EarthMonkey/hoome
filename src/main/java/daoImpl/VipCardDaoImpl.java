package daoImpl;

import dao.VipCardDao;
import entity.User;
import entity.VipCard;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marioquer on 2017/3/17.
 */
@Repository
public class VipCardDaoImpl implements VipCardDao {
    private Session session;
    private Criteria criteria;

    @Override
    public boolean addVip(VipCard vipCard) {
        return HibernateUtil.addObject(vipCard);
    }

    @Override
    public boolean updateVip(VipCard vipCard) {
        return HibernateUtil.updateObject(vipCard);
    }

    @Override
    public VipCard getVipCardByUserId(Integer id) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.VipCard.class);
            criteria.add(Restrictions.eq("userId", id));
            List<VipCard> list = criteria.list();
            if (list.size() == 0) {
                return null;
            } else {
                VipCard vipCard = list.get(0);
                HibernateUtil.closeSession();
                return vipCard;
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return null;
        }
    }

    @Override
    public VipCard getVipCard(Integer user_id) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.VipCard.class);
            criteria.add(Restrictions.eq("userId", user_id));
            List<VipCard> list = criteria.list();

            if (list.size() == 0) {
                System.out.println(list);
                HibernateUtil.closeSession();
                return null;
            } else {
                HibernateUtil.closeSession();
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return null;
        }
    }
}
