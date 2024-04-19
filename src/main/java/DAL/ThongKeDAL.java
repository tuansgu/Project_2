/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.spi.Configurable;
import org.hibernate.query.Query;

/**
 *
 * @author ACER
 */
public class ThongKeDAL {

    private SessionFactory sessionFactory;

    public ThongKeDAL() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public List<Object[]> getAllJoin() {
        Session session = null;
        Transaction transaction = null;
        List<Object[]> results = null;
        try {
            if (sessionFactory == null) {
                throw new IllegalStateException("hello em,mi");
            }
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hqlQuery = "SELECT "
                    + "    DATE_FORMAT(tt.tgVao, '%Y-%m-%d') AS ThoiGian, "
                    + "    tv.khoa, "
                    + "    tv.nganh, "
                    + "    COUNT(*) AS SoLuong "
                    + "FROM "
                    + "    thanhvien tv "
                    + "INNER JOIN "
                    + "    thongtinsd tt ON tv.maTV = tt.maTV "
                    + "GROUP BY "
                    + "    DATE_FORMAT(tt.tgVao, '%Y-%m-%d'), tv.khoa, tv.nganh";
            Query query = session.createQuery(hqlQuery);
            results = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }

    public List<String> getKhoa() {
        Session session = sessionFactory.openSession();
        List<String> listKhoa = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session = sessionFactory.openSession();
            String hqlQuery = "SELECT DISTINCT tv.khoa FROM thanhvien tv";
            Query query = session.createQuery(hqlQuery);
            listKhoa = query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKhoa;
    }

    public List<String> getNganh() {
        Session session = sessionFactory.openSession();
        List<String> listNganh = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session = sessionFactory.openSession();
            String hqlQuery = "SELECT DISTINCT tv.nganh FROM thanhvien tv";
            Query query = session.createQuery(hqlQuery);
            listNganh = query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNganh;
    }

    public List<Object[]> getJoinWithAll(Date startDate, Date endDate, String khoa, String nganh) {
        Session session = null;
        List<Object[]> results = null;
        try {
            if (sessionFactory == null) {
                throw new IllegalStateException("SessionFactory is not initialized.");
            }
            session = sessionFactory.openSession();
            String hqlQuery = "SELECT "
                    + "    DATE_FORMAT(tt.tgVao, '%Y-%m-%d ') AS ThoiGian, "
                    + "    tv.khoa, "
                    + "    tv.nganh, "
                    + "    COUNT(*) AS SoLuong "
                    + "FROM "
                    + "    thanhvien tv "
                    + "INNER JOIN "
                    + "    thongtinsd tt ON tv.maTV = tt.maTV "
                    + "WHERE "
                    + "    tt.tgVao BETWEEN :startDate AND :endDate ";
            if (khoa != null) {
                hqlQuery += "    AND tv.khoa = :khoa ";
            }
            if (nganh != null) {
                hqlQuery += "    AND tv.nganh = :nganh ";
            }
            hqlQuery += "GROUP BY "
                    + "    DATE_FORMAT(tt.tgVao, '%Y-%m-%d '), tv.khoa, tv.nganh";

            Query query = session.createQuery(hqlQuery);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            if (khoa != null) {
                query.setParameter("khoa", khoa);
            }
            if (nganh != null) {
                query.setParameter("nganh", nganh);
            }
            results = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return results;
    }

    public List<Object[]> getAllViPham() {
        Session session = null;
        Transaction transaction = null;
        List<Object[]> results = null;
        try {
            if (sessionFactory == null) {
                throw new IllegalStateException("hello em,mi");
            }
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hqlQuery = "SELECT tv.hoTen,xl.hinhThucXL,xl.soTien,xl.trangThaiXL FROM xuly xl JOIN thanhvien tv ON xl.maTV=tv.maTV";
            Query query = session.createQuery(hqlQuery);
            results = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }

    public List<Object[]> getAllViPhamDangXuLy() {
        Session session = null;
        Transaction transaction = null;
        List<Object[]> results = null;
        try {
            if (sessionFactory == null) {
                throw new IllegalStateException("hello em,mi");
            }
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hqlQuery = "SELECT tv.hoTen,xl.hinhThucXL,xl.soTien,xl.trangThaiXL FROM xuly xl JOIN thanhvien tv ON xl.maTV=tv.maTV";
            Query query = session.createQuery(hqlQuery);
            results = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return results;
    }

    public double tongTienViPham() {
        Session session = null;
        Transaction transaction = null;
        double totalFine = 0;
        try {
            if (sessionFactory == null) {
                throw new IllegalStateException("SessionFactory is not initialized.");
            }
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hqlQuery = "SELECT SUM(CASE WHEN x.trangThaiXL = 0 THEN x.soTien ELSE 0 END) FROM xuly x";
            Query query = session.createQuery(hqlQuery);
            totalFine = (Long) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return totalFine;
    }

    public int soLuongDangDuocXuLy() {
        Session session = null;
        Transaction transaction = null;
        int countTrangThai1 = 0;
        try {
            if (sessionFactory == null) {
                throw new IllegalStateException("SessionFactory is not initialized.");
            }
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hqlQuery = "SELECT COUNT(*) FROM xuly WHERE trangThaiXL = 1";
            Query query = session.createQuery(hqlQuery);
            countTrangThai1 = ((Number) query.uniqueResult()).intValue();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return countTrangThai1;
    }

    public int soLuongDaDuocXuLy() {
        Session session = null;
        Transaction transaction = null;
        int countTrangThai1 = 0;
        try {
            if (sessionFactory == null) {
                throw new IllegalStateException("SessionFactory is not initialized.");
            }
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hqlQuery = "SELECT COUNT(*) FROM xuly WHERE trangThaiXL = 0";
            Query query = session.createQuery(hqlQuery);
            countTrangThai1 = ((Number) query.uniqueResult()).intValue();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return countTrangThai1;
    }

    public List<String> getTenThietBi() {
        Session session = sessionFactory.openSession();
        List<String> listThietBi = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session = sessionFactory.openSession();
            String hqlQuery = "SELECT DISTINCT tb.tenTB FROM thietbi tb";
            Query query = session.createQuery(hqlQuery);
            listThietBi = query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThietBi;
    }

    public List<Object[]> getThietBiDuocMuon() {
        List<Object[]> listThietBiDuocMuon = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String hqlQuery = "SELECT tb.tenTB, tt.tgMuon, tt.tgTra, COUNT(*)"
                    + "FROM thongtinsd tt "
                    + "JOIN thietbi tb ON tt.maTB = tb.maTB "
                    + "WHERE tt.tgMuon IS NOT NULL"
                    +" GROUP BY DATE_FORMAT(tt.tgMuon, '%Y-%m-%d '), DATE_FORMAT(tt.tgTra, '%Y-%m-%d '), tb.tenTB";
            Query query = session.createQuery(hqlQuery);
            listThietBiDuocMuon = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listThietBiDuocMuon;
    }

    public List<Object[]> getJoinThietBiWithAll(Date startDate, Date endDate, String tenThietBi,boolean thoiGianTraIsNull) {
        Session session = null;
        List<Object[]> results = null;
        try {
            if (sessionFactory == null) {
                throw new IllegalStateException("SessionFactory is not initialized.");
            }
            session = sessionFactory.openSession();

            String hqlQuery = "SELECT "
                    + "    tb.tenTB, "
                    + "    DATE_FORMAT(tt.tgMuon, '%Y-%m-%d ') AS ThoiGianMuon, "
                    + "    DATE_FORMAT(tt.tgTra, '%Y-%m-%d ') AS ThoiGianTra, "
                    + "    COUNT(*) AS SoLuongMuon " // Added count column
                    + "FROM "
                    + "    thongtinsd tt "
                    + "INNER JOIN "
                    + "    thietbi tb ON tb.maTB = tt.maTB "
                    + "WHERE "
                    + "    tt.tgMuon BETWEEN :startDate AND :endDate ";

            if (tenThietBi != null && !tenThietBi.isEmpty()) {
                hqlQuery += " AND tb.tenTB = :tenThietBi";
            }
            if(thoiGianTraIsNull){
                 hqlQuery += " AND tt.tgTra IS NULL";
            }
            hqlQuery += " GROUP BY "
                    + "    DATE_FORMAT(tt.tgMuon, '%Y-%m-%d '), DATE_FORMAT(tt.tgTra, '%Y-%m-%d '), tb.tenTB";

            Query query = session.createQuery(hqlQuery);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            if (tenThietBi != null && !tenThietBi.isEmpty()) {
                query.setParameter("tenThietBi", tenThietBi);
            }

            results = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return results;
    }

}
