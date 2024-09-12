/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeHoaDonBanDTO;
import DTO.ThongKe.ThongKeSanPhamBanDTO;
import DTO.ThongKe.ThongKeTheLoaiBanDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author NGOC THUC
 */
public class ThongKeBUS {

    private final ThongKeDAO thongKeDAO;

    public ThongKeBUS() {
        thongKeDAO = new ThongKeDAO();
    }

    public ArrayList<ThongKeDoanhThuDTO> thongKeDoanhThuTuNamDenNam(int namBatDau, int namKetThuc) {
        return thongKeDAO.thongKeDoanhThuTuNamDenNam(namBatDau, namKetThuc);
    }

    public ArrayList<ThongKeDoanhThuDTO> thongKeDoanhThuTheoNam() {
        return thongKeDAO.thongKeDoanhThuTheoNam();
    }

    public ArrayList<ThongKeDoanhThuDTO> thongKeDoanhThuTheoThang(int nam) {
        return thongKeDAO.thongKeDoanhThuTheoThang(nam);
    }

    public ArrayList<ThongKeDoanhThuDTO> thongKeDoanhThuTungNgayTrongThang(int nam, int thang) {
        return thongKeDAO.thongKeDoanhThuTungNgayTrongThang(nam, thang);
    }

    public ArrayList<ThongKeDoanhThuDTO> thongKeDoanhThuTuNgayDenNgay(Date ngayBatDau, Date ngayKetThuc) {
        return thongKeDAO.thongKeDoanhThuTuNgayDenNgay(ngayBatDau, ngayKetThuc);
    }

    public Set<Integer> getDistinctYears() {
        return thongKeDAO.getDistinctYears();
    }
    
    // thống kê sản phẩm bán
    public ArrayList<ThongKeSanPhamBanDTO> thongKeSanPhamBanTrongKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) {
        return thongKeDAO.thongKeSanPhamBanTrongKhoangThoiGian(ngayBatDau, ngayKetThuc);
    }
    
    // thống kê thể loại bán
    public ArrayList<ThongKeTheLoaiBanDTO> thongKeTheLoaiTrongKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc){
        return thongKeDAO.thongKeTheLoaiTrongKhoangThoiGian(ngayBatDau, ngayKetThuc);
    } 
    
    // thống kê hóa đơn bán
     public ArrayList<ThongKeHoaDonBanDTO> thongKeHoaDonTrongKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) {
         return thongKeDAO.thongKeHoaDonTrongKhoangThoiGian(ngayBatDau, ngayKetThuc);
     }
}
