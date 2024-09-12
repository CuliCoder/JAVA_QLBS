/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author NGOC THUC
 */
public class ChucNangDTO {
    private String tenCN, tinhTrang;
    private int maCN;
    
    private boolean Them, Sua, Xoa, TruyCap;

    public ChucNangDTO(int maCN, String tenCN, String tinhTrang) {
        this.maCN = maCN;
        this.tenCN = tenCN;
        this.tinhTrang = tinhTrang;
    }

    public ChucNangDTO(String tenCN, int maCN, boolean Them, boolean Sua, boolean Xoa, boolean TruyCap) {
        this.tenCN = tenCN;
        this.maCN = maCN;
        this.Them = Them;
        this.Sua = Sua;
        this.Xoa = Xoa;
        this.TruyCap = TruyCap;
    }

    public ChucNangDTO(String tenCN, String tinhTrang, int maCN, boolean Them, boolean Sua, boolean Xoa, boolean TruyCap) {
        this.tenCN = tenCN;
        this.tinhTrang = tinhTrang;
        this.maCN = maCN;
        this.Them = Them;
        this.Sua = Sua;
        this.Xoa = Xoa;
        this.TruyCap = TruyCap;
    }
    
    

    public boolean isThem() {
        return Them;
    }

    public void setThem(boolean Them) {
        this.Them = Them;
    }

    public boolean isSua() {
        return Sua;
    }

    public void setSua(boolean Sua) {
        this.Sua = Sua;
    }

    public boolean isXoa() {
        return Xoa;
    }

    public void setXoa(boolean Xoa) {
        this.Xoa = Xoa;
    }

    public boolean isTruyCap() {
        return TruyCap;
    }

    public void setTruyCap(boolean TruyCap) {
        this.TruyCap = TruyCap;
    }
    
    
    

    public int getMaCN() {
        return maCN;
    }

    public void setMaCN(int maCN) {
        this.maCN = maCN;
    }

    public String getTenCN() {
        return tenCN;
    }

    public void setTenCN(String tenCN) {
        this.tenCN = tenCN;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
    
    
}
