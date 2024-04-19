/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.thanhvien;
import DAL.thietbi;
import DAL.thongtinsd;
import DAL.thongtinsdDAL;
import java.util.List;

/**
 *
 * @author pc
 */
public class BLLThongTinSD {

    public static List<thongtinsd> layDanhSachThongTinSD() {
        return thongtinsdDAL.getInstance().layDanhSachThongTinSD();
    }

    public static List<thongtinsd> layDanhSachThongTinSDTGMuon() {
        return thongtinsdDAL.getInstance().layDanhSachThongTinSDTGMuon();
    }

    public static thanhvien layTenThanhVien(int maTV) {
        return thongtinsdDAL.getInstance().layTenThanhVien(maTV);
    }

    public static thietbi layTenThietBi(int maTB) {
        return thongtinsdDAL.getInstance().layTenThietBi(maTB);
    }

    public static List<thietbi> layDanhSachThietBi() {
        return thongtinsdDAL.getInstance().layDanhSachThietBi();
    }

    public static List<thanhvien> searchData(String key) {
        return thongtinsdDAL.getInstance().searchData(key);
    }

    public static int layMaThanhVien(int maTV) {
        return thongtinsdDAL.getInstance().layMaThanhVien(maTV);
    }

    public static String layTenThanhVienTheoID(int maTV) {
        return thongtinsdDAL.getInstance().layTenThanhVienTheoID(maTV);
    }

    public static boolean themThongTinSD(thongtinsd ttsd) {
        return thongtinsdDAL.getInstance().themThongTinSD(ttsd);
    }

    public static boolean updateThongTinSD(thongtinsd ttsd) {
        return thongtinsdDAL.getInstance().updateThongTinSD(ttsd);
    }

    public static boolean checkExistingData(int memberCode, int deviceCode) {
        return thongtinsdDAL.getInstance().checkExistingData(memberCode, deviceCode);
    }

//    public static boolean insert(int maTV, int maTB, Date tgVao, Date tgMuon, Date tgTra, Date tgDatCho) {
//        return thongtinsdDAL.getInstance().insert(maTV, maTB, tgVao, tgMuon, tgTra, tgDatCho);
//    }
//    public static int layTenThietBi(String tenTB) {
//        return thongtinsdDAL.getInstance().layTenThietBi(tenTB);
//    }
//    public static thietbi layMaThietBi(String tenTB) {
//        return thongtinsdDAL.getInstance().layMaThietBi(tenTB);
//    }
//
//    public static thanhvien layTenThanhVien(String hoTen) {
//        return thongtinsdDAL.getInstance().layTenThanhVien(hoTen);
//    }
//
//    public static int layIDThanhVienTheoTen(String hoTen) {
//        return thongtinsdDAL.getInstance().layIDThanhVienTheoTen(hoTen);
//    }
//
//    public static int layTenThietBi(String tenTB) {
//        return thongtinsdDAL.getInstance().layTenThietBi(tenTB);
//    }
//
//    public static thanhvien layThongTinThanhVien(String hoTen) {
//        return thongtinsdDAL.getInstance().layThongTinThanhVien(hoTen);
//    }
//
//    public static thanhvien getMaTV(String hoTen) {
//        return thongtinsdDAL.getInstance().getMaTV(hoTen);
//    }
//
//    public static thietbi getMaTB(String tenTB) {
//        return thongtinsdDAL.getInstance().getMaTB(tenTB);
//    }
}
