package dao;

import entity.Point;

import java.util.List;

/**
 * Created by marioquer on 2017/3/17.
 */
public interface PointDao {
    List<Point> getPointRecords(Integer vip_id);

    boolean addRecord(Point point);
}
