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
import Util.PortServer;
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
            ArrayList<String> listLinkServer
                    = PortServer.getListLinkServer(ConnectDB.currentPortServer);
            String sql = "Select * from ("
                    + "select * from NhanVien where TinhTrang=N'Đang làm việc'"
                    + " union all "
                    + "select * from " + listLinkServer.get(0) + ".QLBS.DBO.NhanVien where TinhTrang=N'Đang làm việc'"
                    + " union all "
                    + "select * from " + listLinkServer.get(1) + ".QLBS.DBO.NhanVien where TinhTrang=N'Đang làm việc'"
                    + ") as combined_results";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String MaNV = rs.getNString("MaNV");
                String TenNV = rs.getNString("TenNV");
                String SDT = rs.getNString("SDT");
                String GioiTinh = rs.getNString("GioiTinh");
                String DiaChi = rs.getNString("DiaChi");
                String Email = rs.getNString("Email");
                String TinhTrang = rs.getNString("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang, MaChiNhanh);
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
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            String sql = "SELECT * FROM ("
                    + "SELECT * FROM NhanVien WHERE MaNV NOT IN (SELECT TenTK FROM TaiKhoan) "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(0) + ".QLBS.DBO.NhanVien WHERE MaNV NOT IN (SELECT TenTK FROM " + listLinkServer.get(0) + ".QLBS.DBO.TaiKhoan) "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(1) + ".QLBS.DBO.NhanVien WHERE MaNV NOT IN (SELECT TenTK FROM " + listLinkServer.get(1) + ".QLBS.DBO.TaiKhoan) "
                    + ") AS combined_results";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String MaNV = rs.getNString("MaNV");
                String TenNV = rs.getNString("TenNV");
                String SDT = rs.getNString("SDT");
                String GioiTinh = rs.getNString("GioiTinh");
                String DiaChi = rs.getNString("DiaChi");
                String Email = rs.getNString("Email");
                String TinhTrang = rs.getNString("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                ketQua.add(new NhanVienDTO(MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang, MaChiNhanh));
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
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            String sql = "SELECT * FROM ("
                    + "SELECT * FROM NhanVien WHERE MaNV=? "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(0) + ".QLBS.DBO.NhanVien WHERE MaNV=? "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(1) + ".QLBS.DBO.NhanVien WHERE MaNV=? "
                    + ") AS combined_results";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tukhoa);
            pst.setString(2, tukhoa);
            pst.setString(3, tukhoa);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String MaNV = rs.getNString("MaNV");
                String TenNV = rs.getNString("TenNV");
                String SDT = rs.getNString("SDT");
                String GioiTinh = rs.getNString("GioiTinh");
                String DiaChi = rs.getNString("DiaChi");
                String Email = rs.getNString("Email");
                String TinhTrang = rs.getNString("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                ketqua = new NhanVienDTO(MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang, MaChiNhanh);
            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketqua;
    }

//    public NhanVienDTO checkExistID(String tukhoa, String linkserver) {
//        NhanVienDTO ketqua = null;
//        try {
//            Connection conn = ConnectDB.getConnection();
//            Statement st = conn.createStatement();
//            String sql = "SELECT * FROM " + linkserver + "NhanVien where MaNV='" + tukhoa + "'";
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//                String MaNV = rs.getNString("MaNV");
//                String TenNV = rs.getNString("TenNV");
//                String SDT = rs.getNString("SDT");
//                String GioiTinh = rs.getNString("GioiTinh");
//                String DiaChi = rs.getNString("DiaChi");
//                String Email = rs.getNString("Email");
//                String TinhTrang = rs.getNString("TinhTrang");
//                int MaChiNhanh = rs.getInt("MaChiNhanh");
//                ketqua = new NhanVienDTO(MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang, MaChiNhanh);
//            }
//
//            ConnectDB.closeConnection(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ketqua;
//    }
    public int deleteNhanVien(String idnv) {
        int ketqua = -1;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            String sql = "update " + getLinkServer(selectNhanVienById(idnv)) + "NhanVien set TinhTrang=N'Không làm việc' where MaNV=?";
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

//    public ArrayList<NhanVienDTO> searchNhanVien(String tukhoa) {
//        ArrayList<NhanVienDTO> ketQua = new ArrayList<>();
//        try {
//            Connection conn = ConnectDB.getConnection();
//            Statement st = conn.createStatement();
//            String sql = "SELECT * FROM NhanVien "
//                    + "where TenNV like N'%" + tukhoa + "%'"
//                    + "And TinhTrang=N'Đang làm việc'";
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//                String MaNV = rs.getNString("MaNV");
//                String TenNV = rs.getNString("TenNV");
//                String SDT = rs.getNString("SDT");
//                String GioiTinh = rs.getNString("GioiTinh");
//                String DiaChi = rs.getNString("DiaChi");
//                String Email = rs.getNString("Email");
//                String TinhTrang = rs.getNString("TinhTrang");
//                int MaChiNhanh = rs.getInt("MaChiNhanh");
//                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang, MaChiNhanh);
//                ketQua.add(nv);
//            }
//
//            ConnectDB.closeConnection(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ketQua;
//    }
    public ArrayList<NhanVienDTO> searchNhanVien(String tukhoa) {
        ArrayList<NhanVienDTO> ketQua = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            String sql = "SELECT * FROM ("
                    + "SELECT * FROM NhanVien WHERE TenNV LIKE N? AND TinhTrang=N'Đang làm việc' "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(0) + ".QLBS.DBO.NhanVien WHERE TenNV LIKE N? AND TinhTrang=N'Đang làm việc' "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(1) + ".QLBS.DBO.NhanVien WHERE TenNV LIKE N? AND TinhTrang=N'Đang làm việc' "
                    + ") AS combined_results";

            PreparedStatement pst = conn.prepareStatement(sql);
            String keyword = "%" + tukhoa + "%";
            pst.setString(1, keyword);
            pst.setString(2, keyword);
            pst.setString(3, keyword);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String MaNV = rs.getNString("MaNV");
                String TenNV = rs.getNString("TenNV");
                String SDT = rs.getNString("SDT");
                String GioiTinh = rs.getNString("GioiTinh");
                String DiaChi = rs.getNString("DiaChi");
                String Email = rs.getNString("Email");
                String TinhTrang = rs.getNString("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                NhanVienDTO nv = new NhanVienDTO(MaNV, TenNV, SDT, GioiTinh, DiaChi, Email, TinhTrang, MaChiNhanh);
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

//    public String getChucVu(String id) {
//        String ketQua = null;
//        try {
//            Connection conn = ConnectDB.getConnection();
//            Statement st = conn.createStatement();
//            String sql = "SELECT TenNQ "
//                    + "FROM NhanVien,TaiKhoan,NhomQuyen "
//                    + "where MaNV=TenTK and MaQuyen=MaNQ and MaNV='" + id + "'";
//            ResultSet rs = st.executeQuery(sql);
//
//            if (rs.next()) {
//                ketQua = rs.getNString("TenNQ");
//            }
//            ConnectDB.closeConnection(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ketQua;
//    }
    public String getChucVu(String id) {
        String ketQua = null;
        try {
            Connection conn = ConnectDB.getConnection();
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            String sql = "SELECT TenNQ FROM ("
                    + "SELECT nq.TenNQ FROM NhanVien nv "
                    + "JOIN TaiKhoan tk ON nv.MaNV = tk.TenTK "
                    + "JOIN NhomQuyen nq ON tk.MaQuyen = nq.MaNQ "
                    + "WHERE nv.MaNV = ? "
                    + "UNION ALL "
                    + "SELECT nq.TenNQ FROM " + listLinkServer.get(0) + ".QLBS.DBO.NhanVien nv "
                    + "JOIN " + listLinkServer.get(0) + ".QLBS.DBO.TaiKhoan tk ON nv.MaNV = tk.TenTK "
                    + "JOIN " + listLinkServer.get(0) + ".QLBS.DBO.NhomQuyen nq ON tk.MaQuyen = nq.MaNQ "
                    + "WHERE nv.MaNV = ? "
                    + "UNION ALL "
                    + "SELECT nq.TenNQ FROM " + listLinkServer.get(1) + ".QLBS.DBO.NhanVien nv "
                    + "JOIN " + listLinkServer.get(1) + ".QLBS.DBO.TaiKhoan tk ON nv.MaNV = tk.TenTK "
                    + "JOIN " + listLinkServer.get(1) + ".QLBS.DBO.NhomQuyen nq ON tk.MaQuyen = nq.MaNQ "
                    + "WHERE nv.MaNV = ? "
                    + ") AS combined_results";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, id);
            pst.setString(3, id);

            ResultSet rs = pst.executeQuery();
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
            String linkserver = getLinkServer(nv);
            String sql = "Insert into " + linkserver + "NhanVien(MaNV,TenNV,SDT,GioiTinh,DiaChi,Email,TinhTrang,MaChiNhanh) values(?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setNString(1, nv.getMaNV());
            st.setNString(2, nv.getTenNV());
            st.setNString(3, nv.getSDT());
            st.setNString(4, nv.getGioiTinh());
            st.setNString(5, nv.getDiaChi());
            st.setNString(6, nv.getEmail());
            st.setNString(7, nv.getTinhTrang());
            st.setInt(8, nv.getMaChiNhanh());
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
            String linkServer = getLinkServer(nv);
            conn = ConnectDB.getConnection();
            String sql = "update " + linkServer + "NhanVien set TenNV=?,SDT=?,GioiTinh=?,DiaChi=?,Email=?,TinhTrang=? where MaNV=?";
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
            ArrayList<String> listLinkServer
                    = PortServer.getListLinkServer(ConnectDB.currentPortServer);
            String sql = "SELECT Max(max_id) AS max_id_across_servers\n"
                    + "FROM (\n"
                    + "	SELECT MAX(MaNV) AS max_id FROM NhanVien\n"
                    + "	union all\n"
                    + "    SELECT MAX(MaNV) AS max_id FROM " + listLinkServer.get(0) + ".QLBS.DBO.NhanVien\n"
                    + "    UNION ALL\n"
                    + "    SELECT MAX(MaNV) AS max_id FROM " + listLinkServer.get(1) + ".QLBS.DBO.NhanVien\n"
                    + ") AS combined_max_ids;";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String MaNV = rs.getString("max_id_across_servers");
                return MaNV;

            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getLinkServer(NhanVienDTO nv) {
        String linkServer = PortServer.listPort.get(nv.getMaChiNhanh()) != null
                && ConnectDB.currentPortServer != null
                && PortServer.listPort.get(nv.getMaChiNhanh()).equals(ConnectDB.currentPortServer)
                ? ""
                : "LINK" + nv.getMaChiNhanh() + ".QLBS.DBO.";
        return linkServer;
    }

    public int addNhanVienMainServer(NhanVienDTO nv) {
        int ketqua = -1;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            String sql = "Insert into LINK0.QLBS.DBO.NhanVien(MaNV,TenNV,SDT,GioiTinh,DiaChi,Email,TinhTrang,MaChiNhanh) values(?,?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setNString(1, nv.getMaNV());
            st.setNString(2, nv.getTenNV());
            st.setNString(3, nv.getSDT());
            st.setNString(4, nv.getGioiTinh());
            st.setNString(5, nv.getDiaChi());
            st.setNString(6, nv.getEmail());
            st.setNString(7, nv.getTinhTrang());
            st.setInt(8, nv.getMaChiNhanh());
            ketqua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return ketqua;
    }

    public int updateNhanVienMainServer(NhanVienDTO nv) {
        int ketqua = -1;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            String sql = "update LINK0.QLBS.DBO.NhanVien set TenNV=?,SDT=?,GioiTinh=?,DiaChi=?,Email=?,TinhTrang=? where MaNV=?";
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

    public int deleteNhanVienMainServer(String idnv) {
        int ketqua = -1;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            String sql = "update LINK0.QLBS.DBO.NhanVien set TinhTrang=N'Không làm việc' where MaNV=?";
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

}
