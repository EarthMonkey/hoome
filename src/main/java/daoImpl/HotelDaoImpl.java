package daoImpl;

import dao.HotelDao;
import entity.Hotel;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marioquer on 2017/3/17.
 */
@Repository
public class HotelDaoImpl implements HotelDao {
    private Session session;
    private Criteria criteria;


    @Override
    public boolean addHotel(Hotel hotel) {
        return HibernateUtil.addObject(hotel);
    }

    @Override
    public boolean updateHotel(Hotel hotel) {
        return HibernateUtil.updateObject(hotel);
    }

    @Override
    public Integer maxId() {
        String sql="select max(id) from hotel";
        session = HibernateUtil.currentSession();
        List<Integer> list = session.createSQLQuery(sql).list();
        HibernateUtil.closeSession();
        return list.get(0);
    }

    @Override
    public Hotel getHotelByOwner(Integer id) {
        Hotel hotel = null;
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.Hotel.class);
            criteria.add(Restrictions.eq("ownerId", id));
            List<Hotel> list = criteria.list();
            if (list.size() != 0) {
                hotel = list.get(0);
            }
            return hotel;
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return hotel;
        }
    }

    public static void main(String[] args){
        HotelDaoImpl a = new HotelDaoImpl();
        Integer b = a.maxId();
        System.out.println(b);

    }
}
