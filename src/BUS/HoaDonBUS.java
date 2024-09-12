/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.HoaDonDAO;
import DTO.HoaDonDTO;
import java.util.ArrayList;

/**
 *
 * @author NGOC THUC
 */
public class HoaDonBUS {

    private final HoaDonDAO hoaDonDAO;

    public HoaDonBUS() {
        hoaDonDAO = new HoaDonDAO();
    }

    public int getMaHoaDonMax() {
        return hoaDonDAO.getMaHoaDonMax();
    }

    public ArrayList<HoaDonDTO> getAll() {
        return hoaDonDAO.selectAll();
    }

    public ArrayList<HoaDonDTO> findHoaDonByMaHD(int maHD) {
        return hoaDonDAO.findHoaDonByMaHD(maHD);
    }

    public boolean luuHoaDon(HoaDonDTO hoaDon) {
        return hoaDonDAO.luuHoaDon(hoaDon);
    }
}
