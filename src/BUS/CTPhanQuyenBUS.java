/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTPhanQuyenDAO;
import DTO.CTQuyenDTO;
import java.util.ArrayList;

/**
 *
 * @author NGOC THUC
 */
public class CTPhanQuyenBUS {
    CTPhanQuyenDAO ctpqDAO = new CTPhanQuyenDAO();
    
     public ArrayList<CTQuyenDTO> getListCTQ(int MaNQ) {
        return ctpqDAO.selectByID(MaNQ);
    }
    
    public ArrayList<CTQuyenDTO> getPerByRole(int MaQuyen) {
        return ctpqDAO.getPerByRole(MaQuyen);
    }
    
    public boolean TaoCTQuyen(ArrayList<CTQuyenDTO> listCTQ) {
        boolean res = false;
        for(CTQuyenDTO ctq : listCTQ) {
            res = ctpqDAO.Them(ctq);
        }
        return res;
    }
    
    public boolean SuaCTQuyen(ArrayList<CTQuyenDTO> listCTQ, int MaNQ) {
        boolean res = false;
        
        boolean deleteCTQ = XoaCTQuyen(MaNQ);
        
        if(deleteCTQ){
            res = TaoCTQuyen(listCTQ);
        }
        return res;
    }
    
    public boolean XoaCTQuyen(int MaNQ) {
        return ctpqDAO.Xoa(MaNQ);
    }
    
    public boolean XoaByMaCN(int MaCN) {
        return ctpqDAO.XoaByMaCN(MaCN);
    }
}
