package task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import service.UserService;

/**
 * Created by Administrator on 2017/3/13.
 */
@Component
public class Task {
    @Autowired
    UserService userService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void test() {
        /**
         * 有效期一年，到期后卡上费用不足将暂停其记录；  更新卡片信息isAvilibled以及用户状态为member_pause
         * 暂停1年后未支付，会员记录停止 ；  更新卡片信息deleteAt以及用户状态为User
         */
        userService.checkVip();
    }

}
