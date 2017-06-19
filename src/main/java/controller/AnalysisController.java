package controller;

import model.CountryMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AnalysisService;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by L.H.S on 2017/6/13.
 */
@Controller
@RequestMapping(value = "/analysis", method = RequestMethod.POST)
@ResponseBody
public class AnalysisController {

    @Autowired
    AnalysisService analysisService;

    // 财务管理
    /**
     * [每月销售额][总销售额]
     * */
    @RequestMapping(value = "/finance/lineBar")
    public double[][] getFinanceLineBar() {

        return analysisService.getFinanceLineBar();
    }

    /**
     * 日历图
     * ["2016-01-01", data]
     * */
    @RequestMapping(value = "/finance/calendar")
    public ArrayList<String[]> getFinanceCalendar() {

        return analysisService.getFinanceCalendar();
    }

    /**
     * china map
     * [季度] [{name: '', value: }]
     * */
    @RequestMapping(value = "/finance/countryMap")
    public ArrayList<ArrayList<CountryMap>> getFinanceCountryMap() {

        return analysisService.getFinanceCountryMap();
    }

    // 订单管理

    // 会员管理

    // 入住管理

    // 销售管理

}
