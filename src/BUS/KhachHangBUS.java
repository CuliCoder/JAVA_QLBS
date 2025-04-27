package BUS;

import DAO.KhachHangDAO;
import DTO.KhachHangDTO;
import java.util.ArrayList;
import java.util.List;

public class KhachHangBUS {
    private KhachHangDAO khachHangDAO;

    public KhachHangBUS() { 
        this.khachHangDAO = new KhachHangDAO();
    }

    // ✅ Thêm khách hàng
    public boolean themKhachHang(KhachHangDTO kh) {
        if (kh == null || kh.getHoTen().isEmpty() || kh.getSdt().isEmpty()) {
            System.out.println("Thông tin khách hàng không hợp lệ!");
            return false;
        }
        return khachHangDAO.themKhachHang(kh);
    }

    // ✅ Sửa thông tin khách hàng
    public boolean suaKhachHang(KhachHangDTO kh) {
        if (kh == null || kh.getIdKH() <= 0) {
            System.out.println("ID khách hàng không hợp lệ!");
            return false;
        }
        return khachHangDAO.suaKhachHang(kh);
    }

    // ✅ Xóa khách hàng
    public boolean xoaKhachHang(int idKH) {
        if (idKH <= 0) {
            System.out.println("ID khách hàng không hợp lệ!");
            return false;
        }
        return khachHangDAO.xoaKhachHang(idKH);
    }

    // ✅ Lấy danh sách tất cả khách hàng
    public ArrayList<KhachHangDTO> getAllKhachHang() {
        return khachHangDAO.getAllKhachHang();
    }

   public List<KhachHangDTO> timKhachHangTheoMa(int idKH) {
    KhachHangDTO kh = khachHangDAO.timKhachHangTheoMa(idKH);
    List<KhachHangDTO> danhSach = new ArrayList<>();
    if (kh != null) {
        danhSach.add(kh); // Bọc đối tượng vào danh sách
    }
    return danhSach;
}

    // ✅ Tìm kiếm khách hàng theo tên
    public List<KhachHangDTO> timKhachHangTheoTen(String hoTen) {
        return khachHangDAO.timKhachHangTheoTen(hoTen);
    }

    // ✅ Tìm kiếm khách hàng theo tên và số điện thoại
//    public List<KhachHangDTO> timKhachHangTheoTenVaSDT(String hoTen, String sdt) {
//        return khachHangDAO.timKhachHangTheoTenVaSDT(hoTen, sdt);
//    }

    // ✅ Lấy ID cuối cùng trong bảng khách hàng
    public int selectLastId() {
        return khachHangDAO.selectLastID();
    }
}
