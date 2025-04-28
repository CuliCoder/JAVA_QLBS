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
import Util.PortServer;
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

//    public String selectStaffByID(String MaNV) {
//        Connection conn = ConnectDB.getConnection();
//        try {
//            String sql = "SELECT TenNV FROM NhanVien where MaNV = ?";
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, MaNV);
//            ResultSet rs = pst.executeQuery();
//
//            while (rs.next()) {
//                String TenNV = rs.getNString("TenNV");
//
//                return TenNV;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            ConnectDB.closeConnection(conn);
//        }
//        return null;
//    }
    public String selectStaffByID(String MaNV) {
        Connection conn = ConnectDB.getConnection();
        try {
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            String sql = "SELECT TenNV FROM ("
                    + "SELECT MaNV, TenNV FROM NhanVien WHERE MaNV = ? "
                    + "UNION ALL "
                    + "SELECT MaNV, TenNV FROM " + listLinkServer.get(0) + ".QLBS.DBO.NhanVien WHERE MaNV = ? "
                    + "UNION ALL "
                    + "SELECT MaNV, TenNV FROM " + listLinkServer.get(1) + ".QLBS.DBO.NhanVien WHERE MaNV = ? "
                    + ") AS combined_results";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, MaNV);
            pst.setString(2, MaNV);
            pst.setString(3, MaNV);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getNString("TenNV");
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

            if (res != 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

//    public TaiKhoanDTO selectByUsername(String username) {
//        Connection conn = ConnectDB.getConnection();
//        try {
//            String sql = "select * from TaiKhoan where TenTK = ?";
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, username);
//            ResultSet rs = pst.executeQuery();
//
//            while (rs.next()) {
//                String MaTK = rs.getString("MaTK");
//                String TenTK = rs.getNString("TenTK");
//                String MatKhau = rs.getNString("MatKhau");
//                String MaQuyen = rs.getString("MaQuyen");
//                Date NgayTao = rs.getDate("NgayTao");
//                String TrangThai = rs.getString("TinhTrang");
//                int MaChiNhanh = rs.getInt("MaChiNhanh");
//                TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, MaChiNhanh, NgayTao);
//                return tk;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            ConnectDB.closeConnection(conn);
//        }
//        return null;
//    }
    public TaiKhoanDTO selectByUsername(String username) {
        Connection conn = ConnectDB.getConnection();
        try {
            ArrayList<String> listLinkServer
                    = PortServer.getListLinkServer(ConnectDB.currentPortServer);
            String sql = "Select * from ("
                    + "select * from TaiKhoan where TenTK = ?"
                    + " union all "
                    + "select * from " + listLinkServer.get(0) + ".QLBS.DBO.TaiKhoan where TenTk = ?"
                    + " union all "
                    + "select * from " + listLinkServer.get(1) + ".QLBS.DBO.TaiKhoan where TenTk = ?"
                    + ") as combined_results";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, username);
            pst.setString(3, username);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String MaTK = rs.getString("MaTK");
                String TenTK = rs.getNString("TenTK");
                String MatKhau = rs.getNString("MatKhau");
                String MaQuyen = rs.getString("MaQuyen");
                Date NgayTao = rs.getDate("NgayTao");
                String TrangThai = rs.getString("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, MaChiNhanh, NgayTao);
                return tk;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    }

//    public TaiKhoanDTO selectById(int id) {
//        Connection conn = ConnectDB.getConnection();
//        try {
//            String sql = "SELECT * FROM TaiKhoan where MaTK = ?";
//            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setInt(1, id);
//            ResultSet rs = pst.executeQuery();
//
//            while (rs.next()) {
//                String MaTK = rs.getString("MaTK");
//                String TenTK = rs.getNString("TenTK");
//                String MatKhau = rs.getNString("MatKhau");
//                String MaQuyen = rs.getString("MaQuyen");
//                Date NgayTao = rs.getDate("NgayTao");
//                String TrangThai = rs.getString("TinhTrang");
//                int MaChiNhanh = rs.getInt("MaChiNhanh");
//                TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, MaChiNhanh, NgayTao);
//                return tk;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            ConnectDB.closeConnection(conn);
//        }
//        return null;
//    }
    public TaiKhoanDTO selectById(int id) {
        TaiKhoanDTO taiKhoan = null;
        try (Connection conn = ConnectDB.getConnection()) {
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            String sql = "SELECT * FROM ("
                    + "SELECT * FROM TaiKhoan WHERE MaTK = ? "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(0) + ".QLBS.DBO.TaiKhoan WHERE MaTK = ? "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(1) + ".QLBS.DBO.TaiKhoan WHERE MaTK = ? "
                    + ") AS combined_results";

            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, id);
                pst.setInt(2, id);
                pst.setInt(3, id);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        String MaTK = rs.getString("MaTK");
                        String TenTK = rs.getNString("TenTK");
                        String MatKhau = rs.getNString("MatKhau");
                        String MaQuyen = rs.getString("MaQuyen");
                        Date NgayTao = rs.getDate("NgayTao");
                        String TrangThai = rs.getString("TinhTrang");
                        int MaChiNhanh = rs.getInt("MaChiNhanh");
                        taiKhoan = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, MaChiNhanh, NgayTao);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taiKhoan;
    }

//    public static ArrayList<TaiKhoanDTO> selectAll() {
//        ArrayList<TaiKhoanDTO> ketQua = new ArrayList<>();
//        try {
//            Connection conn = ConnectDB.getConnection();
//            Statement st = conn.createStatement();
//            String sql = "SELECT * FROM TaiKhoan WHERE TinhTrang <> 0";
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//                String MaTK = rs.getString("MaTK");
//                String TenTK = rs.getNString("TenTK");
//                String MatKhau = rs.getNString("MatKhau");
//                String MaQuyen = rs.getString("MaQuyen");
//                Date NgayTao = rs.getDate("NgayTao");
//                String TrangThai = rs.getString("TinhTrang");
//                int MaChiNhanh = rs.getInt("MaChiNhanh");
//                TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, MaChiNhanh, NgayTao);
//                ketQua.add(tk);
//            }
//
//            ConnectDB.closeConnection(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ketQua;
//    }
    public static ArrayList<TaiKhoanDTO> selectAll() {
        ArrayList<TaiKhoanDTO> ketQua = new ArrayList<>();
        try (Connection conn = ConnectDB.getConnection()) {
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            String sql = "SELECT * FROM ("
                    + "SELECT * FROM TaiKhoan WHERE TinhTrang <> 0 "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(0) + ".QLBS.DBO.TaiKhoan WHERE TinhTrang <> 0 "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(1) + ".QLBS.DBO.TaiKhoan WHERE TinhTrang <> 0 "
                    + ") AS combined_results";

            try (PreparedStatement pst = conn.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    String MaTK = rs.getString("MaTK");
                    String TenTK = rs.getNString("TenTK");
                    String MatKhau = rs.getNString("MatKhau");
                    String MaQuyen = rs.getString("MaQuyen");
                    Date NgayTao = rs.getDate("NgayTao");
                    String TrangThai = rs.getString("TinhTrang");
                    int MaChiNhanh = rs.getInt("MaChiNhanh");
                    TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, MaChiNhanh, NgayTao);
                    ketQua.add(tk);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
//    public ArrayList<TaiKhoanDTO> searchTaiKhoan(String tukhoa) {
//        ArrayList<TaiKhoanDTO> ketQua = new ArrayList<>();
//        try {
//            Connection conn = ConnectDB.getConnection();
//            Statement st = conn.createStatement();
//            String sql = "SELECT * FROM TaiKhoan "
//                    + "where TenTK like '%" + tukhoa + "%'";
//
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//                String MaTK = rs.getString("MaTK");
//                String TenTK = rs.getNString("TenTK");
//                String MatKhau = rs.getNString("MatKhau");
//                String MaQuyen = rs.getString("MaQuyen");
//                Date NgayTao = rs.getDate("NgayTao");
//                String TrangThai = rs.getString("TinhTrang");
//                int MaChiNhanh = rs.getInt("MaChiNhanh");
//                TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, MaChiNhanh, NgayTao);
//                ketQua.add(tk);
//            }
//
//            ConnectDB.closeConnection(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ketQua;
//    }

    public ArrayList<TaiKhoanDTO> searchTaiKhoan(String tukhoa) {
        ArrayList<TaiKhoanDTO> ketQua = new ArrayList<>();
        try (Connection conn = ConnectDB.getConnection()) {
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            String sql = "SELECT * FROM ("
                    + "SELECT * FROM TaiKhoan WHERE TenTK LIKE ? "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(0) + ".QLBS.DBO.TaiKhoan WHERE TenTK LIKE ? "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(1) + ".QLBS.DBO.TaiKhoan WHERE TenTK LIKE ? "
                    + ") AS combined_results";

            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                String keyword = "%" + tukhoa + "%";
                pst.setNString(1, keyword);
                pst.setNString(2, keyword);
                pst.setNString(3, keyword);

                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        String MaTK = rs.getString("MaTK");
                        String TenTK = rs.getNString("TenTK");
                        String MatKhau = rs.getNString("MatKhau");
                        String MaQuyen = rs.getString("MaQuyen");
                        Date NgayTao = rs.getDate("NgayTao");
                        String TrangThai = rs.getString("TinhTrang");
                        int MaChiNhanh = rs.getInt("MaChiNhanh");
                        TaiKhoanDTO tk = new TaiKhoanDTO(MaTK, TenTK, MatKhau, MaQuyen, TrangThai, MaChiNhanh, NgayTao);
                        ketQua.add(tk);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int Them(TaiKhoanDTO taiKhoan) {
        int ketQua = 0;
        int maquyen = getIdChucVu(taiKhoan.getMaQuyen());
        try {
            Connection conn = ConnectDB.getConnection();
            NhanVienDTO nhanVienDTO = new NhanVienDAO().selectNhanVienById(taiKhoan.getTenTK());
            String sql = "INSERT INTO " + getLinkServer(nhanVienDTO) + "TaiKhoan(TenTK, MatKhau, MaQuyen, NgayTao, TinhTrang, MaChiNhanh) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, taiKhoan.getTenTK());
            pst.setString(2, taiKhoan.getMatKhau());
            pst.setInt(3, maquyen);
            pst.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(taiKhoan.getNgayTao()));
            pst.setBoolean(5, true);
            pst.setInt(6, nhanVienDTO.getMaChiNhanh());
            ketQua = pst.executeUpdate();

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int Sua(TaiKhoanDTO taiKhoan) {
        int ketQua = 0;
        int quyen = getIdChucVu(taiKhoan.getMaQuyen());
        try {
            Connection conn = ConnectDB.getConnection();
            int id = Integer.parseInt(taiKhoan.getMaTK().substring(2));
            TaiKhoanDTO taiKhoanDTO = selectById(id);
            String sql = "UPDATE " + getLinkServer(taiKhoanDTO) + "TaiKhoan SET TenTK=?, MatKhau=?, MaQuyen=?, NgayTao=?, TinhTrang=? WHERE MaTK=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, taiKhoan.getTenTK());
            pst.setString(2, taiKhoan.getMatKhau());
            pst.setInt(3, quyen);
            pst.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(taiKhoan.getNgayTao()));
            pst.setBoolean(5, true);
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
            TaiKhoanDTO taiKhoanDTO = selectById(maTK);
            String sql = "UPDATE " + getLinkServer(taiKhoanDTO) + "TaiKhoan SET TinhTrang=0 WHERE MaTK=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, maTK);

            ketQua = pst.executeUpdate();

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int getIdChucVu(String TenChucVu) {
        int ketQua = 0;
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT MaNQ "
                    + "FROM NhomQuyen "
                    + "where TenNQ = N'" + TenChucVu + "'";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                ketQua = rs.getInt("MaNQ");
            }
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
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

    private String getLinkServer(TaiKhoanDTO tk) {
        String linkServer = PortServer.listPort.get(tk.getMaChiNhanh()) != null
                && ConnectDB.currentPortServer != null
                && PortServer.listPort.get(tk.getMaChiNhanh()).equals(ConnectDB.currentPortServer)
                ? ""
                : "LINK" + tk.getMaChiNhanh() + ".QLBS.DBO.";
        return linkServer;
    }

    private String getLinkServer(NhanVienDTO tk) {
        String linkServer = PortServer.listPort.get(tk.getMaChiNhanh()) != null
                && ConnectDB.currentPortServer != null
                && PortServer.listPort.get(tk.getMaChiNhanh()).equals(ConnectDB.currentPortServer)
                ? ""
                : "LINK" + tk.getMaChiNhanh() + ".QLBS.DBO.";
        return linkServer;
    }

    public int ThemMainServer(TaiKhoanDTO taiKhoan) {
        int ketQua = 0;
        int maquyen = getIdChucVu(taiKhoan.getMaQuyen());
        try {
            Connection conn = ConnectDB.getConnection();
            NhanVienDTO nhanVienDTO = new NhanVienDAO().selectNhanVienById(taiKhoan.getTenTK());
            String sql = "INSERT INTO LINK0.QLBS.DBO.TaiKhoan(TenTK, MatKhau, MaQuyen, NgayTao, TinhTrang, MaChiNhanh) VALUES ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, taiKhoan.getTenTK());
            pst.setString(2, taiKhoan.getMatKhau());
            pst.setInt(3, maquyen);
            pst.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(taiKhoan.getNgayTao()));
            pst.setBoolean(5, true);
            pst.setInt(6, nhanVienDTO.getMaChiNhanh());
            ketQua = pst.executeUpdate();

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int SuaMainServer(TaiKhoanDTO taiKhoan) {
        int ketQua = 0;
        int quyen = getIdChucVu(taiKhoan.getMaQuyen());
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE LINK0.QLBS.DBO.TaiKhoan SET TenTK=?, MatKhau=?, MaQuyen=?, NgayTao=?, TinhTrang=? WHERE TenTK=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, taiKhoan.getTenTK());
            pst.setString(2, taiKhoan.getMatKhau());
            pst.setInt(3, quyen);
            pst.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(taiKhoan.getNgayTao()));
            pst.setBoolean(5, true);
            pst.setString(6, taiKhoan.getTenTK());
            ketQua = pst.executeUpdate();

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int XoaMainServer(int maTK) {
        int ketQua = 0;
        try {
            Connection conn = ConnectDB.getConnection();
            TaiKhoanDTO taiKhoanDTO = selectById(maTK);
            String sql = "UPDATE LINK0.QLBS.DBO.TaiKhoan SET TinhTrang=0 WHERE TenTK=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, taiKhoanDTO.getTenTK());
            ketQua = pst.executeUpdate();

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}
