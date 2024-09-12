/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTHoaDonDAO;
import DTO.CTHoaDonDTO;
import java.util.ArrayList;

/**
 *
 * @author NGOC THUC
 */
public class CTHoaDonBUS {

    private CTHoaDonDAO cthd;

    public CTHoaDonBUS() {
        cthd = new CTHoaDonDAO();
    }

    public ArrayList<CTHoaDonDTO> findHoaDonByMaHD(int MaHoaDon) {
        return cthd.findHoaDonByMaHD(MaHoaDon);
    }

    public boolean luuChiTietHoaDon(CTHoaDonDTO chiTietHoaDon) {
        return cthd.luuChiTietHoaDon(chiTietHoaDon);
    }
}
