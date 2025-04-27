/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectDB;
import DTO.CTHoaDonDTO;
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
public class CTHoaDonDAO {

    public ArrayList<CTHoaDonDTO> findHoaDonByMaHD(int MaHoaDon) {
        ArrayList<CTHoaDonDTO> ketQua = new ArrayList<>();
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT TenSP, cthd.SoLuong,Cthd.DonGia FROM SanPham sp join ChiTietHoaDon cthd on sp.MaSP = cthd.MaSP Where MaHD = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, MaHoaDon);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String TenSP = rs.getString("TenSP");
                int soLuong = rs.getInt("SoLuong");
                double DonGia = rs.getDouble("DonGia");

                CTHoaDonDTO cthd = new CTHoaDonDTO(DonGia, soLuong, TenSP);
                ketQua.add(cthd);
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public boolean luuChiTietHoaDon(CTHoaDonDTO chiTietHoaDon) {
        String sql = "INSERT INTO ChiTietHoaDon (MaHD, MaSP, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        boolean rowInserted = false;
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, chiTietHoaDon.getMaHD());
            pst.setInt(2, chiTietHoaDon.getMaSP());
            pst.setInt(3, chiTietHoaDon.getSoLuong());
            pst.setDouble(4, chiTietHoaDon.getDonGia());
            rowInserted = pst.executeUpdate() > 0;
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }

    private int getMaHoaDonMax() {
        int maHD = -1;
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
            String sql = "select max(MaHD) as MaHD from LINK0.QLBS.DBO.HoaDon ";
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

    public boolean luuChiTietHoaDonMainServer(CTHoaDonDTO chiTietHoaDon) {
        String sql = "INSERT INTO LINK0.QLBS.DBO.ChiTietHoaDon (MaHD, MaSP, SoLuong, DonGia) VALUES (?, ?, ?, ?)";
        boolean rowInserted = false;
        try {
            int maHD = getMaHoaDonMax();
            Connection conn = ConnectDB.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, maHD);
            pst.setInt(2, chiTietHoaDon.getMaSP());
            pst.setInt(3, chiTietHoaDon.getSoLuong());
            pst.setDouble(4, chiTietHoaDon.getDonGia());
            rowInserted = pst.executeUpdate() > 0;
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }
}
