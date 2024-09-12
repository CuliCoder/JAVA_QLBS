/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectDB;
import DTO.HoaDonDTO;
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

                HoaDonDTO pn = new HoaDonDTO(MaHD, TenTK, TongTien, NgayTao);
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

                HoaDonDTO hd = new HoaDonDTO(MaHD, TenTK, TongTien, NgayTao);
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
            String sql = "SELECT Max(MaHD) as MaxMaHD FROM HoaDon";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                maHD = rs.getInt("MaxMaHD");
            }
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maHD;
    }

    public boolean luuHoaDon(HoaDonDTO hoaDon) {
        String sql = "INSERT INTO HoaDon (TenTK, NgayTao, TongTien) VALUES (?, ?, ?)";
        boolean rowInserted = false;
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, hoaDon.getTenTK());
            pst.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(hoaDon.getNgayTao()));
            pst.setDouble(3, hoaDon.getTongTien());
            rowInserted = pst.executeUpdate() > 0;
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowInserted;
    }
}
