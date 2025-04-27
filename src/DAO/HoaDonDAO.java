/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectDB;
import DTO.HoaDonDTO;
import Util.PortServer;
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
public class HoaDonDAO {

    public ArrayList<HoaDonDTO> selectAll() {
        ArrayList<HoaDonDTO> ketQua = new ArrayList<>();
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaHD = rs.getInt("MaHD");
                String TenTK = rs.getNString("TenTK");
                Date NgayTao = rs.getDate("NgayTao");
                double TongTien = rs.getFloat("TongTien");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                int idKH = rs.getInt("idKH");
                HoaDonDTO pn = new HoaDonDTO(MaHD, TenTK, TongTien, MaChiNhanh, idKH, NgayTao);
                ketQua.add(pn);
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<HoaDonDTO> findHoaDonByMaHD(int MaHoaDon) {
        ArrayList<HoaDonDTO> ketQua = new ArrayList<>();
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT * FROM HoaDon Where MaHD LIKE ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, "%" + MaHoaDon + "%");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int MaHD = rs.getInt("MaHD");
                String TenTK = rs.getNString("TenTK");
                Date NgayTao = rs.getDate("NgayTao");
                double TongTien = rs.getFloat("TongTien");
                int MaChiNhanh = rs.getInt("MaChiNhanh");
                int idKH = rs.getInt("idKH");
                HoaDonDTO hd = new HoaDonDTO(MaHD, TenTK, TongTien, MaChiNhanh, idKH, NgayTao);
                ketQua.add(hd);
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    // Lấy mã hóa đơn lớn nhất 
    public int getMaHoaDonMax() {
        int maHD = 0;
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
//            ArrayList<String> listLinkServer
//                    = PortServer.getListLinkServer(ConnectDB.currentPortServer);
//            String sql = "SELECT Max(max_id) AS max_id_across_servers\n"
//                    + "FROM (\n"
//                    + "	SELECT MAX(MaHD) AS max_id FROM HoaDon\n"
//                    + "	union all\n"
//                    + "    SELECT MAX(MaHD) AS max_id FROM " + listLinkServer.get(0) + ".QLBS.DBO.HoaDon\n"
//                    + "    UNION ALL\n"
//                    + "    SELECT MAX(MaHD) AS max_id FROM " + listLinkServer.get(1) + ".QLBS.DBO.HoaDon\n"
//                    + ") AS combined_max_ids;";
            String sql = "select max(MaHD) as MaHD from HoaDon ";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                maHD = rs.getInt("MaHD");
            }
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maHD;
    }

    public int luuHoaDon(HoaDonDTO hoaDon) {
        String sql = "INSERT INTO HoaDon (TenTK, NgayTao, TongTien, MaChiNhanh, idKH) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, hoaDon.getTenTK());
            pst.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(hoaDon.getNgayTao()));
            pst.setDouble(3, hoaDon.getTongTien());
            pst.setInt(4, hoaDon.getMaChiNhanh());
            pst.setInt(5, hoaDon.getIdKH());
            boolean rowInserted = pst.executeUpdate() > 0;
            if (rowInserted) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int maHoaDonVuaThem = generatedKeys.getInt(1); // Cột 1 là MaHD
                    return maHoaDonVuaThem;
                }
            }
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int luuHoaDonMainServer(HoaDonDTO hoaDon) {
        String sql = "INSERT INTO LINK0.QLBS.DBO.HoaDon (TenTK, NgayTao, TongTien, MaChiNhanh, idKH) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, hoaDon.getTenTK());
            pst.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(hoaDon.getNgayTao()));
            pst.setDouble(3, hoaDon.getTongTien());
            pst.setInt(4, hoaDon.getMaChiNhanh());
            pst.setInt(5, hoaDon.getIdKH());
            boolean rowInserted = pst.executeUpdate() > 0;
            if (rowInserted) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int maHoaDonVuaThem = generatedKeys.getInt(1); // Cột 1 là MaHD
                    return maHoaDonVuaThem;
                }
            }
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
