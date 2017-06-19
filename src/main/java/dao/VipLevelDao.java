package dao;

import entity.VipLevel;

/**
 * Created by marioquer on 2017/3/17.
 */
public interface VipLevelDao {
    VipLevel getVipLevel(byte level);
}
