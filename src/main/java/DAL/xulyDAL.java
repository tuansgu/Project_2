package DAL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class xulyDAL {
    private SessionFactory sessionFactory;

    public xulyDAL() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public List<xuly> getAllXuLy() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM xuly", xuly.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
