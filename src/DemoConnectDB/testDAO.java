/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DemoConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Connection.ConnectDB;


/**
 *
 * @author NGOC THUC
 */
public class testDAO {

    
    public ArrayList<testDTO> selectAll() {
        ArrayList<testDTO> ketQua = new ArrayList<>();
        try {
            Connection c = ConnectDB.getConnection();
            String sql = "SELECT * FROM TheLoai";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maTL = rs.getString("MaTL");
                String tenTL = rs.getNString("TenTL");
                String tinhTrang = rs.getString("TinhTrang");

                testDTO a = new testDTO(tenTL, tinhTrang, maTL);
                ketQua.add(a);
            }
            ConnectDB.closeConnection(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
}
