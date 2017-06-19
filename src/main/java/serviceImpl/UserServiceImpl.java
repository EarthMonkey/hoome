package serviceImpl;

import dao.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by marioquer on 2017/3/13.
 */
@Service
public class UserServiceImpl implements UserService {
    public static Long ONE_YEAR_MILLI = 31536000000L;

    @Autowired
    UserDao userDao;
    @Autowired
    VipCardDao vipCardDao;
    @Autowired
    VipRecordDao vipRecordDao;
    @Autowired
    PointDao pointDao;
    @Autowired
    RoomDao roomDao;
    @Autowired
    BookRecordDao bookRecordDao;
    @Autowired
    VipLevelDao vipLevelDao;

    @Override
    public Map<String, Object> login(String phone, String password) {
        User user = userDao.getUser(phone);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("state", false);
        result.put("user", null);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                result.replace("state", false, true);
                result.replace("user", null, user);
                System.out.println(result.toString());
            }
        }
        return result;
    }

    @Override
    public User getUser(Integer id) {
        return userDao.getUser(id);
    }

    @Override
    public boolean logup(String phone, String name, String password, byte role) {
        return userDao.addUser(phone, name, password, role);
    }

    @Override
    public boolean newVip(Integer id) {
        byte zero = 0, one = 1;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Timestamp expire_time = new Timestamp(System.currentTimeMillis() + ONE_YEAR_MILLI);
        VipCard vipCard = new VipCard();
        vipCard.setUserId(id);
        vipCard.setBalance(0);
        vipCard.setLevel(zero);
        vipCard.setPoint(0);
        vipCard.setCreatedAt(time);
        vipCard.setUpdatedAt(time);
        vipCard.setExpiredAt(expire_time);
        vipCard.setStatus(zero);
        User user = userDao.getUser(id);
        user.setIsVip(one);
        return vipCardDao.addVip(vipCard) && userDao.updateUser(user);
    }

    @Override
    public boolean activateVip(Integer id, Double balance, String bank_card) {
        Timestamp update_time = new Timestamp(System.currentTimeMillis());
        Timestamp expire_time = new Timestamp(System.currentTimeMillis() + ONE_YEAR_MILLI);
        VipCard vipCard = vipCardDao.getVipCardByUserId(id);
        vipCard.setUpdatedAt(update_time);
        vipCard.setExpiredAt(expire_time);
        vipCard.setBalance(balance);
        vipCard.setStatus((byte) 1);
        User user = userDao.getUser(id);
        user.setIsVip((byte) 2);
        VipRecord vipRecord = new VipRecord();
        vipRecord.setVipId(vipCard.getId());
        vipRecord.setInOut(balance);
        vipRecord.setBalance(balance);
        vipRecord.setCreatedAt(update_time);
        vipRecord.setType((byte) 0);//正常
        return vipCardDao.updateVip(vipCard) && userDao.updateUser(user) && vipRecordDao.addRecord(vipRecord);
    }

    @Override
    public boolean topUp(Integer user_id, Double money) {
        Timestamp update_time = new Timestamp(System.currentTimeMillis());
        Timestamp expire_time = new Timestamp(System.currentTimeMillis() + ONE_YEAR_MILLI);
        VipCard vipCard = vipCardDao.getVipCardByUserId(user_id);
        vipCard.setBalance(vipCard.getBalance() + money);
        vipCard.setUpdatedAt(update_time);
        vipCard.setExpiredAt(expire_time);
        VipRecord vipRecord = new VipRecord();
        vipRecord.setVipId(vipCard.getId());
        vipRecord.setInOut(money);
        vipRecord.setBalance(vipCard.getBalance());
        vipRecord.setType((byte) 0);
        vipRecord.setCreatedAt(update_time);
        return vipCardDao.updateVip(vipCard) && vipRecordDao.addRecord(vipRecord);
    }

    @Override
    public Map<String, Object> getMyVip(Integer id) {
        Map<String, Object> result = new HashMap<String, Object>();
        VipCard vipCard = vipCardDao.getVipCard(id);
        result.put("vipCard", vipCard);
        result.put("vipRecords", vipRecordDao.getRecords(vipCard.getId()));
        result.put("point", pointDao.getPointRecords(vipCard.getId()));
        return result;
    }

    @Override
    public List<Room> searchRooms(String beginTime, String endTime, byte roomType) {
        return roomDao.getRoomsByType(roomType);
    }

    @Override
    public boolean bookRoom(Integer booker_id, Double price, Integer room_id, Integer hotel_id, byte room_style, byte pay_method, String target_in_time, String target_out_time, String hotel_name) {
        //会员卡支付，则生成订单，扣除金额，增加积分
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Timestamp expire = new Timestamp(System.currentTimeMillis() + ONE_YEAR_MILLI);

        Double originPrice = price;
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookerId(booker_id);
        bookRecord.setRoomStyle(room_style);
        bookRecord.setStatus((byte) 0);
        bookRecord.setTargetInTime(target_in_time);
        bookRecord.setTargetOutTime(target_out_time);
        bookRecord.setPayMethod(pay_method);
        bookRecord.setHotelId(hotel_id);
        bookRecord.setHotelName(hotel_name);
        bookRecord.setBookTime(timestamp);
        bookRecord.setIsPaid((byte) 0);
        VipCard vipCard = vipCardDao.getVipCardByUserId(booker_id);
        VipLevel vipLevel = vipLevelDao.getVipLevel(vipCard.getLevel());
        Double amount = originPrice * (vipLevel.getDiscount());
        Double discount = originPrice - amount;
        Integer point = (int) (price * vipLevel.getPointLevel());

        Integer days = Integer.parseInt(target_out_time.split("-")[2]) - Integer.parseInt(target_in_time.split("-")[2]);

        bookRecord.setAmount(amount * days);
        bookRecord.setDiscount(discount * days);

        Point pointRecord = new Point();
        pointRecord.setVipId(vipCard.getId());
        pointRecord.setPoint(point * days);
        pointRecord.setBalance(vipCard.getPoint() + point * days);
        pointRecord.setTime(timestamp);
        pointRecord.setType((byte) 0); // 正常消费

        vipCard.setBalance(vipCard.getBalance() - amount * days);
        vipCard.setPoint(pointRecord.getBalance());
        vipCard.setUpdatedAt(timestamp);
        vipCard.setExpiredAt(expire);

        VipRecord vipRecord = new VipRecord();
        vipRecord.setVipId(vipCard.getId());
        vipRecord.setInOut(0 - amount * days);
        vipRecord.setBalance(vipCard.getBalance());
        vipRecord.setType((byte) 0);
        vipRecord.setCreatedAt(timestamp);

        return bookRecordDao.addRecord(bookRecord) && vipCardDao.updateVip(vipCard) && pointDao.addRecord(pointRecord) && vipRecordDao.addRecord(vipRecord);
    }

    @Override
    public List<BookRecord> getMyOrder(Integer id) {
        return bookRecordDao.getRecordByUser(id);
    }

    public static void main(String[] args) {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        Timestamp b = new Timestamp(System.currentTimeMillis() + 50);
//        System.out.println(timestamp.after(b));
    }

    @Override
    public boolean cancelOrder(Long order_id) {
        //状态改，还钱还积分
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        BookRecord bookRecord = bookRecordDao.getRecord(order_id);
        bookRecord.setStatus((byte) -1);
        VipCard vipCard = vipCardDao.getVipCardByUserId(bookRecord.getBookerId());

        vipCard.setBalance(vipCard.getBalance() + bookRecord.getAmount() * 0.9);
        vipCard.setPoint(vipCard.getPoint() - (int) bookRecord.getAmount());

        Point pointRecord = new Point();
        pointRecord.setVipId(vipCard.getId());
        pointRecord.setPoint(0 - (int) bookRecord.getAmount());
        pointRecord.setBalance(vipCard.getPoint());
        pointRecord.setTime(timestamp);
        pointRecord.setType((byte) 0); // 正常消费

        VipRecord vipRecord = new VipRecord();
        vipRecord.setVipId(vipCard.getId());
        vipRecord.setInOut(bookRecord.getAmount() * 0.9);
        vipRecord.setBalance(vipCard.getBalance());
        vipRecord.setType((byte) 0);
        vipRecord.setCreatedAt(timestamp);

        return bookRecordDao.updateRecord(bookRecord) && vipCardDao.updateVip(vipCard) && pointDao.addRecord(pointRecord) && vipRecordDao.addRecord(vipRecord);
    }

    @Override
    public boolean suspendCard(Integer id) {
        User user = userDao.getUser(id);
        user.setIsVip((byte) 3);
        VipCard vipCard = vipCardDao.getVipCard(id);
        vipCard.setStatus((byte) 3);
        return vipCardDao.updateVip(vipCard) && userDao.updateUser(user);
    }

    @Override
    public boolean reactiveCard(Integer id) {
        User user = userDao.getUser(id);
        user.setIsVip((byte) 2);
        VipCard vipCard = vipCardDao.getVipCard(id);
        vipCard.setStatus((byte) 2);
        return vipCardDao.updateVip(vipCard) && userDao.updateUser(user);
    }

    @Override
    public void checkVip() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        VipCard vipCard = vipCardDao.getVipCard(1);
        if (vipCard.getExpiredAt().before(timestamp)) {
            vipCard.setStatus((byte) 3);
            vipCardDao.updateVip(vipCard);
        }
    }

}
