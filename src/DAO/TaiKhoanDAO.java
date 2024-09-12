/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.TaiKhoanDTO;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connection.ConnectDB;
import DTO.NhanVienDTO;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author NGOC THUC
 */
public class TaiKhoanDAO {
    
    public String selectRoleByID(String MaNQ) {
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "SELECT TenNQ FROM NhomQuyen where MaNQ = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, MaNQ);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String TenNQ = rs.getNString("TenNQ");
                return TenNQ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    } 
    
    public String selectStaffByID(String MaNV) {
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "SELECT TenNV FROM NhanVien where MaNV = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, MaNV);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String TenNV = rs.getNString("TenNV");
               

                return TenNV;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    }

    public boolean changePassword(String newPassword, String username) {
        int res = 0;
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "UPDATE TaiKhoan SET MatKhau=? WHERE TenTK=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, newPassword);
            pst.setString(2, username);

            res = pst.executeUpdate();
            ConnectDB.closeConnection(conn);
            
            if(res != 0) return true;
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public TaiKhoanDTO selectByUsername(String username) {
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM TaiKhoan where TenTK = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String MaTK = rs.getString("MaTK");
                String TenTK = rs.getNString("TenTK");
                String MatKhau = rs.getNString("MatKhau");
                String MaQuyen = rs.getString("MaQuyen");
                Date NgayTao = rs.getDate("NgayTao");
                String TrangThai = rs.getString("TinhTrang");

                TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, NgayTao);
                return tk;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    }
    
    public TaiKhoanDTO selectById(int id) {
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM TaiKhoan where MaTK = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String MaTK = rs.getString("MaTK");
                String TenTK = rs.getNString("TenTK");
                String MatKhau = rs.getNString("MatKhau");
                String MaQuyen = rs.getString("MaQuyen");
                Date NgayTao = rs.getDate("NgayTao");
                String TrangThai = rs.getString("TinhTrang");

                TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, NgayTao);
                return tk;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    }
    public static ArrayList<TaiKhoanDTO> selectAll() {
        ArrayList<TaiKhoanDTO> ketQua = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM TaiKhoan WHERE TinhTrang <> 0";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String MaTK = rs.getString("MaTK");
                String TenTK = rs.getNString("TenTK");
                String MatKhau = rs.getNString("MatKhau");
                String MaQuyen = rs.getString("MaQuyen");
                Date NgayTao = rs.getDate("NgayTao");
                String TrangThai = rs.getString("TinhTrang");

                TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, NgayTao);
                ketQua.add(tk);
            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    public ArrayList<TaiKhoanDTO> searchTaiKhoan(String tukhoa) {
        ArrayList<TaiKhoanDTO> ketQua = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM TaiKhoan "
                    + "where TenTK like '%"+tukhoa+"%'";
                    
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String MaTK = rs.getString("MaTK");
                String TenTK = rs.getNString("TenTK");
                String MatKhau = rs.getNString("MatKhau");
                String MaQuyen = rs.getString("MaQuyen");
                Date NgayTao = rs.getDate("NgayTao");
                String TrangThai = rs.getString("TinhTrang");

                TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, NgayTao);
                ketQua.add(tk);
            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    public int Them(TaiKhoanDTO taiKhoan) {
        int ketQua = 0;
        int maquyen=getIdChucVu(taiKhoan.getMaQuyen());
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "INSERT INTO TaiKhoan(TenTK, MatKhau, MaQuyen, NgayTao, TinhTrang) VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, taiKhoan.getTenTK());
            pst.setString(2, taiKhoan.getMatKhau());
            pst.setInt(3, maquyen);
            pst.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(taiKhoan.getNgayTao()));
            pst.setBoolean(5, true);

            ketQua = pst.executeUpdate();

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int Sua(TaiKhoanDTO taiKhoan) {
        int ketQua = 0;
        int quyen=getIdChucVu(taiKhoan.getMaQuyen());
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE TaiKhoan SET TenTK=?, MatKhau=?, MaQuyen=?, NgayTao=?, TinhTrang=? WHERE MaTK=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, taiKhoan.getTenTK());
            pst.setString(2, taiKhoan.getMatKhau());
            pst.setInt(3, quyen);
            pst.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(taiKhoan.getNgayTao()));
            pst.setBoolean(5, true);
            int id= Integer.parseInt(taiKhoan.getMaTK().substring(2));
            pst.setInt(6, id);

            ketQua = pst.executeUpdate();

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int Xoa(int maTK) {
        int ketQua = 0;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE TaiKhoan SET TinhTrang=0 WHERE MaTK=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, maTK);

            ketQua = pst.executeUpdate();
            
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int getIdChucVu(String TenChucVu){
        int ketQua = 0;
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT MaNQ " +
                         "FROM NhomQuyen " +
                         "where TenNQ = N'"+TenChucVu+"'";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                ketQua=rs.getInt("MaNQ");
            }
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    public static void main(String[] args) throws ParseException {
        TaiKhoanDAO a = new TaiKhoanDAO();
        // ArrayList<TaiKhoanDTO> dstk = a.selectAll();
//        for(TaiKhoanDTO tk : dstk)
//        {
//            System.out.println(tk.getTinhTrang());
//        }

        // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày tháng
        // String userInput = "2023-10-13";
        // Date ngayTao = dateFormat.parse(userInput)
        // TaiKhoanDTO tk = new TaiKhoanDTO("nhanvien11", "123456", "1", "1", ngayTao);
//        int ketqua = a.Xoa("15");
//        if (ketqua > 0) {
//            System.out.println("Xoa thanh cong");
//        } else {
//            System.out.println("That bai");
//        }
// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày tháng
//        String userInput = "2023-10-13";
//        Date ngayTao = dateFormat.parse(userInput);
//  TaiKhoanDTO tk = new TaiKhoanDTO("9","nhanvien11", "123456", "1", "1", ngayTao);
//    a.Sua(tk);
    }
    public int selectLastID() {
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 MaTK\n"
                    + "FROM TaiKhoan\n"
                    + "ORDER BY MaTK DESC";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaTK = rs.getInt("MaTK");
                return MaTK;

            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
