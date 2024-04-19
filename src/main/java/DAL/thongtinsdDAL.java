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

    public List<thongtinsd> layDanhSachThongTinSDTGMuon(int maTV) {
        try {
            session.beginTransaction();
            String hql = "from thongtinsd where MaTV = :MaTV and TGTra = null and TGMuon > 0";
            Query<thongtinsd> query = session.createQuery(hql, thongtinsd.class);
            query.setParameter("MaTV", maTV);
            List<thongtinsd> result = query.getResultList();
            session.getTransaction().commit();
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
            List<thietbi> result = session.createQuery("FROM thietbi",
                    thietbi.class).getResultList();
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

    public List<Integer> getMaThietBi() {
        List<Integer> listThietBi = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hqlQuery = "SELECT DISTINCT ttsd.MaTB FROM thongtinsd ttsd\n"
                    + "WHERE ttsd.TGDatCho IS NULL AND ttsd.TGMuon IS NULL";
            Query query = session.createQuery(hqlQuery);
            listThietBi = query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThietBi;
    }

    public List<thongtinsd> getALLTTSD() {
        try {
            List<thongtinsd> result = session.createQuery("from thongtinsd where TGDatCho IS NULL AND TGMuon IS NULL", thongtinsd.class).getResultList();
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

    public boolean checkThietBiDaMuon(int deviceCode) {
        boolean isExisting = true;
        try {
            String hql = "SELECT COUNT(*) FROM thongtinsd WHERE MaTB = :MaTB and (TGDatCho IS NULL or TGMuon IS NULL)";
            Query<Long> query = session.createQuery(hql, Long.class);
//            query.setParameter("MaTV", memberCode);
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

    public boolean checkMemberStatus(int memberCode) {
        boolean isProcessed = false;

        try {
            // Sử dụng HQL để kiểm tra trạng thái xử lý của mã thành viên
            String hql = "SELECT COUNT(*) FROM xuly WHERE MaTV = :MaTV AND TrangThaiXL = 1";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("MaTV", memberCode);

            Long count = query.uniqueResult();
            isProcessed = (count > 0);
            System.out.println(count);
            System.out.println(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return !isProcessed;
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

    public String layMoTaThietBi(int maTB) {
        String mota = "";
        try {
            thietbi tb = session.get(thietbi.class, maTB);
            if (tb != null) {
                mota = tb.getMoTaTB();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return mota;
    }
}
