/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThongKe;

/**
 *
 * @author ASUS
 */
public class ThongKeHoaDonBanDTO {

    private String thoiGian;
    private int soHoaDon, SoSP, SoLoaiSP;
    private long DoanhThu;

    public ThongKeHoaDonBanDTO(String thoiGian, int soHoaDon, int SoSP, int SoLoaiSP,long DoanhThu) {
        this.thoiGian = thoiGian;
        this.soHoaDon = soHoaDon;
        this.SoSP = SoSP;
        this.SoLoaiSP = SoLoaiSP;
        this.DoanhThu = DoanhThu;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public int getSoHoaDon() {
        return soHoaDon;
    }

    public int getSoSP() {
        return SoSP;
    }

    public int getSoLoaiSP() {
        return SoLoaiSP;
    }

    public long getDoanhThu() {
        return DoanhThu;
    }

    @Override
    public String toString() {
        return "ThongKeHoaDonBanDTO{" + "thoiGian=" + thoiGian + ", soHoaDon=" + soHoaDon + ", SoSP=" + SoSP + ", SoLoaiSP=" + SoLoaiSP + ", DoanhThu=" + DoanhThu + '}';
    }

}
