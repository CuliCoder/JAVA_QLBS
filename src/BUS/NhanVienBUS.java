/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import DTO.NhanVienDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author NGOC THUC
 */
public class NhanVienBUS {

    public NhanVienBUS() {
    }
    
    NhanVienDAO nvDao=new NhanVienDAO();
    public ArrayList<NhanVienDTO> selectAll(){
        return nvDao.selectAll();
    }
    
    public ArrayList<NhanVienDTO> selectAllChuaTaoTK(){
        return nvDao.selectAllChuaTaoTK();
    }
    public NhanVienDTO selectNhanVienById(String id){
        return nvDao.selectNhanVienById(id);
    }
    public String getChucVu(String id){
        return nvDao.getChucVu(id);
    }
    public boolean deleteNhanVien(String idnv){
        int check = nvDao.deleteNhanVien(idnv);
        if (check != -1) {
            JOptionPane.showMessageDialog(null, "Xoá Nhân Viên thành công");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Xoá Nhân Viên thất bại");
            return false;
        }
    }
    public boolean updateNhanVien(NhanVienDTO nv){
        int check = nvDao.updateNhanVien(nv);
        if (check != -1) {
            JOptionPane.showMessageDialog(null, "Sửa nhân viên thành công");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Sửa nhân viên thất bại");
            return false;
        }
    }
    public boolean insertNhanVien(NhanVienDTO nv){
        int check = nvDao.addNhanVien(nv);
        if (check != -1) {
            JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại");
            return false;
        }
    }
    public String lastId(){
        return nvDao.selectLastID();
    }
}
