package dao;

import entity.Room;

import java.util.List;

/**
 * Created by marioquer on 2017/3/17.
 */
public interface RoomDao {
    List<Room> getRoomsByHotel(Integer hotel_id);

    boolean addRoom(Room room);

    boolean updateRoom(Room room);

    List<Room> getRoomsByType(byte type);
}
