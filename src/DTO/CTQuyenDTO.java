/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author NGOC THUC
 */
public class CTQuyenDTO {
    private String HanhDong;
    private int MaQuyen, MaCN, MaCTQ, TinhTrang;

    public CTQuyenDTO(int MaCTQ, int MaQuyen, int MaCN, String HanhDong, int TinhTrang) {
        this.MaCTQ = MaCTQ;
        this.MaQuyen = MaQuyen;
        this.MaCN = MaCN;
        this.HanhDong = HanhDong;
        this.TinhTrang = TinhTrang;
    }

    public int getMaCTQ() {
        return MaCTQ;
    }

    public void setMaCTQ(int MaCTQ) {
        this.MaCTQ = MaCTQ;
    }

    public int getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(int MaQuyen) {
        this.MaQuyen = MaQuyen;
    }

    public int getMaCN() {
        return MaCN;
    }

    public void setMaCN(int MaCN) {
        this.MaCN = MaCN;
    }

    public String getHanhDong() {
        return HanhDong;
    }

    public void setHanhDong(String HanhDong) {
        this.HanhDong = HanhDong;
    }

    public int getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(int TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    
}
