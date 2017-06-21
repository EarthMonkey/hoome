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
        for (BookRecord br: books) {
            String time = br.getBookTime().toString();
            String formTime = (time.split(" "))[0];

            int month = Integer.parseInt((formTime.split("-"))[1]);
            result[0][month-1] += 1;
        }

        for (int i=0; i<12; i++) {
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
        for (BookRecord br: books) {
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

    public static void main(String[] args) {
        AnalysisServiceImpl a = new AnalysisServiceImpl();
        a.getFinanceCalendar();
    }
}
