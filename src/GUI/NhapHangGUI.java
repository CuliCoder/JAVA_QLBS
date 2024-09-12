/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import Util.sharedFunction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author NGOC THUC
 */
public final class NhapHangGUI extends javax.swing.JPanel {

    /**
     * Creates new form NhapHangGUI
     */
    PhieuNhapBUS pnBUS = new PhieuNhapBUS();
    SanPhamBUS sanPhamBUS = new SanPhamBUS();
    SanPhamGUI sanPhamGUI = new SanPhamGUI();
    private static DefaultTableModel modelSanPham, modelCartImport;
    private static JTable tableSanPham, tableChitiet;

    public NhapHangGUI() {
        initComponents();
        createTable();
        selectProduct();
        createCart();
        placeholder();

    }

    public void selectProduct() {

        tableSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableSanPham.rowAtPoint(e.getPoint());
                pnBUS.loadData(NhapHangGUI.this, row);
            }
        });

        tableChitiet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableChitiet.rowAtPoint(e.getPoint());
                int column = tableChitiet.columnAtPoint(e.getPoint());

                if (column == 4) {
                    pnBUS.loadDataFromCart(NhapHangGUI.this, row);
                } else if (column == 5) {
                    int opt = JOptionPane.showConfirmDialog(null, "Xác nhận loại bỏ sản phẩm này ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.YES_OPTION) {
                        modelCartImport.removeRow(row);
                        getTotalCart();
                    }
                }
            }
        });
    }

    public void placeholder() {
        tfTimkiem.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (tfTimkiem.getText().equals("Tìm kiếm sản phẩm")) {
                    tfTimkiem.setText("");
                    tfTimkiem.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (tfTimkiem.getText().isEmpty()) {
                    tfTimkiem.setForeground(Color.GRAY);
                    tfTimkiem.setText("Tìm kiếm sản phẩm");
                    tfTimkiem.setForeground(new Color(135, 172, 217));
                }
            }
        });
    }

    public void createCart() {
        pnBUS.createCart(this);
        getTotalCart();
    }

    public JLabel getLblHinhAnh() {
        return lblHinhAnh;
    }

    public static DefaultTableModel getModelSanPham() {
        return modelSanPham;
    }

    public static DefaultTableModel getModelCartImport() {
        return modelCartImport;
    }

    public JComboBox<String> getCbCongTy() {
        return cbCongTy;
    }

    public JTextField getTfIDHoadon() {
        return tfIDHoadon;
    }

    public JTextField getTfIDNhanvien() {
        return tfIDNhanvien;
    }

    public JTextField getTfNgaytao() {
        return tfNgaytao;
    }

    public JTextField getTfTongtien() {
        return tfTongtien;
    }

    public static JTable getTableSanPham() {
        return tableSanPham;
    }

    public static JTable getTableChitiet() {
        return tableChitiet;
    }

    public JTextField getTfDongia() {
        return tfDongia;
    }

    public JTextField getTfIDSanPham() {
        return tfIDSanPham;
    }

    public JTextField getTfSoluong() {
        return tfSoluong;
    }

    public JTextField getTfTenSanpham() {
        return tfTenSanpham;
    }

    public JTextField getTfTenTacgia() {
        return tfTenTacgia;
    }

    public JTextField getTfTheloai() {
        return tfTheloai;
    }

    public JTextField getTfPhanTram() {
        return tfPhanTram;
    }

    public void createTable() {
        tableSanPham = createTableSanPham();
        tableSanPham.setPreferredScrollableViewportSize(PanelTable1.getPreferredSize());
        JScrollPane scrollPaneSanPham = new JScrollPane(tableSanPham);
        MatteBorder matteBorder = new MatteBorder(0, 1, 1, 1, new Color(164, 191, 226));
        scrollPaneSanPham.setBorder(matteBorder);
        PanelTable1.setLayout(new BorderLayout());
        PanelTable1.add(scrollPaneSanPham);

        pnBUS.createTableProduct(modelSanPham);

        tableChitiet = createTableChitietSanpham();
        tableChitiet.setPreferredScrollableViewportSize(PanelTable2.getPreferredSize());
        JScrollPane scrollPaneChitiet = new JScrollPane(tableChitiet);
        scrollPaneChitiet.setBorder(matteBorder);
        PanelTable2.setLayout(new BorderLayout());
        PanelTable2.add(scrollPaneChitiet);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        PanelTable1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfIDHoadon = new javax.swing.JTextField();
        tfIDNhanvien = new javax.swing.JTextField();
        tfNgaytao = new javax.swing.JTextField();
        tfTongtien = new javax.swing.JTextField();
        btnHuydon = new Components.ButtonRadius();
        btnThanhtoan = new Components.ButtonRadius();
        jLabel7 = new javax.swing.JLabel();
        PanelTable2 = new javax.swing.JPanel();
        cbCongTy = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnChon = new Components.ButtonRadius();
        lblHinhAnh = new javax.swing.JLabel();
        tfIDSanPham = new javax.swing.JTextField();
        tfTenSanpham = new javax.swing.JTextField();
        tfTenTacgia = new javax.swing.JTextField();
        tfTheloai = new javax.swing.JTextField();
        tfSoluong = new javax.swing.JTextField();
        tfDongia = new javax.swing.JTextField();
        tfPhanTram = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        tfTimkiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnTimkiem = new Components.ButtonRadius();
        btnLammoi2 = new Components.ButtonRadius();

        setPreferredSize(new java.awt.Dimension(1020, 750));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1020, 720));

        PanelTable1.setBackground(new java.awt.Color(255, 255, 255));
        PanelTable1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 1, true));
        PanelTable1.setPreferredSize(new java.awt.Dimension(650, 300));

        javax.swing.GroupLayout PanelTable1Layout = new javax.swing.GroupLayout(PanelTable1);
        PanelTable1.setLayout(PanelTable1Layout);
        PanelTable1Layout.setHorizontalGroup(
            PanelTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        PanelTable1Layout.setVerticalGroup(
            PanelTable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
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

        tfIDHoadon.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfIDHoadon.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "ID. Phiếu nhập", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfIDHoadon.setMaximumSize(new java.awt.Dimension(140, 50));
        tfIDHoadon.setPreferredSize(new java.awt.Dimension(140, 50));

        tfIDNhanvien.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfIDNhanvien.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Nhân viên", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfIDNhanvien.setMaximumSize(new java.awt.Dimension(2147483647, 50));
        tfIDNhanvien.setPreferredSize(new java.awt.Dimension(140, 50));
        tfIDNhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDNhanvienActionPerformed(evt);
            }
        });

        tfNgaytao.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfNgaytao.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Ngày lập phiếu nhập", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfNgaytao.setMaximumSize(new java.awt.Dimension(300, 50));
        tfNgaytao.setPreferredSize(new java.awt.Dimension(300, 50));

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

        btnHuydon.setForeground(new java.awt.Color(135, 172, 217));
        btnHuydon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/cancel.png"))); // NOI18N
        btnHuydon.setText("Hủy phiếu");
        btnHuydon.setFocusPainted(false);
        btnHuydon.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 17)); // NOI18N
        btnHuydon.setIconTextGap(3);
        btnHuydon.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnHuydon.setPreferredSize(new java.awt.Dimension(140, 40));
        btnHuydon.setRadius(40);
        btnHuydon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnHuydon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuydonActionPerformed(evt);
            }
        });

        btnThanhtoan.setForeground(new java.awt.Color(135, 172, 217));
        btnThanhtoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/fix.png"))); // NOI18N
        btnThanhtoan.setText("Lưu phiếu");
        btnThanhtoan.setFocusPainted(false);
        btnThanhtoan.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 16)); // NOI18N
        btnThanhtoan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThanhtoan.setIconTextGap(0);
        btnThanhtoan.setMargin(new java.awt.Insets(2, 0, 2, 0));
        btnThanhtoan.setPreferredSize(new java.awt.Dimension(140, 40));
        btnThanhtoan.setRadius(40);
        btnThanhtoan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThanhtoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhtoanMouseClicked(evt);
            }
        });
        btnThanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhtoanActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(135, 172, 217));
        jLabel7.setText("Chi tiết phiếu nhập");

        PanelTable2.setBackground(new java.awt.Color(255, 255, 255));
        PanelTable2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 1, true));
        PanelTable2.setMaximumSize(new java.awt.Dimension(285, 245));
        PanelTable2.setMinimumSize(new java.awt.Dimension(285, 245));
        PanelTable2.setPreferredSize(new java.awt.Dimension(285, 245));

        javax.swing.GroupLayout PanelTable2Layout = new javax.swing.GroupLayout(PanelTable2);
        PanelTable2.setLayout(PanelTable2Layout);
        PanelTable2Layout.setHorizontalGroup(
            PanelTable2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelTable2Layout.setVerticalGroup(
            PanelTable2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 244, Short.MAX_VALUE)
        );

        cbCongTy.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        cbCongTy.setForeground(new java.awt.Color(135, 172, 217));
        cbCongTy.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--- Chọn công ty sách ---" }));
        cbCongTy.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true));

        jLabel8.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(135, 172, 217));
        jLabel8.setText("Công ty sách");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfNgaytao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfTongtien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbCongTy, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTable2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel8))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(tfIDHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfIDNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfIDHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIDNhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfNgaytao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(0, 0, 0)
                .addComponent(PanelTable2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addGap(1, 1, 1)
                .addComponent(cbCongTy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThanhtoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuydon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
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
        btnChon.setMargin(new java.awt.Insets(2, 10, 3, 14));
        btnChon.setPreferredSize(new java.awt.Dimension(130, 40));
        btnChon.setRadius(40);
        btnChon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        lblHinhAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHinhAnh.setText("Hình ảnh");
        lblHinhAnh.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true));
        lblHinhAnh.setPreferredSize(new java.awt.Dimension(60, 25));

        tfIDSanPham.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        tfIDSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "ID. Sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfIDSanPham.setFocusable(false);
        tfIDSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDSanPhamjTextField1ActionPerformed(evt);
            }
        });

        tfTenSanpham.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        tfTenSanpham.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Tên sản phẩm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfTenSanpham.setFocusable(false);

        tfTenTacgia.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        tfTenTacgia.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Tên tác giả", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfTenTacgia.setFocusable(false);

        tfTheloai.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        tfTheloai.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Thể loại", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfTheloai.setFocusable(false);

        tfSoluong.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        tfSoluong.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Số lượng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N

        tfDongia.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        tfDongia.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Đơn giá nhập", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N

        tfPhanTram.setFont(new java.awt.Font("Josefin Sans Medium", 0, 14)); // NOI18N
        tfPhanTram.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Phần trăm lợi nhuận", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(tfTenTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(tfTheloai, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(tfIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(tfTenSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(tfSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfDongia, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfPhanTram))))
                    .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
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
                                .addComponent(tfIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfTenSanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(80, 80, 80)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfDongia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfPhanTram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTenTacgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTheloai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(253, 183, 58));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_40px/pack_1.png"))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(253, 183, 58));
        jLabel6.setText("Nhập hàng");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addContainerGap())
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

        tfTimkiem.setBackground(new java.awt.Color(243, 243, 244));
        tfTimkiem.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 18)); // NOI18N
        tfTimkiem.setForeground(new java.awt.Color(135, 172, 217));
        tfTimkiem.setText("Tìm kiếm sản phẩm");
        tfTimkiem.setBorder(null);
        tfTimkiem.setHighlighter(null);
        tfTimkiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfTimkiemFocusGained(evt);
            }
        });
        tfTimkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfTimkiemMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tfTimkiemMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tfTimkiemMouseReleased(evt);
            }
        });
        tfTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTimkiemActionPerformed(evt);
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
                .addComponent(tfTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tfTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(6, 6, 6))
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
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        btnLammoi2.setBorder(null);
        btnLammoi2.setForeground(new java.awt.Color(135, 172, 217));
        btnLammoi2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/back.png"))); // NOI18N
        btnLammoi2.setText("Làm mới");
        btnLammoi2.setFocusPainted(false);
        btnLammoi2.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 17)); // NOI18N
        btnLammoi2.setIconTextGap(0);
        btnLammoi2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnLammoi2.setMaximumSize(new java.awt.Dimension(100, 40));
        btnLammoi2.setPreferredSize(new java.awt.Dimension(100, 40));
        btnLammoi2.setRadius(40);
        btnLammoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoi2ActionPerformed(evt);
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
                                .addComponent(btnLammoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PanelTable1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(btnLammoi2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(PanelTable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE))
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
        if (validateInput()) {
            pnBUS.NhapHang(this);
            createCart();
            pnBUS.createTableProduct(modelSanPham);
            modelCartImport.setRowCount(0);
            getCbCongTy().setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnThanhtoanActionPerformed
    public boolean validateInput() {
        if (modelCartImport.getRowCount() == 0) {
            displayErrorMessage("Vui lòng thêm sản phẩm cần nhập");
            return false;
        } else if (cbCongTy.getSelectedIndex() == 0 || cbCongTy.getSelectedIndex() == -1) {
            displayErrorMessage("Vui lòng chọn công ty nhập");
            return false;
        }
        return true;
    }
    private void tfIDSanPhamjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIDSanPhamjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIDSanPhamjTextField1ActionPerformed

    private void tfTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimkiemActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        // TODO add your handling code here:
        sanPhamGUI.findSanPhamByTenSP_or_MaSP(tfTimkiem.getText(), modelSanPham);
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        // TODO add your handling code here:
        if (tfIDSanPham.getText().equals("")) {
            displayErrorMessage("Vui lòng chọn sản phẩm");
        } else if (tfSoluong.getText().equals("")) {
            displayErrorMessage("vui lòng nhập số lượng");
        }
        else if (tfPhanTram.getText().equals("")) {
            displayErrorMessage("vui lòng nhập phần trăm lợi nhuận");
        }else {
            int quantity = 0;
            double price = 0;
            double phanTram = 0;
            String nameProduct = tfTenSanpham.getText();
            String quantityStr = tfSoluong.getText();
            
            String IDSanPham = tfIDSanPham.getText();
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0) {
                    displayErrorMessage("Số lượng không hợp lệ!!");
                    return;
                }
            } catch (Exception e) {
                displayErrorMessage("Số lượng không hợp lệ!!");
                return;
            }
            String priceStr = tfDongia.getText();
            try {
                price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    displayErrorMessage("Đơn giá không hợp lệ!!");
                    return;
                }
            } catch (Exception e) {
                displayErrorMessage("Đơn giá không hợp lệ!!");
                return;
            }
            String percentStr = tfPhanTram.getText();
            try {
                phanTram = Double.parseDouble(percentStr);
                if(phanTram <= 0) {
                    displayErrorMessage("Phần trăm lợi nhuận không hợp lệ!!!");
                    return;
                }
            } catch (Exception e) {
                displayErrorMessage("Phần trăm lợi nhuận không hợp lệ!!!");
                    return;
            }
            
            double percent = (double) phanTram / 100;
            double loiNhuan = price * percent;
            double giaBan = BigDecimal.valueOf(price).add(BigDecimal.valueOf(loiNhuan)).doubleValue(); // tính giá bán = giá nhập * phần trăm lợi nhuận
            long totalPriceDbl = (long) price * quantity;
            
            Object[] row = {
                IDSanPham, quantity, totalPriceDbl, giaBan, "", ""
            };
            int existNameRow = existProduct(IDSanPham);
            if (existNameRow == -1) {
                modelCartImport.addRow(row);
            } else {
                modelCartImport.setValueAt(quantity, existNameRow, 1);
                modelCartImport.setValueAt(totalPriceDbl, existNameRow, 2);
                modelCartImport.setValueAt(giaBan, existNameRow, 3);
            }
            getTotalCart();
            clearTextField();
        }
    }//GEN-LAST:event_btnChonActionPerformed
    public void clearTextField() {
        tfIDSanPham.setText("");
        tfTenSanpham.setText("");
        tfTenTacgia.setText("");
        tfTheloai.setText("");
        tfSoluong.setText("");
        tfDongia.setText("");
        tfPhanTram.setText("");
        lblHinhAnh.setIcon(null);
        lblHinhAnh.setText("Hình ảnh");
       
    }

    public void getTotalCart() {
        int rowCount = modelCartImport.getRowCount();
        long totalPrice = 0;

        for (int i = 0; i < rowCount; i++) {
            Object value = modelCartImport.getValueAt(i, 2);
            String stringValue = value.toString(); // Chuyển đổi giá trị thành chuỗi
            totalPrice += Long.parseLong(stringValue);
        }

        tfTongtien.setText(sharedFunction.formatVND(totalPrice));
    }

    public int existProduct(String id) {
        int rowCount = modelCartImport.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            Object value = modelCartImport.getValueAt(i, 0);
            if (id.equals(value)) {
                return i;
            }
        }

        return -1;
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

    private void btnLammoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoi2ActionPerformed
        // TODO add your handling code here:
        ArrayList<SanPhamDTO> listSanPham = sanPhamBUS.getAllSanPham();
        sanPhamGUI.loadTableSanPham(listSanPham, modelSanPham);
    }//GEN-LAST:event_btnLammoi2ActionPerformed

    private void btnHuydonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuydonActionPerformed
        // TODO add your handling code here:
        modelCartImport.setRowCount(0);
        cbCongTy.setSelectedIndex(0);
        getTotalCart();
    }//GEN-LAST:event_btnHuydonActionPerformed

    private void tfIDNhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIDNhanvienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIDNhanvienActionPerformed

    private void tfTimkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfTimkiemMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimkiemMouseClicked

    private void tfTimkiemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfTimkiemMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tfTimkiemMouseReleased

    private void tfTimkiemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfTimkiemMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_tfTimkiemMousePressed

    private void tfTimkiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfTimkiemFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimkiemFocusGained

    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public static void EditHeaderTable2(JTable table) {
        // Increase the header height
        table.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 40));
        
        // Create a custom header renderer
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JTableHeader header = table.getTableHeader();
                if (header != null) {
                    setForeground(new Color(254, 194, 92)); // Set text color
                    setBackground(new Color(255, 255, 255)); // Set background color
                    Font headerFont = new Font("Josefin Sans", Font.BOLD, 16); // Adjust font and font size
                    header.setFont(headerFont);
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    label.setHorizontalAlignment(SwingConstants.CENTER); // Center the content
                    label.setFont(headerFont);

                    // Add vertical lines between columns
                    int thickness = 1; // Adjust the line thickness as needed
                    label.setBorder(BorderFactory.createMatteBorder(0, 0, thickness, thickness, new Color(254, 194, 92)));

                    return label;
                }
                setText((value == null) ? "" : value.toString());
                return this;
            }
        };

        // Set the custom renderer for the table header
        table.getTableHeader().setDefaultRenderer(headerRenderer);
    }

    public static void EditTableContent(JTable table) {
        // Đặt độ cao cho từng dòng (trừ header)
        int rowHeight = 30;
        table.setRowHeight(rowHeight);
        table.setGridColor(new Color(254, 194, 92));
        table.setShowGrid(true);
        table.setBackground(Color.WHITE);
        Font font = new Font("Josefin Sans", Font.PLAIN, 14);
        table.setFont(font);
        table.setSelectionBackground(Color.WHITE);
        table.setSelectionForeground(new Color(153, 184, 224));
        table.setFocusable(false);
        // Vô hiệu hóa kéo cột
        table.getTableHeader().setReorderingAllowed(false);
        // Vô hiệu hóa kéo dãng cột
        table.getTableHeader().setResizingAllowed(false);
        // Căn giữa nội dung trong các ô chữ
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    public JTable createTableChitietSanpham() {
        // Tiêu đề của các cột

        String[] columnNames = {"ID", "SL", "Th.Tiền", "Giá bán", "", ""};
        modelCartImport = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) { // Cột STT và Số lượng
                    return Integer.class; // Kiểu dữ liệu Integer
                } else if (columnIndex == 4 || columnIndex == 5) { // Cột Đơn giá
                    return ImageIcon.class; // Kiểu dữ liệu Icon
                }else if(columnIndex == 2 || columnIndex == 3) {
                    return Long.class;
                }
                return String.class; // Các cột khác có kiểu dữ liệu String
            }
        };
        modelCartImport.setColumnIdentifiers(columnNames);
        // Tạo JTable với DefaultTableModel
        JTable table = new JTable(modelCartImport);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150); // Độ rộng cột 0
        columnModel.getColumn(1).setPreferredWidth(200); // Độ rộng cột 0
        columnModel.getColumn(2).setPreferredWidth(300); // Độ rộng cột 1
        columnModel.getColumn(3).setPreferredWidth(320); // Độ rộng cột 2
        columnModel.getColumn(4).setPreferredWidth(60); // Độ rộng cột 3
        columnModel.getColumn(5).setPreferredWidth(60); // Độ rộng cột 3

        EditHeaderTable2(table);
        EditTableContent(table);
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
                } else if (columnIndex == 6) { // Cột Đơn giá
                    return Float.class; // Kiểu dữ liệu Float
                }
                return String.class; // Các cột khác có kiểu dữ liệu String
            }
        };
        modelSanPham.setColumnIdentifiers(columnNames);
        // Tạo JTable với DefaultTableModel
        JTable table = new JTable(modelSanPham);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60); // Độ rộng cột 0
        columnModel.getColumn(1).setPreferredWidth(150); // Độ rộng cột 1
        columnModel.getColumn(2).setPreferredWidth(240); // Độ rộng cột 2
        columnModel.getColumn(3).setPreferredWidth(240); // Độ rộng cột 3
        columnModel.getColumn(4).setPreferredWidth(120); // Độ rộng cột 4
        columnModel.getColumn(5).setPreferredWidth(120); // Độ rộng cột 5
        columnModel.getColumn(6).setPreferredWidth(120); // Độ rộng cột 6

        sharedFunction.EditHeaderTable(table);
        sharedFunction.EditTableContent(table);
        return table;
    }

    public void CustomizeCcolumnWidth(JTable table, int column1, int column2, int column3) {

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Tắt tự động điều chỉnh rộng cột
//       table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        TableColumnModel columnModel = table.getColumnModel();
        // Tính tổng độ rộng của các cột cố định
        int fixedColumnsWidth = column1 + column2 + column3;
//    
//    // Xác định độ rộng của cột cuối (cột 4) bằng phần còn lại của không gian
        int column4 = 1003 - fixedColumnsWidth;

        columnModel.getColumn(0).setPreferredWidth(column1); // Độ rộng cột 0
        columnModel.getColumn(1).setPreferredWidth(column2); // Độ rộng cột 1
        columnModel.getColumn(2).setPreferredWidth(column3); // Độ rộng cột 2
        columnModel.getColumn(3).setPreferredWidth(column4); // Độ rộng cột 3
    }

    public void removePlaceholderStyle(JTextField textFiled) {
        textFiled.setForeground(Color.black);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelTable1;
    private javax.swing.JPanel PanelTable2;
    private Components.ButtonRadius btnChon;
    private Components.ButtonRadius btnHuydon;
    private Components.ButtonRadius btnLammoi2;
    private Components.ButtonRadius btnThanhtoan;
    private Components.ButtonRadius btnTimkiem;
    private javax.swing.JComboBox<String> cbCongTy;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JTextField tfDongia;
    private javax.swing.JTextField tfIDHoadon;
    private javax.swing.JTextField tfIDNhanvien;
    private javax.swing.JTextField tfIDSanPham;
    private javax.swing.JTextField tfNgaytao;
    private javax.swing.JTextField tfPhanTram;
    private javax.swing.JTextField tfSoluong;
    private javax.swing.JTextField tfTenSanpham;
    private javax.swing.JTextField tfTenTacgia;
    private javax.swing.JTextField tfTheloai;
    private javax.swing.JTextField tfTimkiem;
    private javax.swing.JTextField tfTongtien;
    // End of variables declaration//GEN-END:variables
}
