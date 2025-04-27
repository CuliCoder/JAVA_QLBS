/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import GUI.LocAnd;
import GUI.MainFrameGUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ASUS
 */
public class sharedFunction {

    private static int mouseX, mouseY;

    public static void moveLayout(JFrame jframe, JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                jframe.setLocation(x - mouseX, y - mouseY);
            }
        });
    }

    public static void addPlaceholder(JTextField textField, String searchPlaceholder) {
        textField.setText(searchPlaceholder); // Hiển thị placeholder mặc định                     
        // FocusListener để xử lý khi nhập vào hoặc ra khỏi JTextField
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Khi click vào JTextField
                if (textField.getText().equals(searchPlaceholder)) {
                    textField.setText(""); // Xóa nội dung khi focus vào
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Khi click ra khỏi JTextField
                if (textField.getText().isEmpty()) {
//                    textField.setText(searchPlaceholder); // Hiển thị placeholder khi không focus
                }
            }
        });

        // MouseListener để xử lý khi con trỏ chuột rời khỏi JTextField
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                if (!textField.isFocusOwner() && textField.getText().isEmpty()) {
                    textField.setText(searchPlaceholder); // Hiển thị placeholder khi con trỏ chuột rời khỏi và nội dung trống
                }
            }
        });
    }

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
                    Font headerFont = new Font("Segoe UI", Font.BOLD, 16); // Điều chỉnh font và cỡ chữ
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

    public static void EditTableContent(JTable table) {
        // Đặt độ cao cho từng dòng (trừ header)

        int rowHeight = 30;
        table.setRowHeight(rowHeight);
        table.setGridColor(new Color(153, 184, 224));
        table.setShowGrid(true);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(Color.WHITE);
        table.setSelectionForeground(new Color(253, 191, 84));
        table.setFocusable(false);
        table.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        table.setFont(font);
        // Căn giữa nội dung trong các ô chữ
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        // Vô hiệu hóa sửa nội dung trong ô
        table.setDefaultEditor(Object.class, null);
        // Vô hiệu hóa sắp xếp cột tự động
        table.setAutoCreateRowSorter(true);
        // Vô hiệu hóa kéo cột
        table.getTableHeader().setReorderingAllowed(false);
        // Vô hiệu hóa kéo dãng cột
        table.getTableHeader().setResizingAllowed(false);
    }

    public static boolean isPositiveInteger(String str) {
        try {
            int number = Integer.parseInt(str);
            return number > 0;
        } catch (NumberFormatException e) {
            // Nếu có lỗi NumberFormatException, chuỗi không phải là số nguyên.
            return false;
        }
    }

    public static int convertToInteger(String input) {

            try {
                return Integer.parseInt(input); // Trả về số nguyên nếu thành công
            } catch (NumberFormatException e) {
                // Xử lý lỗi nếu không thể chuyển đổi thành số nguyên
                return -1;
            }
        
    }

    public static void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public static String FormatID(String prefix, int ma) {
        return String.format("%s%02d", prefix, ma);
    }

    public static String formatVND(double temp) {
        // Tạo một đối tượng NumberFormat cho tiền tệ của Việt Nam
        NumberFormat vndFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Sử dụng đối tượng NumberFormat để định dạng số tiền
        return vndFormat.format(temp);
    }

    public static double parseMoneyString(String moneyString) {

        // Loại bỏ các ký tự không cần thiết từ chuỗi đơn giá
        moneyString = moneyString.replaceAll("[^\\d.]", "").replaceAll("\\.", "").trim();

        // Chuyển đổi chuỗi thành số double
        double amount = Double.parseDouble(moneyString);
        return amount;

    }

    public static long parseVNDString(String moneyString) {

        // Loại bỏ các ký tự không cần thiết từ chuỗi đơn giá
        moneyString = moneyString.replaceAll("[^\\d.]", "").replaceAll("\\.", "").trim();

        // Chuyển đổi chuỗi thành số double
        long amount = Long.parseLong(moneyString);
        return amount;

    }

    public static double calculateTotalPrice(DefaultTableModel model, int priceColumnIndex) {
        double totalPrice = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            String priceString = (String) model.getValueAt(i, priceColumnIndex); // priceColumnIndex là chỉ mục cột đơn giá       
            // Loại bỏ các ký tự không hợp lệ
            priceString = priceString.replaceAll("[^\\d.]", "").replaceAll("\\.", "").trim();
            try {
                double price = Double.parseDouble(priceString);
                // Thực hiện tính tổng
                totalPrice += price;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return totalPrice;
    }

    public static Date stringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static double stringToDouble(String doubleString) {
        try {
            return Double.parseDouble(doubleString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0; // Xử lý lỗi và trả về giá trị mặc định nếu có lỗi
        }
    }

    public static int stringToInteger(String intString) {
        try {
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0; // Xử lý lỗi và trả về giá trị mặc định nếu có lỗi
        }
    }

    public static void openNewFrame(LocAnd frame) {
        // Tạo JFrame mới
        // Lấy vị trí của JFrame chính 
        JFrame mainFrame = MainFrameGUI.getMainFrameInstance();
        int mainFrameX = mainFrame.getX();
        int mainFrameY = mainFrame.getY();

        // Tính toán vị trí cho JFrame mới
        int newX = mainFrameX + 270; //  tăng theo trục x
        int newY = mainFrameY + 35; //  tăng theo trục y

        // Đặt vị trí của JFrame mới
        frame.setLocation(newX, newY);

        frame.setVisible(true);
    }

    public static String getCurrentDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String chooseFilePath(String fileType) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter;

        switch (fileType.toLowerCase()) {
            case "pdf" ->
                filter = new FileNameExtensionFilter("PDF files (*.pdf)", "pdf");
            case "xlsx" ->
                filter = new FileNameExtensionFilter("Excel files (*.xlsx)", "xlsx");
            default ->
                throw new IllegalArgumentException("Unsupported file type: " + fileType);
        }

        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if (!filePath.toLowerCase().endsWith("." + fileType.toLowerCase())) {
                filePath += "." + fileType.toLowerCase();
            }

            return filePath;
        } else {
            return null;
        }
    }
}
