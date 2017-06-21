package daoImpl;

import dao.HotelDao;
import entity.Hotel;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marioquer on 2017/3/17.
 */
@Repository
public class HotelDaoImpl implements HotelDao {
    private Session session;
    private Criteria criteria;


    @Override
    public boolean addHotel(Hotel hotel) {
        return HibernateUtil.addObject(hotel);
    }

    @Override
    public boolean updateHotel(Hotel hotel) {
        return HibernateUtil.updateObject(hotel);
    }

    @Override
    public Integer maxId() {
        String sql = "select max(id) from hotel";
        session = HibernateUtil.currentSession();
        List<Integer> list = session.createSQLQuery(sql).list();
        HibernateUtil.closeSession();
        return list.get(0);
    }

    @Override
    public Hotel getHotelByOwner(Integer id) {
        Hotel hotel = null;
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.Hotel.class);
            criteria.add(Restrictions.eq("ownerId", id));
            List<Hotel> list = criteria.list();
            if (list.size() != 0) {
                hotel = list.get(0);
            }
            return hotel;
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return hotel;
        }
    }

    @Override
    public Hotel getHotelById(int id) {

        Hotel hotel = null;
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.Hotel.class);
            criteria.add(Restrictions.eq("id", id));
            List<Hotel> list = criteria.list();
            if (list.size() != 0) {
                hotel = list.get(0);
            }
            return hotel;
        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtil.closeSession();
            return hotel;
        }

    }

    public static void main(String[] args) {
        HotelDaoImpl a = new HotelDaoImpl();

        String[] address = {"北京-鼓楼", "黑龙江-哈尔滨", "内蒙古-呼和浩特", "新疆-塔里木", "西藏-香格里拉"
                , "青海-青海", "甘肃-西安", "宁夏-银川", "山西-山西", "天津-狗不理", "山东-青岛", "安徽-合肥"
                , "江苏-南京", "上海-浦东", "湖北-武汉", "湖南-湖南", "贵州-贵阳", "重庆-火锅", "四川-吐鲁番"
                , "云南-西双版纳", "广西-西部", "广东-广州", "福建-胡建", "江西-赣江", "浙江-杭州", "香港-九龙湾"
                , "澳门-都城", "海南-海口", "台湾-台北", "陕西-兵马俑", "河北-衡水", "辽宁-长春"};
        int ownerId[] = {5, 6, 9, 10, 11, 15};

        int n = address.length;
        for (int i = 0; i < n * 5; i++) {

            Hotel hotel = new Hotel();
            hotel.setSmallNum(100);
            hotel.setBigNum(200);
            hotel.setAddress(address[i % n]);
            hotel.setPhone("12334456");
            hotel.setOwnerId(ownerId[i % 6]);
            hotel.setIntroduction(address[i % n] + "第" + i + "客栈");
            hotel.setName(address[i % n] + "第" + i + "客栈");

            a.addHotel(hotel);
        }
    }

}
