/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 *
 * @author tuvu
 */
public class thanhvienDAL {
    private SessionFactory sessionFactory;
    
    public thanhvienDAL() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    
    public List<thanhvien> getAllThanhVien() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM thanhvien", thanhvien.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean insertThanhVien(int maTV, String hoTen, String khoa, String nganh, String sdt, String password, String email) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            thanhvien thanhVien = new thanhvien();
            thanhVien.setMaTV(maTV);
            thanhVien.setHoTen(hoTen);
            thanhVien.setKhoa(khoa);
            thanhVien.setNganh(nganh);
            thanhVien.setSdt(sdt);
            thanhVien.setPassword(password);
            thanhVien.setEmail(email);
            session.save(thanhVien);
            tx.commit();
            flag = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }
    
    public boolean importFromExcel(File file) {
        boolean flag = false;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

            // Mở session từ SessionFactory
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            // Duyệt qua các hàng trong tệp Excel và thêm dữ liệu vào cơ sở dữ liệu
            for (org.apache.poi.ss.usermodel.Row row : sheet) {
                // Bỏ qua hàng tiêu đề
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Đọc dữ liệu từ các cột
                int maTV = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0)));
                String HoTen = dataFormatter.formatCellValue(row.getCell(1));
                String Khoa = dataFormatter.formatCellValue(row.getCell(2));
                String Nganh = dataFormatter.formatCellValue(row.getCell(3));
                String SDT = dataFormatter.formatCellValue(row.getCell(4), formulaEvaluator); 
                String Password = dataFormatter.formatCellValue(row.getCell(5));
                String Email = dataFormatter.formatCellValue(row.getCell(6));

                // Tạo đối tượng xuly
                thanhvien thanhVienObj = new thanhvien(maTV, HoTen, Khoa, Nganh, SDT, Password, Email);

                // Lưu đối tượng vào cơ sở dữ liệu
                session.save(thanhVienObj);
                flag = true;
            }

            // Commit transaction và đóng session
            transaction.commit();
            session.close();

            // Đóng workbook và inputStream
            workbook.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }
    
    public List<Integer> getDistinctMaTV() {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT MaTV FROM xuly UNION SELECT MaTV FROM thongtinsd";
            return session.createSQLQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deleteThanhVien(int maTV) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<thanhvien> query = session.createQuery("DELETE FROM thanhvien WHERE maTV = :maTV");
            query.setParameter("maTV", maTV);
            int rowsAffected = query.executeUpdate();
            tx.commit();
            if (rowsAffected > 0) {
                flag = true;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }
    
    public boolean updateThanhVien(int maTV, String hoTen, String khoa, String nganh, String sdt, String password, String email) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Bắt đầu một giao dịch
            transaction = session.beginTransaction();

            thanhvien thanhVien = session.get(thanhvien.class, maTV);
            if (thanhVien != null) {
                // Cập nhật thông tin cho đối tượng thanhVien
                thanhVien.setMaTV(maTV);
                thanhVien.setHoTen(hoTen);
                thanhVien.setKhoa(khoa);
                thanhVien.setNganh(nganh);
                thanhVien.setSdt(sdt);
                thanhVien.setPassword(password);
                thanhVien.setEmail(email);

                // Lưu thay đổi vào cơ sở dữ liệu
                session.update(thanhVien);

                // Commit giao dịch
                transaction.commit();
                return true;
            } else {
                System.out.println("Không tìm thấy mã xử lý để cập nhật " + maTV);
                return false;
            }
        } catch (Exception ex) {
            if (transaction != null) {
                // Rollback giao dịch nếu có lỗi xảy ra
                transaction.rollback();
            }
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<thanhvien> searchData(String key) {
        List<thanhvien> resultList = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            // Sử dụng câu truy vấn HQL để tìm kiếm các bản ghi có chứa từ khóa trong cột nào đó
            String hql = "FROM thanhvien WHERE MaTV LIKE :keyword OR HoTen LIKE :keyword";
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

    public List<String> loadTriggerYear() {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT DISTINCT SUBSTRING(MaTV, 3, 2) AS SubstringMaTV FROM thanhvien;";
            return session.createSQLQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deleteThanhVienByTriggerYear(String triggerYear) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Convert Integer year to String
            String yearString = String.valueOf(triggerYear);

            Query<thanhvien> query = session.createQuery("DELETE FROM thanhvien WHERE SUBSTRING(MaTV, 3, 2) = :yearString");
            query.setParameter("yearString", yearString);

            int rowsAffected = query.executeUpdate();
            tx.commit();

            flag = rowsAffected > 0;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return flag;
    }



}
