package daoImpl;

import dao.PointDao;
import entity.Point;
import helper.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marioquer on 2017/3/17.
 */
@Repository
public class PointDaoImpl implements PointDao {
    private Session session;
    private Criteria criteria;

    @Override
    public List<Point> getPointRecords(Integer vip_id) {
        try {
            session = HibernateUtil.currentSession();
            criteria = session.createCriteria(entity.Point.class);
            criteria.add(Restrictions.eq("vipId", vip_id));
            List<Point> list = criteria.list();

            if (list.size() == 0) {
                System.out.println(list);
                HibernateUtil.closeSession();
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

    @Override
    public boolean addRecord(Point point) {
        return HibernateUtil.addObject(point);
    }
}
