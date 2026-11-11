package opp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ListHangThucPham ql = new ListHangThucPham();
    static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        duLieuMau();
        int chon;
        do {
            menu();
            try {
                chon = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số!");
                chon = -1;
                continue;
            }

            switch (chon) {
                case 1:
                    themThucPham();
                    break;
                case 2:
                    xoaThucPham();
                    break;
                case 3:
                    suaThucPham();
                    break;
                case 4:
                    ql.hienThiDanhSach();
                    break;
                case 5:
                    sapXepMenu();
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (chon != 0);

        sc.close();
    }

    static void menu() {
        System.out.println();
        System.out.println("            QUẢN LÝ THỰC PHẨM               ");
        System.out.println("1. Thêm thực phẩm");
        System.out.println("2. Xóa thực phẩm");
        System.out.println("3. Sửa thực phẩm");
        System.out.println("4. Hiển thị danh sách");
        System.out.println("5. Sắp xếp");
        System.out.println("0. Thoát");
        System.out.print("Chọn: ");
    }

    static void duLieuMau() {
        ql.them(new HangThucPham(1, "TP001", "Sua tuoi Vinamilk", LocalDate.of(2026, 1, 15), LocalDate.of(2026, 2, 25), 25000));
        ql.them(new HangThucPham(2, "TP002", "Banh quy Oreo", LocalDate.of(2026, 3, 5), LocalDate.of(2026, 6, 5), 15000));
        ql.them(new HangThucPham(3, "TP003", "Nuoc ngot Coca", LocalDate.of(2026, 3, 20), LocalDate.of(2026, 9, 12), 12000));
        ql.them(new HangThucPham(4, "TP004", "Thit heo tuoi", LocalDate.of(2026, 5, 10), LocalDate.of(2026, 5, 25), 80000));
        ql.them(new HangThucPham(5, "TP005", "Dau an Tuong An", LocalDate.of(2026, 5, 15), LocalDate.of(2026, 8, 5), 88000));
        ql.them(new HangThucPham(6, "TP006", "Mi an lien Hao Hao", LocalDate.of(2026, 8, 1), LocalDate.of(2026, 8, 15), 20000));
        System.out.println("Da nap 6 thuc pham mau!");
    }


      
    static void themThucPham() {
        try {
            System.out.print("Mã hàng: ");
            String ma = sc.nextLine();

            System.out.print("Tên hàng: ");
            String ten = sc.nextLine();

            System.out.print("Ngày sản xuất (dd/MM/yyyy): ");
            LocalDate ngaySX = LocalDate.parse(sc.nextLine(), df);

            System.out.print("Ngày hết hạn (dd/MM/yyyy): ");
            LocalDate ngayHH = LocalDate.parse(sc.nextLine(), df);

            System.out.print("Đơn giá: ");
            float gia = Float.parseFloat(sc.nextLine());

            HangThucPham h = new HangThucPham(0,ma, ten, ngaySX, ngayHH, gia);

            if (ql.them(h)) {
                System.out.println("Thêm thành công!");
            } else {
                System.out.println("Thêm thất bại!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    static void xoaThucPham() {
        try {
            System.out.print("Nhập ID cần xóa: ");
            int id = Integer.parseInt(sc.nextLine());

            if (ql.xoa(id)) {
                System.out.println("Xóa thành công!");
            } else {
                System.out.println("Xóa thất bại!");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID phải là số!");
        }
    }

    static void suaThucPham() {
        try {
            System.out.print("Nhập ID cần sửa: ");
            int id = Integer.parseInt(sc.nextLine());

            HangThucPham h = ql.timTheoId(id);

            if (h == null) {
                System.out.println("Không tìm thấy ID " + id);
                return;
            }

            System.out.println("Thông tin hiện tại:");
            System.out.println(h);

            System.out.print("Tên hàng mới: ");
            String ten = sc.nextLine();

            System.out.print("Ngày SX mới (dd/MM/yyyy): ");
            LocalDate ngaySX = LocalDate.parse(sc.nextLine(), df);

            System.out.print("Ngày HH mới (dd/MM/yyyy): ");
            LocalDate ngayHH = LocalDate.parse(sc.nextLine(), df);

            System.out.print("Giá mới: ");
            float gia = Float.parseFloat(sc.nextLine());

            if (ql.sua(id, ten, ngaySX, ngayHH, gia)) {
                System.out.println("Sửa thành công!");
            } else {
                System.out.println("Sửa thất bại!");
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }

    static void sapXepMenu() {
        System.out.println();
        System.out.println("========== SẮP XẾP ==========");
        System.out.println("1. Theo giá");
        System.out.println("2. Theo ngày sản xuất");
        System.out.print("Chọn: ");

        String c = sc.nextLine();

        if (c.equals("1")) {
            sapXepGiaMenu();
        } else if (c.equals("2")) {
            sapXepNgaySXMenu();
        } else {
            System.out.println("Lựa chọn không hợp lệ!");
        }
    }

    static void sapXepGiaMenu() {
        System.out.println();
        System.out.println("1. Tăng dần");
        System.out.println("2. Giảm dần");
        System.out.print("Chọn: ");

        String c = sc.nextLine();

        if (c.equals("1")) {
            ql.sapXepTheoGia(true);
        } else if (c.equals("2")) {
            ql.sapXepTheoGia(false);
        } else {
            System.out.println("Lựa chọn không hợp lệ!");
            return;
        }

        ql.hienThiDanhSach();
    }

    static void sapXepNgaySXMenu() {
        System.out.println();
        System.out.println("1. Tăng dần");
        System.out.println("2. Giảm dần");
        System.out.print("Chọn: ");

        String c = sc.nextLine();

        if (c.equals("1")) {
            ql.sapXepTheoNgaySanXuat(true);
        } else if (c.equals("2")) {
            ql.sapXepTheoNgaySanXuat(false);
        } else {
            System.out.println("Lựa chọn không hợp lệ!");
            return;
        }

        ql.hienThiDanhSach();
    }
}
