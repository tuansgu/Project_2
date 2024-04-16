package DAL;

import com.mysql.cj.result.Row;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.DataFormatter;

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

    public boolean insertViPham(int maTV, String selectedHinhThuc, int soTien, Timestamp ngayXL, int trangthai) {
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
            if (tx != null) {
                tx.rollback();
            }
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

    public boolean importFromExcel(File file) {
        boolean flag = false;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();

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
                int maXL = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(0)));
                int maTV = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(1)));
                String hinhThucXL = dataFormatter.formatCellValue(row.getCell(2));
                int soTien = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(3)));
                // Đọc ngày từ ô và parse thành java.sql.Timestamp
                java.util.Date date = row.getCell(4).getDateCellValue();
                Timestamp ngayXL = new Timestamp(date.getTime());
                int trangThaiXL = Integer.parseInt(dataFormatter.formatCellValue(row.getCell(5)));

                // Tạo đối tượng xuly
                xuly xulyObj = new xuly(maXL, maTV, hinhThucXL, soTien, (Timestamp) ngayXL, trangThaiXL);

                // Lưu đối tượng vào cơ sở dữ liệu
                session.save(xulyObj);
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

    public boolean deleteXuLy(int maXL) {
        boolean flag = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<xuly> query = session.createQuery("DELETE FROM xuly WHERE maXL = :maXL");
            query.setParameter("maXL", maXL);
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

    public int getMaTVByID(int maXL) {
        int maTV = -1; // Giá trị mặc định nếu không tìm thấy
        Session session = null;
        try {
            session = sessionFactory.openSession();
            // Lấy đối tượng xử lý từ cơ sở dữ liệu
            xuly xuly = session.get(xuly.class, maXL);
            if (xuly != null) {
                maTV = xuly.getMaTV();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return maTV;
    }

    public boolean updateXuLy(int maXL, int maTV, String selectedHinhThuc, int soTien, int trangthai) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            // Bắt đầu một giao dịch
            transaction = session.beginTransaction();

            // Lấy đối tượng XuLy từ cơ sở dữ liệu dựa trên maXL
            xuly xuLy = session.get(xuly.class, maXL);
            if (xuLy != null) {
                // Cập nhật thông tin cho đối tượng XuLy
                xuLy.setMaTV(maTV);
                xuLy.setHinhThucXL(selectedHinhThuc);
                xuLy.setSoTien(soTien);
                xuLy.setTrangThaiXL(trangthai);

                // Lưu thay đổi vào cơ sở dữ liệu
                session.update(xuLy);

                // Commit giao dịch
                transaction.commit();
                return true;
            } else {
                System.out.println("Không tìm thấy mã xử lý để cập nhật " + maXL);
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

}
