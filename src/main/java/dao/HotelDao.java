package dao;

import entity.Hotel;

/**
 * Created by marioquer on 2017/3/17.
 */
public interface HotelDao {
    Hotel getHotelByOwner(Integer id);
    boolean addHotel(Hotel hotel);
    boolean updateHotel(Hotel hotel);
    Integer maxId();

    Hotel getHotelById(int id);
}
