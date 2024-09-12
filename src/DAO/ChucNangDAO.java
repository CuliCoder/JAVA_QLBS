/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChucNangDTO;
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
public class ChucNangDAO {

    public ChucNangDAO() {

    }

    public String getNameByID(int ID) {
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "SELECT TenCN FROM ChucNang WHERE MaCN= ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, ID+"");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String TenNQ = rs.getNString("TenCN");
                return TenNQ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return null;
    }

    public ArrayList<ChucNangDTO> selectStateRole() {
        ArrayList<ChucNangDTO> res = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM ChucNang WHERE TinhTrang <> 0";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int maCN = rs.getInt("MaCN");
                String tenCN = rs.getNString("TenCN");
                boolean them = rs.getBoolean("Them");
                boolean xoa = rs.getBoolean("Xoa");
                boolean sua = rs.getBoolean("Sua");
                boolean truyCap = rs.getBoolean("Doc");
                ChucNangDTO cn = new ChucNangDTO(tenCN, maCN, them, sua, xoa, truyCap);
                res.add(cn);
            }

            ConnectDB.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int getCurrentID() {
        int res = 0;
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT TOP 1 MaCN\n"
                    + "FROM ChucNang\n"
                    + "ORDER BY MaCN DESC";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int maCN = rs.getInt("MaCN");
                res = maCN;
            }

            ConnectDB.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<ChucNangDTO> selectAll() {
        ArrayList<ChucNangDTO> res = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM ChucNang WHERE TinhTrang <> 0";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int maCN = rs.getInt("MaCN");
                String tenCN = rs.getNString("TenCN");
                String tinhTrang = rs.getString("TinhTrang");

                ChucNangDTO cn = new ChucNangDTO(maCN, tenCN, tinhTrang);
                res.add(cn);
            }

            ConnectDB.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<ChucNangDTO> selectAllData() {
        ArrayList<ChucNangDTO> res = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM ChucNang";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int maCN = rs.getInt("MaCN");
                String tenCN = rs.getNString("TenCN");
                boolean them = rs.getBoolean("Them");
                boolean xoa = rs.getBoolean("Xoa");
                boolean sua = rs.getBoolean("Sua");
                boolean truyCap = rs.getBoolean("Doc");
                String TinhTrang = rs.getString("TinhTrang");
                ChucNangDTO cn = new ChucNangDTO(tenCN, TinhTrang, maCN, them, sua, xoa, truyCap);
                res.add(cn);
            }

            ConnectDB.closeConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public boolean Them(ChucNangDTO cn) {
        boolean ketQua = false;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "INSERT INTO ChucNang(TenCN, TinhTrang, Them, Sua, Xoa, Doc) "
                    + " VALUES(?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, cn.getTenCN());
            pst.setBoolean(2, true);
            pst.setBoolean(3, cn.isThem());
            pst.setBoolean(4, cn.isSua());
            pst.setBoolean(5, cn.isXoa());
            pst.setBoolean(6, cn.isTruyCap());

            ketQua = pst.executeUpdate() > 0;

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public boolean Sua(ChucNangDTO cn) {
        boolean ketQua = false;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE ChucNang SET TenCN = ?, Them = ?, Sua = ?, Xoa = ?, Doc = ? WHERE MaCN = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, cn.getTenCN());
            pst.setBoolean(2, cn.isThem());
            pst.setBoolean(3, cn.isSua());
            pst.setBoolean(4, cn.isXoa());
            pst.setBoolean(5, cn.isTruyCap());
            pst.setInt(6, cn.getMaCN());

            ketQua = pst.executeUpdate() > 0;

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public boolean Xoa(int MaCN) {
        int ketQua = 0;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE ChucNang SET TINHTRANG = ? "
                    + " WHERE MaCN=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setBoolean(1, false);
            pst.setInt(2, MaCN);

            ketQua = pst.executeUpdate();

            ConnectDB.closeConnection(conn);
            if (ketQua > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
