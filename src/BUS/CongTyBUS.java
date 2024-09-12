/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CongTyDAO;
import DTO.CongTyDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author NGOC THUC
 */
public class CongTyBUS {

    CongTyDAO ctyDao = new CongTyDAO();

    public static ArrayList<CongTyDTO> selectAll() {
        return CongTyDAO.selectAll();
    }
    public ArrayList<CongTyDTO> searchCongTy(String tukhoa) {
        return ctyDao.searchCongTy(tukhoa);
    }
    public CongTyDTO getCongTyById(int id){
        return ctyDao.getCongTyById(id);
    }
    public boolean insertCongTy(CongTyDTO cty) {
        int check = ctyDao.addCongTy(cty);
        if (check != -1) {
            JOptionPane.showMessageDialog(null, "Thêm Công ty thành công");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Thêm Công ty thất bại");
            return false;
        }
    }
    public boolean updateCongTy(CongTyDTO cty) {
        int check = ctyDao.updateCongTy(cty);
        if (check != -1) {
            JOptionPane.showMessageDialog(null, "Sửa Công ty thành công");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Sửa Công ty thất bại");
            return false;
        }
    }
    public boolean deleteCongTy(int idcty) {
        int check = ctyDao.deleteCongTy(idcty);
        if (check != -1) {
            JOptionPane.showMessageDialog(null, "Xoá Công ty thành công");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Xoá Công ty thất bại");
            return false;
        }
    }
    public int selectLastId(){
        return ctyDao.selectLastID();
    }
}
