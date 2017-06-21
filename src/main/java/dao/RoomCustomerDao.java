package dao;

import entity.RoomCustomer;

import java.util.List;

/**
 * Created by marioquer on 2017/3/17.
 */
public interface RoomCustomerDao {
    boolean addCustomer(RoomCustomer roomCustomer);

    List<RoomCustomer> getCheckIns();
}
