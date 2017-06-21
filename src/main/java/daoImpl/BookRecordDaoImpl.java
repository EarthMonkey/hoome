package daoImpl;

import dao.BookRecordDao;
import entity.BookRecord;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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

        BookRecordDaoImpl b = new BookRecordDaoImpl();

        String first = "2016-08-15 12:00:00";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date date = new Date();
        try {
            date = df.parse(first);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 10; i++) {


            date = new Date(date.getTime() + 24*3600*1000* (i%100));
            String dstr = df.format(date);

            BookRecord bookRecord = new BookRecord();
            bookRecord.setBookerId(7);
            double amount = Math.random() * 1000;
            DecimalFormat decimalFormat = new DecimalFormat("#.0");
            bookRecord.setAmount(Double.parseDouble(decimalFormat.format(amount)));
            bookRecord.setRoomStyle((byte) 0);
            bookRecord.setStatus((byte) 1);
            bookRecord.setTargetInTime("");
            bookRecord.setTargetOutTime("");
            bookRecord.setPayMethod((byte) 1);
            bookRecord.setHotelId(i % 165 + 1);
            bookRecord.setHotelName("");
            bookRecord.setBookTime(Timestamp.valueOf(dstr));
            bookRecord.setIsPaid((byte) 0);

            b.addRecord(bookRecord);
        }


    }
}
