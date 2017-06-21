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

    /**订单管理 */
    double[][] getOrderLineBar();

    ArrayList<String[]> getOrderCalendar();

    ArrayList<ArrayList<CountryMap>> getOrderCountryMap();

    ArrayList<CountryMap> getOrderPieChart();

    /**会员管理 */

    /**入住管理 */

    /**销售管理 */


}
