package DAL;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public int[] getAllThanhVienById() {
        List<Integer> listMaThanhVien = null;
        int[] maTV = new int[0];
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            listMaThanhVien = session.createQuery("SELECT maTV FROM thanhvien")
                    .getResultList();
            session.getTransaction().commit();

            // Chuyển danh sách thành mảng
            maTV = listMaThanhVien.stream().mapToInt(Integer::intValue).toArray();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return maTV;
    }

    public String getThanhVienById(int maTV) {
        String name = "";
        Session session = null;
        try {
            session = sessionFactory.openSession();
            thanhvien tv = session.get(thanhvien.class, maTV);
            if (tv != null) {
                name = tv.getHoTen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Đóng session ở đây thay vì trong khối finally
            }
        }
        return name;
    }
    public boolean insertViPham(int maTV, String selectedHinhThuc,int soTien, Timestamp ngayXL, int trangthai)
    {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            xuly xuLy = new xuly();
            xuLy.setMaTV(maTV);
            xuLy.setHinhThucXL(selectedHinhThuc);
            xuLy.setSoTien(soTien);
            xuLy.setNgayXL(ngayXL);
            xuLy.setTrangThaiXL(trangthai);
            session.save(xuLy);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }
    public List<xuly> searchData(String key) {
    List<xuly> resultList = new ArrayList<>();
    Session session = null;
    try {
        session = sessionFactory.openSession();
        // Sử dụng câu truy vấn HQL để tìm kiếm các bản ghi có chứa từ khóa trong cột nào đó
        String hql = "FROM xuly WHERE MaTV LIKE :keyword";
        Query<xuly> query = session.createQuery(hql, xuly.class);
        query.setParameter("keyword", "%" + key + "%");
        resultList = query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close();
        }
    }
    return resultList;
}

}
