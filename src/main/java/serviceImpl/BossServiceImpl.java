package serviceImpl;

import dao.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.BossService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by marioquer on 2017/3/18.
 */
@Service
public class BossServiceImpl implements BossService {
    @Autowired
    ApplyDao applyDao;
    @Autowired
    HotelDao hotelDao;
    @Autowired
    RoomDao roomDao;
    @Autowired
    BookRecordDao bookRecordDao;
    @Autowired
    BossPayDao bossPayDao;

    @Override
    public boolean approveNew(Integer id) {
        //把apply变成新的hotel，修改标志位
        byte approve = 1;
        Apply apply = applyDao.getApplyById(id);
        apply.setStatus(approve);
        if (applyDao.updateApply(apply)) {
            Hotel hotel = new Hotel();
            hotel.setSmallNum(apply.getSmallNum());
            hotel.setBigNum(apply.getBigNum());
            hotel.setAddress(apply.getAddress());
            hotel.setPhone(apply.getPhone());
            hotel.setOwnerId(apply.getOwnerId());
            hotel.setIntroduction(apply.getIntroduction());
            hotel.setName(apply.getName());
            if (hotelDao.addHotel(hotel)) {
                byte small = 0, big = 1;
                Room smallRoom = new Room();
                Room bigRoom = new Room();
                int new_id = hotelDao.maxId();
                smallRoom.setHotelId(new_id);
                smallRoom.setRoomStyle(small);
                smallRoom.setPrice(apply.getSmallPrice());
                smallRoom.setHotelName(hotel.getName());
                smallRoom.setSpecialPrice(apply.getSmallPrice());
                bigRoom.setHotelId(new_id);
                bigRoom.setRoomStyle(big);
                bigRoom.setPrice(apply.getBigPrice());
                bigRoom.setHotelName(hotel.getName());
                bigRoom.setSpecialPrice(apply.getBigPrice());
                roomDao.addRoom(smallRoom);
                roomDao.addRoom(bigRoom);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean approveChange(Integer id) {
        //修改hotel和apply
        byte approve = 1;
        Apply apply = applyDao.getApplyById(id);
        apply.setStatus(approve);
        if (applyDao.updateApply(apply)) {
            Hotel hotel = hotelDao.getHotelByOwner(apply.getOwnerId());
            hotel.setSmallNum(apply.getSmallNum());
            hotel.setBigNum(apply.getBigNum());
            hotel.setAddress(apply.getAddress());
            hotel.setPhone(apply.getPhone());
            hotel.setIntroduction(apply.getIntroduction());
            hotel.setName(apply.getName());
            if (hotelDao.updateHotel(hotel)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Map<String, Object> getAllApply() {
        return applyDao.getSuspendApply();
    }

    @Override
    public List<BookRecord> getHotelOrder() {
        return bookRecordDao.getRecords();
    }

    @Override
    public boolean pay(Long record_id) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        BookRecord bookRecord = bookRecordDao.getRecord(record_id);
        bookRecord.setIsPaid((byte) 1);
        Double amount = bookRecord.getAmount();

        BossPay bossPay = new BossPay();
        bossPay.setCreatedAt(timestamp);
        bossPay.setMoney(amount * 0.8);
        bossPay.setRecordId(record_id);

        return bookRecordDao.updateRecord(bookRecord) && bossPayDao.addBossPay(bossPay);
    }
}
