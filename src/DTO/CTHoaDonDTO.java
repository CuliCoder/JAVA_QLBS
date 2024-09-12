/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author NGOC THUC
 */
public class CTHoaDonDTO {

    private int MaHD, MaSP;
    private double DonGia;
    private int SoLuong;
    private String TenSP;

    public CTHoaDonDTO(int MaHD, int MaSP, String tenSP, double DonGia, int SoLuong) {
        this.MaHD = MaHD;
        this.MaSP = MaSP;
        this.TenSP = tenSP;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
    }

    public CTHoaDonDTO(double DonGia, int SoLuong, String TenSP) {
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
        this.TenSP = TenSP;
    }

    public CTHoaDonDTO(int MaHD, int MaSP, double DonGia, int SoLuong) {
        this.MaHD = MaHD;
        this.MaSP = MaSP;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
    }



    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public int getMaSP() {
        return MaSP;
    }

    public void setMaSP(int MaSP) {
        this.MaSP = MaSP;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

}
