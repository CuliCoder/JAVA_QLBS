/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.TheLoaiDAO;
import DTO.TheLoaiDTO;

import java.util.ArrayList;

/**
 *
 * @author NGOC THUC
 */
public class TheLoaiBUS {

    private final TheLoaiDAO theLoaiDAO;

    public TheLoaiBUS() {
        theLoaiDAO = new TheLoaiDAO(); // Khởi tạo lớp DAO
    }
    // Hàm load dữ liệu từ DAO và trả về một danh sách các thể loại

    public ArrayList<TheLoaiDTO> getAll() {
        return theLoaiDAO.selectAll();
    }

    public int getMaTheLoaiMax() {
        return theLoaiDAO.getMaTheLoaiMax();
    }

    public TheLoaiDTO findTheLoaiByMaTL(String maTL) {
        return theLoaiDAO.findTheLoaiByMaTL(maTL);
    }

    public ArrayList<TheLoaiDTO> findTheLoaiByMaTL_or_TenTL(String tenTL) {
        return theLoaiDAO.findTheLoaiByMaTL_or_TenTL(tenTL);
    }

    public boolean deleteTheLoaiByMaTL(int maTL) {
        return theLoaiDAO.deleteTheLoaiByMaTL(maTL);
    }

    public boolean addTheLoai(TheLoaiDTO tl) {
        String tenTheLoai = tl.getTenTL();
        if (theLoaiDAO.checkTenTheLoaiDaTonTai(tenTheLoai)) {
            // Thể loại đã tồn tại và tình trạng bằng true, hiển thị thông báo lỗi
            return false;
        }
        return theLoaiDAO.addTheLoai(tl);
    }

    public boolean updateTheLoai(TheLoaiDTO tl) {
        String tenTheLoai = tl.getTenTL();
        if (theLoaiDAO.checkTenTheLoaiDaTonTai(tenTheLoai)) {
            // Thể loại đã tồn tại và tình trạng bằng true, hiển thị thông báo lỗi
            return false;
        }
        return theLoaiDAO.Update(tl);
    }

    public int getMaTLbyTenTL(String tenTL) {
        return theLoaiDAO.getMaTLbyTenTL(tenTL);
    }
}
