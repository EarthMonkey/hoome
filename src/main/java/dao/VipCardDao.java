package dao;

import entity.VipCard;

import java.util.List;

/**
 * Created by marioquer on 2017/3/17.
 */
public interface VipCardDao {
    boolean addVip(VipCard vipCard);

    boolean updateVip(VipCard vipCard);

    VipCard getVipCardByUserId(Integer id);

    VipCard getVipCard(Integer user_id);
}
