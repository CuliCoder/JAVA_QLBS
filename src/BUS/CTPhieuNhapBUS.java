/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTPhieuNhapDAO;
import DTO.CTPhieuNhapDTO;
import GUI.ChiTietPhieuNhap;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author NGOC THUC
 */
public class CTPhieuNhapBUS {

    CTPhieuNhapDAO ctpnDAO = new CTPhieuNhapDAO();

    public boolean TaoCTPhieuNhap(CTPhieuNhapDTO ctpn) {
        return ctpnDAO.Them(ctpn);
    }

    public int getCurrentQuantity(int MaSP) {
        return ctpnDAO.getCurrentQuantity(MaSP);
    }

    public boolean CapNhatSoLuong(int MaSP, int SoLuong, double DonGia) {
        return ctpnDAO.updateSP(MaSP, SoLuong, DonGia);
    }

    public ArrayList<CTPhieuNhapDTO> selectByID(int MaPN) {
        return ctpnDAO.selectByID(MaPN);
    }

    public void XoaSLCu(int MaPN) {
        ArrayList<CTPhieuNhapDTO> listctpn = ctpnDAO.selectByID(MaPN);
        for (CTPhieuNhapDTO ctpn : listctpn) {
            ctpnDAO.XoaSLCu(MaPN, ctpn.getMaSP());
        }
    }
    
    public boolean XoaCTPhieuNhap(int MaPN) {
        return ctpnDAO.XoaCTPhieuNhap(MaPN);
    }

    public boolean SuaPhieuNhap(int MaPN, int MaSP, int SoLuongMoi, double DonGiaMoi) {
        boolean sua = SuaCTPhieuNhap(MaPN, MaSP, SoLuongMoi, DonGiaMoi);

        boolean update = false;
        if (sua) {
            int newQuantity = getCurrentQuantity(MaSP) + SoLuongMoi;
            update = CapNhatSoLuong(MaSP, newQuantity, DonGiaMoi);
            if (update) {
                
            }
        } else {
            System.out.println("Xoa Khong thanh cong Dong 39 CTPhieuNhapBUS");
        }
        return update;
    }

    public boolean SuaCTPhieuNhap(int MaPN, int MaSP, int SoLuongMoi, double DonGiaMoi) {
        return ctpnDAO.updateCTPhieuNhap(MaPN, MaSP, SoLuongMoi, DonGiaMoi);
    }

    public boolean DoiTrangThai(int MaPN) {
        return ctpnDAO.DoiTrangThai(MaPN);
    }
}
