/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CTPhieuNhapDAO;
import DAO.PhieuNhapDAO;
import DAO.SanPhamDAO;
import DTO.CTPhieuNhapDTO;
import DTO.CongTyDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import GUI.NhapHangGUI;
import GUI.PhieuNhapGUI;
import Util.sharedFunction;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;

/**
 *
 * @author NGOC THUC
 */
public class PhieuNhapBUS {

    SanPhamDAO spDAO = new SanPhamDAO();
    PhieuNhapDAO pnDAO = new PhieuNhapDAO();
    TaiKhoanBUS tkBUS = new TaiKhoanBUS();
    CTPhieuNhapBUS ctpnBUS = new CTPhieuNhapBUS();
    CTPhieuNhapDAO ctpnDAO = new CTPhieuNhapDAO();

    public ArrayList<SanPhamDTO> loadSanPham() {
        return spDAO.selectAll();
    }

    public double getCurrentPrice(int maSP) {
        // Query database to get the current price of the product with maSP
        double price = 0.0;
        try {
            price = pnDAO.queryPriceByProductId(maSP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    public ArrayList<PhieuNhapDTO> findPhieuNhapByMaPN(int maPN) {
        return pnDAO.getLikeByID(maPN);
    }

    public void NhapHang(NhapHangGUI cartImport) {
        String nameNCC = cartImport.getCbCongTy().getSelectedItem().toString();
        int IDNCC = pnDAO.queryByNameSupplier(nameNCC);
        String TenTK = tkBUS.getCurrentAcc().getTenTK();
        int MaChiNhanh = tkBUS.getCurrentAcc().getMaChiNhanh();
        String NgayTao = cartImport.getTfNgaytao().getText();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = new java.util.Date();
        try {
            startDate = df.parse(NgayTao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date ngayLap = new Date(startDate.getTime());
        String total = cartImport.getTfTongtien().getText();
        long TongTien = (long) sharedFunction.parseMoneyString(total);
        PhieuNhapDTO pn = new PhieuNhapDTO(1, IDNCC + "", TenTK, TongTien, ngayLap, "1", MaChiNhanh);
        int res = pnDAO.Them(pn);

        if (res != -1) {
            // lấy chi tiết phiếu nhập
            ArrayList<CTPhieuNhapDTO> listSPNhap = getListCTPN(cartImport, res);
            boolean resCT = false, resSP = false;
            boolean success = false;
            int i = 0;
            for (CTPhieuNhapDTO ctpn : listSPNhap) {
                resCT = ctpnBUS.TaoCTPhieuNhap(ctpn);
                if (!resCT) {
                    cartImport.displayErrorMessage("lỗi nhập hàng " + ctpn.getMaSP());
                } else {
                    int currentQuantity = ctpnBUS.getCurrentQuantity(ctpn.getMaSP());
                    double currentPrice = getCurrentPrice(ctpn.getMaSP()); // Lấy giá bán hiện tại

                    // Lấy giá bán mới nhập từ bảng chi tiết
                    double newPrice = Double.parseDouble(cartImport.getTableChitiet().getModel().getValueAt(i++, 3).toString());

                    // So sánh giá hiện tại và giá mới, chọn giá cao hơn
                    double updatedPrice = Math.max(currentPrice, newPrice);

                    // Cập nhật số lượng và giá bán
                    int newQuantity = currentQuantity + ctpn.getSoLuong();
                    resSP = ctpnBUS.CapNhatSoLuong(ctpn.getMaSP(), newQuantity, updatedPrice);
                    if (resSP) {
                        success = true;
                    }
                }
            }
            if (success) {
                JOptionPane.showMessageDialog(cartImport, "Nhập hàng thành công!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

    public ArrayList<CTPhieuNhapDTO> getListCTPN(NhapHangGUI cartImport, int idPN) {
        ArrayList<CTPhieuNhapDTO> listID = new ArrayList<>();
//        int IDPN = Integer.parseInt(cartImport.getTfIDHoadon().getText().substring(2));
        int rows = cartImport.getTableChitiet().getModel().getRowCount();
        for (int i = 0; i < rows; i++) {
            int ID = Integer.parseInt(cartImport.getTableChitiet().getModel().getValueAt(i, 0).toString().substring(2));
            int quantity = Integer.parseInt(cartImport.getTableChitiet().getModel().getValueAt(i, 1).toString());
            double ThanhTien = Double.parseDouble(cartImport.getTableChitiet().getModel().getValueAt(i, 2).toString());
            double DonGiaNhap = (double) ThanhTien / quantity;
            CTPhieuNhapDTO ctpn = new CTPhieuNhapDTO(idPN, ID, DonGiaNhap, quantity);
            listID.add(ctpn);
        }
        return listID;
    }

    public void queryListSupplier(NhapHangGUI cartImport) {
        ArrayList<CongTyDTO> listCty = pnDAO.querySupplier();

        for (CongTyDTO cty : listCty) {
            cartImport.getCbCongTy().addItem(cty.getTenNCC());
        }

    }

    public void createCart(NhapHangGUI cartImport) {
//        String IDNV = tkBUS.getCurrentAcc().getTenTK();
        String IDPN = "PN00" + (pnDAO.selectLastID() + 1);

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();

        // Định dạng ngày theo "dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);

        cartImport.getTfIDHoadon().setText(IDPN);
        if (tkBUS.getCurrentAcc() == null) {
            return;
        }
        String nameNV = tkBUS.selectNameStaff(tkBUS.getCurrentAcc().getTenTK());

        cartImport.getTfIDNhanvien().setText(nameNV); // sửa sau
        cartImport.getTfNgaytao().setText(formattedDate);

        cartImport.getTfIDHoadon().setFocusable(false);
        cartImport.getTfIDNhanvien().setFocusable(false);
        cartImport.getTfNgaytao().setFocusable(false);

        queryListSupplier(cartImport);
    }

    public void loadData(NhapHangGUI cartImport, int row_index) {
        int idSP = Integer.parseInt(cartImport.getTableSanPham().getValueAt(row_index, 1).toString().substring(2));
        SanPhamDTO sp = spDAO.getHinhAnhandNamXB(idSP);
        String nameImg = sp.getHinhAnh();
        ImageIcon imgPro = new ImageIcon(new ImageIcon(getClass().getResource("/Assets/IMG/" + nameImg)).getImage().getScaledInstance(cartImport.getLblHinhAnh().getWidth(), cartImport.getLblHinhAnh().getHeight(), Image.SCALE_DEFAULT));

        cartImport.getLblHinhAnh().setIcon(imgPro);
        cartImport.getLblHinhAnh().setText("");
        cartImport.getTfIDSanPham().setText(cartImport.getTableSanPham().getValueAt(row_index, 1).toString());
        cartImport.getTfTenSanpham().setText(cartImport.getTableSanPham().getValueAt(row_index, 2).toString());
        cartImport.getTfTenTacgia().setText(cartImport.getTableSanPham().getValueAt(row_index, 3).toString());
        cartImport.getTfTheloai().setText(cartImport.getTableSanPham().getValueAt(row_index, 4).toString());
        String donGiaStr = cartImport.getTableSanPham().getValueAt(row_index, 6).toString();

        // Kiểm tra và loại bỏ các ký tự không hợp lệ
        donGiaStr = donGiaStr.replaceAll("[^\\d.,-]", ""); // Loại bỏ ký tự không phải số, dấu phẩy, dấu chấm hoặc dấu âm

        // Nếu chuỗi sử dụng dấu phẩy cho thập phân (ví dụ 22,100), hãy thay thế dấu phẩy bằng dấu chấm
        if (donGiaStr.contains(",")) {
            donGiaStr = donGiaStr.replace(",", ".");
        }

        // Chuyển đổi chuỗi đã được làm sạch thành kiểu double
        double donGia = Double.parseDouble(donGiaStr);
        double donGiaNhap = pnDAO.queryImportPriceByProductId(idSP);
        cartImport.getTfDongia().setText(String.format("%.0f", donGiaNhap));
        cartImport.getTfSoluong().setText("");
        if (donGiaNhap > 0) {
            double phanTram = (donGia / donGiaNhap) * 100000;
            cartImport.getTfPhanTram().setText(String.format("%.0f", phanTram % 100)); // Giới hạn 2 chữ số thập phân
        } else {
            cartImport.getTfPhanTram().setText("N/A"); // Đặt "N/A" nếu giá nhập bằng 0
        }
        cartImport.getTfPhanTram().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cartImport.getTfPhanTram().getText().equals("N/A")) {
                    cartImport.getTfPhanTram().setText(""); // Xóa chữ "N/A"
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (cartImport.getTfPhanTram().getText().trim().isEmpty()) {
                    cartImport.getTfPhanTram().setText("N/A"); // Đặt lại "N/A" nếu người dùng không nhập gì
                }
            }
        });
        cartImport.getTfSoluong().requestFocus();
    }

    public int GetQuantity(NhapHangGUI cartImport, int row_index) {
        String quantity = cartImport.getTableSanPham().getValueAt(row_index, 5).toString();

        // Convert the quantity to an integer
        int quantityNum = Integer.parseInt(quantity);

        // Return the integer quantity
        return quantityNum;
    }

    public void loadDataFromCart(NhapHangGUI cartImport, int row_index) {
        String idProduct = cartImport.getTableChitiet().getValueAt(row_index, 0).toString();
//        String nameProduct = cartImport.getTableChitiet().getValueAt(row_index, 1).toString();
        int newQuantity = Integer.parseInt(cartImport.getTableChitiet().getValueAt(row_index, 1).toString());
        double newPrice = (double) Double.parseDouble(cartImport.getTableChitiet().getValueAt(row_index, 2).toString()) / newQuantity * 1.0;
        double giaBan = Double.parseDouble(cartImport.getTableChitiet().getValueAt(row_index, 3).toString());
        double chechLechGia = (BigDecimal.valueOf(giaBan).subtract(BigDecimal.valueOf(newPrice))).doubleValue();
        double percent = (BigDecimal.valueOf(chechLechGia).divide(BigDecimal.valueOf(newPrice))).doubleValue();
        double resPercent = BigDecimal.valueOf(percent).multiply(BigDecimal.valueOf(100.0)).doubleValue();
        int rowCount = cartImport.getTableSanPham().getModel().getRowCount();
        for (int i = 0; i < rowCount; i++) {
            Object value = cartImport.getTableSanPham().getModel().getValueAt(i, 1);

            if (idProduct.equals(value)) {
                cartImport.getTfIDSanPham().setText(cartImport.getTableSanPham().getValueAt(i, 1).toString());
                cartImport.getTfTenSanpham().setText(cartImport.getTableSanPham().getValueAt(i, 2).toString());
                cartImport.getTfTenTacgia().setText(cartImport.getTableSanPham().getValueAt(i, 3).toString());
                cartImport.getTfTheloai().setText(cartImport.getTableSanPham().getValueAt(i, 4).toString());
                cartImport.getTfDongia().setText(newPrice + "");
                cartImport.getTfSoluong().setText(newQuantity + "");
                cartImport.getTfPhanTram().setText(resPercent + "");
                cartImport.getTfSoluong().requestFocus();
                return;
            }
        }
    }

    public void createTableImport(DefaultTableModel modelPhieuNhap) {
        ArrayList<PhieuNhapDTO> listPhieuNhap = loadPhieuNhap();
        modelPhieuNhap.setRowCount(0);
        int STT = 1;
        for (PhieuNhapDTO pn : listPhieuNhap) {
            int maPN = pn.getMaPN();
            String maPnText = String.format("PN%02d", maPN);
            Date ngayTao = pn.getNgayTao();
            double TongTien = pn.getTongTien();
            Object[] row = {STT++, maPnText, ngayTao, TongTien};
            modelPhieuNhap.addRow(row);
        }
    }

    public ArrayList<PhieuNhapDTO> loadPhieuNhap() {
        return pnDAO.selectAll();
    }

    public void createTableProduct(DefaultTableModel modelSanPham) {
        ArrayList<SanPhamDTO> listSanPham = loadSanPham();
        modelSanPham.setRowCount(0);
        int STT = 1;
        for (SanPhamDTO sanPham : listSanPham) {
            int maSP = sanPham.getMaSP();
            String maSPtext = String.format("SP%02d", maSP);
            String tenTL = sanPham.getTenTL();
            String tenSanPham = sanPham.getTenSP();
            String tenTacGia = sanPham.getTacGia();
            int soLuong = sanPham.getSoLuong();
            double donGia = sanPham.getDonGia();
            Object[] row = {STT++, maSPtext, tenSanPham, tenTacGia, tenTL, soLuong, donGia};
            modelSanPham.addRow(row);
        }
    }

    public void loadInfoImport(PhieuNhapGUI importInfo, int row) {
        importInfo.getModelImportDetai().setRowCount(0);
        String idPN = importInfo.getTablePhieunhap().getValueAt(row, 1).toString().substring(2);
        PhieuNhapDTO pnSelected = pnDAO.selectByID(idPN);
        String maPN = String.format("PN%02d", pnSelected.getMaPN());
        importInfo.getTfIDHoadon().setText(maPN);
        importInfo.getTfIDNhanVien().setText(pnSelected.getTenTK());
        importInfo.getTfNgayLap().setText(pnSelected.getNgayTao() + "");
        String nameNCC = pnDAO.selectSupplierByID(pnSelected.getMaNCC());
        importInfo.getTfCongTy().setText(nameNCC);
        importInfo.getTfTongTien().setText(sharedFunction.formatVND(pnSelected.getTongTien()));

        ArrayList<CTPhieuNhapDTO> listCTPN = ctpnBUS.selectByID(Integer.parseInt(idPN));
        for (CTPhieuNhapDTO ctpn : listCTPN) {
            int idSP = ctpn.getMaSP();
            String idSPText = String.format("SP%02d", idSP);
            String nameSP = pnDAO.getNameSPByID(idSP);
            int soLuong = ctpn.getSoLuong();
            long tongGiaNhap = (long) ctpn.getDonGia() * soLuong;

            Object[] rowSP = {idSPText, nameSP, soLuong, sharedFunction.formatVND(tongGiaNhap)};

            importInfo.getModelImportDetai().addRow(rowSP);
        }

    }

    public boolean XoaPhieuNhap(int MaPN) {
        ArrayList<CTPhieuNhapDTO> listctpn = ctpnDAO.selectByID(MaPN);
        for (CTPhieuNhapDTO ctpn : listctpn) {
            ctpnDAO.XoaSLCu(MaPN, ctpn.getMaSP());
        }
        return pnDAO.XoaPhieuNhap(MaPN);
    }

    public boolean DoiThanhTienPhieuNhap(int MaPN, double ThanhTien) {
        return pnDAO.ChangePriceProductById(MaPN, ThanhTien);
    }

}
