package serviceImpl;

import dao.BookRecordDao;
import dao.BossPayDao;
import dao.HotelDao;
import dao.RoomCustomerDao;
import entity.BookRecord;
import entity.BossPay;
import model.CountryMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AnalysisService;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by L.H.S on 2017/6/18.
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    BossPayDao bossPayDao;
    @Autowired
    BookRecordDao bookRecordDao;
    @Autowired
    HotelDao hotelDao;
    @Autowired
    RoomCustomerDao roomCustomerDao;

    @Override
    public double[][] getFinanceLineBar() {

        double result[][] = new double[2][12];

        List<BossPay> bossPays = bossPayDao.getAllBossPay();
        for (BossPay bp : bossPays) {
            String time = bp.getCreatedAt().toString();
            String formTime = (time.split(" "))[0];

            int month = Integer.parseInt((formTime.split("-"))[1]);
            result[0][month - 1] += bp.getMoney();

        }

        for (int i = 0; i < 12; i++) {
            result[1][i] = result[0][i];
            for (int j = 0; j < i; j++) {
                result[1][i] += result[0][j];
            }
        }

        return result;
    }

    @Override
    public ArrayList<String[]> getFinanceCalendar() {

        Map<String, Double> map = new HashMap<>();

        List<BossPay> bossPays = bossPayDao.getAllBossPay();
        for (BossPay bp : bossPays) {

            String time = bp.getCreatedAt().toString();
            String formTime = (time.split(" "))[0];


            if (map.containsKey(formTime)) {
                map.put(formTime, map.get(formTime) + bp.getMoney());
            } else {
                map.put(formTime, bp.getMoney());
            }
        }

        ArrayList<String[]> list = new ArrayList<>();

        Set<String> keySets = map.keySet();
        for (String key : keySets) {
            String[] str = new String[2];
            str[0] = key;
            str[1] = map.get(key).toString();
            list.add(str);
        }

        return list;
    }

    @Override
    public ArrayList<ArrayList<CountryMap>> getFinanceCountryMap() {

        Map<String, Double> map1 = new HashMap<>();
        Map<String, Double> map2 = new HashMap<>();
        Map<String, Double> map3 = new HashMap<>();
        Map<String, Double> map4 = new HashMap<>();

        List<BookRecord> books = bookRecordDao.getRecords();
        for (BookRecord br : books) {

            int hotelId = br.getHotelId();
            double money = br.getAmount();
            String bookDate = (br.getBookTime().toString().split(" "))[0];

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            long bookTime = 0;
            try {
                bookTime = df.parse(bookDate).getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }

            String location = (hotelDao.getHotelById(hotelId).getAddress().split("-"))[0];

            try {

                if (bookTime <= df.parse("2016-03-31").getTime()) {
                    if (map1.containsKey(location)) {
                        map1.put(location, map1.get(location) + money);
                    } else {
                        map1.put(location, money);
                    }
                } else if (bookTime <= df.parse("2016-06-30").getTime()) {
                    if (map2.containsKey(location)) {
                        map2.put(location, map2.get(location) + money);
                    } else {
                        map2.put(location, money);
                    }
                } else if (bookTime <= df.parse("2016-09-30").getTime()) {
                    if (map3.containsKey(location)) {
                        map3.put(location, map3.get(location) + money);
                    } else {
                        map3.put(location, money);
                    }
                } else {
                    if (map4.containsKey(location)) {
                        map4.put(location, map4.get(location) + money);
                    } else {
                        map4.put(location, money);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        ArrayList<CountryMap> lists1 = new ArrayList<>();
        Set<String> keySets1 = map1.keySet();
        for (String key : keySets1) {
            CountryMap cmap = new CountryMap(key, map1.get(key));
            lists1.add(cmap);
        }

        ArrayList<CountryMap> lists2 = new ArrayList<>();
        Set<String> keySets2 = map2.keySet();
        for (String key : keySets2) {
            CountryMap cmap = new CountryMap(key, map2.get(key));
            lists2.add(cmap);
        }

        ArrayList<CountryMap> lists3 = new ArrayList<>();
        Set<String> keySets3 = map3.keySet();
        for (String key : keySets3) {
            CountryMap cmap = new CountryMap(key, map3.get(key));
            lists3.add(cmap);
        }

        ArrayList<CountryMap> lists4 = new ArrayList<>();
        Set<String> keySets4 = map4.keySet();
        for (String key : keySets4) {
            CountryMap cmap = new CountryMap(key, map4.get(key));
            lists4.add(cmap);
        }

        ArrayList<ArrayList<CountryMap>> results = new ArrayList<>();
        results.add(lists1);
        results.add(lists2);
        results.add(lists3);
        results.add(lists4);

        return results;
    }

    @Override
    public double[][] getOrderLineBar() {

        double result[][] = new double[2][12];

        List<BookRecord> books = bookRecordDao.getRecords();
        for (BookRecord br : books) {
            String time = br.getBookTime().toString();
            String formTime = (time.split(" "))[0];

            int month = Integer.parseInt((formTime.split("-"))[1]);
            result[0][month - 1] += 1;
        }

        for (int i = 0; i < 12; i++) {
            result[1][i] = result[0][i];
            for (int j = 0; j < i; j++) {
                result[1][i] += result[0][j];
            }
        }

        return result;
    }

    @Override
    public ArrayList<String[]> getOrderCalendar() {

        Map<String, Integer> map = new HashMap<>();

        List<BookRecord> books = bookRecordDao.getRecords();
        for (BookRecord br : books) {
            String time = br.getBookTime().toString();
            String formTime = (time.split(" "))[0];

            if (map.containsKey(formTime)) {
                map.put(formTime, map.get(formTime) + 1);
            } else {
                map.put(formTime, 1);
            }
        }

        ArrayList<String[]> list = new ArrayList<>();

        Set<String> keySets = map.keySet();
        for (String key : keySets) {
            String[] str = new String[2];
            str[0] = key;
            str[1] = map.get(key).toString();
            list.add(str);
        }

        return list;
    }

    @Override
    public ArrayList<ArrayList<CountryMap>> getOrderCountryMap() {

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        Map<String, Integer> map3 = new HashMap<>();
        Map<String, Integer> map4 = new HashMap<>();

        List<BookRecord> books = bookRecordDao.getRecords();
        for (BookRecord br : books) {

            int hotelId = br.getHotelId();
            String bookDate = (br.getBookTime().toString().split(" "))[0];

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            long bookTime = 0;
            try {
                bookTime = df.parse(bookDate).getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }

            String location = (hotelDao.getHotelById(hotelId).getAddress().split("-"))[0];

            try {

                if (bookTime <= df.parse("2016-03-31").getTime()) {
                    if (map1.containsKey(location)) {
                        map1.put(location, map1.get(location) + 1);
                    } else {
                        map1.put(location, 1);
                    }
                } else if (bookTime <= df.parse("2016-06-30").getTime()) {
                    if (map2.containsKey(location)) {
                        map2.put(location, map2.get(location) + 1);
                    } else {
                        map2.put(location, 1);
                    }
                } else if (bookTime <= df.parse("2016-09-30").getTime()) {
                    if (map3.containsKey(location)) {
                        map3.put(location, map3.get(location) + 1);
                    } else {
                        map3.put(location, 1);
                    }
                } else {
                    if (map4.containsKey(location)) {
                        map4.put(location, map4.get(location) + 1);
                    } else {
                        map4.put(location, 1);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        ArrayList<CountryMap> lists1 = new ArrayList<>();
        Set<String> keySets1 = map1.keySet();
        for (String key : keySets1) {
            CountryMap cmap = new CountryMap(key, map1.get(key));
            lists1.add(cmap);
        }

        ArrayList<CountryMap> lists2 = new ArrayList<>();
        Set<String> keySets2 = map2.keySet();
        for (String key : keySets2) {
            CountryMap cmap = new CountryMap(key, map2.get(key));
            lists2.add(cmap);
        }

        ArrayList<CountryMap> lists3 = new ArrayList<>();
        Set<String> keySets3 = map3.keySet();
        for (String key : keySets3) {
            CountryMap cmap = new CountryMap(key, map3.get(key));
            lists3.add(cmap);
        }

        ArrayList<CountryMap> lists4 = new ArrayList<>();
        Set<String> keySets4 = map4.keySet();
        for (String key : keySets4) {
            CountryMap cmap = new CountryMap(key, map4.get(key));
            lists4.add(cmap);
        }

        ArrayList<ArrayList<CountryMap>> results = new ArrayList<>();
        results.add(lists1);
        results.add(lists2);
        results.add(lists3);
        results.add(lists4);

        return results;
    }

    @Override
    public ArrayList<CountryMap> getOrderPieChart() {

        double orderNums = getOrderLineBar()[1][11];
        double totalNums = roomCustomerDao.getCheckIns().size();

        double otherNums = orderNums - totalNums;

        ArrayList<CountryMap> list = new ArrayList<>();

        CountryMap c1 = new CountryMap("会员预订数", orderNums);
        list.add(c1);
        CountryMap c2 = new CountryMap("非会员入住数", otherNums);
        list.add(c2);

        return list;
    }

    /**
     * 会员管理
     */
    @Override
    public ArrayList<ArrayList<CountryMap>> getMemCountryMap() {

        Map<String, Integer> map1 = new HashMap<>();  // 订单完成数
        Map<String, Integer> map3 = new HashMap<>();  // 总数
        Map<String, Double> map4 = new HashMap<>();  // 总金额

        List<BookRecord> books = bookRecordDao.getRecords();

        for (BookRecord br : books) {

            int hotelId = br.getHotelId();
            String location = (hotelDao.getHotelById(hotelId).getAddress().split("-"))[0];

            if (br.getStatus() == 1) {
                if (map1.containsKey(location)) {
                    map1.put(location, map1.get(location) + 1);
                } else {
                    map1.put(location, 1);
                }
            }

            if (map3.containsKey(location)) {
                map3.put(location, map3.get(location) + 1);
            } else {
                map3.put(location, 1);
            }

            if (map4.containsKey(location)) {
                map4.put(location, map4.get(location) + br.getAmount());
            } else {
                map4.put(location, br.getAmount());
            }

        }

        ArrayList<CountryMap> list1 = new ArrayList<>();
        ArrayList<CountryMap> list2 = new ArrayList<>();
        ArrayList<CountryMap> list3 = new ArrayList<>();
        ArrayList<CountryMap> list4 = new ArrayList<>();

        Set<String> keySets1 = map1.keySet();
        for (String key : keySets1) {
            CountryMap cmap1 = new CountryMap(key, map1.get(key) * 1.0000000 / map3.get(key));
            list1.add(cmap1);

            CountryMap cmap = new CountryMap(key, map4.get(key) / map3.get(key));
            list2.add(cmap);

            list3.add(new CountryMap(key, map3.get(key)));

            list4.add(new CountryMap(key, map4.get(key)));
        }


        ArrayList<ArrayList<CountryMap>> results = new ArrayList<>();
        results.add(list1);
        results.add(list2);
        results.add(list3);
        results.add(list4);

        return results;
    }

    @Override
    public ArrayList<CountryMap> getMemberPie() {
        ArrayList<CountryMap> cmap = getStylePie();

        int total = 0;
        for (CountryMap cm : cmap) {
            total += cm.getValue();
        }

        for (CountryMap cm : cmap) {
            cm.setValue((int) (cm.getValue() / total * 376));
        }

        ArrayList<CountryMap> result = new ArrayList<>();
        result.add(new CountryMap("经济单人间", cmap.get(2).getValue()));
        result.add(new CountryMap("舒适大床房", cmap.get(0).getValue()));
        result.add(new CountryMap("豪华商务房", cmap.get(1).getValue()));
        result.add(new CountryMap("其他", cmap.get(3).getValue()));

        return result;
    }

    /**
     * 入住管理
     */
    @Override
    public ArrayList<CountryMap> getStylePie() {

        ArrayList<CountryMap> results = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 0);
        map.put(2, 0);

        List<BookRecord> books = bookRecordDao.getRecords();
        for (BookRecord br : books) {
            int count = br.getRoomStyle();
            map.put(count, map.get(count) + 1);
        }

        String styles[] = {"经济单人间", "舒适大床房", "豪华商务房"};
        for (int i = 0; i < 3; i++) {
            results.add(new CountryMap(styles[i], map.get(i) * 2));
        }
        results.add(new CountryMap("其他", 34 * 2));

        return results;
    }

    @Override
    public ArrayList<ArrayList<CountryMap>> getCheckInCountryMap() {

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        Map<String, Integer> map3 = new HashMap<>();
        Map<String, Integer> map4 = new HashMap<>();

        List<BookRecord> books = bookRecordDao.getRecords();
        for (BookRecord br : books) {

            int hotelId = br.getHotelId();
            String bookDate = (br.getBookTime().toString().split(" "))[0];

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            long bookTime = 0;
            try {
                bookTime = df.parse(bookDate).getTime();
            } catch (Exception e) {
                e.printStackTrace();
            }

            String location = (hotelDao.getHotelById(hotelId).getAddress().split("-"))[0];

            try {

                if (bookTime <= df.parse("2016-03-31").getTime()) {
                    if (map1.containsKey(location)) {
                        map1.put(location, map1.get(location) + 1);
                    } else {
                        map1.put(location, 1);
                    }
                } else if (bookTime <= df.parse("2016-06-30").getTime()) {
                    if (map2.containsKey(location)) {
                        map2.put(location, map2.get(location) + 1);
                    } else {
                        map2.put(location, 1);
                    }
                } else if (bookTime <= df.parse("2016-09-30").getTime()) {
                    if (map3.containsKey(location)) {
                        map3.put(location, map3.get(location) + 1);
                    } else {
                        map3.put(location, 1);
                    }
                } else {
                    if (map4.containsKey(location)) {
                        map4.put(location, map4.get(location) + 1);
                    } else {
                        map4.put(location, 1);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        ArrayList<CountryMap> lists1 = new ArrayList<>();
        Set<String> keySets1 = map1.keySet();
        for (String key : keySets1) {
            CountryMap cmap = new CountryMap(key, map1.get(key)*2);
            lists1.add(cmap);
        }

        ArrayList<CountryMap> lists2 = new ArrayList<>();
        Set<String> keySets2 = map2.keySet();
        for (String key : keySets2) {
            CountryMap cmap = new CountryMap(key, map2.get(key)*2);
            lists2.add(cmap);
        }

        ArrayList<CountryMap> lists3 = new ArrayList<>();
        Set<String> keySets3 = map3.keySet();
        for (String key : keySets3) {
            CountryMap cmap = new CountryMap(key, map3.get(key)*2);
            lists3.add(cmap);
        }

        ArrayList<CountryMap> lists4 = new ArrayList<>();
        Set<String> keySets4 = map4.keySet();
        for (String key : keySets4) {
            CountryMap cmap = new CountryMap(key, map4.get(key)*2);
            lists4.add(cmap);
        }

        ArrayList<ArrayList<CountryMap>> results = new ArrayList<>();
        results.add(lists1);
        results.add(lists2);
        results.add(lists3);
        results.add(lists4);

        return results;
    }

    /**
     * 销售管理
     */
    @Override
    public ArrayList<String> getMarket() {

        ArrayList<String> results = new ArrayList<>();

        double financeLineBar[][] = getFinanceLineBar();
        String str1 = "";
        int increaseCount = 0;
        double max = 0;
        int maxMonth = 1;

        double maxBenifit = 0;
        int maxBenifitMonth = 0;

        double minB = financeLineBar[0][0];
        int minBM = 1;

        for (int i = 0; i < 11; i++) {
            double cha = financeLineBar[1][i + 1] - financeLineBar[1][i];
            if (cha > 0) {
                increaseCount++;
                if (cha > max) {
                    max = cha;
                    maxMonth = i + 1;
                }
            }

            if (financeLineBar[0][i] > maxBenifit) {
                maxBenifit = financeLineBar[0][i];
                maxBenifitMonth = i + 1;
            }

            if (financeLineBar[0][i + 1] < minB) {
                minB = financeLineBar[0][i + 1];
                minBM = i + 2;
            }

        }

        System.out.println(increaseCount + "   ----   " + (financeLineBar[1][11] - financeLineBar[1][0]));

        DecimalFormat df = new DecimalFormat("#.0");

        if (increaseCount > 3 && financeLineBar[1][11] - financeLineBar[1][0] > 0) {
            str1 = "客栈销售额总体呈现稳步增长趋势<br>最大涨幅在" + maxMonth + "月至" + (maxMonth + 1) + "月，达到" + df.format(max) + "元";

        } else {
            str1 = "客栈经营成果并不理想";
        }
        results.add(str1);

        String str2 = "其中，" + maxBenifitMonth + "月的营业成果尤为突出<br>2月和7月紧随其后<br>" +
                "因此在今年同期，可适当提升客栈价格";
        results.add(str2);

        String str3 = "同时，不可忽视在" + minBM + "月的惨淡经营<br>与" + maxBenifitMonth + "月相差" + df.format(maxBenifit - minB) + "之多<br>" +
                "因此，在今年同期可是适当发布一定的优惠计划，以促进销售";
        results.add(str3);

        ArrayList<CountryMap> orderPieChart = getOrderPieChart();
        String str4 = "在过去一年里，会员共预定（成功入住）" + orderPieChart.get(0).getValue() + "次<br>" +
                "其中，以\"" + "舒适大床房\"房型居多<br>对于其他房型可适当增加对会员的优惠，以促进销售";
        results.add(str4);

        ArrayList<CountryMap> orders = getOrderCountryMap().get(0);
        double orderM = 0;
        String location = "";
        for (CountryMap cm : orders) {
            if (cm.getValue() > orderM) {
                orderM = cm.getValue();
                location = cm.getName();
            }
        }

        String str5 = "另外，去年以江苏和" + location + "两个地区入住人次最多<br>可以考虑在该地区扩收新的客栈加盟";
        results.add(str5);

        return results;
    }
}
