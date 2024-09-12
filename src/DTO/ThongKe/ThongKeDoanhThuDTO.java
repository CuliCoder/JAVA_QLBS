/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThongKe;

/**
 *
 * @author ASUS
 */
public class ThongKeDoanhThuDTO {

    private String thoiGian;
    private long von;
    private long doanhThu;
    private long loiNhuan;
    private String Ngay;

    private String vonVND,doanhThuVND,loiNhuanVND;
    public ThongKeDoanhThuDTO(String thoiGian, long von, long doanhThu, long loiNhuan) {
        this.thoiGian = thoiGian;
        this.von = von;
        this.doanhThu = doanhThu;
        this.loiNhuan = loiNhuan;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public long getVon() {
        return von;
    }

    public long getDoanhThu() {
        return doanhThu;
    }

    public long getLoiNhuan() {
        return loiNhuan;
    }

    public String getNgay() {
        return Ngay;
    }


    @Override
    public String toString() {
        return "ThongKeDoanhThuDTO{" + "thoiGian=" + thoiGian + ", von=" + von + ", doanhThu=" + doanhThu + ", loiNhuan=" + loiNhuan + '}';
    }

}
