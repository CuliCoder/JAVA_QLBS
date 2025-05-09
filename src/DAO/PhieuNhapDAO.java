/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectDB;
import DTO.CongTyDTO;
import DTO.HoaDonDTO;
import DTO.PhieuNhapDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author NGOC THUC
 */
public class PhieuNhapDAO {

    public void updateImg() {
        int res = 0;
        for (int i = 1; i <= 9; i++) {
            try {
                Connection conn = ConnectDB.getConnection();
                String sql = "UPDATE SanPham SET HinhAnh=? WHERE MaSP=?";
                PreparedStatement pst = conn.prepareStatement(sql);
                String name = "sp0" + i + ".jpg";
                pst.setNString(1, name);
                pst.setInt(2, i);
                pst.executeUpdate();
                ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String selectSupplierByID(String ID) {
        String name = null;
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT TenNCC FROM NhaCungCap WHERE MaNCC = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, ID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                name = rs.getString("TenNCC");
            }
            ConnectDB.closeConnection(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public int queryByNameSupplier(String name) {
        int ID = 0;
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT MaNCC FROM NhaCungCap WHERE TenNCC = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ID = rs.getInt("MaNCC");
            }
            ConnectDB.closeConnection(c);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID;
    }

    public ArrayList<CongTyDTO> querySupplier() {
        ArrayList<CongTyDTO> ketQua = new ArrayList<>();
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT * FROM NhaCungCap WHERE TinhTrang <> 0";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaNCC = rs.getInt("MaNCC");
                String TenNCC = rs.getString("TenNCC");
                String SDT = rs.getString("SDT");
                String DiaChi = rs.getString("DiaChi");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                CongTyDTO cty = new CongTyDTO(MaNCC, TenNCC, SDT, DiaChi, true, MaChiNhanh);
                ketQua.add(cty);
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<PhieuNhapDTO> selectAll() {
        ArrayList<PhieuNhapDTO> ketQua = new ArrayList<>();
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuNhap WHERE TinhTrang <> 0";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaPN = rs.getInt("MaPN");
                String MaNCC = rs.getString("MaNCC");
                String TenTK = rs.getString("TenTK");
                Date NgayTao = rs.getDate("NgayTao");
                long TongTien = rs.getLong("TongTien");
                String TinhTrang = rs.getString("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                PhieuNhapDTO pn = new PhieuNhapDTO(MaPN, MaNCC, TenTK, TongTien, NgayTao, TinhTrang, MaChiNhanh);
                ketQua.add(pn);
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int selectLastID() {
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 MaPN\n"
                    + "FROM PhieuNhap\n"
                    + "ORDER BY MaPN DESC";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaPN = rs.getInt("MaPN");
                return MaPN;

            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public PhieuNhapDTO selectByID(String id) {
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuNhap WHERE MaPN = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaPN = rs.getInt("MaPN");
                String MaNCC = rs.getString("MaNCC");
                String TenTK = rs.getNString("TenTK");
                Date NgayTao = rs.getDate("NgayTao");
                long TongTien = rs.getLong("TongTien");
                String TinhTrang = rs.getString("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                PhieuNhapDTO pn = new PhieuNhapDTO(MaPN, MaNCC, TenTK, TongTien, NgayTao, TinhTrang, MaChiNhanh);
                return pn;
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<PhieuNhapDTO> getLikeByID(int id) {
        ArrayList<PhieuNhapDTO> ketQua = new ArrayList<>();
        try {

            Connection c = ConnectDB.getConnection();
            String sql = "SELECT * FROM PhieuNhap WHERE MaPN like  ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, "%" + id + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int MaPN = rs.getInt("MaPN");
                String MaNCC = rs.getString("MaNCC");
                String TenTK = rs.getNString("TenTK");
                Date NgayTao = rs.getDate("NgayTao");
                long TongTien = rs.getLong("TongTien");
                String TinhTrang = rs.getString("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                PhieuNhapDTO pn = new PhieuNhapDTO(MaPN, MaNCC, TenTK, TongTien, NgayTao, TinhTrang, MaChiNhanh);
                ketQua.add(pn);
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public double queryPriceByProductId(int maSP) {
        double price = 0.0;

        try {
            Connection c = ConnectDB.getConnection();
            // Câu lệnh SQL để lấy giá bán hiện tại của sản phẩm từ bảng sản phẩm
            String sql = "SELECT DonGia FROM SanPham WHERE maSP = ?";

            // Tạo PreparedStatement để tránh SQL Injection
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, maSP);
            ResultSet rs = pstmt.executeQuery();
            // Thực hiện truy vấn
            rs = pstmt.executeQuery();

            // Nếu có kết quả, lấy giá bán từ cột "giaBan"
            if (rs.next()) {
                price = rs.getDouble("DonGia");
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price; // Trả về giá bán hiện tại của sản phẩm
    }

    public double queryImportPriceByProductId(int maSP) {
        double price = 0.0;
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 DonGiaNhap \n"
                    + "FROM ChiTietPhieuNhap \n"
                    + "WHERE maSP = ?\n"
                    + "ORDER BY MaPN DESC;";
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, maSP);
            ResultSet rs = pstmt.executeQuery();
            // Thực hiện truy vấn
            rs = pstmt.executeQuery();

            // Nếu có kết quả, lấy giá bán từ cột "giaBan"
            if (rs.next()) {
                price = rs.getDouble("DonGiaNhap");
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    public int Them(PhieuNhapDTO pn) {
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "INSERT INTO PhieuNhap(MaNCC, TenTK, NgayTao, TongTien, TinhTrang, MaChiNhanh) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            java.util.Date utilDate = pn.getNgayTao();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            pst.setInt(1, Integer.parseInt(pn.getMaNCC()));
            pst.setString(2, pn.getTenTK());
            pst.setDate(3, sqlDate);
            pst.setDouble(4, pn.getTongTien());
            pst.setBoolean(5, true);
            pst.setInt(6, pn.getMaChiNhanh());
            if (pst.executeUpdate() > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int mapn = generatedKeys.getInt(1); // Cột 1 là MaHD
                    ConnectDB.closeConnection(conn);
                    return mapn;
                }
            }
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getNameSPByID(int id) {
        String name = null;
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT TenSP FROM SanPham WHERE MaSP = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                name = rs.getString("TenSP");
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public boolean XoaPhieuNhap(int MaPN) {
        int ketQua = 0;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE PhieuNhap SET TinhTrang=? WHERE MaPN=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setBoolean(1, false);
            pst.setInt(2, MaPN);

            ketQua = pst.executeUpdate();
            ConnectDB.closeConnection(conn);
            if (ketQua == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ChangePriceProductById(int MaPn, double thanhTien) {
        boolean rowUpdate = false;
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "UPDATE PhieuNhap SET TongTien =? WHERE MaPN=?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setDouble(1, thanhTien);
            pst.setInt(2, MaPn);
            rowUpdate = pst.executeUpdate() > 0;
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdate;
    }

    public static void main(String[] args) {
//        PhieuNhapDAO a = new PhieuNhapDAO();
//        a.updateImg();
    }
}
