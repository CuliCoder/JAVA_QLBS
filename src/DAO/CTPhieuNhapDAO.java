/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.ConnectDB;
import DTO.CTPhieuNhapDTO;
import DTO.SanPhamDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author NGOC THUC
 */
public class CTPhieuNhapDAO {
    
    public boolean DoiTrangThai(int MaPN) {
        boolean rowUpdate = false;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE ChiTietPhieuNhap SET TinhTrang = ? WHERE MaPN=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setBoolean(1, false);
            pst.setInt(2, MaPN);
            rowUpdate = pst.executeUpdate() > 0;
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdate;
    }

    public boolean XoaSLCu(int MaPN, int MaSP) {

        CTPhieuNhapDTO ctpn = selectQuantity(MaPN, MaSP);
        int SoLuongCu = ctpn.getSoLuong();
        int SoLuongMoi = getCurrentQuantity(MaSP) - SoLuongCu;

        return updateSP(MaSP, SoLuongMoi, ctpn.getDonGia());

    }

    public CTPhieuNhapDTO selectQuantity(int MaPN, int MaSP) {
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT SoLuong,DonGiaNhap FROM ChiTietPhieuNhap WHERE MaPN = ? AND MaSP = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, MaPN);
            pst.setInt(2, MaSP);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int SoLuong = rs.getInt("SoLuong");
                double DonGia = rs.getDouble("DonGiaNhap");
                CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO(MaPN, MaSP, DonGia, SoLuong);

                return ctpn;
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<CTPhieuNhapDTO> selectByID(int MaPN) {
        ArrayList<CTPhieuNhapDTO> ketQua = new ArrayList<>();
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT * FROM ChiTietPhieuNhap WHERE MaPN = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, MaPN);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int MaSP = rs.getInt("MaSP");
                double donGiaNhap = rs.getDouble("DonGiaNhap");
                int soLuong = rs.getInt("SoLuong");

                CTPhieuNhapDTO pn = new CTPhieuNhapDTO(MaPN, MaSP, donGiaNhap, soLuong);
                ketQua.add(pn);
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public boolean Them(CTPhieuNhapDTO ctpn) {
        int ketQua = 0;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "INSERT INTO ChiTietPhieuNhap(MaPN, MaSP, DonGiaNhap, SoLuong) VALUES ( ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, ctpn.getMaPN());
            pst.setInt(2, ctpn.getMaSP());
            pst.setDouble(3, ctpn.getDonGia());
            pst.setInt(4, ctpn.getSoLuong());

            ketQua = pst.executeUpdate();

            ConnectDB.closeConnection(conn);
            if (ketQua != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSP(int MaSP, int SoLuong, double DonGia) {
        boolean rowUpdate = false;
        int currentQuantity = getCurrentQuantity(MaSP);
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE SanPham SET SoLuong=?, DonGia=? WHERE MaSP=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setInt(1, SoLuong);
            pst.setDouble(2, DonGia);
            pst.setInt(3, MaSP);
            rowUpdate = pst.executeUpdate() > 0;
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdate;
    }

    public int getCurrentQuantity(int MaSP) {
        int SoLuong = 0;
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT SoLuong FROM SanPham where MaSP = ?";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, MaSP);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                SoLuong = rs.getInt("SoLuong");
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return SoLuong;
    }
    
    public boolean XoaCTPhieuNhap(int MaPN){
        boolean rowUpdate = false;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "DELETE FROM ChiTietPhieuNhap  WHERE MaPN=? AND TinhTrang=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, MaPN);
            pst.setBoolean(2, false);
            rowUpdate = pst.executeUpdate() > 0;
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdate;
    }

    public boolean updateCTPhieuNhap(int MaPN, int MaSP, int SoLuongMoi, double DonGiaMoi) {
        boolean rowUpdate = false;
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "UPDATE ChiTietPhieuNhap SET SoLuong=?, DonGiaNhap=?, TinhTrang=? WHERE MaSP=? AND MaPN=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, SoLuongMoi);
            pst.setDouble(2, DonGiaMoi);
            pst.setBoolean(3, true);
            pst.setInt(4, MaSP);
            pst.setInt(5, MaPN);
            rowUpdate = pst.executeUpdate() > 0;
            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdate;
    }
}
