package serviceImpl;

import dao.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LandlordService;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by marioquer on 2017/3/17.
 */
@Service
public class LandlordServiceImpl implements LandlordService {
    @Autowired
    ApplyDao applyDao;
    @Autowired
    HotelDao hotelDao;
    @Autowired
    RoomDao roomDao;
    @Autowired
    BookRecordDao bookRecordDao;
    @Autowired
    RoomCustomerDao roomCustomerDao;

    @Override
    public boolean createHotel(Integer owner_id, String phone, String name, Integer small_num, Integer big_num, String address, String introduction, Double big_price, Double small_price) {
        byte bit = 0;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Apply apply = new Apply();
        System.out.println(owner_id);
        apply.setOwnerId(owner_id);
        apply.setPhone(phone);
        apply.setName(name);
        apply.setSmallNum(small_num);
        apply.setBigNum(big_num);
        apply.setAddress(address);
        apply.setIntroduction(introduction);
        apply.setBigPrice(big_price);
        apply.setSmallPrice(small_price);
        apply.setType(bit);
        apply.setStatus(bit);
        apply.setApplyTime(time);
        System.out.println(apply.getName());
        return applyDao.addApply(apply);
    }


    @Override
    public boolean updateHotel(Integer owner_id, String phone, String name, Integer small_num, Integer big_num, String address, String introduction) {
        byte bit = 0;
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Apply apply = new Apply();
        System.out.println(owner_id);
        apply.setOwnerId(owner_id);
        apply.setPhone(phone);
        apply.setName(name);
        apply.setSmallNum(small_num);
        apply.setBigNum(big_num);
        apply.setAddress(address);
        apply.setIntroduction(introduction);
        apply.setType((byte) 1);
        apply.setStatus(bit);
        apply.setApplyTime(time);
        System.out.println(apply.getName());
        return applyDao.addApply(apply);
    }

    @Override
    public Map<String, Object> getMyHotel(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Hotel hotel = hotelDao.getHotelByOwner(id);
        Apply apply = applyDao.getApplyByOwner(id);
        if (hotel != null) {
            List<Room> rooms = roomDao.getRoomsByHotel(hotel.getId());
            map.put("state", "approved");
            map.put("hotel", hotel);
            if (rooms != null) {
                if (rooms.get(0).getRoomStyle() == 0) {
                    map.put("small_room", rooms.get(0));
                    map.put("big_room", rooms.get(1));
                } else {
                    map.put("big_room", rooms.get(0));
                    map.put("small_room", rooms.get(1));
                }
            }
        } else {
            if (apply != null && apply.getStatus() == 0) {
                map.put("state", "suspend");
                map.put("apply", apply);
            } else {
                map.put("state", "none");
            }
        }
        return map;
    }

    @Override
    public List<Room> getTargetRooms(Timestamp beginTime, Timestamp endTime, byte roomType) {

        return null;
    }

    public static void main(String[] args) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Timestamp a = new Timestamp(System.currentTimeMillis());
        System.out.println(a);
        System.out.println("aaaa");
    }

    @Override
    public boolean publishSpecial(Integer user_id, String time, Double smallPrice, Double bigPrice) {
        Hotel hotel = hotelDao.getHotelByOwner(user_id);
        List<Room> rooms = roomDao.getRoomsByHotel(hotel.getId());
        Room one = rooms.get(0);
        Room two = rooms.get(1);
        if (one.getRoomStyle() == (byte) 0) {
            one.setSpecialPrice(smallPrice);
            two.setSpecialPrice(bigPrice);
        } else {
            one.setSpecialPrice(bigPrice);
            two.setSpecialPrice(smallPrice);
        }
        return roomDao.updateRoom(one) && roomDao.updateRoom(two);
    }

    @Override
    public List<BookRecord> getHotelOrder(Integer id) {
        Hotel hotel = hotelDao.getHotelByOwner(id);
        return bookRecordDao.getRecordByHoterOwner(hotel.getId());
    }

    @Override
    public boolean checkin(Long record_id, ArrayList<Map<String, String>> peoples) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        BookRecord bookRecord = bookRecordDao.getRecord(record_id);
        bookRecord.setInTime(timestamp);
        bookRecord.setStatus((byte) 1);
        boolean result = true;

        System.out.println(peoples.size() + "service");

        for (int i = 0; i < peoples.size(); i++) {
            RoomCustomer roomCustomer = new RoomCustomer();
            roomCustomer.setRecordId(record_id);
            roomCustomer.setName(peoples.get(i).get("name"));
            roomCustomer.setIdentityId(peoples.get(i).get("identity_id"));
            result = result && roomCustomerDao.addCustomer(roomCustomer);
        }

        return result && bookRecordDao.updateRecord(bookRecord);
    }

    @Override
    public boolean cashCheckin(Integer owner_id, ArrayList<Map<String, String>> peoples, byte room_style, Double price) {
        Hotel hotel = hotelDao.getHotelByOwner(owner_id);
        boolean result = true;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookerId(8);
        bookRecord.setDiscount(0);
        bookRecord.setAmount(price);
        bookRecord.setHotelId(hotel.getId());
        bookRecord.setRoomStyle(room_style);
        bookRecord.setStatus((byte) 1);
        bookRecord.setBookTime(timestamp);
        bookRecord.setInTime(timestamp);
        bookRecord.setPayMethod((byte) 0);
        bookRecord.setTargetInTime(timestamp.toString());
        bookRecord.setTargetOutTime(timestamp.toString());
        bookRecord.setIsPaid((byte) 0);
        result = result && bookRecordDao.addRecord(bookRecord);

        Long record_id = bookRecordDao.maxId();

        for (int i = 0; i < peoples.size(); i++) {
            RoomCustomer roomCustomer = new RoomCustomer();
            roomCustomer.setRecordId(record_id);
            roomCustomer.setName(peoples.get(i).get("name"));
            roomCustomer.setIdentityId(peoples.get(i).get("identity_id"));
            result = result && roomCustomerDao.addCustomer(roomCustomer);
        }

        return result;
    }

    @Override
    public boolean checkout(Long record_id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        BookRecord bookRecord = bookRecordDao.getRecord(record_id);
        bookRecord.setOutTime(timestamp);
        bookRecord.setStatus((byte) 2);
        return bookRecordDao.updateRecord(bookRecord);
    }
}
