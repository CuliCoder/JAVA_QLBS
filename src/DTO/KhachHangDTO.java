/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Tran Hoai bao
 */
import java.util.Date;

public class KhachHangDTO {
    private int idKH;       // Mã khách hàng
    private String hoTen;   // Họ tên khách hàng
    private String sdt;     // Số điện thoại
    private String email;   // Email khách hàng
    private String diaChi;  // Địa chỉ
    private Date ngayTao;   // Ngày tạo tài khoản

    // Constructor mặc định
    public KhachHangDTO() {}

    // Constructor có tham số
    public KhachHangDTO(int idKH, String hoTen, String sdt, String email, String diaChi, Date ngayTao) {
        this.idKH = idKH;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.ngayTao = ngayTao;
    }

    // Getter và Setter
    public int getIdKH() {
        return idKH;
    }

    public void setIdKH(int idKH) {
        this.idKH = idKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    // Phương thức toString() để hiển thị thông tin khách hàng
    @Override
    public String toString() {
        return "KhachHangDTO{" +
                "idKH=" + idKH +
                ", hoTen='" + hoTen + '\'' +
                ", sdt='" + sdt + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", ngayTao=" + ngayTao +
                '}';
    }
}

