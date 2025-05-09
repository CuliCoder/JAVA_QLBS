/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectDB;
import DTO.CongTyDTO;
import Util.PortServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author NGOC THUC
 */
public class CongTyDAO {

//    public static ArrayList<CongTyDTO> selectAll() {
//        ArrayList<CongTyDTO> ketQua = new ArrayList<>();
//        try {
//            Connection conn = ConnectDB.getConnection();
//            Statement st = conn.createStatement();
//            String sql = "SELECT * FROM NhaCungCap";
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//                int MaNCC = rs.getInt("MaNCC");
//                String TenNCC = rs.getNString("TenNCC");
//                String SDT = rs.getNString("SDT");
//                String DiaChi = rs.getNString("DiaChi");
//                Boolean TinhTrang = rs.getBoolean("TinhTrang");
//
//                CongTyDTO nv = new CongTyDTO(MaNCC, TenNCC, SDT, DiaChi, TinhTrang);
//                ketQua.add(nv);
//            }
//
//            ConnectDB.closeConnection(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ketQua;
//    }
    public static ArrayList<CongTyDTO> selectAll() {
        ArrayList<CongTyDTO> ketQua = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            // SQL truy vấn lấy dữ liệu từ 3 server
            String sql = "SELECT * FROM ("
                    + "SELECT * FROM NhaCungCap "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(0) + ".QLBS.DBO.NhaCungCap "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(1) + ".QLBS.DBO.NhaCungCap "
                    + ") AS combined_results";

            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaNCC = rs.getInt("MaNCC");
                String TenNCC = rs.getNString("TenNCC");
                String SDT = rs.getNString("SDT");
                String DiaChi = rs.getNString("DiaChi");
                Boolean TinhTrang = rs.getBoolean("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                CongTyDTO nv = new CongTyDTO(MaNCC, TenNCC, SDT, DiaChi, TinhTrang, MaChiNhanh);
                ketQua.add(nv);
            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
//    public CongTyDTO getCongTyById(int id) {
//        CongTyDTO ctyDto = null;
//        try {
//            Connection conn = ConnectDB.getConnection();
//            Statement st = conn.createStatement();
//            String sql = "SELECT * FROM NhaCungCap WHERE MaNCC=" + id;
//            ResultSet rs = st.executeQuery(sql);
//
//            if (rs.next()) {
//                int MaNCC = rs.getInt("MaNCC");
//                String TenNCC = rs.getNString("TenNCC");
//                String SDT = rs.getNString("SDT");
//                String DiaChi = rs.getNString("DiaChi");
//                Boolean TinhTrang = rs.getBoolean("TinhTrang");
//                int MaChiNhanh = rs.getInt("MaChiNhanh");
//                ctyDto = new CongTyDTO(MaNCC, TenNCC, SDT, DiaChi, TinhTrang, MaChiNhanh);
//            }
//            ConnectDB.closeConnection(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ctyDto;
//    }

    public CongTyDTO getCongTyById(int id) {
        CongTyDTO ctyDto = null;
        try {
            Connection conn = ConnectDB.getConnection();
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            // SQL truy vấn lấy dữ liệu từ 3 server
            String sql = "SELECT * FROM ("
                    + "SELECT * FROM NhaCungCap WHERE MaNCC = ? "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(0) + ".QLBS.DBO.NhaCungCap WHERE MaNCC = ? "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(1) + ".QLBS.DBO.NhaCungCap WHERE MaNCC = ? "
                    + ") AS combined_results";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setInt(2, id);
            pst.setInt(3, id);

            ResultSet rs = pst.executeQuery();

            // Chỉ lấy dữ liệu của công ty đầu tiên nếu có
            if (rs.next()) {
                int MaNCC = rs.getInt("MaNCC");
                String TenNCC = rs.getNString("TenNCC");
                String SDT = rs.getNString("SDT");
                String DiaChi = rs.getNString("DiaChi");
                Boolean TinhTrang = rs.getBoolean("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                ctyDto = new CongTyDTO(MaNCC, TenNCC, SDT, DiaChi, TinhTrang, MaChiNhanh);
            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ctyDto;
    }

    public int addCongTy(CongTyDTO cty) {
        int ketqua = -1;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            String sql = "Insert into " + getLinkServer(cty) + "NhaCungCap(TenNCC,SDT,DiaChi,TinhTrang,MaChiNhanh) values(?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, cty.getTenNCC());
            st.setString(2, cty.getSDT());
            st.setString(3, cty.getDiaChi());
            st.setBoolean(4, true);
            st.setInt(5, cty.getMaChiNhanh());
            ketqua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return ketqua;
    }

    public int updateCongTy(CongTyDTO cty) {
        int ketqua = -1;
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            String sql = "update " + getLinkServer(cty) + "NhaCungCap set TenNCC=?,SDT=?,DiaChi=? where MaNCC=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, cty.getTenNCC());
            st.setString(2, cty.getSDT());
            st.setString(3, cty.getDiaChi());
            st.setInt(4, cty.getMaNCC());
            ketqua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return ketqua;
    }

    public int deleteCongTy(int idcty) {
        int ketqua = -1;
        Connection conn = null;
        try {
            CongTyDTO congTyDTO = getCongTyById(idcty);
            conn = ConnectDB.getConnection();
            String sql = "update " + getLinkServer(congTyDTO) + "NhaCungCap set TinhTrang=0 where MaNCC=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, idcty);
            ketqua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return ketqua;
    }

//    public ArrayList<CongTyDTO> searchCongTy(String tukhoa) {
//        ArrayList<CongTyDTO> ketQua = new ArrayList<>();
//        try {
//            Connection conn = ConnectDB.getConnection();
//            Statement st = conn.createStatement();
//            String sql = "SELECT * FROM NhaCungCap where"
//                    + "(TenNCC like N'%" + tukhoa + "%')"
//                    + "and TinhTrang <> 0";
//            ResultSet rs = st.executeQuery(sql);
//
//            while (rs.next()) {
//                int MaNCC = rs.getInt("MaNCC");
//                String TenNCC = rs.getNString("TenNCC");
//                String SDT = rs.getNString("SDT");
//                String DiaChi = rs.getNString("DiaChi");
//                Boolean TinhTrang = rs.getBoolean("TinhTrang");
//                int MaChiNhanh = rs.getInt("MaChiNhanh");
//                CongTyDTO nv = new CongTyDTO(MaNCC, TenNCC, SDT, DiaChi, TinhTrang, MaChiNhanh);
//                ketQua.add(nv);
//            }
//
//            ConnectDB.closeConnection(conn);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ketQua;
//    }
    public ArrayList<CongTyDTO> searchCongTy(String tukhoa) {
        ArrayList<CongTyDTO> ketQua = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            ArrayList<String> listLinkServer = PortServer.getListLinkServer(ConnectDB.currentPortServer);

            // SQL truy vấn lấy dữ liệu từ 3 server
            String sql = "SELECT * FROM ("
                    + "SELECT * FROM NhaCungCap WHERE TenNCC LIKE N? AND TinhTrang <> 0 "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(0) + ".QLBS.DBO.NhaCungCap WHERE TenNCC LIKE N? AND TinhTrang <> 0 "
                    + "UNION ALL "
                    + "SELECT * FROM " + listLinkServer.get(1) + ".QLBS.DBO.NhaCungCap WHERE TenNCC LIKE N? AND TinhTrang <> 0 "
                    + ") AS combined_results";

            PreparedStatement pst = conn.prepareStatement(sql);
            String keyword = "%" + tukhoa + "%";
            pst.setString(1, keyword);
            pst.setString(2, keyword);
            pst.setString(3, keyword);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaNCC = rs.getInt("MaNCC");
                String TenNCC = rs.getNString("TenNCC");
                String SDT = rs.getNString("SDT");
                String DiaChi = rs.getNString("DiaChi");
                Boolean TinhTrang = rs.getBoolean("TinhTrang");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                CongTyDTO nv = new CongTyDTO(MaNCC, TenNCC, SDT, DiaChi, TinhTrang, MaChiNhanh);
                ketQua.add(nv);
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

    private String getLinkServer(CongTyDTO congTyDTO) {
        String linkServer = PortServer.listPort.get(congTyDTO.getMaChiNhanh()) != null
                && ConnectDB.currentPortServer != null
                && PortServer.listPort.get(congTyDTO.getMaChiNhanh()).equals(ConnectDB.currentPortServer)
                ? ""
                : "LINK" + congTyDTO.getMaChiNhanh() + ".QLBS.DBO.";
        return linkServer;
    }
}
