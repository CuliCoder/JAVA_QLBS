/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.CTHoaDonBUS;
import BUS.HoaDonBUS;
import BUS.SanPhamBUS;
import BUS.TaiKhoanBUS;
import DTO.CTHoaDonDTO;
import DTO.HoaDonDTO;
import DTO.SanPhamDTO;
import Util.sharedFunction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author NGOC THUC
 */
public final class BanHangGUI extends javax.swing.JPanel {

    /**
     * Creates new form BanHangGUI
     */
    private static JTable tableSanPham;
    private static DefaultTableModel modelSanPham;
    private static JTable tableHoaDon;
    private static DefaultTableModel modelHoaDon;

    SanPhamBUS sanPhamBUS = new SanPhamBUS();
    SanPhamGUI sanPhamGUI = new SanPhamGUI();
    HoaDonBUS hoaDonBUS = new HoaDonBUS();
    CTHoaDonBUS ctBUS = new CTHoaDonBUS();

    public BanHangGUI() {
        initComponents();
        setTextAndDate();
        createTable();
        selectRow();
        sharedFunction.addPlaceholder(txtTimKiem, "Tìm kiếm theo mã hoặc tên sản phẩm");
    }

    public void selectRow() {
        tableSanPham.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                loadDataThongTinChiTiet(tableSanPham, 1);
            }
        });
        tableHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableHoaDon.rowAtPoint(e.getPoint());
                int column = tableHoaDon.columnAtPoint(e.getPoint());
                if (column == 4) {
                    loadDataThongTinChiTiet(tableHoaDon, 0);
                    txtSoluong.requestFocus();
                } else if (column == 5) {
                    modelHoaDon.removeRow(row);
                    TongTien();
                }
            }
        });
    }

    public void createTable() {
        tableSanPham = createTableSanPham();
        ArrayList<SanPhamDTO> listSanPham = sanPhamBUS.getAllSanPham();
        sanPhamGUI.loadTableSanPham(listSanPham, modelSanPham);

        tableSanPham.setPreferredScrollableViewportSize(PanelTable1.getPreferredSize());
        JScrollPane scrollPaneSanPham = new JScrollPane(tableSanPham);
        MatteBorder matteBorder = new MatteBorder(0, 1, 1, 1, new Color(164, 191, 226));
        scrollPaneSanPham.setBorder(matteBorder);
        PanelTable1.setLayout(new BorderLayout());
        PanelTable1.add(scrollPaneSanPham);

        tableHoaDon = createTableHoaDon();
        tableHoaDon.setPreferredScrollableViewportSize(PanelTable2.getPreferredSize());
        JScrollPane scrollPaneHoaDon = new JScrollPane(tableHoaDon);
        scrollPaneHoaDon.setBorder(matteBorder);
        PanelTable2.setLayout(new BorderLayout());
        PanelTable2.add(scrollPaneHoaDon);
    }

    public void loadDataThongTinChiTiet(JTable table, int positionMaSP) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy mã sản phẩm từ bảng dựa trên hàng được chọn
            String maSP = (String) table.getValueAt(selectedRow, positionMaSP);
            int maSPnumber = Integer.parseInt(maSP.substring(2));
            SanPhamDTO sp = sanPhamBUS.getSPByMaSP(maSPnumber);

            // Lấy thông tin sản phẩm
            String tenSP = sp.getTenSP();
            String theLoai = sp.getTenTL();
            String tacGia = sp.getTacGia();
            int soLuong = sp.getSoLuong();
            double donGia = sp.getDonGia();
            String formatDonGia = sharedFunction.formatVND(donGia);
            String hinhAnh = sp.getHinhAnh();

            // Hiển thị thông tin sản phẩm trên giao diện
            txtIDSanpham.setText(maSP);
            txtTenSanpham.setText(tenSP);
            txtTenTacgia.setText(tacGia);
            txtTheloai.setText(theLoai);
            txtSoluong.setFocusable(true);
            txtSoluong.setText("");
            txtSoluong.requestFocus();
            txtDonGia.setText(formatDonGia);

            // Hiển thị hình ảnh sản phẩm
            if (hinhAnh != null) {
                String imagePath = "./../Assets/IMG/" + hinhAnh;
                ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
                Image img = imageIcon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(img);
                lblImage.setIcon(scaledImageIcon);
            }

            // Kiểm tra số lượng sản phẩm trong bảng sản phẩm
            if (soLuong <= 0) {
                sharedFunction.displayErrorMessage("Sản phẩm này đã hết hàng.");
                // Vô hiệu hóa nút "Chọn" 
            }
        }
    }

    public void loadDataGioHang() {
        String idSP = txtIDSanpham.getText();
        String tenSP = txtTenSanpham.getText();
        String soLuong = txtSoluong.getText();
        String donGia = txtDonGia.getText();
        Object[] rowData = {idSP, tenSP, soLuong, donGia, "", ""}; // Thêm biểu tượng vào mảng dữ liệu
        modelHoaDon.addRow(rowData);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        PanelTable1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtIDHoadon = new javax.swing.JTextField();
        txtIDNhanvien = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        tfTongtien = new javax.swing.JTextField();
        tfTienkhach = new javax.swing.JTextField();
        tfTienthoi = new javax.swing.JTextField();
        btnHuydon = new Components.ButtonRadius();
        btnThanhtoan = new Components.ButtonRadius();
        jLabel7 = new javax.swing.JLabel();
        PanelTable2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnChon = new Components.ButtonRadius();
        lblImage = new javax.swing.JLabel();
        txtIDSanpham = new javax.swing.JTextField();
        txtTenSanpham = new javax.swing.JTextField();
        txtTenTacgia = new javax.swing.JTextField();
        txtTheloai = new javax.swing.JTextField();
        txtSoluong = new javax.swing.JTextField();
        txtDonGia = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnTimkiem = new Components.ButtonRadius();
        btnLamMoi = new Components.ButtonRadius();
        timKiemTheo = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1020, 750));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1020, 720));

        PanelTable1.setBackground(new java.awt.Color(255, 255, 255));
        PanelTable1.setPreferredSize(new java.awt.Dimension(650, 300));

        javax.swing.GroupLayout PanelTable1Layout = new javax.swing.GroupLayout(PanelTable1);
        PanelTable1.setLayout(PanelTable1Layout);
        PanelTable1Layout.setHorizontalGroup(
            PanelTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        PanelTable1Layout.setVerticalGroup(
            PanelTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true));
        jPanel5.setPreferredSize(new java.awt.Dimension(320, 620));

        jPanel7.setBackground(new java.awt.Color(135, 172, 217));
        jPanel7.setMaximumSize(new java.awt.Dimension(32767, 40));

        jLabel2.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Giỏ hàng");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtIDHoadon.setEditable(false);
        txtIDHoadon.setBackground(new java.awt.Color(255, 255, 255));
        txtIDHoadon.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        txtIDHoadon.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "ID. Hóa đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        txtIDHoadon.setFocusable(false);
        txtIDHoadon.setMaximumSize(new java.awt.Dimension(140, 50));
        txtIDHoadon.setPreferredSize(new java.awt.Dimension(140, 50));

        txtIDNhanvien.setEditable(false);
        txtIDNhanvien.setBackground(new java.awt.Color(255, 255, 255));
        txtIDNhanvien.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        txtIDNhanvien.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "ID. Nhân viên", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        txtIDNhanvien.setFocusable(false);
        txtIDNhanvien.setMaximumSize(new java.awt.Dimension(2147483647, 50));
        txtIDNhanvien.setPreferredSize(new java.awt.Dimension(140, 50));

        txtNgayTao.setEditable(false);
        txtNgayTao.setBackground(new java.awt.Color(255, 255, 255));
        txtNgayTao.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        txtNgayTao.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Ngày lập hóa đơn", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        txtNgayTao.setFocusable(false);
        txtNgayTao.setMaximumSize(new java.awt.Dimension(300, 50));
        txtNgayTao.setPreferredSize(new java.awt.Dimension(300, 50));

        tfTongtien.setEditable(false);
        tfTongtien.setBackground(new java.awt.Color(255, 255, 255));
        tfTongtien.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfTongtien.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Tổng tiền", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfTongtien.setFocusable(false);
        tfTongtien.setMaximumSize(new java.awt.Dimension(2147483647, 50));
        tfTongtien.setMinimumSize(new java.awt.Dimension(64, 40));
        tfTongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTongtienActionPerformed(evt);
            }
        });

        tfTienkhach.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfTienkhach.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Tiền khách đưa", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfTienkhach.setFocusable(false);
        tfTienkhach.setMaximumSize(new java.awt.Dimension(2147483647, 50));
        tfTienkhach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTienkhachActionPerformed(evt);
            }
        });
        tfTienkhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfTienkhachKeyReleased(evt);
            }
        });

        tfTienthoi.setEditable(false);
        tfTienthoi.setBackground(new java.awt.Color(255, 255, 255));
        tfTienthoi.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfTienthoi.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Tiền thối lại", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfTienthoi.setFocusable(false);
        tfTienthoi.setMaximumSize(new java.awt.Dimension(2147483647, 50));

        btnHuydon.setForeground(new java.awt.Color(135, 172, 217));
        btnHuydon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/cancel.png"))); // NOI18N
        btnHuydon.setText("Hủy ");
        btnHuydon.setFocusPainted(false);
        btnHuydon.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 17)); // NOI18N
        btnHuydon.setIconTextGap(3);
        btnHuydon.setPreferredSize(new java.awt.Dimension(140, 40));
        btnHuydon.setRadius(40);
        btnHuydon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHuydon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHuydonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHuydonMouseExited(evt);
            }
        });
        btnHuydon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuydonActionPerformed(evt);
            }
        });

        btnThanhtoan.setForeground(new java.awt.Color(135, 172, 217));
        btnThanhtoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/dolar.png"))); // NOI18N
        btnThanhtoan.setText("Thanh toán");
        btnThanhtoan.setFocusPainted(false);
        btnThanhtoan.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 16)); // NOI18N
        btnThanhtoan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThanhtoan.setIconTextGap(0);
        btnThanhtoan.setMargin(new java.awt.Insets(2, -10, 3, 0));
        btnThanhtoan.setPreferredSize(new java.awt.Dimension(140, 40));
        btnThanhtoan.setRadius(40);
        btnThanhtoan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThanhtoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhtoanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThanhtoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThanhtoanMouseExited(evt);
            }
        });
        btnThanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhtoanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(135, 172, 217));
        jLabel7.setText("Chi tiết hóa đơn");

        PanelTable2.setBackground(new java.awt.Color(255, 255, 255));
        PanelTable2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(135, 172, 217)));

        javax.swing.GroupLayout PanelTable2Layout = new javax.swing.GroupLayout(PanelTable2);
        PanelTable2.setLayout(PanelTable2Layout);
        PanelTable2Layout.setHorizontalGroup(
            PanelTable2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelTable2Layout.setVerticalGroup(
            PanelTable2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(PanelTable2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfTienthoi, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNgayTao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(txtIDHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtIDNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(btnThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(btnHuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tfTienkhach, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(12, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTable2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(tfTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(tfTienkhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(tfTienthoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThanhtoan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuydon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true));
        jPanel6.setPreferredSize(new java.awt.Dimension(650, 300));

        jPanel8.setBackground(new java.awt.Color(135, 172, 217));
        jPanel8.setMaximumSize(new java.awt.Dimension(32767, 40));

        jLabel3.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Thông tin sản phẩm");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnChon.setForeground(new java.awt.Color(135, 172, 217));
        btnChon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/add.png"))); // NOI18N
        btnChon.setText("Chọn");
        btnChon.setFocusPainted(false);
        btnChon.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 16)); // NOI18N
        btnChon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnChon.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnChon.setMargin(new java.awt.Insets(2, 10, 3, 14));
        btnChon.setPreferredSize(new java.awt.Dimension(130, 40));
        btnChon.setRadius(40);
        btnChon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnChonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnChonMouseExited(evt);
            }
        });
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true));

        txtIDSanpham.setEditable(false);
        txtIDSanpham.setBackground(new java.awt.Color(255, 255, 255));
        txtIDSanpham.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        txtIDSanpham.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "ID. Sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        txtIDSanpham.setFocusable(false);
        txtIDSanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDSanphamjTextField1ActionPerformed(evt);
            }
        });

        txtTenSanpham.setEditable(false);
        txtTenSanpham.setBackground(new java.awt.Color(255, 255, 255));
        txtTenSanpham.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        txtTenSanpham.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Tên sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        txtTenSanpham.setFocusable(false);

        txtTenTacgia.setEditable(false);
        txtTenTacgia.setBackground(new java.awt.Color(255, 255, 255));
        txtTenTacgia.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        txtTenTacgia.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Tên tác giả", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        txtTenTacgia.setFocusable(false);

        txtTheloai.setEditable(false);
        txtTheloai.setBackground(new java.awt.Color(255, 255, 255));
        txtTheloai.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        txtTheloai.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Thể loại", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        txtTheloai.setFocusable(false);

        txtSoluong.setBackground(new java.awt.Color(255, 255, 255));
        txtSoluong.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        txtSoluong.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Số lượng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        txtSoluong.setFocusable(false);

        txtDonGia.setEditable(false);
        txtDonGia.setBackground(new java.awt.Color(255, 255, 255));
        txtDonGia.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        txtDonGia.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Đơn giá", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        txtDonGia.setFocusable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(txtTenTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTheloai, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(txtIDSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTenSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIDSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTenSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(80, 80, 80)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTheloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(253, 183, 58));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_40px/cart_1.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(253, 183, 58));
        jLabel6.setText("Bán hàng");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(243, 243, 244));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true));
        jPanel4.setMaximumSize(new java.awt.Dimension(660, 34));
        jPanel4.setPreferredSize(new java.awt.Dimension(660, 34));

        txtTimKiem.setBackground(new java.awt.Color(243, 243, 244));
        txtTimKiem.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 18)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(135, 172, 217));
        txtTimKiem.setText("Tìm kiếm sản phẩm");
        txtTimKiem.setBorder(null);
        txtTimKiem.setHighlighter(null);
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusGained(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(243, 243, 244));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 818, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnTimkiem.setBorder(null);
        btnTimkiem.setForeground(new java.awt.Color(135, 172, 217));
        btnTimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/search.png"))); // NOI18N
        btnTimkiem.setText("Tìm");
        btnTimkiem.setFocusPainted(false);
        btnTimkiem.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 17)); // NOI18N
        btnTimkiem.setMaximumSize(new java.awt.Dimension(100, 40));
        btnTimkiem.setPreferredSize(new java.awt.Dimension(100, 40));
        btnTimkiem.setRadius(40);
        btnTimkiem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTimkiemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTimkiemMouseExited(evt);
            }
        });
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        btnLamMoi.setBorder(null);
        btnLamMoi.setForeground(new java.awt.Color(135, 172, 217));
        btnLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/back.png"))); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setFocusPainted(false);
        btnLamMoi.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 17)); // NOI18N
        btnLamMoi.setIconTextGap(0);
        btnLamMoi.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnLamMoi.setMaximumSize(new java.awt.Dimension(100, 40));
        btnLamMoi.setPreferredSize(new java.awt.Dimension(100, 40));
        btnLamMoi.setRadius(40);
        btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLamMoiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLamMoiMouseExited(evt);
            }
        });
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        timKiemTheo.setBackground(new java.awt.Color(255, 255, 255));
        timKiemTheo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        timKiemTheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm kiếm theo", "Mã sản phẩm", "Tên sản phẩm", "Tác giả", "Thể loại", "Tìm kiếm nâng cao" }));
        timKiemTheo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        timKiemTheo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timKiemTheoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PanelTable1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(timKiemTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))))
                .addGap(0, 17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(timKiemTheo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 2, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(PanelTable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfTongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTongtienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTongtienActionPerformed

    private void btnThanhtoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhtoanMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_btnThanhtoanMouseClicked

    private void btnThanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhtoanActionPerformed
        // TODO add your handling code here:

        if (tfTienkhach.getText().isEmpty()) {
            sharedFunction.displayErrorMessage("Vui lòng nhập tiền khách đưa");
            tfTienkhach.requestFocus();
            return;
        }
        if (!sharedFunction.isPositiveInteger(tfTienkhach.getText())) {
            sharedFunction.displayErrorMessage("Tiền khách đưa không hợp lệ");
            tfTienkhach.requestFocus();
            return;
        }

        // Kiểm tra tiền thối có hợp lệ không
        boolean isChangeValid = isChangeValid();
        if (isChangeValid) {
            doPayment(); // Thực hiện thanh toán nếu tiền thối hợp lệ
            resetAll();  // Reset trạng thái sau khi thanh toán
        } else {
            sharedFunction.displayErrorMessage("Chưa thanh toán đủ tiền");
            tfTienkhach.requestFocus();
        }

    }//GEN-LAST:event_btnThanhtoanActionPerformed

    private boolean isChangeValid() {
        Color textColor = tfTienthoi.getForeground();
        return !textColor.equals(Color.RED);
    }

    // Đặt lại thông tin chi tiết sản phẩm
    private void resetProductDetails() {
        txtIDSanpham.setText("");
        txtTenSanpham.setText("");
        txtTenTacgia.setText("");
        txtTheloai.setText("");
        txtSoluong.setText("");
        txtDonGia.setText("");
        lblImage.setIcon(null);
        txtSoluong.setFocusable(false);
        if(!tfTongtien.getText().isEmpty()){
            tfTienkhach.setFocusable(true);
        }
        
    }

    // Reset toàn bộ trạng thái
    public void resetAll() {
        setTextAndDate();
        ArrayList<SanPhamDTO> listSanPham = sanPhamBUS.getAllSanPham();
        sanPhamGUI.loadTableSanPham(listSanPham, modelSanPham);
        resetProductDetails();
        modelHoaDon.setRowCount(0);
        tfTienkhach.setText("");
        tfTienthoi.setText("");
        tfTongtien.setText("");
    }

    // Thực hiện thanh toán
    private void doPayment() {
        if (tfTongtien.getText().isEmpty()) {
            sharedFunction.displayErrorMessage("Vui lòng chọn sản phẩm ");
        } else {
            String HDtext = txtIDHoadon.getText();
            int HDnumber = Integer.parseInt(HDtext.substring(2));
            String IDNhanVien = txtIDNhanvien.getText();
            String NgayTaotext = txtNgayTao.getText();
            Date NgayTao = sharedFunction.stringToDate(NgayTaotext);
            String TongTientext = tfTongtien.getText();
            Double TongTien = sharedFunction.parseMoneyString(TongTientext);
            String TienKhach = tfTienkhach.getText();
            String tienKhachVND = sharedFunction.formatVND(sharedFunction.stringToDouble(TienKhach));
            String TienThoi = tfTienthoi.getText();
            HoaDonDTO hoaDon = new HoaDonDTO(IDNhanVien, TongTien, NgayTao);
            boolean hoaDonLuuThanhCong = hoaDonBUS.luuHoaDon(hoaDon);
            boolean luuChiTiet = true;
            for (int i = 0; i < tableHoaDon.getRowCount(); i++) {
                String maSP = (String) modelHoaDon.getValueAt(i, 0);
                int maSPnumber = Integer.parseInt(maSP.substring(2));
                String soLuongText = (String) modelHoaDon.getValueAt(i, 2);
                int soLuong = Integer.parseInt(soLuongText);
                String DonGiatext = (String) modelHoaDon.getValueAt(i, 3);
                Double donGia = sharedFunction.parseMoneyString(DonGiatext) / soLuong;
                CTHoaDonDTO chiTietHoaDon = new CTHoaDonDTO(HDnumber, maSPnumber, donGia, soLuong);
                boolean luuChiTietHoaDon = ctBUS.luuChiTietHoaDon(chiTietHoaDon);
                if (!luuChiTietHoaDon) {
                    luuChiTiet = false;
                }
            }
            if (hoaDonLuuThanhCong && luuChiTiet) {
                // Thực hiện các thao tác sau khi thanh toán thành công
                updateProductQuantity();
                BillFormGUI bill = new BillFormGUI(HDtext, IDNhanVien, tienKhachVND, TienThoi, TongTientext, NgayTaotext, modelHoaDon);
                bill.setVisible(true);
            }
        }
    }

    private void setTextAndDate() {
        // Lấy mã hóa đơn
        int maHD = hoaDonBUS.getMaHoaDonMax() + 1;
        String maHDtext = sharedFunction.FormatID("HD", maHD);
        txtIDHoadon.setText(maHDtext);

        // Lấy ID nhân viên 
        String tenTK = TaiKhoanBUS.getCurrentAcc().getTenTK();
        txtIDNhanvien.setText( tenTK);
        // Lấy ngày hiện tại
//        Date currentDate = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        String formattedDate = dateFormat.format(currentDate);
//        txtNgayTao.setText(formattedDate);
    txtNgayTao.setText("22/12/2024");

    }

    // Cập nhật số lượng sản phẩm sau khi thanh toán
    private void updateProductQuantity() {
        for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
            String maSP = (String) modelHoaDon.getValueAt(i, 0);
            String soLuongStr = (String) modelHoaDon.getValueAt(i, 2);
            int soLuong = Integer.parseInt(soLuongStr);
            int maSPnumber = Integer.parseInt(maSP.substring(2));
            sanPhamBUS.updateProductQuantity(maSPnumber, soLuong);
        }
        // Load lại dữ liệu lên bảng sản phẩm
        ArrayList<SanPhamDTO> listSanPham = sanPhamBUS.getAllSanPham();
        sanPhamGUI.loadTableSanPham(listSanPham, modelSanPham);
    }
    private void txtIDSanphamjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDSanphamjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDSanphamjTextField1ActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        // TODO add your handling code here:
//        sanPhamGUI.findSanPhamByTenSP_or_MaSP(txtTimKiem.getText(), modelSanPham);
        int selectedIndex = timKiemTheo.getSelectedIndex();

        sanPhamGUI.findSanPham(txtTimKiem.getText(), selectedIndex, modelSanPham);
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        ArrayList<SanPhamDTO> listSanPham = sanPhamBUS.getAllSanPham();
        sanPhamGUI.loadTableSanPham(listSanPham, modelSanPham);
        resetProductDetails();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        // TODO add your handling code here:
        if (txtIDSanpham.getText().equals("")) {
            sharedFunction.displayErrorMessage("Vui lòng chọn sản phẩm");
            return;
        }

        String quantity = txtSoluong.getText();
        if (!isValidQuantity(quantity)) {
            return;
        }

        int desiredQuantity = Integer.parseInt(quantity);
        String idSP = txtIDSanpham.getText();

        if (isAvailableQuantity(idSP, desiredQuantity)) {
            updateOrAddProductToCart(idSP, desiredQuantity);
            TongTien();
        }
    }//GEN-LAST:event_btnChonActionPerformed
    // Kiểm tra tính hợp lệ của số lượng và hiển thị thông báo lỗi nếu không hợp lệ
    private boolean isValidQuantity(String quantity) {
        if (quantity.isEmpty()) {
            // Hiển thị thông báo lỗi nếu số lượng rỗng
            sharedFunction.displayErrorMessage("Vui lòng nhập số lượng");
            return false;
        }

        if (!sharedFunction.isPositiveInteger(quantity)) {
            // Hiển thị thông báo lỗi nếu số lượng không phải số nguyên dương
            sharedFunction.displayErrorMessage("Số lượng không hợp lệ");
            return false;
        }

        return true;
    }

// Kiểm tra xem số lượng mua có đủ hoặc không và hiển thị thông báo lỗi nếu không đủ
    private boolean isAvailableQuantity(String idSP, int desiredQuantity) {
        int availableQuantity = 0;
        for (int i = 0; i < modelSanPham.getRowCount(); i++) {
            String currentID = (String) modelSanPham.getValueAt(i, 1);
            if (idSP.equals(currentID)) {
                availableQuantity = (int) modelSanPham.getValueAt(i, 5); // Lấy số lượng của sản phẩm trong bảng sản phẩm
                break;
            }
        }

        if (desiredQuantity > availableQuantity) {
            // Hiển thị thông báo lỗi nếu không đủ số lượng sản phẩm
            sharedFunction.displayErrorMessage("Không đủ số lượng sản phẩm");
            return false;
        }

        return true;
    }

// Cập nhật hoặc thêm sản phẩm vào giỏ hàng
    private void updateOrAddProductToCart(String idSP, int desiredQuantity) {
        String tenSP = txtTenSanpham.getText();
        String donGiaText = txtDonGia.getText();
        double donGia = sharedFunction.parseMoneyString(donGiaText);
        double ThanhTien = desiredQuantity * donGia;
        String ThanhTienText = sharedFunction.formatVND(ThanhTien);

        boolean productExists = false;
        for (int i = 0; i < modelHoaDon.getRowCount(); i++) {
            String currentID = (String) modelHoaDon.getValueAt(i, 0);
            if (idSP.equals(currentID)) {
                // Sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng và thành tiền
                modelHoaDon.setValueAt(String.valueOf(desiredQuantity), i, 2);
                modelHoaDon.setValueAt(ThanhTienText, i, 3);
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            // Sản phẩm chưa tồn tại trong giỏ hàng, thêm mới vào bảng giỏ hàng
            Object[] rowData = {idSP, tenSP, String.valueOf(desiredQuantity), ThanhTienText, "", ""};
            modelHoaDon.addRow(rowData);
        }
    }

    private void TongTien() {
        // Tính tổng tiền từ dữ liệu trong bảng
        double tongTien = sharedFunction.calculateTotalPrice(modelHoaDon, 3);
        if (tongTien == 0) {
            tfTienkhach.setText("");
            tfTienthoi.setText("");
            tfTongtien.setText("");
            tfTienkhach.setFocusable(false);
        } else {
            // Cập nhật JTextField "Tổng tiền"
            String formatTongTien = sharedFunction.formatVND(tongTien);
            tfTongtien.setText(formatTongTien);
            // Tính số tiền thối lại nếu có
            updateTienThoiLai();
            tfTienkhach.setFocusable(true);
            tfTienkhach.requestFocus();
        }

    }

    private void updateTienThoiLai() {
        // Lấy số tiền khách đưa từ JTextField
        String tienKhachDuaStr = tfTienkhach.getText();

        if (!tienKhachDuaStr.isEmpty()) {
            if (sharedFunction.isPositiveInteger(tienKhachDuaStr)) {
                double tienKhachDua = Double.parseDouble(tienKhachDuaStr);
                double tongTien = sharedFunction.parseMoneyString(tfTongtien.getText());
                double tienThoiLai = tienKhachDua - tongTien;
                if (tienThoiLai >= 0) {
                    tfTienthoi.setForeground(Color.black);
                } else {
                    tfTienthoi.setForeground(Color.RED);
                }
                // Cập nhật giá trị vào JTextField Tiền thối lại
                tfTienthoi.setText(sharedFunction.formatVND(tienThoiLai));
            }else{
                sharedFunction.displayErrorMessage("Tiền khách đưa không hợp lệ");
            }
        }
    }
    private void btnHuydonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuydonActionPerformed
        // TODO add your handling code here:
        modelHoaDon.setRowCount(0);
        tfTienkhach.setText("");
        tfTienthoi.setText("");
        tfTongtien.setText("");
        resetProductDetails();
    }//GEN-LAST:event_btnHuydonActionPerformed

    private void btnLamMoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiMouseEntered
        // TODO add your handling code here:
        btnLamMoi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnLamMoiMouseEntered

    private void btnLamMoiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiMouseExited
        // TODO add your handling code here:
        btnLamMoi.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnLamMoiMouseExited

    private void btnChonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonMouseEntered
        // TODO add your handling code here:    
        btnChon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

    }//GEN-LAST:event_btnChonMouseEntered

    private void btnChonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChonMouseExited
        // TODO add your handling code here:
        btnChon.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnChonMouseExited

    private void btnThanhtoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhtoanMouseEntered
        // TODO add your handling code here:
        btnThanhtoan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnThanhtoanMouseEntered

    private void btnThanhtoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhtoanMouseExited
        // TODO add your handling code here:
        btnThanhtoan.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnThanhtoanMouseExited

    private void btnHuydonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuydonMouseEntered
        // TODO add your handling code here:
        btnHuydon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnHuydonMouseEntered

    private void btnHuydonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHuydonMouseExited
        // TODO add your handling code here:
        btnHuydon.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnHuydonMouseExited

    private void btnTimkiemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimkiemMouseEntered
        // TODO add your handling code here:
        btnTimkiem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnTimkiemMouseEntered

    private void btnTimkiemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimkiemMouseExited
        // TODO add your handling code here:
        btnTimkiem.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_btnTimkiemMouseExited

    private void tfTienkhachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTienkhachActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tfTienkhachActionPerformed

    private void tfTienkhachKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTienkhachKeyReleased
        // TODO add your handling code here:
        updateTienThoiLai();
    }//GEN-LAST:event_tfTienkhachKeyReleased

    private void timKiemTheoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timKiemTheoActionPerformed
        // TODO add your handling code here:
        int selectedIndex = timKiemTheo.getSelectedIndex();

        if (selectedIndex != 5) {
            sharedFunction.addPlaceholder(txtTimKiem, sanPhamGUI.getPlaceholderByIndex(selectedIndex));
        } else {

            LocAnd l = new LocAnd(2);
            sharedFunction.openNewFrame(l);
        }
    }//GEN-LAST:event_timKiemTheoActionPerformed

    private void txtTimKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemFocusGained
//    public static String FormatMaHD(int MaHD) {
//        return String.format("HD%02d", MaHD);
//    }

    public JTable createTableHoaDon() {
        // Tiêu đề của các cột
        String[] columnNames = {"ID", "Sản phẩm", "SL", "T.Tiền", "", ""};
        modelHoaDon = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 2 -> {
                        // Cột SL
                        return Integer.class; // Kiểu dữ liệu Integer
                    }
                    case 4 -> {
                        // Cột Update và Delete
                        return Icon.class; // Kiểu dữ liệu Icon
                    }
                    default -> {
                    }
                }
                return String.class; // Các cột khác có kiểu dữ liệu String
            }
        };
        modelHoaDon.setColumnIdentifiers(columnNames);

        // Tạo JTable với DefaultTableModel
        JTable table = new JTable(modelHoaDon);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(80); // Độ rộng cột 0
        columnModel.getColumn(1).setPreferredWidth(270); // Độ rộng cột 1
        columnModel.getColumn(2).setPreferredWidth(60); // Độ rộng cột 2
        columnModel.getColumn(3).setPreferredWidth(150); // Độ rộng cột 3
        columnModel.getColumn(4).setPreferredWidth(40); // Độ rộng cột 4
        columnModel.getColumn(5).setPreferredWidth(40); // Độ rộng cột 5

        NhapHangGUI.EditHeaderTable2(table);
        sharedFunction.EditTableContent(table);
        table.setBorder(null);
        Icon iconDelete = new ImageIcon(getClass().getResource("/Assets/icon_24px/cancel.png"));
        Icon iconUpdate = new ImageIcon(getClass().getResource("/Assets/icon_24px/fix.png"));
        table.getColumnModel().getColumn(5).setCellRenderer(new ImageRender(iconDelete));
        table.getColumnModel().getColumn(4).setCellRenderer(new ImageRender(iconUpdate));
        return table;
    }

    public JTable createTableSanPham() {
        // Tiêu đề của các cột
        String[] columnNames = {"STT", "ID Sản phẩm", "Tên sản phẩm", "Tên tác giả", "Thể loại", "Số lượng", "Đơn giá"};
        modelSanPham = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0 || columnIndex == 5) { // Cột STT và Số lượng
                    return Integer.class; // Kiểu dữ liệu Integer
                }
                return String.class; // Các cột khác có kiểu dữ liệu String
            }
        };
        modelSanPham.setColumnIdentifiers(columnNames);
        // Tạo JTable với DefaultTableModel
        JTable table = new JTable(modelSanPham);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60); // Độ rộng cột 0
        columnModel.getColumn(1).setPreferredWidth(180); // Độ rộng cột 1
        columnModel.getColumn(2).setPreferredWidth(240); // Độ rộng cột 2
        columnModel.getColumn(3).setPreferredWidth(240); // Độ rộng cột 3
        columnModel.getColumn(4).setPreferredWidth(120); // Độ rộng cột 4
        columnModel.getColumn(5).setPreferredWidth(120); // Độ rộng cột 5
        columnModel.getColumn(6).setPreferredWidth(120); // Độ rộng cột 6

        sharedFunction.EditHeaderTable(table);
        sharedFunction.EditTableContent(table);
        return table;
    }

    private void addPlaceholderStyle(JTextField textField, String name) {
        Font customFont = new Font("Tahoma", Font.BOLD, 16);
        textField.setFont(customFont);
        textField.setForeground(new Color(157, 185, 223));
        textField.setText(name);

    }

    public void removePlaceholderStyle(JTextField textFiled) {
        textFiled.setForeground(Color.black);
    }

    private class ImageRender extends DefaultTableCellRenderer {

        private Icon icon;

        public ImageRender(Icon icon) {
            this.icon = icon;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            return new JLabel(icon);
        }

    }

    public static DefaultTableModel getModelSanPham() {
        return modelSanPham;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelTable1;
    private javax.swing.JPanel PanelTable2;
    private Components.ButtonRadius btnChon;
    private Components.ButtonRadius btnHuydon;
    private Components.ButtonRadius btnLamMoi;
    private Components.ButtonRadius btnThanhtoan;
    private Components.ButtonRadius btnTimkiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextField tfTienkhach;
    private javax.swing.JTextField tfTienthoi;
    private javax.swing.JTextField tfTongtien;
    private javax.swing.JComboBox<String> timKiemTheo;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtIDHoadon;
    private javax.swing.JTextField txtIDNhanvien;
    private javax.swing.JTextField txtIDSanpham;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenSanpham;
    private javax.swing.JTextField txtTenTacgia;
    private javax.swing.JTextField txtTheloai;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
