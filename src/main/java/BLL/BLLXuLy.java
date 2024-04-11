package BLL;

import DAL.thanhvien;
import DAL.xulyDAL;
import DAL.xuly;
import java.sql.Timestamp;
import java.util.List;
public class BLLXuLy {
    private xulyDAL xulyDAL;

    public BLLXuLy() {
        xulyDAL = new xulyDAL();
    }

    public List<xuly> getAllXuLy() {
        return xulyDAL.getAllXuLy();
    }
    public int[] getAllThanhVienById()
    {
        return xulyDAL.getAllThanhVienById();
    }
    public String getThanhVienById(int id)
    {
        return xulyDAL.getThanhVienById(id);
    }
    public boolean insertViPham(int maTV, String selectedHinhThuc,int soTien, Timestamp ngayXL, int trangthai)
    {
        return xulyDAL.insertViPham(maTV, selectedHinhThuc, soTien, ngayXL, trangthai);
    }
    public List<xuly> searchData(String key)
    {
        return xulyDAL.searchData(key);
    }
}
