package daoImpl;

import dao.RoomCustomerDao;
import entity.RoomCustomer;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marioquer on 2017/3/17.
 */
@Repository
public class RoomCustomerDaoImpl implements RoomCustomerDao {

    private Session session;
    private Criteria criteria;

    @Override
    public boolean addCustomer(RoomCustomer roomCustomer) {
        return HibernateUtil.addObject(roomCustomer);
    }

    @Override
    public List<RoomCustomer> getCheckIns() {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.RoomCustomer.class);
            List<RoomCustomer> list = criteria.list();

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

        RoomCustomerDaoImpl r = new RoomCustomerDaoImpl();

        for (int i=0; i<100; i++) {
            RoomCustomer roomCustomer = new RoomCustomer();
            roomCustomer.setRecordId(i+1);
            roomCustomer.setName("入住第" + i + "人");
            roomCustomer.setIdentityId("1");

            r.addCustomer(roomCustomer);
        }

    }
}
