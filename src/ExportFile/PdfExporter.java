/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ExportFile;

import Util.sharedFunction;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PdfExporter {

    private static final String FONT_PATH = "Libraries/Font/TimesNewRoman/SVN-Times New Roman.ttf";

    public static void exportToPdfDoanhThu(JTable dataTable, String filePath, String namBatDau, String namKetThuc, String nguoiTao, String Title, String reportType) {
        Document document = new Document();

        try {
            Font fontTitle = setFont(FONT_PATH, 18, Font.BOLD, BaseColor.BLACK);
            Font fontTitleTable = setFont(FONT_PATH, 14, Font.BOLD, BaseColor.BLACK);
            Font fontNormal = setFont(FONT_PATH, 14, Font.NORMAL, BaseColor.BLACK);
            File file = new File(filePath);
            if (file.exists()) {
                sharedFunction.displayErrorMessage("File đã tồn tại");
                return;
            }

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            String titleText = generateTitle(namBatDau, namKetThuc, Title, reportType);
            Paragraph title = new Paragraph(titleText, fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);

            Paragraph ngayTaoVaNguoiTao = new Paragraph("Nhân Viên: " + nguoiTao + "                                            Ngày Tạo: " + sharedFunction.getCurrentDateTime(), fontNormal);
            ngayTaoVaNguoiTao.setAlignment(Element.ALIGN_JUSTIFIED);
            document.add(ngayTaoVaNguoiTao);

            document.add(new Paragraph(" "));

            PdfPTable table = createTable(dataTable, fontTitleTable, fontNormal);
            document.add(table);

            JOptionPane.showMessageDialog(null, "Xuất file thành công");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static void exportToPdfReport(String startDate, String endDate, JTable dataTable, String Title, String filePath) {
        Document document = new Document();

        try {
            Font fontTitle = setFont(FONT_PATH, 18, Font.BOLD, BaseColor.BLACK);
            Font fontTitleTable = setFont(FONT_PATH, 14, Font.BOLD, BaseColor.BLACK);
            Font fontNormal = setFont(FONT_PATH, 14, Font.NORMAL, BaseColor.BLACK);
            File file = new File(filePath);
            if (file.exists()) {
                JOptionPane.showMessageDialog(null, "File đã tồn tại");
                return;
            }

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Tiêu đề
            String titleText = Title + startDate + " đến " + endDate;
            Paragraph title = new Paragraph(titleText, fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);

            // Bảng dữ liệu
            PdfPTable table = createTable(dataTable, fontTitleTable, fontNormal);
            document.add(table);

            JOptionPane.showMessageDialog(null, "Xuất file thành công");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    public static void exportToPdfHoaDon(String invoiceNumber, String creationDate, String cashier, JTable dataTable, String totalAmount, String customerPayment, String changeAmount, String filePath) {
        Document document = new Document();

        try {
            Font fontTitle = setFont(FONT_PATH, 18, Font.BOLD, BaseColor.BLACK);
            Font fontTitleTable = setFont(FONT_PATH, 14, Font.BOLD, BaseColor.BLACK);
            Font fontNormal = setFont(FONT_PATH, 14, Font.NORMAL, BaseColor.BLACK);
            File file = new File(filePath);
            if (file.exists()) {
                sharedFunction.displayErrorMessage("File đã tồn tại");
                return;
            }

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Hóa đơn số: " + invoiceNumber, fontNormal));

            document.add(new Paragraph("Ngày tạo: " + creationDate, fontNormal));

            document.add(new Paragraph("Thu ngân: " + cashier, fontNormal));

            document.add(Chunk.NEWLINE);

            Paragraph title = new Paragraph("Hóa đơn thanh toán", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            PdfPTable table = createTable(dataTable, fontTitleTable, fontNormal);
            document.add(table);

            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("Tổng tiền: " + totalAmount, fontNormal));

            // Kiểm tra nếu customerPayment không phải là null
            if (customerPayment != null) {
                document.add(new Paragraph("Tiền khách đưa: " + customerPayment, fontNormal));
            }

            // Kiểm tra nếu changeAmount không phải là null
            if (changeAmount != null) {
                document.add(new Paragraph("Tiền thối lại: " + changeAmount, fontNormal));
            }

            JOptionPane.showMessageDialog(null, "Xuất file thành công");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private static PdfPTable createTable(JTable dataTable, Font fontTitleTable, Font fontNormal) {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();

        PdfPTable table = new PdfPTable(colCount);
        table.setWidthPercentage(100);

        // Thêm tiêu đề cột
        for (int col = 0; col < colCount; col++) {
            PdfPCell cell = new PdfPCell(new Phrase(dataTable.getColumnName(col), fontTitleTable));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
        }

        // Thêm dữ liệu từ JTable
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                PdfPCell cell = new PdfPCell(new Phrase(String.valueOf(model.getValueAt(row, col)), fontNormal));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
            }
        }

        return table;
    }

    private static Font setFont(String fontPath, int size, int style, BaseColor color) {
        try {
            BaseFont unicodeFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            return new Font(unicodeFont, size, style, color);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return new Font(Font.FontFamily.TIMES_ROMAN, size, style, color);
        }
    }

    public static String generateTitle(String namBatDau, String namKetThuc, String title, String reportType) {
        switch (reportType) {
            case "NamBatDauNamKetThuc" -> {
                if (namBatDau.equals(namKetThuc)) {
                    return title + " Năm " + namBatDau;
                } else {
                    return title + "  Từ Năm " + namBatDau + " Đến Năm " + namKetThuc;
                }
            }
            case "Nam" -> {
                return title + " Trong Năm " + namBatDau;
            }
            case "Thang" -> {
                return title + namKetThuc + " năm  " + namBatDau;
            }
            case "NgayDenNgay" -> {
                if (namBatDau.equals(namKetThuc)) {
                    return title + " Ngay " + namBatDau;
                } else {
                    return title + " Từ Ngày " + namBatDau + " Đến Ngày " + namKetThuc;
                }

            }
            default -> {
                return title;
            }
        }
    }
}
