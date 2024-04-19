/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
public class thietbiDAL {
    private SessionFactory sessionFactory;
    
    public thietbiDAL() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    
    public List<thietbi> getAllThietBi() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM thietbi", thietbi.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean insertThietBi(int maTB, String tenTB, String moTaTB) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            thietbi thietBi = new thietbi();
            thietBi.setMaTB(maTB);
            thietBi.setTenTB(tenTB);
            thietBi.setMoTaTB(moTaTB);
            session.save(thietBi);
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
                int maTB = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0)));
                String TenTB = dataFormatter.formatCellValue(row.getCell(1));
                String MoTaTB = dataFormatter.formatCellValue(row.getCell(2));

                // Tạo đối tượng xuly
                thietbi thietBiObj = new thietbi(maTB, TenTB, MoTaTB);

                // Lưu đối tượng vào cơ sở dữ liệu
                session.save(thietBiObj);
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
    
    public boolean deleteThietBi(int maTB) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<thanhvien> query = session.createQuery("DELETE FROM thietbi WHERE maTB = :maTB");
            query.setParameter("maTB", maTB);
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
    
    public boolean updateThietBi(int maTB, String tenTB, String moTaTB) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Bắt đầu một giao dịch
            transaction = session.beginTransaction();

            thietbi thietBi = session.get(thietbi.class, maTB);
            if (thietBi != null) {
                // Cập nhật thông tin cho đối tượng thietbi
                thietBi.setMaTB(maTB);
                thietBi.setTenTB(tenTB);
                thietBi.setMoTaTB(moTaTB);

                // Lưu thay đổi vào cơ sở dữ liệu
                session.update(thietBi);

                // Commit giao dịch
                transaction.commit();
                return true;
            } else {
                System.out.println("Không tìm thấy mã xử lý để cập nhật " + maTB);
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
    
    public List<thietbi> searchData(String key) {
        List<thietbi> resultList = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            // Sử dụng câu truy vấn HQL để tìm kiếm các bản ghi có chứa từ khóa trong cột nào đó
            String hql = "FROM thietbi WHERE MaTB LIKE :keyword OR TenTB LIKE :keyword";
            Query<thietbi> query = session.createQuery(hql, thietbi.class);
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

    public List<String> loadCategoryDevice() {
        try (Session session = sessionFactory.openSession()) {
            String sql = "SELECT DISTINCT LEFT(MaTB, 1) AS FirstCharacter FROM thietbi";
            return session.createSQLQuery(sql).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean deleteThanhVienByCategoryDevice(String categoryDevice) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Convert Integer year to String
            String categoryDeviceString = String.valueOf(categoryDevice);

            Query<thanhvien> query = session.createQuery("DELETE FROM thietbi WHERE SUBSTRING(MaTB, 1, 1) = :categoryDeviceString");
            query.setParameter("categoryDeviceString", categoryDeviceString);

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
