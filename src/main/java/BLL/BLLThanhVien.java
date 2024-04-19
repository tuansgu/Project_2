/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.thanhvien;
import DAL.thanhvienDAL;
import java.io.File;
import java.util.List;

/**
 *
 * @author pc
 */
public class BLLThanhVien {
    private thanhvienDAL thanhvienDAL;

    public BLLThanhVien() {
        thanhvienDAL = new thanhvienDAL();
    }
    
    public List<thanhvien> getAllThanhVien() {
        return thanhvienDAL.getAllThanhVien();
    }
    
    public boolean insertThanhVien(int maTV, String hoTen, String khoa, String nganh, String sdt, String password, String email)
    {
        return thanhvienDAL.insertThanhVien(maTV, hoTen, khoa, nganh, sdt, password, email);
    }
    
    public boolean importFromExcel(File file)
    {
        return thanhvienDAL.importFromExcel(file);
    }
    
    public List<Integer> getDistinctMaTV() {
        return thanhvienDAL.getDistinctMaTV();
    }

    public boolean deleteThanhVien(int maTV)
    {
        return thanhvienDAL.deleteThanhVien(maTV);
    }
    
    public boolean updateThanhVien(int maTV, String hoTen, String khoa, String nganh, String sdt, String password, String email)
    {
        return thanhvienDAL.updateThanhVien(maTV, hoTen, khoa, nganh, sdt, password, email);
    }
    
    public List<thanhvien> searchData(String key) {
        return thanhvienDAL.searchData(key);
    }
    
    public List<String> loadTriggerYear() {
        return thanhvienDAL.loadTriggerYear();
    }
    
    public boolean deleteThanhVienByTriggerYear(String triggerYear)
    {
        return thanhvienDAL.deleteThanhVienByTriggerYear(triggerYear);
    }
}
