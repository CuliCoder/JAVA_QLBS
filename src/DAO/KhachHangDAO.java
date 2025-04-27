package DAO;

import Connection.ConnectDB;
import DTO.KhachHangDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {

    private Connection conn;

    // ✅ Constructor tự động thiết lập kết nối nếu không có
    public KhachHangDAO() {
        this.conn = ConnectDB.getConnection();
    }

    public KhachHangDAO(Connection conn) {
        this.conn = conn;
    }

    // ✅ Thêm khách hàng
    public boolean themKhachHang(KhachHangDTO kh) {
        String sql = "INSERT INTO KhachHang (hoTen, sdt, email, diaChi, ngayTao) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kh.getHoTen());
            stmt.setString(2, kh.getSdt());
            stmt.setString(3, kh.getEmail());
            stmt.setString(4, kh.getDiaChi());
            stmt.setDate(5, new java.sql.Date(kh.getNgayTao().getTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Cập nhật khách hàng
    public boolean suaKhachHang(KhachHangDTO kh) {
        String sql = "UPDATE KhachHang SET hoTen = ?, sdt = ?, email = ?, diaChi = ?, ngayTao = ? WHERE idKH = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, kh.getHoTen());
            stmt.setString(2, kh.getSdt());
            stmt.setString(3, kh.getEmail());
            stmt.setString(4, kh.getDiaChi());
            stmt.setDate(5, new java.sql.Date(kh.getNgayTao().getTime()));
            stmt.setInt(6, kh.getIdKH());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Xóa khách hàng
    public boolean xoaKhachHang(int idKH) {
        String sql = "DELETE FROM KhachHang WHERE idKH = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idKH);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Tìm khách hàng theo ID
    public KhachHangDTO timKhachHangTheoMa(int idKH) {
        String sql = "SELECT * FROM KhachHang WHERE idKH = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idKH);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new KhachHangDTO(
                        rs.getInt("idKH"),
                        rs.getString("hoTen"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("diaChi"),
                        rs.getDate("ngayTao")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Tìm khách hàng theo tên
    public List<KhachHangDTO> timKhachHangTheoTen(String hoTen) {
        List<KhachHangDTO> danhSachKH = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE hoTen LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + hoTen + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                danhSachKH.add(new KhachHangDTO(
                        rs.getInt("idKH"),
                        rs.getString("hoTen"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("diaChi"),
                        rs.getDate("ngayTao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachKH;
    }

    public int selectLastID() {
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 MaNCC\n"
                    + "FROM NhaCungCap\n"
                    + "ORDER BY MaNCC DESC";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaNCC = rs.getInt("MaNCC");
                return MaNCC;

            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<KhachHangDTO> getAllKhachHang() {
        ArrayList<KhachHangDTO> danhSachKH = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                danhSachKH.add(new KhachHangDTO(
                        rs.getInt("idKH"),
                        rs.getString("hoTen"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("diaChi"),
                        rs.getDate("ngayTao")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachKH;
    }

}
