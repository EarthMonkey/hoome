package daoImpl;

import dao.RoomCustomerDao;
import entity.RoomCustomer;
import helper.HibernateUtil;
import org.springframework.stereotype.Repository;

/**
 * Created by marioquer on 2017/3/17.
 */
@Repository
public class RoomCustomerDaoImpl implements RoomCustomerDao {
    @Override
    public boolean addCustomer(RoomCustomer roomCustomer) {
        return HibernateUtil.addObject(roomCustomer);
    }
}
