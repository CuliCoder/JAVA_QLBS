/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author NGOC THUC
 */
public class NhanVienDTO {
    private String MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang;

    public NhanVienDTO() {
    }
    
    
    public NhanVienDTO(String MaNV, String TenNV, String SDT, String GioiTinh, String DiaChi, String Email, String TinhTrang) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.SDT = SDT;
        this.GioiTinh = GioiTinh;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.TinhTrang = TinhTrang;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    
}
