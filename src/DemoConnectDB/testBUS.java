/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DemoConnectDB;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGOC THUC
 */
public class testBUS {

    testDAO testDAO = new testDAO();
    
    public ArrayList<testDTO> selectALL() {
        return testDAO.selectAll();
    }

    public static void main(String[] args) {

    }
}
