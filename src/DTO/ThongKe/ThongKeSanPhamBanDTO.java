/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThongKe;

/**
 *
 * @author ASUS
 */
public class ThongKeSanPhamBanDTO {
    private String maSP,tenSP;
    private int soLuong,soLuongDon;
    private long doanhThu;

    public ThongKeSanPhamBanDTO(String maSP, String tenSP, int soLuong, int soLuongDon, long doanhThu) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.soLuongDon = soLuongDon;
        this.doanhThu = doanhThu;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getSoLuongDon() {
        return soLuongDon;
    }

    public long getDoanhThu() {
        return doanhThu;
    }

    @Override
    public String toString() {
        return "ThongKeSanPhamBanDTO{" + "maSP=" + maSP + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", soLuongDon=" + soLuongDon + ", doanhThu=" + doanhThu + '}';
    }
    
}
