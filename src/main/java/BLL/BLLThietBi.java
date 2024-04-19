/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.thietbi;
import DAL.thietbiDAL;
import java.io.File;
import java.util.List;

/**
 *
 * @author pc
 */
public class BLLThietBi {
    private thietbiDAL thietBiDAL;

    public BLLThietBi() {
        thietBiDAL = new thietbiDAL();
    }
    
    public List<thietbi> getAllThietBi() {
        return thietBiDAL.getAllThietBi();
    }
    
    public boolean insertThietBi(int maTB, String tenTB, String moTaTB)
    {
        return thietBiDAL.insertThietBi(maTB, tenTB, moTaTB);
    }
    
    public boolean importFromExcel(File file)
    {
        return thietBiDAL.importFromExcel(file);
    }

    public boolean deleteThietBi(int maTB)
    {
        return thietBiDAL.deleteThietBi(maTB);
    }
    
    public boolean updateThietBi(int maTB, String tenTB, String moTaTB)
    {
        return thietBiDAL.updateThietBi(maTB, tenTB, moTaTB);
    }
    
    public List<thietbi> searchData(String key) {
        return thietBiDAL.searchData(key);
    }
    
    public List<String> loadCategoryDevice() {
        return thietBiDAL.loadCategoryDevice();
    }
    
    public boolean deleteThanhVienByCategoryDevice(String categoryDevice)
    {
        return thietBiDAL.deleteThanhVienByCategoryDevice(categoryDevice);
    }
    
}
