package daoImpl;

import dao.BookRecordDao;
import entity.BookRecord;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by marioquer on 2017/3/17.
 */
@Repository
public class BookRecordDaoImpl implements BookRecordDao {
    private Session session;
    private Criteria criteria;

    @Override
    public boolean addRecord(BookRecord bookRecord) {
        return HibernateUtil.addObject(bookRecord);
    }

    @Override
    public boolean updateRecord(BookRecord bookRecord) {
        return HibernateUtil.updateObject(bookRecord);
    }

    @Override
    public List<BookRecord> getRecordByUser(Integer id) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.BookRecord.class);
            criteria.add(Restrictions.eq("bookerId", id));
            List<BookRecord> list = criteria.list();

            if (list.size() == 0) {
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

    @Override
    public BookRecord getRecord(Long id) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.BookRecord.class);
            criteria.add(Restrictions.eq("id", id));
            List<BookRecord> list = criteria.list();

            if (list.size() == 0) {
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

    @Override
    public List<BookRecord> getRecordByHoterOwner(Integer id) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.BookRecord.class);
            criteria.add(Restrictions.eq("hotelId", id));
            List<BookRecord> list = criteria.list();

            if (list.size() == 0) {
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

    @Override
    public Long maxId() {
        String sql = "select max(id) from book_record";
        session = HibernateUtil.currentSession();
        List<BigInteger> list = session.createSQLQuery(sql).list();
        HibernateUtil.closeSession();
        return new Long(list.get(0).toString());
    }


    @Override
    public List<BookRecord> getRecords() {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.BookRecord.class);
            List<BookRecord> list = criteria.list();

            if (list.size() == 0) {
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

    public static void main(String[] args) {
        Long a = new Long(1111);
        BigInteger b = new BigInteger(a.toString());
        System.out.println(b);
        System.out.println(new Long(b.toString()));
    }
}
