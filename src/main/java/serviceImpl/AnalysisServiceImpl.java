package serviceImpl;

import dao.BossPayDao;
import entity.BossPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AnalysisService;

import java.util.*;

/**
 * Created by L.H.S on 2017/6/18.
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    BossPayDao bossPayDao;

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
        for (BossPay bp: bossPays) {

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
        for (String key: keySets) {
            String[] str = new String[2];
            str[0] = key;
            str[1] = map.get(key).toString();
            list.add(str);
        }

        return list;
    }

    @Override
    public Object[][] getFinanceCountryMap() {


        return new Object[0][];
    }

    class CountryMap {
        private String name;
        private double value;

        public CountryMap(String name, double value) {
            this.name = name;
            this.value = value;
        }
    }
}
