/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.ThongKeDAL;
import DAL.thanhvien;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ACER
 */
public class ThongKeBLL {

    private ThongKeDAL thongKeDAL;

    public ThongKeBLL() {
        thongKeDAL = new ThongKeDAL();
    }

    public List<Object[]> getAllJoin() {
        return thongKeDAL.getAllJoin();
    }

    public List<String> getKhoa() {
        return thongKeDAL.getKhoa();
    }

    public List<String> getNganh() {
        return thongKeDAL.getNganh();
    }

    public List<Object[]> getJoinWithAll(Date startDate, Date endDate, String khoa, String nganh) {
        return thongKeDAL.getJoinWithAll(startDate, endDate, khoa, nganh);
    }

    public List<Object[]> getAllViPham() {
        return thongKeDAL.getAllViPham();
    }

    public double tongTienViPham() {
        return thongKeDAL.tongTienViPham();
    }

    public int soLuongDangDuocXuLy() {
        return thongKeDAL.soLuongDangDuocXuLy();
    }

    public int soLuongDaDuocXuLy() {
        return thongKeDAL.soLuongDaDuocXuLy();
    }

    public List<String> getTenThietBi() {
        return thongKeDAL.getTenThietBi();
    }

    public List<Object[]> getThietBiDuocMuon() {
        return thongKeDAL.getThietBiDuocMuon();
    }

    public List<Object[]> getJoinThietBiWithAll(Date startDate, Date endDate, String tenThietBi,boolean thoiGianTraIsNull) {
        return thongKeDAL.getJoinThietBiWithAll(startDate, endDate, tenThietBi,thoiGianTraIsNull);
    }
}
