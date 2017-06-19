package daoImpl;

import dao.ApplyDao;
import entity.Apply;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by marioquer on 2017/3/17.
 */
@Repository
public class ApplyDaoImpl implements ApplyDao {
    private Session session;
    private Criteria criteria;

    @Override
    public Apply getApplyById(Integer id) {
        Apply apply = null;
        try {
            System.out.println(id);
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.Apply.class);
            criteria.add(Restrictions.eq("id", id));
            List<Apply> list = criteria.list();
            if (list.size() != 0) {
                System.out.println("notnull");
                apply = list.get(0);
            }
            HibernateUtil.closeSession();
            return apply;
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return apply;
        }
    }

    @Override
    public Map<String, Object> getSuspendApply() {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            byte apply = 0;
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.Apply.class);
            criteria.add(Restrictions.eq("status", apply));
            List<Apply> list = criteria.list();
            List<Apply> newList = new ArrayList<>();
            List<Apply> changeList = new ArrayList<>();
            if (list.size() == 0) {
                return null;
            }
            for (Apply a : list) {
                if (a.getType()==0)
                    newList.add(a);
                else
                    changeList.add(a);
            }
            result.put("newApply",newList);
            result.put("changeApply",changeList);
            HibernateUtil.closeSession();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return null;
        }
    }

    @Override
    public Apply getApplyByOwner(Integer id) {
        Apply apply = null;
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.Apply.class);
            criteria.add(Restrictions.eq("ownerId", id));
            List<Apply> list = criteria.list();
            if (list.size() != 0) {
                apply = list.get(0);
            }
            HibernateUtil.closeSession();
            return apply;
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return apply;
        }
    }

    @Override
    public boolean addApply(Apply apply) {
        return HibernateUtil.addObject(apply);
    }

    @Override
    public boolean updateApply(Apply apply) {
        return HibernateUtil.updateObject(apply);
    }
}
