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
     */
    @RequestMapping(value = "/finance/lineBar")
    public double[][] getFinanceLineBar() {

        return analysisService.getFinanceLineBar();
    }

    /**
     * 日历图
     * ["2016-01-01", data]
     */
    @RequestMapping(value = "/finance/calendar")
    public ArrayList<String[]> getFinanceCalendar() {

        return analysisService.getFinanceCalendar();
    }

    /**
     * china map
     * [季度] [{name: '', value: }]
     */
    @RequestMapping(value = "/finance/countryMap")
    public ArrayList<ArrayList<CountryMap>> getFinanceCountryMap() {

        return analysisService.getFinanceCountryMap();
    }

    // 订单管理
    @RequestMapping(value = "/order/lineBar")
    public double[][] getOrderLineBar() {

        return analysisService.getOrderLineBar();
    }

    @RequestMapping(value = "/order/calendar")
    public ArrayList<String[]> getOrderCalendar() {

        return analysisService.getOrderCalendar();
    }

    @RequestMapping(value = "/order/countryMap")
    public ArrayList<ArrayList<CountryMap>> getOrderCountryMap() {

        return analysisService.getOrderCountryMap();
    }

    @RequestMapping(value = "/order/pieChart")
    public ArrayList<CountryMap> getOrderPie() {

        return analysisService.getOrderPieChart();
    }

    // 会员管理
    @RequestMapping(value = "/member/countryMap")
    public ArrayList<ArrayList<CountryMap>> getMemCountryMap() {

        return analysisService.getMemCountryMap();
    }

    @RequestMapping(value = "/member/pieChart")
    public ArrayList<CountryMap> getMemberPie() {

        return analysisService.getMemberPie();
    }

    // 入住管理
    @RequestMapping(value = "/checkIn/stylePieChart")
    public ArrayList<CountryMap> getStylePie() {

        return analysisService.getStylePie();
    }

    @RequestMapping(value = "/checkIn/countryMap")
    ArrayList<ArrayList<CountryMap>> getCheckInCountryMap() {

        return analysisService.getCheckInCountryMap();
    }

    // 销售管理
    @RequestMapping(value = "/market/plan")
    public ArrayList<String> getMarketPlan() {

        return analysisService.getMarket();
    }
}
