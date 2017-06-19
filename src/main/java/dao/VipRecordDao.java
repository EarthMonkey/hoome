package dao;

import entity.VipRecord;

import java.util.List;

/**
 * Created by marioquer on 2017/3/19.
 */
public interface VipRecordDao {
    boolean addRecord(VipRecord vipRecord);

    List<VipRecord> getRecords(Integer vip_id);
}
