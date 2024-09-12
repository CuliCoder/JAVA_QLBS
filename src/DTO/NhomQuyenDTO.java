/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author NGOC THUC
 */
public class NhomQuyenDTO {
    private String TenNQ, MoTa, TinhTrang;
    private int MaNQ;

    public NhomQuyenDTO(int MaNQ, String TenNQ, String MoTa, String TinhTrang) {
        this.MaNQ = MaNQ;
        this.TenNQ = TenNQ;
        this.MoTa = MoTa;
        this.TinhTrang = TinhTrang;
    }

    public int getMaNQ() {
        return MaNQ;
    }

    public void setMaNQ(int MaNQ) {
        this.MaNQ = MaNQ;
    }

    public String getTenNQ() {
        return TenNQ;
    }

    public void setTenNQ(String TenNQ) {
        this.TenNQ = TenNQ;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    
}
