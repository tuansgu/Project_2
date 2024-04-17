/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author phanq
 */
public class thongtinsdDAL {

    Session session = HibernateUtil.getSessionFactory().openSession();
    thanhvien TV = new thanhvien();
    thietbi TB = new thietbi();

    public static thongtinsdDAL getInstance() {
        return new thongtinsdDAL();
    }

    public List<thongtinsd> layDanhSachThongTinSD() {
//        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery("from thongtinsd", thongtinsd.class).list();
//        } catch (Exception e) {
//            System.out.println(e);
//            return null;
//        }
        try {

            List<thongtinsd> result = session.createQuery("from thongtinsd", thongtinsd.class).getResultList();
            return result;

        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public List<thongtinsd> layDanhSachThongTinSDTGMuon() {
        try {
            List<thongtinsd> result = session.createQuery("from thongtinsd where TGTra = null and TGMuon != null", thongtinsd.class).getResultList();
            return result;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public thanhvien layTenThanhVien(int maTV) {
        TV = new thanhvien();
        try {
            session.beginTransaction();

            String hql = "FROM thanhvien WHERE MaTV = :MaTV";
            Query query = session.createQuery(hql);
            query.setParameter("MaTV", maTV);

            thanhvien tv = (thanhvien) query.uniqueResult();
            if (tv != null) {
                TV = new thanhvien(tv.getMaTV(), tv.getHoTen(), tv.getKhoa(), tv.getNganh(), tv.getSdt(), tv.getPassword(), tv.getEmail());
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return TV;
    }

    public thietbi layTenThietBi(int maTB) {
        TB = new thietbi();

        try {
            session.beginTransaction();

            String hql = "FROM thietbi WHERE MaTB = :MaTB";
            Query query = session.createQuery(hql);
            query.setParameter("MaTB", maTB);

            thietbi tb = (thietbi) query.uniqueResult();
            if (tb != null) {
                TB = new thietbi(tb.getMaTB(), tb.getTenTB(), tb.getMoTaTB());
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return TB;
    }

    public List<thietbi> layDanhSachThietBi() {
        try {
            List<thietbi> result = session.createQuery("from thietbi", thietbi.class).getResultList();
            return result;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public int layMaThanhVien(int maTV) {
        int id = 0;
        try {
            thanhvien tv = session.get(thanhvien.class, maTV);
            if (tv != null) {
                id = tv.getMaTV();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public String layTenThanhVienTheoID(int maTV) {
        String name = "";
        try {
            thanhvien tv = session.get(thanhvien.class, maTV);
            if (tv != null) {
                name = tv.getHoTen();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return name;
    }

    public List<thanhvien> searchData(String key) {
        List<thanhvien> resultList = new ArrayList<>();
        try {
            String hql = "FROM thanhvien WHERE MaTV LIKE :keyword";
            Query<thanhvien> query = session.createQuery(hql, thanhvien.class);
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

    public thongtinsd layThongTinSD(int maTT) {
        thongtinsd ttsd = null;
        try {
            ttsd = session.get(thongtinsd.class, maTT);
        } catch (HibernateException ex) {
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ttsd;
    }

    // Kiểm tra sự tồn tại của mã thành viên và mã thiết bị trong database
    public boolean checkExistingData(int memberCode, int deviceCode) {
        boolean isExisting = true;
        try {
            String hql = "SELECT COUNT(*) FROM thongtinsd WHERE MaTV = :MaTV AND MaTB = :MaTB";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("MaTV", memberCode);
            query.setParameter("MaTB", deviceCode);

            Long count = query.getSingleResult();
            isExisting = (count > 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return isExisting;
    }

    public boolean themThongTinSD(thongtinsd ttsd) {
     
        if (thongtinsdDAL.getInstance().layThongTinSD(ttsd.getMaTT()) != null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(ttsd);
            transaction.commit();
        } catch (HibernateException ex) {
            // Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }

    public Boolean updateThongTinSD(thongtinsd ttsd) {
        if (thongtinsdDAL.getInstance().layThongTinSD(ttsd.getMaTT()) == null) {
            System.out.println("Khong co mon hoc");
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(ttsd);
            transaction.commit();
        } catch (HibernateException ex) {
            // Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        System.out.println("Update successfully!");
        return true;

    }

//    public thietbi layMaThietBi(String tenTB) {
//        thietbi result = null;
//        try {
//            session.beginTransaction();
//
//            String hql = "FROM thietbi WHERE TenTB = :TenTB";
//            Query query = session.createQuery(hql);
//            query.setParameter("TenTB", tenTB);
//
//            thietbi tb = (thietbi) query.getResultList();
//            if (tb != null) {
//                result = new thietbi(tb.getMaTB(), tb.getTenTB(), tb.getMoTaTB());
//            }
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            System.err.println(e);
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//
//        return result;
//    }
//
//    public int layTenThietBi(String tenTB) {
//        int id = 0;
//        try {
//            thietbi tb = session.get(thietbi.class, tenTB);
//            if (tb != null) {
//                id = tb.getMaTB();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return id;
//    }
//    public int layIDThanhVienTheoTen(String hoTen) {
//        int id = 0;
//        try {
//            thanhvien tv = session.get(thanhvien.class, hoTen);
//            if (tv != null) {
//                id = tv.getMaTV();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//        return id;
//    }
//    public thanhvien layTenThanhVien(String hoTen) {
//        thanhvien result = null;
//        try {
//            session.beginTransaction();
//
//            String hql = "FROM thanhvien WHERE MaTV = :MaTV";
//            Query query = session.createQuery(hql);
//            query.setParameter("MaTV", hoTen);
//
//            thanhvien tv = (thanhvien) query.uniqueResult();
//            if (tv != null) {
//                result = new thanhvien(tv.getMaTV(), tv.getHoTen(), tv.getKhoa(), tv.getNganh(), tv.getSdt(), tv.getPassword(), tv.getEmail());
//            }
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            System.err.println(e);
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//
//        return result;
//    }
//    public thanhvien layThongTinThanhVien(String hoTen) {
//        thanhvien tv = new thanhvien();
//        try {
//            tv = session.get(thanhvien.class, hoTen);
//        } catch (HibernateException ex) {
//            // Log the exception
//            System.err.println(ex);
//        } finally {
//            session.close();
//        }
//        return tv;
//    }
//    public thanhvien getMaTV(String hoTen) {
//        thanhvien result = null;
//        try {
//            session.beginTransaction();
//
//            String hql = "FROM thanhvien WHERE HoTen = :HoTen";
//            Query<thanhvien> query = session.createQuery(hql, thanhvien.class);
//            query.setParameter("HoTen", hoTen);
//
//            result = query.uniqueResult();
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//
//        return result;
//    }
//
//    public thietbi getMaTB(String tenTB) {
//        thietbi result = null;
//        try {
//            session.beginTransaction();
//
//            String hql = "FROM thietbi WHERE TenTB = :TenTB";
//            Query<thietbi> query = session.createQuery(hql, thietbi.class);
//            query.setParameter("TenTB", tenTB);
//
//            result = (thietbi) query.getResultList();
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//
//        return result;
//    }
}
