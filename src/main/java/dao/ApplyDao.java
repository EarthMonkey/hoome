package dao;

import entity.Apply;

import java.util.Map;

/**
 * Created by marioquer on 2017/3/17.
 */
public interface ApplyDao {
    Apply getApplyByOwner(Integer id);

    Apply getApplyById(Integer id);

    Map<String, Object> getSuspendApply();

    boolean addApply(Apply apply);

    boolean updateApply(Apply apply);
}
