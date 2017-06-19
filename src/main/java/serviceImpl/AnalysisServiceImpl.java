package serviceImpl;

import dao.BookRecordDao;
import dao.BossPayDao;
import dao.HotelDao;
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

    @Override
    public double[][] getFinanceLineBar() {

        double result[][] = new double[2][12];

        List<BossPay> bossPays = bossPayDao.getAllBossPay();
        for (BossPay bp : bossPays) {
            String time = bp.getCreatedAt().toString();
            String formTime = (time.split(" "))[0];

            int month = Integer.parseInt((formTime.split("-"))[1]);
            result[0][month] += bp.getMoney();

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

            if (map.get(formTime) != null) {
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
                    if (map1.get(location) != null) {
                        map1.put(location, map1.get(location) + money);
                    } else {
                        map1.put(location, money);
                    }
                } else if (bookTime <= df.parse("2016-06-30").getTime()) {
                    if (map2.get(location) != null) {
                        map2.put(location, map2.get(location) + money);
                    } else {
                        map2.put(location, money);
                    }
                } else if (bookTime <= df.parse("2016-09-30").getTime()) {
                    if (map3.get(location) != null) {
                        map3.put(location, map3.get(location) + money);
                    } else {
                        map3.put(location, money);
                    }
                } else {
                    if (map4.get(location) != null) {
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

}
