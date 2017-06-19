package daoImpl;

import dao.RoomDao;
import entity.Room;
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
public class RoomDaoImpl implements RoomDao {
    private Session session;
    private Criteria criteria;
    @Override
    public List<Room> getRoomsByHotel(Integer hotel_id) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.Room.class);
            criteria.add(Restrictions.eq("hotelId", hotel_id));
            List<Room> list = criteria.list();
            if (list.size()!=0){
                return list;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return null;
        }
    }

    @Override
    public boolean addRoom(Room room) {
        try {
            session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();
            session.save(room);
            tx.commit();
            HibernateUtil.closeSession();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return false;
        }
    }

    @Override
    public boolean updateRoom(Room room) {
        return HibernateUtil.updateObject(room);
    }

    @Override
    public List<Room> getRoomsByType(byte type) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.Room.class);
            criteria.add(Restrictions.eq("roomStyle", type));
            List<Room> list = criteria.list();
            if (list.size()!=0){
                return list;
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
