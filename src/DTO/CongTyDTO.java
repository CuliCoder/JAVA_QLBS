/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author NGOC THUC
 */
public class CongTyDTO {
    int MaNCC;
    private String TenNCC, SDT, DiaChi;
    Boolean TinhTrang;

    public CongTyDTO() {
    }

    public CongTyDTO(int MaNCC, String TenNCC, String SDT, String DiaChi, Boolean TinhTrang) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.TinhTrang = TinhTrang;
    }

    public int getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(int MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public Boolean getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Boolean TinhTrang) {
        this.TinhTrang = TinhTrang;
    }

    @Override
    public String toString() {
        return "CongTyDTO{" + "MaNCC=" + MaNCC + ", TenNCC=" + TenNCC + ", SDT=" + SDT + ", DiaChi=" + DiaChi + ", TinhTrang=" + TinhTrang + '}';
    }
    
    
}
