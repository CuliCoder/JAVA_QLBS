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
public class TaiKhoanDTO {

    private String MaTK, TenTK, MatKhau, MaQuyen, TinhTrang;
    private Date NgayTao;
    private int MaChiNhanh;

    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(String MaTK, String TenTK, String MatKhau, String MaQuyen, String TinhTrang, int MaChiNhanh, Date NgayTao) {
        this.MaTK = MaTK;
        this.TenTK = TenTK;
        this.MatKhau = MatKhau;
        this.MaQuyen = MaQuyen;
        this.TinhTrang = TinhTrang;
        this.MaChiNhanh = MaChiNhanh;
        this.NgayTao = NgayTao;
    }

    public TaiKhoanDTO(String TenTK, String MatKhau, String MaQuyen, String TinhTrang, int MaChiNhanh, Date NgayTao) {
        this.TenTK = TenTK;
        this.MatKhau = MatKhau;
        this.MaQuyen = MaQuyen;
        this.TinhTrang = TinhTrang;
        this.MaChiNhanh = MaChiNhanh;
        this.NgayTao = NgayTao;
    }

    public String getMaTK() {
        return MaTK;
    }

    public void setMaTK(String MaTK) {
        this.MaTK = MaTK;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(String MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    public int getMaChiNhanh() {
        return MaChiNhanh;
    }

    public void setMaChiNhanh(int MaChiNhanh) {
        this.MaChiNhanh = MaChiNhanh;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

}
