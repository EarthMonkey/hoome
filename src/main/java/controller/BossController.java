package controller;

import entity.BookRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BossService;

import java.util.List;
import java.util.Map;

/**
 * Created by marioquer on 2017/3/18.
 */
@Controller
@RequestMapping(value = "/boss")
public class BossController {
    @Autowired
    BossService bossService;

    @RequestMapping(value = "/allApply", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> myHotel() {
        Map<String, Object> json = bossService.getAllApply();
        return json;
    }

    @RequestMapping(value = "/approveNew", method = RequestMethod.POST)
    @ResponseBody
    public String approveNew(Integer apply_id) {
        if (bossService.approveNew(apply_id))
            return "success";
        else
            return "fail";
    }

    @RequestMapping(value = "/approveChange", method = RequestMethod.POST)
    @ResponseBody
    public String approveChange(Integer apply_id) {
        if (bossService.approveChange(apply_id))
            return "success";
        else
            return "fail";
    }

    @RequestMapping(value = "/getHotelOrder", method = RequestMethod.POST)
    @ResponseBody
    public List<BookRecord> getHotelOrder() {
        return bossService.getHotelOrder();
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public String pay(Long record_id) {
        return bossService.pay(record_id) ? "success" : "fail";
    }
}
