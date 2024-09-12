/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectDB;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connection.ConnectDB;
import java.sql.Statement;

/**
 *
 * @author NGOC THUC
 */
public class NhanVienDAO {

    public ArrayList<NhanVienDTO> selectAll() {
        ArrayList<NhanVienDTO> ketQua = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM NhanVien where TinhTrang=N'Đang làm việc'";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String MaNV = rs.getNString("MaNV");
                String TenNV = rs.getNString("TenNV");
                String SDT = rs.getNString("SDT");
                String GioiTinh = rs.getNString("GioiTinh");
                String DiaChi = rs.getNString("DiaChi");
                String Email = rs.getNString("Email");
                String TinhTrang = rs.getNString("TinhTrang");

                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang);
                ketQua.add(nv);
            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<NhanVienDTO> selectAllChuaTaoTK() {
        ArrayList<NhanVienDTO> ketQua = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM NhanVien where MaNV NOT IN ( SELECT TenTK from TaiKhoan)";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String MaNV = rs.getNString("MaNV");
                String TenNV = rs.getNString("TenNV");
                String SDT = rs.getNString("SDT");
                String GioiTinh = rs.getNString("GioiTinh");
                String DiaChi = rs.getNString("DiaChi");
                String Email = rs.getNString("Email");
                String TinhTrang = rs.getNString("TinhTrang");

                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang);
                ketQua.add(nv);
            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public NhanVienDTO selectNhanVienById(String tukhoa) {
        NhanVienDTO ketqua = new NhanVienDTO();
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM NhanVien where MaNV='" + tukhoa + "'";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String MaNV = rs.getNString("MaNV");
                String TenNV = rs.getNString("TenNV");
                String SDT = rs.getNString("SDT");
                String GioiTinh = rs.getNString("GioiTinh");
                String DiaChi = rs.getNString("DiaChi");
                String Email = rs.getNString("Email");
                String TinhTrang = rs.getNString("TinhTrang");

                ketqua = new NhanVienDTO(MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang);

            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

    public int deleteNhanVien(String idnv) {
        int ketqua = -1;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            String sql = "update NhanVien set TinhTrang=N'Không làm việc' where MaNV=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setNString(1, idnv);
            ketqua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return ketqua;
    }

    public ArrayList<NhanVienDTO> searchNhanVien(String tukhoa) {
        ArrayList<NhanVienDTO> ketQua = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM NhanVien "
                    + "where TenNV like N'%" + tukhoa + "%'"
                    + "And TinhTrang=N'Đang làm việc'";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String MaNV = rs.getNString("MaNV");
                String TenNV = rs.getNString("TenNV");
                String SDT = rs.getNString("SDT");
                String GioiTinh = rs.getNString("GioiTinh");
                String DiaChi = rs.getNString("DiaChi");
                String Email = rs.getNString("Email");
                String TinhTrang = rs.getNString("TinhTrang");

                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang);
                ketQua.add(nv);
            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<String> fullChucVu() {
        ArrayList<String> ketQua = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT TenNQ FROM NhomQuyen where TinhTrang <> 0";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                ketQua.add(rs.getNString("TenNQ"));
            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public String getChucVu(String id) {
        String ketQua = null;
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT TenNQ "
                    + "FROM NhanVien,TaiKhoan,NhomQuyen "
                    + "where MaNV=TenTK and MaQuyen=MaNQ and MaNV='" + id + "'";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                ketQua = rs.getNString("TenNQ");
            }
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int addNhanVien(NhanVienDTO nv) {
        int ketqua = -1;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            String sql = "Insert into NhanVien(MaNV,TenNV,SDT,GioiTinh,DiaChi,Email,TinhTrang) values(?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setNString(1, nv.getMaNV());
            st.setNString(2, nv.getTenNV());
            st.setNString(3, nv.getSDT());
            st.setNString(4, nv.getGioiTinh());
            st.setNString(5, nv.getDiaChi());
            st.setNString(6, nv.getEmail());
            st.setNString(7, nv.getTinhTrang());
            ketqua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return ketqua;
    }

    public int updateNhanVien(NhanVienDTO nv) {
        int ketqua = -1;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            String sql = "update NhanVien set TenNV=?,SDT=?,GioiTinh=?,DiaChi=?,Email=?,TinhTrang=? where MaNV=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setNString(1, nv.getTenNV());
            st.setNString(2, nv.getSDT());
            st.setNString(3, nv.getGioiTinh());
            st.setNString(4, nv.getDiaChi());
            st.setNString(5, nv.getEmail());
            st.setNString(6, nv.getTinhTrang());
            st.setNString(7, nv.getMaNV());
            ketqua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return ketqua;
    }

    public String selectLastID() {
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 MaNV\n"
                    + "FROM NhanVien\n"
                    + "ORDER BY MaNV DESC";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String MaNV = rs.getString("MaNV");
                return MaNV;

            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
