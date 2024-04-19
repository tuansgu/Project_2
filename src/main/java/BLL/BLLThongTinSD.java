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

    public static List<thongtinsd> layDanhSachThongTinSDTGMuon(int maTV) {
        return thongtinsdDAL.getInstance().layDanhSachThongTinSDTGMuon(maTV);
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

    public static boolean checkMemberStatus(int memberCode) {
        return thongtinsdDAL.getInstance().checkMemberStatus(memberCode);
    }

    public static List<Integer> getMaThietBi() {
        return thongtinsdDAL.getInstance().getMaThietBi();
    }

    public static List<thongtinsd> getALLTTSD() {
        return thongtinsdDAL.getInstance().getALLTTSD();
    }

    public static String layMoTaThietBi(int maTB) {
        return thongtinsdDAL.getInstance().layMoTaThietBi(maTB);
    }

    public static boolean checkThietBiDaMuon(int deviceCode) {
        return thongtinsdDAL.getInstance().checkThietBiDaMuon(deviceCode);
    }
}
