package daoImpl;

import dao.UserDao;
import entity.User;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marioquer on 2017/3/13.
 */
@Repository
public class UserDaoImpl implements UserDao {
    private Session session;
    private Criteria criteria;

    @Override
    public boolean addUser(String phone, String name, String password, byte role) {
        try {
            session = HibernateUtil.currentSession();
            User user = new User();
            User isExist = this.getUser(phone);
            if(isExist==null){
                byte isVip = 0;
                user.setPhone(phone);
                user.setPassword(password);
                user.setName(name);
                user.setRole(role);
                Transaction tx = session.beginTransaction();
                session.save(user);
                tx.commit();
                HibernateUtil.closeSession();
                return true;
            }else{
                HibernateUtil.closeSession();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return false;
        }
    }

    @Override
    public User getUser(Integer id) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.User.class);
            criteria.add(Restrictions.eq("id", id));
            List<User> list = criteria.list();

            if (list.size() == 0) {
                return null;
            } else {
                User user = list.get(0);
                HibernateUtil.closeSession();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return null;
        }
    }

    @Override
    public User getUser(String phone) {

        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.User.class);
            criteria.add(Restrictions.eq("phone", phone));
            List<User> list = criteria.list();

            if (list.size() == 0) {
                System.out.println(phone);
                return null;
            } else {
                User user = list.get(0);
                HibernateUtil.closeSession();
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return null;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try {
            session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return false;
        }
    }
}
