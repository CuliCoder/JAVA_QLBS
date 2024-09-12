/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThongKe;

/**
 *
 * @author ASUS
 */
public class ThongKeTheLoaiBanDTO {
    private String maTL,tenTL;
    private int soLuong,soLuongDon,soLoaiSP;
    private long doanhThu;

    public ThongKeTheLoaiBanDTO(String maTL, String tenTL, int soLuong, int soLuongDon, int soLoaiSP, long doanhThu) {
        this.maTL = maTL;
        this.tenTL = tenTL;
        this.soLuong = soLuong;
        this.soLuongDon = soLuongDon;
        this.soLoaiSP = soLoaiSP;
        this.doanhThu = doanhThu;
    }


    public String getMaTL() {
        return maTL;
    }

    public String getTenTL() {
        return tenTL;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public int getSoLuongDon() {
        return soLuongDon;
    }

    public long getDoanhThu() {
        return doanhThu;
    }

    public int getSoLoaiSP() {
        return soLoaiSP;
    }
    
}
