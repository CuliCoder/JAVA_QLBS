/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author NGOC THUC
 */
public class TheLoaiDTO {
    private int MaTL;
    private String TenTL;
    private Boolean TinhTrang;

    // dùng khi thêm thể loại
    public TheLoaiDTO(String TenTL, Boolean TinhTrang) {
        this.TenTL = TenTL;
        this.TinhTrang = TinhTrang;
    }

    // dùng khi sửa thể loại
    public TheLoaiDTO(int MaTL, String TenTL, Boolean TinhTrang) {
        this.MaTL = MaTL;
        this.TenTL = TenTL;
        this.TinhTrang = TinhTrang;
    }

//    // dùng khi thêm thể loại mới 
//    public TheLoaiDTO(int MaTL, String TenTL) {
//        this.MaTL = MaTL;
//        this.TenTL = TenTL;
//    }

    public int getMaTL() {
        return MaTL;
    }

    public void setMaTL(int MaTL) {
        this.MaTL = MaTL;
    }

    public String getTenTL() {
        return TenTL;
    }

    public void setTenTL(String TenTL) {
        this.TenTL = TenTL;
    }

    public Boolean getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Boolean TinhTrang) {
        this.TinhTrang = TinhTrang;
    }
    
    
}
