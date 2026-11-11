package opp;

import java.time.LocalDate;

public class ListHangThucPham {

    private HangThucPham[] danhSach;
    private int soLuong;
    private int khaNang;

    private static final int KICH_THUOC_BAN_DAU = 5;

    public ListHangThucPham() {
        this.khaNang = KICH_THUOC_BAN_DAU;
        this.danhSach = new HangThucPham[khaNang];
        this.soLuong = 0;
    }

    private void moRongMang() {
        int kichThuocMoi = khaNang * 2;

        HangThucPham[] mangMoi =
                new HangThucPham[kichThuocMoi];

        for (int i = 0; i < soLuong; i++) {
            mangMoi[i] = danhSach[i];
        }

        danhSach = mangMoi;
        khaNang = kichThuocMoi;

        System.out.println(" Đã mở rộng mảng lên "+ khaNang + " phần tử.");
    }

    
    public int timViTriTheoMa(String maHang) {

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i].getMaHang()
                    .equalsIgnoreCase(maHang)) {

                return i;
            }
        }

        return -1;
    }

    public int timViTriTheoId(int id) {

        for (int i = 0; i < soLuong; i++) {

            if (danhSach[i].getId() == id) {
                return i;
            }
        }

        return -1;
    }

    public boolean them(HangThucPham h) {

        if (timViTriTheoMa(h.getMaHang()) != -1) {

            System.out.println(" Lỗi: Mã hàng " + h.getMaHang() + " đã tồn tại!");

            return false;
        }

        if (soLuong == khaNang) {
            moRongMang();
        }

        danhSach[soLuong] = h;
        soLuong++;

        return true;
    }

    public boolean xoa(int id) {

        int viTri = timViTriTheoId(id);

        if (viTri == -1) {

            System.out.println( " Không tìm thấy hàng có id "+ id);

            return false;
        }

        for (int i = viTri; i < soLuong - 1; i++) {

            danhSach[i] = danhSach[i + 1];
        }

        danhSach[soLuong - 1] = null;

        soLuong--;

        return true;
    }

    public boolean sua(int id, String tenMoi, LocalDate ngaySXMoi,LocalDate ngayHhMoi, float giaMoi) {

        int viTri = timViTriTheoId(id);

        if (viTri == -1) {

            System.out.println( " Không tìm thấy hàng có id "+ id );

            return false;
        }

        HangThucPham h = danhSach[viTri];

        h.setTenHang(tenMoi);
        h.setNgaySX(ngaySXMoi);
        h.setNgayHH(ngayHhMoi);
        h.setGia(giaMoi);

        return true;
    }
    public HangThucPham timTheoId(int id) {
        int viTri = timViTriTheoId(id);
        return (viTri == -1) ? null : danhSach[viTri];
    }

    public void sapXepTheoGia(boolean tangDan) {
        for (int i = 0; i < soLuong - 1; i++) {
            for (int j = i + 1; j < soLuong; j++) {
                boolean canDoi = tangDan
                        ? danhSach[i].getGia() > danhSach[j].getGia()
                        : danhSach[i].getGia() < danhSach[j].getGia();

                if (canDoi) {
                    HangThucPham temp = danhSach[i];
                    danhSach[i] = danhSach[j];
                    danhSach[j] = temp;
                }
            }
        }

        System.out.println(" Đã sắp xếp theo giá " + (tangDan ? "tăng dần" : "giảm dần"));
    }

    public void sapXepTheoNgaySanXuat(boolean tangDan) {
        for (int i = 0; i < soLuong - 1; i++) {
            for (int j = i + 1; j < soLuong; j++) {
                boolean canDoi = tangDan
                        ? danhSach[i].getNgaySX().isAfter(danhSach[j].getNgaySX())
                        : danhSach[i].getNgaySX().isBefore(danhSach[j].getNgaySX());

                if (canDoi) {
                    HangThucPham temp = danhSach[i];
                    danhSach[i] = danhSach[j];
                    danhSach[j] = temp;
                }
            }
        }

        System.out.println(" Đã sắp xếp theo ngày SX "
                + (tangDan ? "tăng dần" : "giảm dần"));
    }

	public void hienThiDanhSach() {
		
		
	}

}
