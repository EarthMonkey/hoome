package dao;

import entity.BossPay;

import java.util.List;

/**
 * Created by marioquer on 2017/3/19.
 */
public interface BossPayDao {
    boolean addBossPay(BossPay bossPay);

    boolean updateBossPay(BossPay bossPay);

    List<BossPay> getAllBossPay();

}
