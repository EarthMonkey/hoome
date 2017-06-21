package service;

import model.CountryMap;

import java.util.ArrayList;

/**
 * Created by L.H.S on 2017/6/18.
 */
public interface AnalysisService {

    /**财务管理 */
    double[][] getFinanceLineBar();

    ArrayList<String[]> getFinanceCalendar();

    ArrayList<ArrayList<CountryMap>> getFinanceCountryMap();

    ArrayList<String[]> getFinanceBigBar();

    /**订单管理 */
    double[][] getOrderLineBar();

    ArrayList<String[]> getOrderCalendar();

    ArrayList<ArrayList<CountryMap>> getOrderCountryMap();

    ArrayList<CountryMap> getOrderPieChart();

    ArrayList<String[]> getOrderBigBar();

    /**会员管理 */
    ArrayList<ArrayList<CountryMap>> getMemCountryMap();

    ArrayList<CountryMap> getMemberPie();

    /**入住管理 */
    ArrayList<CountryMap> getStylePie();

    ArrayList<ArrayList<CountryMap>> getCheckInCountryMap();

    /**销售管理 */
    ArrayList<String> getMarket();


}
