package helper;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by marioquer on 2017/3/14.
 */
public class HibernateUtil {
    public HibernateUtil() {
    }
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static final ThreadLocal threadLocal = new ThreadLocal();

    public static Session currentSession() {
        Session currentSession = (Session) threadLocal.get();
        if (currentSession == null) {
            currentSession = sessionFactory.openSession();
            threadLocal.set(currentSession);
        }
        return currentSession;
    }

    public static void closeSession() {
        Session currentSession = (Session) threadLocal.get();
        if (currentSession == null) {
            currentSession.close();
        }
        threadLocal.set(null);
    }


    public static boolean addObject(Object object){
        Session session;
        Criteria criteria;
        try {
            session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            session.save(object);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return false;
        }
    }

    public static boolean updateObject(Object object){
        Session session;
        Criteria criteria;
        try {
            session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            session.update(object);
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
