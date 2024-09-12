/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhomQuyenDAO;
import DTO.NhomQuyenDTO;
import DTO.PhieuNhapDTO;
import GUI.ChiTietQuyen;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGOC THUC
 */
public class NhomQuyenBUS {
    
    NhomQuyenDAO nqDAO = new NhomQuyenDAO();
    CTPhanQuyenBUS ctpqBUS = new CTPhanQuyenBUS();
    public void createTablePer(DefaultTableModel modelPhieuNhap) {
        ArrayList<NhomQuyenDTO> listNhomQuyen = loadNhomQuyen();
        modelPhieuNhap.setRowCount(0);
        int STT = 1;
        for (NhomQuyenDTO nq : listNhomQuyen) {
            int MaNQ = nq.getMaNQ();
            String MaNQStr = String.format("NQ%02d", MaNQ);
            String TenNQ = nq.getTenNQ();
            String MoTa = nq.getMoTa();
            Object[] row = {STT++, MaNQStr, TenNQ, MoTa};
            modelPhieuNhap.addRow(row);
        }
    }
    
    public ArrayList<NhomQuyenDTO> loadNhomQuyen(){
        return nqDAO.selectAll();
    }
    
    public int getCurrentID() {
        return nqDAO.getCurrentID();
    }
    
    public boolean taoNhomQuyenMoi(ChiTietQuyen ctq) {
        String tenNQ = ctq.getTxtName().getText();
        String MoTa = ctq.getTxtDes().getText();
        
        NhomQuyenDTO nqNew = new NhomQuyenDTO(0, tenNQ, MoTa, "1");
        return nqDAO.Them(nqNew);
    }
    
    public boolean suaNhomQuyen(ChiTietQuyen ctq) {
        int idNQ = Integer.parseInt(ctq.getTxtID().getText().substring(2));
        String tenNQ = ctq.getTxtName().getText();
        String MoTa = ctq.getTxtDes().getText();
        
        NhomQuyenDTO nqNew = new NhomQuyenDTO(idNQ, tenNQ, MoTa, "1");
        return nqDAO.Sua(nqNew);
    }
    
    public boolean xoaNhomQuyen(int MaNQ){
        boolean res = false;
        boolean delete = ctpqBUS.XoaCTQuyen(MaNQ);
        if(delete) {
            res = nqDAO.Xoa(MaNQ);
        }
        return res;
    }
}
