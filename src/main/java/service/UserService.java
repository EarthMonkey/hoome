package service;

import entity.BookRecord;
import entity.Room;
import entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by marioquer on 2017/3/13.
 */
public interface UserService {
    User getUser(Integer id);

    Map<String, Object> login(String phone, String password);

    boolean logup(String phone, String name, String password, byte role);

    boolean newVip(Integer id);

    boolean activateVip(Integer id, Double balance, String bank_card);

    boolean topUp(Integer user_id, Double money);

    Map<String, Object> getMyVip(Integer id);

    List<Room> searchRooms(String beginTime, String endTime, byte roomType);

    boolean bookRoom(Integer booker_id, Double price, Integer room_id, Integer hotel_id, byte room_style, byte pay_method, String target_in_time, String target_out_time, String hotel_name);

    List<BookRecord> getMyOrder(Integer id);

    boolean cancelOrder(Long order_id);

    boolean suspendCard(Integer id);

    boolean reactiveCard(Integer id);

    void checkVip();
}
