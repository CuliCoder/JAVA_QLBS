/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.CTPhieuNhapBUS;
import BUS.PhieuNhapBUS;
import Components.ButtonRadius;
import DTO.PhieuNhapDTO;
import Util.sharedFunction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
public class PhieuNhapGUI extends javax.swing.JPanel {

    /**
     * Creates new form PhieuNhapGUI
     */
    PhieuNhapBUS pnBUS = new PhieuNhapBUS();
    CTPhieuNhapBUS ctpnBUS = new CTPhieuNhapBUS();
    private static DefaultTableModel modelPhieuNhap, modelImportDetail;
    private static JTable tablePhieunhap, tableChitiet;

    public PhieuNhapGUI() {
        initComponents();
        createTable();
        selectProduct();
        sharedFunction.addPlaceholder(tfTimkiem, "Tìm kiếm theo mã phiếu nhập");
    }

    public void selectProduct() {
//        tableSanPham.getSelectionModel().addListSelectionListener((e) -> {
//            if (!e.getValueIsAdjusting()) {
//                pnBUS.loadData(this, getTableSanPham().getSelectedRow());
//            }
//        });

        tablePhieunhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tablePhieunhap.rowAtPoint(e.getPoint());
                pnBUS.loadInfoImport(PhieuNhapGUI.this, row);
            }
        });

        tableChitiet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableChitiet.rowAtPoint(e.getPoint());
                int column = tableChitiet.columnAtPoint(e.getPoint());

                if (column == 4) {
                    ChiTietPhieuNhap ctpnGUI = new ChiTietPhieuNhap(PhieuNhapGUI.this);
                    ctpnGUI.setVisible(true);

                    String maPN = modelPhieuNhap.getValueAt(tablePhieunhap.getSelectedRow(), 1).toString();
                    String maSP = modelImportDetail.getValueAt(row, 0).toString();
                    int soLuong = Integer.parseInt(modelImportDetail.getValueAt(row, 2).toString());
                    long thanhTien = (long) sharedFunction.parseMoneyString(modelImportDetail.getValueAt(row, 3).toString());
                    double donGia = thanhTien / soLuong;

                    ctpnGUI.getTxtIDPhieuNhap().setText(maPN);
                    ctpnGUI.getTxtIDSanPham().setText(maSP);
                    ctpnGUI.getTxtSoLuong().setText(soLuong + "");
                    ctpnGUI.getTxtDonGia().setText(donGia + "");

                } else if (column == 5) {
                    modelImportDetail.removeRow(row);
                    setTongTien();
                }
            }
        });
    }

    public void setTongTien() {
        double TongTien = 0;
        if (modelImportDetail.getRowCount() == 0) {
            tfTongTien.setText("0");
        } else {
            for (int i = 0; i < getTableChitiet().getRowCount(); i++) {
                TongTien += Double.parseDouble(getModelImportDetai().getValueAt(i, 3).toString());
            }
            getTfTongTien().setText(TongTien + "");
        }

    }

    public void createTable() {
        tablePhieunhap = createTablePhieunhap();
        tablePhieunhap.setPreferredScrollableViewportSize(PanelTable.getPreferredSize());
        JScrollPane scrollPaneSanPham = new JScrollPane(tablePhieunhap);
        MatteBorder matteBorder = new MatteBorder(0, 1, 1, 1, new Color(164, 191, 226));
        scrollPaneSanPham.setBorder(matteBorder);
        PanelTable.setLayout(new BorderLayout());
        PanelTable.add(scrollPaneSanPham);
        ArrayList<PhieuNhapDTO> dspn = pnBUS.loadPhieuNhap();
        loadDataTablePhieuNhap(dspn, modelPhieuNhap);
        tableChitiet = createTableChitietSanpham();
        tableChitiet.setPreferredScrollableViewportSize(PanelTable2.getPreferredSize());
        JScrollPane scrollPaneChitiet = new JScrollPane(tableChitiet);
        scrollPaneChitiet.setBorder(matteBorder);
        PanelTable2.setLayout(new BorderLayout());
        PanelTable2.add(scrollPaneChitiet);
    }

    public JTextField getTfCongTy() {
        return tfCongTy;
    }

    public JTextField getTfNgayLap() {
        return tfNgayLap;
    }

    public JTextField getTfIDHoadon() {
        return tfIDHoadon;
    }

    public JTextField getTfIDNhanVien() {
        return tfIDNhanVien;
    }

    public JTextField getTfTongTien() {
        return tfTongTien;
    }

    public static JTable getTablePhieunhap() {
        return tablePhieunhap;
    }

    public static JTable getTableChitiet() {
        return tableChitiet;
    }

    public static DefaultTableModel getModelPhieuNhap() {
        return modelPhieuNhap;
    }

    public static DefaultTableModel getModelImportDetai() {
        return modelImportDetail;
    }

    public ButtonRadius getBtnCancel() {
        return btnCancel;
    }

    public ButtonRadius getBtnSave() {
        return btnSave;
    }

    public ButtonRadius getBtnXoa() {
        return btnXoa;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        PanelTable = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        tfTimkiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnTimkiem = new Components.ButtonRadius();
        btnXoa = new Components.ButtonRadius();
        btnLammoi = new Components.ButtonRadius();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfIDHoadon = new javax.swing.JTextField();
        tfIDNhanVien = new javax.swing.JTextField();
        tfTongTien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnSave = new Components.ButtonRadius();
        btnCancel = new Components.ButtonRadius();
        PanelTable2 = new javax.swing.JPanel();
        tfCongTy = new javax.swing.JTextField();
        tfNgayLap = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1020, 750));
        jPanel1.setPreferredSize(new java.awt.Dimension(1020, 750));

        jLabel6.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(253, 183, 58));
        jLabel6.setText("Quản lý phiếu nhập");
        jLabel6.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(253, 183, 58));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_40px/order_1.png"))); // NOI18N

        PanelTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 1, true));
        PanelTable.setMaximumSize(new java.awt.Dimension(483, 620));
        PanelTable.setMinimumSize(new java.awt.Dimension(483, 620));
        PanelTable.setPreferredSize(new java.awt.Dimension(483, 620));
        PanelTable.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

        btnXoa.setBorder(null);
        btnXoa.setForeground(new java.awt.Color(135, 172, 217));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/cancel.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setFocusPainted(false);
        btnXoa.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 17)); // NOI18N
        btnXoa.setMaximumSize(new java.awt.Dimension(100, 40));
        btnXoa.setPreferredSize(new java.awt.Dimension(100, 40));
        btnXoa.setRadius(40);
        btnXoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLammoi.setBorder(null);
        btnLammoi.setForeground(new java.awt.Color(135, 172, 217));
        btnLammoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/back.png"))); // NOI18N
        btnLammoi.setText("Làm mới");
        btnLammoi.setFocusPainted(false);
        btnLammoi.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 17)); // NOI18N
        btnLammoi.setIconTextGap(0);
        btnLammoi.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnLammoi.setMaximumSize(new java.awt.Dimension(100, 40));
        btnLammoi.setPreferredSize(new java.awt.Dimension(100, 40));
        btnLammoi.setRadius(40);
        btnLammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLammoiActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true));
        jPanel2.setMaximumSize(new java.awt.Dimension(483, 620));
        jPanel2.setMinimumSize(new java.awt.Dimension(483, 620));
        jPanel2.setPreferredSize(new java.awt.Dimension(483, 620));

        jPanel3.setBackground(new java.awt.Color(135, 172, 217));
        jPanel3.setMaximumSize(new java.awt.Dimension(476, 40));
        jPanel3.setMinimumSize(new java.awt.Dimension(476, 40));
        jPanel3.setPreferredSize(new java.awt.Dimension(476, 40));

        jLabel2.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông tin chi tiết");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        tfIDHoadon.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfIDHoadon.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "ID Phiếu nhập", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfIDHoadon.setFocusable(false);
        tfIDHoadon.setMaximumSize(new java.awt.Dimension(480, 50));
        tfIDHoadon.setMinimumSize(new java.awt.Dimension(480, 50));
        tfIDHoadon.setPreferredSize(new java.awt.Dimension(480, 50));
        tfIDHoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDHoadonActionPerformed(evt);
            }
        });

        tfIDNhanVien.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfIDNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "ID Nhân viên lập", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfIDNhanVien.setFocusable(false);
        tfIDNhanVien.setMaximumSize(new java.awt.Dimension(480, 50));
        tfIDNhanVien.setMinimumSize(new java.awt.Dimension(480, 50));
        tfIDNhanVien.setPreferredSize(new java.awt.Dimension(480, 50));

        tfTongTien.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfTongTien.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Tổng tiền", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfTongTien.setFocusable(false);
        tfTongTien.setMaximumSize(new java.awt.Dimension(480, 50));
        tfTongTien.setMinimumSize(new java.awt.Dimension(480, 50));
        tfTongTien.setPreferredSize(new java.awt.Dimension(480, 50));

        jLabel3.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(135, 172, 217));
        jLabel3.setText("Chi tiết phiếu nhập");

        btnSave.setForeground(new java.awt.Color(135, 172, 217));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/fix.png"))); // NOI18N
        btnSave.setText("Lưu");
        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 18)); // NOI18N
        btnSave.setPreferredSize(new java.awt.Dimension(100, 40));
        btnSave.setRadius(40);
        btnSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnCancel.setForeground(new java.awt.Color(135, 172, 217));
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/cancel.png"))); // NOI18N
        btnCancel.setText("Hủy");
        btnCancel.setBorderPainted(false);
        btnCancel.setFocusPainted(false);
        btnCancel.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 18)); // NOI18N
        btnCancel.setPreferredSize(new java.awt.Dimension(100, 40));
        btnCancel.setRadius(40);
        btnCancel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        PanelTable2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 1, true));
        PanelTable2.setMaximumSize(new java.awt.Dimension(438, 230));
        PanelTable2.setMinimumSize(new java.awt.Dimension(438, 230));
        PanelTable2.setPreferredSize(new java.awt.Dimension(438, 230));

        javax.swing.GroupLayout PanelTable2Layout = new javax.swing.GroupLayout(PanelTable2);
        PanelTable2.setLayout(PanelTable2Layout);
        PanelTable2Layout.setHorizontalGroup(
            PanelTable2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelTable2Layout.setVerticalGroup(
            PanelTable2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 228, Short.MAX_VALUE)
        );

        tfCongTy.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfCongTy.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Công ty sách", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfCongTy.setFocusable(false);
        tfCongTy.setMaximumSize(new java.awt.Dimension(480, 50));
        tfCongTy.setMinimumSize(new java.awt.Dimension(480, 50));
        tfCongTy.setPreferredSize(new java.awt.Dimension(480, 50));
        tfCongTy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfCongTyActionPerformed(evt);
            }
        });

        tfNgayLap.setFont(new java.awt.Font("Josefin Sans SemiBold", 0, 14)); // NOI18N
        tfNgayLap.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(135, 172, 217), 2, true), "Ngày Lập\n", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Josefin Sans SemiBold", 0, 16), new java.awt.Color(135, 172, 217))); // NOI18N
        tfNgayLap.setFocusable(false);
        tfNgayLap.setMaximumSize(new java.awt.Dimension(480, 50));
        tfNgayLap.setMinimumSize(new java.awt.Dimension(480, 50));
        tfNgayLap.setPreferredSize(new java.awt.Dimension(480, 50));
        tfNgayLap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNgayLapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(238, 238, 238)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelTable2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(289, 289, 289))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tfIDHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfIDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tfCongTy, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(tfNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfIDHoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfIDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTable2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tfCongTy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PanelTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLammoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tfTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimkiemActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        // TODO add your handling code here:
        String searchKeyword = tfTimkiem.getText().trim();
        if (searchKeyword.isEmpty() || searchKeyword.equals("Tìm kiếm theo mã phiếu nhập")) {
            ArrayList<PhieuNhapDTO> dspn = pnBUS.loadPhieuNhap();
            loadDataTablePhieuNhap(dspn, modelPhieuNhap);
        }
        findPhieuNhapByMaPN(tfTimkiem.getText(), modelPhieuNhap);
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (tfIDHoadon.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập cần xóa", "lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int res = JOptionPane.showConfirmDialog(null, "Xác nhận muốn xóa phiếu nhập?", "xác nhận", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            int MaPN = Integer.parseInt(modelPhieuNhap.getValueAt(tablePhieunhap.getSelectedRow(), 1).toString().substring(2));
            boolean xoa = pnBUS.XoaPhieuNhap(MaPN);
            if (xoa) {
                JOptionPane.showMessageDialog(null, "Xóa phiếu nhập thành công!!");
            }
            modelImportDetail.setRowCount(0);
            pnBUS.createTableImport(modelPhieuNhap);

            tfCongTy.setText("");
            tfIDHoadon.setText("");
            tfIDNhanVien.setText("");
            tfNgayLap.setText("");
            tfTongTien.setText("");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLammoiActionPerformed
        // TODO add your handling code here:
        ArrayList<PhieuNhapDTO> dspn = pnBUS.loadPhieuNhap();
        loadDataTablePhieuNhap(dspn, modelPhieuNhap);
    }//GEN-LAST:event_btnLammoiActionPerformed

    private void tfIDHoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIDHoadonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIDHoadonActionPerformed

    private void tfCongTyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCongTyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCongTyActionPerformed

    private void tfNgayLapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNgayLapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNgayLapActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        pnBUS.loadInfoImport(PhieuNhapGUI.this, tablePhieunhap.getSelectedRow());
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (tfIDHoadon.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu nhập cần sửa");
            return;
        }
        if (modelImportDetail.getRowCount() == 0) {
            int confirmDel = JOptionPane.showConfirmDialog(this, "Bảng sản phẩm nhập đang rỗng, xác nhận xóa phiếu nhập ?", "thông báo", JOptionPane.YES_NO_OPTION);
            if (confirmDel == JOptionPane.YES_OPTION) {
                int MaPN = Integer.parseInt(modelPhieuNhap.getValueAt(tablePhieunhap.getSelectedRow(), 1).toString().substring(2));
                pnBUS.XoaPhieuNhap(MaPN);
                pnBUS.createTableImport(modelPhieuNhap);

                tfCongTy.setText("");
                tfIDHoadon.setText("");
                tfIDNhanVien.setText("");
                tfNgayLap.setText("");
                tfTongTien.setText("");
            }
            return;
        }
        int res = JOptionPane.showConfirmDialog(null, "Xác nhận muốn cập nhập phiếu nhập?", "xác nhận", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            boolean update = false;
            String MaPNStr = modelPhieuNhap.getValueAt(tablePhieunhap.getSelectedRow(), 1).toString();
            int MaPN = Integer.parseInt(MaPNStr.substring(2));
            ctpnBUS.DoiTrangThai(MaPN);
            ctpnBUS.XoaSLCu(MaPN);
            for (int i = 0; i < tableChitiet.getRowCount(); i++) {

                String MaSPStr = modelImportDetail.getValueAt(i, 0).toString();
                int soLuong = Integer.parseInt(modelImportDetail.getValueAt(i, 2).toString());
                double thanhTien = Double.parseDouble(modelImportDetail.getValueAt(i, 3).toString());
                double donGia = thanhTien / soLuong;
                int MaSP = Integer.parseInt(MaSPStr.substring(2));
                update = ctpnBUS.SuaPhieuNhap(MaPN, MaSP, soLuong, donGia);
            }
            ctpnBUS.XoaCTPhieuNhap(MaPN);
            if (update) {
                JOptionPane.showMessageDialog(null, "Sửa phiếu nhập thành công", "thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            pnBUS.loadInfoImport(PhieuNhapGUI.this, tablePhieunhap.getSelectedRow());

        }
    }//GEN-LAST:event_btnSaveActionPerformed
    public static void EditHeaderTable(JTable table) {
        // Tăng độ cao của header
        table.getTableHeader().setPreferredSize(new java.awt.Dimension(0, 40)); // Điều chỉnh 40 thành độ cao
        // Tạo một renderer tùy chỉnh cho header
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JTableHeader header = table.getTableHeader();
                if (header != null) {
                    setForeground(new Color(251, 252, 254)); // Đặt màu chữ
                    setBackground(new Color(134, 172, 218)); // Đặt màu nền
                    Font headerFont = new Font("Josefin Sans", Font.BOLD, 14); // Điều chỉnh font và cỡ chữ
                    header.setFont(headerFont);
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value,
                            isSelected, hasFocus, row, column);
                    label.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa nội dung
                    label.setFont(headerFont);
                }
                setText((value == null) ? "" : value.toString());
                return this;
            }
        };
        // Đặt renderer tùy chỉnh cho header
        table.getTableHeader().setDefaultRenderer(headerRenderer);
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
                    Font headerFont = new Font("Josefin Sans", Font.BOLD, 14); // Adjust font and font size
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
        // Vô hiệu hóa sắp xếp cột tự động
        // table.setAutoCreateRowSorter(false);
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

    public JTable createTableChitietSanpham() {
        // Tiêu đề của các cột
        String[] columnNames = {"ID", "Tên sản phẩm", "SL", "Thành tiền", "", ""};
        modelImportDetail = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 2) { // Cột STT và Số lượng
                    return Integer.class; // Kiểu dữ liệu Integer
                } else if (columnIndex == 3) { // Cột Đơn giá
                    return Long.class; // Kiểu dữ liệu Float
                } else if (columnIndex == 4 || columnIndex == 5) {
                    return ImageIcon.class;
                }
                return String.class; // Các cột khác có kiểu dữ liệu String
            }
        };
        modelImportDetail.setColumnIdentifiers(columnNames);
        // Tạo JTable với DefaultTableModel
        JTable table = new JTable(modelImportDetail);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200); // Độ rộng cột 0
        columnModel.getColumn(1).setPreferredWidth(300); // Độ rộng cột 1
        columnModel.getColumn(2).setPreferredWidth(100); // Độ rộng cột 2
        columnModel.getColumn(3).setPreferredWidth(200); // Độ rộng cột 3
        columnModel.getColumn(4).setPreferredWidth(60); // Độ rộng cột 3
        columnModel.getColumn(5).setPreferredWidth(60); // Độ rộng cột 3

        EditHeaderTable2(table);
        EditTableContent(table);
        Icon iconDelete = new ImageIcon(getClass().getResource("/Assets/icon_24px/cancel.png"));
        Icon iconUpdate = new ImageIcon(getClass().getResource("/Assets/icon_24px/fix.png"));
        table.getColumnModel().getColumn(5).setCellRenderer(new PhieuNhapGUI.ImageRender(iconDelete));
        table.getColumnModel().getColumn(4).setCellRenderer(new PhieuNhapGUI.ImageRender(iconUpdate));
        return table;
    }

    public JTable createTablePhieunhap() {
        // Tiêu đề của các cột
        String[] columnNames = {"STT", "ID Phiếu nhập", "ID Nhân viên", "Ngày lập", "Tổng tiền"};
        modelPhieuNhap = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) { // Cột STT và Số lượng
                    return Integer.class; // Kiểu dữ liệu Integer

                } else if (columnIndex == 4) { // Cột Đơn giá
                    return Long.class; // Kiểu dữ liệu Float

                }
                return String.class; // Các cột khác có kiểu dữ liệu String
            }
        };
        modelPhieuNhap.setColumnIdentifiers(columnNames);
        // Tạo JTable với DefaultTableModel
        JTable table = new JTable(modelPhieuNhap);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60); // Độ rộng cột 0
        columnModel.getColumn(1).setPreferredWidth(200); // Độ rộng cột 1
        columnModel.getColumn(2).setPreferredWidth(200); // Độ rộng cột 2
        columnModel.getColumn(3).setPreferredWidth(200); // Độ rộng cột 3
        columnModel.getColumn(4).setPreferredWidth(200); // Độ rộng cột 5

        sharedFunction.EditHeaderTable(table);
        sharedFunction.EditTableContent(table);
        return table;

    }

    public void findPhieuNhapByMaPN(String maPN, DefaultTableModel model) {
        ArrayList<PhieuNhapDTO> dspn = pnBUS.loadPhieuNhap();

        if (maPN.isEmpty() || maPN.trim().equals("Tìm kiếm theo mã phiếu nhập")) {
            loadDataTablePhieuNhap(dspn, modelPhieuNhap);
        } else {
            if (maPN.toUpperCase().startsWith("PN")) {
                // Nếu chuỗi bắt đầu bằng "PN", tìm kiếm trong danh sách mã hóa đơn
                String maPNDisplay = maPN.toUpperCase();
                ArrayList<PhieuNhapDTO> filteredList = new ArrayList<>();

                for (PhieuNhapDTO phieuNhap : dspn) {
                    String maPNtext = sharedFunction.FormatID("PN", phieuNhap.getMaPN());
                    if (maPNtext.equals(maPNDisplay)) {
                        filteredList.add(phieuNhap);
                    }
                }

                if (!filteredList.isEmpty()) {
                    loadDataTablePhieuNhap(filteredList, model);
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                int maPNnumber = sharedFunction.convertToInteger(maPN);
                if (maPNnumber == -1) {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ArrayList<PhieuNhapDTO> listPhieuNhap = pnBUS.findPhieuNhapByMaPN(maPNnumber);
                    if (!listPhieuNhap.isEmpty()) {
                        loadDataTablePhieuNhap(listPhieuNhap, model);
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }

    public static void loadDataTablePhieuNhap(ArrayList<PhieuNhapDTO> listPhieuNhap, DefaultTableModel modelPhieuNhap) {
        modelPhieuNhap.setRowCount(0);
        int STT = 1;
        for (PhieuNhapDTO pn : listPhieuNhap) {
            int maPN = pn.getMaPN();
            String maPNtext = sharedFunction.FormatID("PN", maPN);
            String maNV = pn.getTenTK();
            Date ngayLap = pn.getNgayTao();
            long TongTien = pn.getTongTien();
            String TongTienText = sharedFunction.formatVND(TongTien);
            Object[] row = {STT++, maPNtext, maNV, ngayLap, TongTienText};
            modelPhieuNhap.addRow(row);
        }
    }

    public void removePlaceholderStyle(JTextField textFiled) {
        textFiled.setForeground(Color.black);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelTable;
    private javax.swing.JPanel PanelTable2;
    private Components.ButtonRadius btnCancel;
    private Components.ButtonRadius btnLammoi;
    private Components.ButtonRadius btnSave;
    private Components.ButtonRadius btnTimkiem;
    private Components.ButtonRadius btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField tfCongTy;
    private javax.swing.JTextField tfIDHoadon;
    private javax.swing.JTextField tfIDNhanVien;
    private javax.swing.JTextField tfNgayLap;
    private javax.swing.JTextField tfTimkiem;
    private javax.swing.JTextField tfTongTien;
    // End of variables declaration//GEN-END:variables
}
