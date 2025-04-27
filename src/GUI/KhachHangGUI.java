/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import GUI.CTKhachHangGUI;
import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import Util.sharedFunction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran Hoai bao
 */
public class KhachHangGUI extends javax.swing.JFrame {

    private static JTable tableKhachHang; // Bảng khách hàng
    private static DefaultTableModel modelKhachHang; // Model dữ liệu cho bảng khách hàng
    private KhachHangBUS khachHangBUS;
    private BanHangGUI banHangGUI;
    private static final long serialVersionUID = 1L;

    public KhachHangGUI(BanHangGUI banHangGUI) {
        initComponents();
        createTableKhachHang();
        PanelTable = new javax.swing.JPanel();
        PanelTable.setLayout(new BorderLayout());
        getContentPane().add(PanelTable, BorderLayout.CENTER);
        this.banHangGUI = banHangGUI;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        BtnTimKiem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bthThem = new javax.swing.JButton();
        bthChon = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        PanelTable = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(253, 183, 58));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(253, 183, 58));
        jLabel1.setText("Thông Tin Khách Hàng");

        jLabel3.setForeground(new java.awt.Color(253, 183, 58));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_40px/employee_1.png"))); // NOI18N
        jLabel3.setText("jLabel3");

        jLabel4.setForeground(new java.awt.Color(253, 183, 58));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/search.png"))); // NOI18N

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        BtnTimKiem.setBackground(new java.awt.Color(250, 232, 189));
        BtnTimKiem.setForeground(new java.awt.Color(134, 172, 218));
        BtnTimKiem.setText("Tìm Kiếm ");
        BtnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnTimKiem)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(BtnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(7, Short.MAX_VALUE))))
        );

        BtnTimKiem.getAccessibleContext().setAccessibleParent(txtTimKiem);

        jButton2.setBackground(new java.awt.Color(250, 232, 189));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(134, 172, 218));
        jButton2.setText("Xem Chi tiết khách hàng ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        bthThem.setBackground(new java.awt.Color(250, 232, 189));
        bthThem.setForeground(new java.awt.Color(134, 172, 218));
        bthThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/icon_24px/add.png"))); // NOI18N
        bthThem.setText("Thêm Khách Hàng");
        bthThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthThemActionPerformed(evt);
            }
        });

        bthChon.setBackground(new java.awt.Color(250, 232, 189));
        bthChon.setForeground(new java.awt.Color(134, 172, 218));
        bthChon.setText("Chọn khánh hàng");
        bthChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthChonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jButton2)
                                .addGap(32, 32, 32)
                                .addComponent(bthThem, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(bthChon)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bthThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bthChon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout PanelTableLayout = new javax.swing.GroupLayout(PanelTable);
        PanelTable.setLayout(PanelTableLayout);
        PanelTableLayout.setHorizontalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelTableLayout.setVerticalGroup(
            PanelTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 509, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void bthThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthThemActionPerformed
        CTKhachHangGUI ctkh = new CTKhachHangGUI();

        // Lấy ID cuối cùng từ KhachHangBUS
        KhachHangBUS khBus = new KhachHangBUS();
        int lastId = khBus.selectLastId() + 1;

        if (lastId > 0) {
            ctkh.setId(lastId);
            ctkh.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Không thể lấy ID cuối cùng!");
        }
    }//GEN-LAST:event_bthThemActionPerformed
    private void createTableKhachHang() {
        // Tạo bảng khách hàng
        tableKhachHang = createTableKhachHangModel();
        // Lấy dữ liệu khách hàng từ BUS
        KhachHangBUS khachHangBUS = new KhachHangBUS();
        ArrayList<KhachHangDTO> listKhachHang = khachHangBUS.getAllKhachHang();

        // Load dữ liệu vào bảng
        loadTableKhachHang(listKhachHang, modelKhachHang);

        // Đặt kích thước bảng bằng với Panel chứa nó
        tableKhachHang.setPreferredScrollableViewportSize(PanelTable.getPreferredSize());

        // Tạo JScrollPane để chứa bảng
        JScrollPane scrollPaneKhachHang = new JScrollPane(tableKhachHang);

        // Thêm viền cho JScrollPane
        MatteBorder matteBorder = new MatteBorder(0, 1, 1, 1, new Color(164, 191, 226));
        scrollPaneKhachHang.setBorder(matteBorder);
        // Thêm bảng vào PanelTable
        PanelTable.removeAll(); // Xóa nội dung cũ trước khi thêm mới
        PanelTable.setLayout(new BorderLayout());
        PanelTable.add(scrollPaneKhachHang, BorderLayout.CENTER);
        PanelTable.revalidate();
        PanelTable.repaint();
    }

    private JTable createTableKhachHangModel() {
        String[] columnNames = {"ID", "Họ tên", "SĐT", "Email", "Địa chỉ", "Ngày tạo"};
        modelKhachHang = new DefaultTableModel(columnNames, 0);
        return new JTable(modelKhachHang);
    }

    private void loadTableKhachHang(ArrayList<KhachHangDTO> listKhachHang, DefaultTableModel model) {
        model.setRowCount(0); // Xóa dữ liệu cũ trước khi tải mới
        for (KhachHangDTO kh : listKhachHang) {
            model.addRow(new Object[]{
                kh.getIdKH(),
                kh.getHoTen(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getDiaChi(),
                kh.getNgayTao()
            });
        }
    }

    private void timKiemKhachHang() {
        String keyword = BtnTimKiem.getText().trim(); // Lấy giá trị từ ô tìm kiếm

        if (keyword.isEmpty() || keyword.equals("Tìm kiếm theo mã hoặc tên khách hàng")) {
            // Nếu ô tìm kiếm rỗng, hiển thị tất cả khách hàng
            ArrayList<KhachHangDTO> listKhachHang = (ArrayList<KhachHangDTO>) khachHangBUS.getAllKhachHang();
            loadTableKhachHang(listKhachHang, modelKhachHang);
        } else {
            if (keyword.toUpperCase().startsWith("KH")) {
                // Nếu bắt đầu bằng "KH", tìm kiếm theo mã khách hàng có định dạng KHxxx
                String maKHDisplay = keyword.toUpperCase();
                ArrayList<KhachHangDTO> filteredList = new ArrayList<>();

                for (KhachHangDTO khachHang : khachHangBUS.getAllKhachHang()) {
                    String maKHtext = sharedFunction.FormatID("KH", khachHang.getIdKH());
                    if (maKHtext.equals(maKHDisplay)) {
                        filteredList.add(khachHang);
                    }
                }

                if (!filteredList.isEmpty()) {
                    loadTableKhachHang(filteredList, modelKhachHang);
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                int maKHnumber = sharedFunction.convertToInteger(keyword);
                if (maKHnumber != -1) {
                    // Nếu nhập số, tìm kiếm theo mã khách hàng
                    ArrayList<KhachHangDTO> listKhachHang = (ArrayList<KhachHangDTO>) khachHangBUS.timKhachHangTheoMa(maKHnumber);
                    if (!listKhachHang.isEmpty()) {
                        loadTableKhachHang(listKhachHang, modelKhachHang);
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    // Nếu không phải số, tìm kiếm theo tên khách hàng
                    ArrayList<KhachHangDTO> listKhachHang = (ArrayList<KhachHangDTO>) khachHangBUS.timKhachHangTheoTen(keyword);
                    if (!listKhachHang.isEmpty()) {
                        loadTableKhachHang(listKhachHang, modelKhachHang);
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }


    private void BtnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnTimKiemActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bthChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthChonActionPerformed
        int selectRow = tableKhachHang.getSelectedRow();
        if (selectRow >= 0) {
            BanHangGUI.idKH = Integer.parseInt(tableKhachHang.getValueAt(selectRow, 0).toString());
            banHangGUI.loadIdKH();
            this.setVisible(false);
        }
    }//GEN-LAST:event_bthChonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnTimKiem;
    private javax.swing.JPanel PanelTable;
    private javax.swing.JButton bthChon;
    private javax.swing.JButton bthThem;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

}
