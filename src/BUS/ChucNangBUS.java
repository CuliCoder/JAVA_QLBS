/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTPhanQuyenDAO;
import DAO.ChucNangDAO;
import DTO.ChucNangDTO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author NGOC THUC
 */
public class ChucNangBUS {

    ChucNangDAO cnDAO = new ChucNangDAO();
    CTPhanQuyenBUS ctqBUS = new CTPhanQuyenBUS();

    public void createTableRole(DefaultTableModel modelPhieuNhap) {
        ArrayList<ChucNangDTO> listChucNang = selectAll();
        modelPhieuNhap.setRowCount(0);
        int STT = 1;
        for (ChucNangDTO cn : listChucNang) {
            int MaCN = cn.getMaCN();
            String MaCNStr = String.format("CN%02d", MaCN);
            String TenCN = cn.getTenCN();
            Object[] row = {STT++, MaCNStr, TenCN};
            modelPhieuNhap.addRow(row);
        }
    }
    
    public DefaultTableModel createTableCTQ(DefaultTableModel model) {
        ArrayList<ChucNangDTO> listChucNang = selectStateRole();
        model.setRowCount(0);
        for (ChucNangDTO cn : listChucNang) {
            int MaCN = cn.getMaCN();
            String MaCNStr = String.format("CN%02d", MaCN);
            String TenCN = cn.getTenCN();
            if(cn.isThem()){
                Object[] row = {MaCNStr, TenCN, "Thêm", false};
                model.addRow(row);
            }
            if(cn.isSua()){
                Object[] row = {MaCNStr, TenCN, "Sửa", false};
                model.addRow(row);
            }
            if(cn.isXoa()){
                Object[] row = {MaCNStr, TenCN, "Xóa", false};
                model.addRow(row);
            }
            if(cn.isTruyCap()){
                Object[] row = {MaCNStr, TenCN, "Truy cập", false};
                model.addRow(row);
            }
        }
        
        return model;
    }

    public ArrayList<ChucNangDTO> selectAll() {
        return cnDAO.selectAll();
    }
    
    public ArrayList<ChucNangDTO> selectStateRole() {
        return cnDAO.selectStateRole();
    }
    
    public int getCurrentID(){
        return cnDAO.getCurrentID();
    }
    
    public ArrayList<ChucNangDTO> selectAllData() {
        return cnDAO.selectAllData();
    }
    
    public ArrayList<String> getListAction(int MaCN) {
        ArrayList<String> listAction = new ArrayList<>();
        ArrayList<ChucNangDTO> listCN = selectStateRole();
        String action = "";
        for(ChucNangDTO cn : listCN) {
            if(cn.getMaCN() == MaCN) {
                listAction.add(action = cn.isThem() ? "Thêm" : "");
                listAction.add(action = cn.isSua()? "Sửa" : "");
                listAction.add(action = cn.isXoa()? "Xóa" : "");
                listAction.add(action = cn.isTruyCap()? "Truy Cập" : "");
                break;
            }
        }     
        return listAction;
    }
    
    public boolean ThemChucNang(ChucNangDTO cnDTO) {
        boolean res = false;
        res = cnDAO.Them(cnDTO);
        return res;
    }
    
    public boolean SuaChucNang(ChucNangDTO cnDTO){
        boolean res = false;
        res = cnDAO.Sua(cnDTO);
        return res;
    }
    
    public boolean XoaChucNang(int MaCN) {
        boolean res = false;
        res = cnDAO.Xoa(MaCN);
        if(res) {
            ctqBUS.XoaByMaCN(MaCN);
        }
        return res;
    }
    
    public String getNameByID(int ID) {
        return cnDAO.getNameByID(ID);
    }
}
