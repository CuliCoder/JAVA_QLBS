/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author NGOC THUC
 */
public class HoaDonDTO {

    private int MaHD;
    private String TenTK;
    private Date NgayTao;
    private double TongTien;
    private int MaChiNhanh;
    private int idKH;

    public HoaDonDTO(int MaHD, String TenTK, double TongTien, int MaChiNhanh, int idKH, Date NgayTao) {
        this.MaHD = MaHD;
        this.TenTK = TenTK;
        this.TongTien = TongTien;
        this.NgayTao = NgayTao;
        this.MaChiNhanh = MaChiNhanh;
        this.idKH = idKH;
    }

    public HoaDonDTO(String TenTK, double TongTien, int MaChiNhanh, int idKH, Date NgayTao) {
        this.TenTK = TenTK;
        this.TongTien = TongTien;
        this.NgayTao = NgayTao;
        this.MaChiNhanh = MaChiNhanh;
        this.idKH = idKH;
    }

    public int getIdKH() {
        return idKH;
    }

    public void setIdHK(int idKH) {
        this.idKH = idKH;
    }

    public int getMaChiNhanh() {
        return MaChiNhanh;
    }

    public void setMaChiNhanh(int MaChiNhanh) {
        this.MaChiNhanh = MaChiNhanh;
    }

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int MaHD) {
        this.MaHD = MaHD;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public double getTongTien() {
        return TongTien;
    }

    public void setTongTien(double TongTien) {
        this.TongTien = TongTien;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

}
