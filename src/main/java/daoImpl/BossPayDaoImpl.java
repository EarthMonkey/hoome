package daoImpl;

import dao.BossPayDao;
import entity.BossPay;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by marioquer on 2017/3/19.
 */
@Repository
public class BossPayDaoImpl implements BossPayDao {
    private Session session;
    private Criteria criteria;

    @Override
    public boolean addBossPay(BossPay bossPay) {
        return HibernateUtil.addObject(bossPay);
    }

    @Override
    public boolean updateBossPay(BossPay bossPay) {
        return HibernateUtil.updateObject(bossPay);
    }

    @Override
    public List<BossPay> getAllBossPay() {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.BossPay.class);
            List<BossPay> list = criteria.list();

            if (list.size() == 0) {
                return null;
            } else {
                HibernateUtil.closeSession();
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return null;
        }
    }

    public static void main(String[] args) {

        BossPayDaoImpl b = new BossPayDaoImpl();

        String first = "2016-10-02 12:00:00";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date date = new Date();
        try {
            date = df.parse(first);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {

            date = new Date(date.getTime() + 24 * 3600 * 1000 * i);
            String dstr = df.format(date);

            BossPay bossPay = new BossPay();
            bossPay.setCreatedAt(Timestamp.valueOf(dstr));

            int amount = (int) (Math.random() * 1000);

            bossPay.setMoney(amount * 0.8);
            bossPay.setRecordId(i + 10);

            b.addBossPay(bossPay);
        }


    }
}
