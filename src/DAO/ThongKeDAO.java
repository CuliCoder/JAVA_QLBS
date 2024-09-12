/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Connection.ConnectDB;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeHoaDonBanDTO;
import DTO.ThongKe.ThongKeSanPhamBanDTO;
import DTO.ThongKe.ThongKeTheLoaiBanDTO;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ASUS
 */
public class ThongKeDAO {

    public ArrayList< ThongKeDoanhThuDTO> thongKeDoanhThuTheoNam() {
        ArrayList< ThongKeDoanhThuDTO> danhSachDoanhThu = new ArrayList<>();
        String query = "SELECT YEAR(NgayTao) AS Nam, SUM(TongTien) AS DoanhThu FROM HoaDon GROUP BY YEAR(NgayTao)";

        try (
                java.sql.Connection conn = ConnectDB.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int nam = resultSet.getInt("Nam");
                long doanhThu = resultSet.getLong("DoanhThu");
                long von = tinhVonTrongNam(nam, conn);
                long loiNhuan = doanhThu - von;

                ThongKeDoanhThuDTO doanhThuObj = new ThongKeDoanhThuDTO(String.valueOf(nam), von, doanhThu, loiNhuan);
                danhSachDoanhThu.add(doanhThuObj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachDoanhThu;
    }

    public ArrayList< ThongKeDoanhThuDTO> thongKeDoanhThuTuNamDenNam(int namBatDau, int namKetThuc) {
        ArrayList< ThongKeDoanhThuDTO> danhSachDoanhThu = new ArrayList<>();
        String query = "SELECT YEAR(NgayTao) AS Nam, SUM(TongTien) AS DoanhThu FROM HoaDon WHERE YEAR(NgayTao) BETWEEN ? AND ? GROUP BY YEAR(NgayTao)";

        try (
                java.sql.Connection conn = ConnectDB.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setInt(1, namBatDau);
            preparedStatement.setInt(2, namKetThuc);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int nam = resultSet.getInt("Nam");
                    long doanhThu = resultSet.getLong("DoanhThu");
                    long von = tinhVonTrongNam(nam, conn);
                    long loiNhuan = doanhThu - von;

                    ThongKeDoanhThuDTO doanhThuObj = new ThongKeDoanhThuDTO(String.valueOf(nam), von, doanhThu, loiNhuan);
                    danhSachDoanhThu.add(doanhThuObj);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachDoanhThu;
    }

    private long tinhVonTrongNam(int nam, java.sql.Connection connection) {
        long von = 0;
        String query = "SELECT SUM(TongTien) AS Von FROM PhieuNhap WHERE YEAR(NgayTao) = ?";

        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, nam);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    von = resultSet.getLong("Von");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return von;
    }

    public ArrayList<ThongKeDoanhThuDTO> thongKeDoanhThuTheoThang(int nam) {
        ArrayList<ThongKeDoanhThuDTO> danhSachDoanhThu = new ArrayList<>();

        // Tạo bảng tạm với tất cả các tháng trong năm
        String createTempTableQuery = "CREATE TABLE #AllMonths (Month INT);"
                + "INSERT INTO #AllMonths VALUES (1), (2), (3), (4), (5), (6), (7), (8), (9), (10), (11), (12);";

        String selectQuery = "SELECT M.Month AS Thang, ISNULL(SUM(HD.TongTien), 0) AS DoanhThu "
                + "FROM #AllMonths M "
                + "LEFT JOIN HoaDon HD ON M.Month = MONTH(HD.NgayTao) AND YEAR(HD.NgayTao) = ? "
                + "GROUP BY M.Month;";

        String dropTempTableQuery = "DROP TABLE #AllMonths;";

        try (java.sql.Connection conn = ConnectDB.getConnection(); java.sql.Statement statement = conn.createStatement(); PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {

            statement.executeUpdate(createTempTableQuery);

            preparedStatement.setInt(1, nam);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int thang = resultSet.getInt("Thang");
                    long doanhThu = resultSet.getLong("DoanhThu");
                    long von = tinhVonTungThangTrongNam(nam, conn, thang);
                    long loiNhuan = doanhThu - von;

                    ThongKeDoanhThuDTO doanhThuObj = new ThongKeDoanhThuDTO(String.valueOf(thang), von, doanhThu, loiNhuan);
                    danhSachDoanhThu.add(doanhThuObj);
                }
            }

            // Xóa bảng tạm
            statement.executeUpdate(dropTempTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachDoanhThu;
    }

    public ArrayList<ThongKeDoanhThuDTO> thongKeDoanhThuTungNgayTrongThang(int nam, int thang) {
        ArrayList<ThongKeDoanhThuDTO> danhSachDoanhThu = new ArrayList<>();

        // Kiểm tra số ngày trong tháng
        int soNgayTrongThang = YearMonth.of(nam, thang).lengthOfMonth();
        // Tạo bảng tạm với tất cả các ngày trong tháng
        String createTempTableQuery = "CREATE TABLE #AllDays (Day INT);";
        for (int i = 1; i <= soNgayTrongThang; i++) {
            createTempTableQuery += "INSERT INTO #AllDays VALUES (" + i + ");";
        }

        String selectQuery = "SELECT D.Day AS Ngay, ISNULL(SUM(HD.TongTien), 0) AS DoanhThu "
                + "FROM #AllDays D "
                + "LEFT JOIN HoaDon HD ON D.Day = DAY(HD.NgayTao) AND MONTH(HD.NgayTao) = ? AND YEAR(HD.NgayTao) = ? "
                + "GROUP BY D.Day;";

        String dropTempTableQuery = "DROP TABLE #AllDays;";

        try (java.sql.Connection conn = ConnectDB.getConnection(); java.sql.Statement statement = conn.createStatement(); PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {

            statement.executeUpdate(createTempTableQuery);

            preparedStatement.setInt(1, thang);
            preparedStatement.setInt(2, nam);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String ngay = resultSet.getString("Ngay");
                    long doanhThu = resultSet.getLong("DoanhThu");
                    long von = tinhVonTungNgayTrongThang(nam, thang, conn, ngay);
                    long loiNhuan = doanhThu - von;

                    ThongKeDoanhThuDTO doanhThuObj = new ThongKeDoanhThuDTO(ngay, von, doanhThu, loiNhuan);
                    danhSachDoanhThu.add(doanhThuObj);
                }
            }

            // Xóa bảng tạm
            statement.executeUpdate(dropTempTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachDoanhThu;
    }

    public ArrayList<ThongKeDoanhThuDTO> thongKeDoanhThuTuNgayDenNgay(Date ngayBatDau, Date ngayKetThuc) {
        ArrayList<ThongKeDoanhThuDTO> danhSachDoanhThu = new ArrayList<>();

        if (ngayBatDau == null || ngayKetThuc == null) {
            // một trong hai ngày là null
            return danhSachDoanhThu;
        }

        // Tạo bảng tạm với tất cả các ngày trong khoảng thời gian        
        String createTempTableQuery = "CREATE TABLE #AllDays (Ngay DATE);";
        String insertDaysQuery = "INSERT INTO #AllDays VALUES (?);";

        String selectQuery = "SELECT A.Ngay AS Ngay, ISNULL(SUM(HD.TongTien), 0) AS DoanhThu "
                + "FROM #AllDays A "
                + "LEFT JOIN HoaDon HD ON A.Ngay = CONVERT(DATE, HD.NgayTao) "
                + "WHERE A.Ngay BETWEEN ? AND ? "
                + "GROUP BY A.Ngay;";

        // Xóa bảng tạm sau khi sử dụng
        String dropTempTableQuery = "DROP TABLE #AllDays;";

        try (java.sql.Connection conn = ConnectDB.getConnection(); java.sql.Statement createTempTableStatement = conn.createStatement(); PreparedStatement insertDaysStatement = conn.prepareStatement(insertDaysQuery); PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {

            createTempTableStatement.executeUpdate(createTempTableQuery);

            java.sql.Date currentDate = new java.sql.Date(ngayBatDau.getTime());
            while (!currentDate.after(ngayKetThuc)) {
                insertDaysStatement.setDate(1, new java.sql.Date(currentDate.getTime()));
                insertDaysStatement.executeUpdate();
                currentDate = new java.sql.Date(currentDate.getTime() + 24 * 60 * 60 * 1000); // Tăng 1 ngày
            }

            preparedStatement.setDate(1, new java.sql.Date(ngayBatDau.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(ngayKetThuc.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    java.sql.Date ngay = resultSet.getDate("Ngay");
                    long doanhThu = resultSet.getLong("DoanhThu");
                    long von = tinhVonTungNgayTrongKhoangThoiGian(ngay, conn);
                    long loiNhuan = doanhThu - von;

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDate = dateFormat.format(ngay);

                    ThongKeDoanhThuDTO doanhThuObj = new ThongKeDoanhThuDTO(formattedDate, von, doanhThu, loiNhuan);
                    danhSachDoanhThu.add(doanhThuObj);
                }
            }

            // Xóa bảng tạm
            createTempTableStatement.executeUpdate(dropTempTableQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachDoanhThu;
    }

// Hàm tính vốn từ ngày đến ngày cho mỗi ngày trong khoảng
    private long tinhVonTungNgayTrongKhoangThoiGian(java.sql.Date ngay, java.sql.Connection connection) {
        long von = 0;

        String query = "SELECT SUM(TongTien) AS Von FROM PhieuNhap WHERE NgayTao = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, ngay);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    von = resultSet.getLong("Von");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return von;
    }

    private long tinhVonTungNgayTrongThang(int nam, int thang, java.sql.Connection connection, String ngay) {
        long von = 0;

        // Câu truy vấn tính vốn cho từng ngày trong tháng
        String query = "SELECT SUM(TongTien) AS Von "
                + "FROM PhieuNhap "
                + "WHERE YEAR(NgayTao) = ? AND MONTH(NgayTao) = ? AND DAY(NgayTao) = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, nam);
            preparedStatement.setInt(2, thang);
            preparedStatement.setString(3, ngay);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    von = resultSet.getLong("Von");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return von;
    }

    private long tinhVonTungThangTrongNam(int nam, java.sql.Connection connection, int thang) {
        long von = 0;
        String query = "SELECT SUM(TongTien) AS Von FROM PhieuNhap WHERE YEAR(NgayTao) = ? AND MONTH(NgayTao) = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, nam);
            preparedStatement.setInt(2, thang);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    von = resultSet.getLong("Von");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return von;
    }

    public Map<String, Integer> thongKeSoLuongSachTheoTheLoai() {
        Map<String, Integer> thongKe = new HashMap<>();

        try {
            java.sql.Connection conn = ConnectDB.getConnection();
            String sql = "SELECT TheLoai.TenTL, COUNT(Sach.MaSach) AS SoLuongSach "
                    + "FROM Sach "
                    + "JOIN TheLoai ON Sach.MaTL = TheLoai.MaTL "
                    + "GROUP BY TheLoai.TenTL";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tenTL = rs.getString("TenTL");
                int soLuongSach = rs.getInt("SoLuongSach");
                thongKe.put(tenTL, soLuongSach);
            }

            ConnectDB.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return thongKe;
    }

    public Set<Integer> getDistinctYears() {
        Set<Integer> years = new HashSet<>();

        try {
            java.sql.Connection conn = ConnectDB.getConnection();
            String query = "SELECT DISTINCT YEAR(NgayTao) AS Nam FROM HoaDon";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int year = resultSet.getInt("Nam");
                years.add(year);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return years;
    }

    // Thống kê sản phẩm bán 
    public ArrayList<ThongKeSanPhamBanDTO> thongKeSanPhamBanTrongKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) {

        ArrayList<ThongKeSanPhamBanDTO> danhSachSanPham = new ArrayList<>();
        if (ngayBatDau == null || ngayKetThuc == null) {
            // một trong hai ngày là null
            return danhSachSanPham;
        }
        String query = "SELECT SP.MaSP, SP.TenSP, "
                + "SUM(CTHD.SoLuong) AS SoLuongBan, "
                + "COUNT(DISTINCT HD.MaHD) AS SoDonBan, "
                + "SUM(CTHD.SoLuong * CTHD.DonGia) AS DoanhThu "
                + "FROM ChiTietHoaDon CTHD "
                + "JOIN HoaDon HD ON CTHD.MaHD = HD.MaHD "
                + "JOIN SanPham SP ON CTHD.MaSP = SP.MaSP "
                + "WHERE HD.NgayTao BETWEEN ? AND ? "
                + "GROUP BY SP.MaSP, SP.TenSP "
                + "ORDER BY SoLuongBan DESC";

        try (java.sql.Connection conn = ConnectDB.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setDate(1, new java.sql.Date(ngayBatDau.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(ngayKetThuc.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String maSP = resultSet.getString("MaSP");
                    String tenSP = resultSet.getString("TenSP");
                    int soLuongBan = resultSet.getInt("SoLuongBan");
                    int soDonBan = resultSet.getInt("SoDonBan");
                    long doanhThu = resultSet.getLong("DoanhThu");

                    ThongKeSanPhamBanDTO thongKeObj = new ThongKeSanPhamBanDTO(maSP, tenSP, soLuongBan, soDonBan, doanhThu);
                    danhSachSanPham.add(thongKeObj);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachSanPham;
    }

    // Thống kê thể loại
    public ArrayList<ThongKeTheLoaiBanDTO> thongKeTheLoaiTrongKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) {
        ArrayList<ThongKeTheLoaiBanDTO> danhSachTheLoai = new ArrayList<>();

        if (ngayBatDau == null || ngayKetThuc == null) {
            // Một trong hai ngày là null
            return danhSachTheLoai;
        }

        String query = "SELECT TL.MaTL, TL.TenTL, "
                + "COUNT(DISTINCT HD.MaHD) AS SoDonBan, "
                + "SUM(CTHD.SoLuong) AS SoLuongSanPhamBan, "
                + "SUM(CTHD.SoLuong * CTHD.DonGia) AS DoanhThu, "
                + "(SELECT COUNT(DISTINCT SP.MaSP) FROM ChiTietHoaDon CTHD1 "
                + "JOIN SanPham SP ON CTHD1.MaSP = SP.MaSP "
                + "WHERE SP.MaTL = TL.MaTL) AS SoLoaiSanPham "
                + "FROM ChiTietHoaDon CTHD "
                + "JOIN HoaDon HD ON CTHD.MaHD = HD.MaHD "
                + "JOIN SanPham SP ON CTHD.MaSP = SP.MaSP "
                + "JOIN TheLoai TL ON SP.MaTL = TL.MaTL "
                + "WHERE HD.NgayTao BETWEEN ? AND ? "
                + "GROUP BY TL.MaTL, TL.TenTL "
                + "ORDER BY SoLuongSanPhamBan DESC";

        try (java.sql.Connection conn = ConnectDB.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(ngayBatDau.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(ngayKetThuc.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String maTL = resultSet.getString("MaTL");
                    String tenTL = resultSet.getString("TenTL");
                    int soDonBan = resultSet.getInt("SoDonBan");
                    int soLuongSanPhamBan = resultSet.getInt("SoLuongSanPhamBan");
                    long doanhThu = resultSet.getLong("DoanhThu");
                    int soLoaiSanPham = resultSet.getInt("SoLoaiSanPham");

                    ThongKeTheLoaiBanDTO thongKeObj = new ThongKeTheLoaiBanDTO(maTL, tenTL, soLuongSanPhamBan, soDonBan, soLoaiSanPham, doanhThu);
                    danhSachTheLoai.add(thongKeObj);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachTheLoai;
    }

// Thống kê Số hóa đơn bán được trong khoảng thời gian
    public ArrayList<ThongKeHoaDonBanDTO> thongKeHoaDonTrongKhoangThoiGian(Date ngayBatDau, Date ngayKetThuc) {
        ArrayList<ThongKeHoaDonBanDTO> danhSachHoaDon = new ArrayList<>();

        if (ngayBatDau == null || ngayKetThuc == null) {
            // Một trong hai ngày là null
            return danhSachHoaDon;
        }

        String query = "WITH AllDays AS ( "
                + "    SELECT ? AS Ngay "
                + "    UNION ALL "
                + "    SELECT DATEADD(DAY, 1, Ngay) "
                + "    FROM AllDays "
                + "    WHERE DATEADD(DAY, 1, Ngay) <= ? "
                + ") "
                + "SELECT A.Ngay, "
                + "    COUNT(DISTINCT HD.MaHD) AS SoLuongDon, "
                + "    SUM(ISNULL(CTHD.SoLuong, 0)) AS SoLuongSanPham, "
                + "    COUNT(DISTINCT CTHD.MaSP) AS SoLoaiSanPham, "
                + "    SUM(ISNULL(CTHD.SoLuong * CTHD.DonGia, 0)) AS DoanhThu "
                + "FROM AllDays A "
                + "LEFT JOIN HoaDon HD ON A.Ngay = CONVERT(DATE, HD.NgayTao) "
                + "LEFT JOIN ChiTietHoaDon CTHD ON HD.MaHD = CTHD.MaHD "
                + "GROUP BY A.Ngay "
                + "ORDER BY A.Ngay "
                + "OPTION (MAXRECURSION 0)";;

        try (java.sql.Connection conn = ConnectDB.getConnection(); PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setDate(1, new java.sql.Date(ngayBatDau.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(ngayKetThuc.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Date ngay = resultSet.getDate("Ngay");
                    int soLuongDon = resultSet.getInt("SoLuongDon");
                    int soLuongSanPham = resultSet.getInt("SoLuongSanPham");
                    int soLoaiSanPham = resultSet.getInt("SoLoaiSanPham");
                    long doanhThu = resultSet.getLong("DoanhThu");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDate = dateFormat.format(ngay);
                    ThongKeHoaDonBanDTO thongKeObj = new ThongKeHoaDonBanDTO(formattedDate, soLuongDon, soLuongSanPham, soLoaiSanPham, doanhThu);
                    danhSachHoaDon.add(thongKeObj);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachHoaDon;
    }

}
