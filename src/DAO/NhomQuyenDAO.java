/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectDB;
import DTO.NhomQuyenDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author NGOC THUC
 */
public class NhomQuyenDAO {

    
    public int getCurrentID() {
        int res = 0;
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT TOP 1 MaNQ\n"
                    + "FROM NhomQuyen\n"
                    + "ORDER BY MaNQ DESC";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int maCN = rs.getInt("MaNQ");
                res = maCN;
            }

            ConnectDB.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static String selectNameByID(String ID) {

        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT TenNQ FROM NhomQuyen WHERE MaNQ = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setString(1, ID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String TenNQ = rs.getNString("TenNQ");
                return TenNQ;
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<NhomQuyenDTO> selectAll() {
        ArrayList<NhomQuyenDTO> ketQua = new ArrayList<>();
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT * FROM NhomQuyen WHERE TinhTrang <> 0";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaNQ = rs.getInt("MaNQ");
                String TenNQ = rs.getNString("TenNQ");
                String MoTa = rs.getNString("MoTa");
                String TinhTrang = rs.getString("TinhTrang");

                NhomQuyenDTO nq = new NhomQuyenDTO(MaNQ, TenNQ, MoTa, TinhTrang);
                ketQua.add(nq);
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public boolean Them(NhomQuyenDTO nq) {
        boolean ketQua = false;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "INSERT INTO NhomQuyen(TenNQ, MoTa, TinhTrang) VALUES ( ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nq.getTenNQ());
            pst.setString(2, nq.getMoTa());
            pst.setBoolean(3, true);
           

            ketQua = pst.executeUpdate() > 0;

            ConnectDB.closeConnection(conn);
            ketQua = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public boolean Sua(NhomQuyenDTO nq) {
        boolean ketQua = false;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE NhomQuyen SET TenNQ = ? , MoTa = ? WHERE MaNQ = ?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nq.getTenNQ());
            pst.setString(2, nq.getMoTa());
            pst.setInt(3, nq.getMaNQ());
           

            ketQua = pst.executeUpdate() > 0;

            ConnectDB.closeConnection(conn);
            ketQua = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    
    public boolean Xoa(int MaNQ) {
        boolean ketQua = false;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE NhomQuyen SET TinhTrang = ? WHERE MaNQ = ?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setBoolean(1, false);
            pst.setInt(2, MaNQ);
           

            ketQua = pst.executeUpdate() > 0;

            ConnectDB.closeConnection(conn);
            ketQua = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}
