/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhomQuyenDAO;
import DAO.TaiKhoanDAO;
import DTO.CTQuyenDTO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.DangNhapGUI;
import GUI.MainFrameGUI;
import GUI.MatKhauCu;
import GUI.MatKhauMoiGUI;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NGOC THUC
 */
public class TaiKhoanBUS {

    TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    NhomQuyenDAO nqDAO = new NhomQuyenDAO();
    private String tenTK, matKhau;
    private static TaiKhoanDTO currentAcc;

    public static ArrayList<TaiKhoanDTO> selectAll() {
        return TaiKhoanDAO.selectAll();
    }

    public static TaiKhoanDTO getCurrentAcc() {
        return currentAcc;
    }

    public static void createTableAccount(DefaultTableModel modelTaiKhoan) {
        ArrayList<TaiKhoanDTO> listAccount = selectAll();
        modelTaiKhoan.setRowCount(0);
        int STT = 1;
        for (TaiKhoanDTO acc : listAccount) {
            String MaTK = "TK" + acc.getMaTK();
            String TenTK = acc.getTenTK();
            String MatKhau = acc.getMatKhau();
            String TenNQ = NhomQuyenDAO.selectNameByID(acc.getMaQuyen());
            String NgayTao = acc.getNgayTao().toString();
            Object[] row = {STT++, MaTK, TenTK, MatKhau, TenNQ, NgayTao};
            modelTaiKhoan.addRow(row);
        }
    }

    public TaiKhoanDTO selectByUsername(String username) {
        return tkDAO.selectByUsername(username);
    }

    public TaiKhoanDTO selectById(int id) {
        return tkDAO.selectById(id);
    }

    boolean comparePassword(String currentPass, String inputPass) {
        return currentPass.equals(inputPass);
    }

    public void PhanQuyen(ArrayList<CTQuyenDTO> listPer, MainFrameGUI layout) {
        layout.getLblBanHang().setVisible(false);
        layout.getLblCongTy().setVisible(false);
        layout.getLblHoaDon().setVisible(false);
        layout.getLblNhanVien().setVisible(false);
        layout.getLblNhapHang().setVisible(false);
        layout.getLblPhanQuyen().setVisible(false);
        layout.getLblPhieuNhap().setVisible(false);
        layout.getLblSanPham().setVisible(false);
        layout.getLblTaiKhoan().setVisible(false);
        layout.getLblThongKe().setVisible(false);
        layout.setInittialButtonState();
        layout.getChucNang(listPer);
    }

    public String selectNameStaff(String TenTK) {
        return tkDAO.selectStaffByID(TenTK);
    }

    public void displayName(TaiKhoanDTO staff, MainFrameGUI layout) {
        String tenNV = selectNameStaff(staff.getTenTK());
        String chucVu = tkDAO.selectRoleByID(staff.getMaQuyen());

        layout.getLblName().setText(tenNV);
        layout.getLblRole().setText(chucVu);
    }

    public void DangNhap(DangNhapGUI acc) {
        tenTK = acc.getTxtUsername().getText();
        matKhau = acc.getTxtPassword().getText();

        currentAcc = tkDAO.selectByUsername(tenTK);
        if (currentAcc == null) {
            JOptionPane.showMessageDialog(null, "Sai tên đăng nhập");
            return;
        } else {
            String trangThai = currentAcc.getTinhTrang();
            if (trangThai == "0") {
                JOptionPane.showMessageDialog(null, "Tài khoản bị vô hiệu hóa ");
                return;
            } else {
                String currentPass = currentAcc.getMatKhau();
                if (!comparePassword(currentPass, matKhau)) {
                    JOptionPane.showMessageDialog(null, "Sai mật khẩu");
                    return;
                } else {
                    String maQuyen = currentAcc.getMaQuyen();
                    int MaNQ = Integer.parseInt(maQuyen);
                    ArrayList<CTQuyenDTO> listPer = new CTPhanQuyenBUS().getPerByRole(MaNQ);
                    MainFrameGUI layout = new MainFrameGUI();
                    layout.setVisible(true);
                    displayName(currentAcc, layout);
                    PhanQuyen(listPer, layout);
                    acc.setVisible(false);
                 
                }
            }
        }
    }

    public void checkOldPass(MatKhauCu oldPass) {
        String txtOldPass = new String(oldPass.getTxtPassword().getPassword());
        if(!txtOldPass.isEmpty()){
               boolean checkPass = currentAcc.getMatKhau().equals(txtOldPass);
        if (checkPass) {
            MatKhauMoiGUI newPass = new MatKhauMoiGUI();
            newPass.setVisible(true);
            oldPass.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Mật khẩu không chính xác!");
        }
        }else{
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu");
        }
     
    }

    public void DoiMatKhau(MatKhauMoiGUI newPass) {
        String txtNewPass = newPass.getTxtNewPass().getText();
        String txtConfirmPass = newPass.getTxtConfirmPass().getText();
        if (!txtNewPass.equals(txtConfirmPass)) {
            JOptionPane.showMessageDialog(null, "Mật khẩu xác nhận không trùng!");
        } else {
            boolean changePass = tkDAO.changePassword(txtNewPass, currentAcc.getTenTK());
            if (!changePass) {
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại!");
            } else {
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
                newPass.dispose();
            }
        }
    }

    public boolean XoaTaiKhoan(int ID) {
        return tkDAO.Xoa(ID) != 0;
    }

    public boolean insertTaiKhoan(TaiKhoanDTO tk) {
        int check = tkDAO.Them(tk);
        if (check > 0) {
            JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Thêm tài khoản thất bại");
            return false;
        }
    }

    public boolean updateTaiKhoan(TaiKhoanDTO tk) {
        int check = tkDAO.Sua(tk);
        if (check > 0) {
            JOptionPane.showMessageDialog(null, "Sửa tài khoản thành công");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Sửa tài khoản thất bại");
            return false;
        }
    }
    public int selectLastId(){
        return tkDAO.selectLastID();
    }
}
