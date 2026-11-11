package opp;

import java.time.LocalDate;

public class HangThucPham {
	private int id;
	private final String maHang;
	private String tenHang;
	private LocalDate ngaySX,ngayHH;
	private float gia;
	
	private static int demSoLuong=0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTenHang() {
		return tenHang;
	}
	public void setTenHang(String tenHang) {
		if(tenHang==null|| tenHang.trim().isEmpty())
			throw new IllegalArgumentException("Ten hang khong duoc rong");
		this.tenHang = tenHang;
	}
	public LocalDate getNgaySX() {
		return ngaySX;
	}
	public void setNgaySX(LocalDate ngaySX) {
		if(!kiemTraNgaySanXuat(ngaySX))
			throw new IllegalArgumentException("Ngay san xuat khong duoc lon hon ngay hien tai!");
		if(this.ngayHH!=null &&!kiemTraNgayHetHan(ngaySX, this.ngayHH))
			throw new IllegalArgumentException("Ngay san xuat phai truoc ngay het han");
		this.ngaySX = ngaySX;
	}
	public LocalDate getNgayHH() {
		return ngayHH;
	}
	public void setNgayHH(LocalDate ngayHH) {
		if(this.ngaySX==null)
			throw new IllegalArgumentException("Chua co ngay san xuat");
		if(!kiemTraNgayHetHan(this.ngaySX,ngayHH))
			throw new IllegalArgumentException("Ngay het han phai sau ngay san xuat");
		
		this.ngayHH = ngayHH;
	}
	public float getGia() {
		return gia;
	}
	public void setGia(float gia) {
		if(gia <=0)
			throw new IllegalArgumentException("Gia phai lon hon 0");
		this.gia = gia;
	}
	public static int getDemSoLuong() {
		return demSoLuong;
	}
	public static void setDemSoLuong(int demSoLuong) {
		HangThucPham.demSoLuong = demSoLuong;
	}
	public String getMaHang() {
		return maHang;
	}
	public HangThucPham() {
		this.id = ++demSoLuong;
		this.maHang = "Mac_dinh";
		this.tenHang = "Hang Mac Dinh";
		this.ngaySX = LocalDate.now();
		this.ngayHH = LocalDate.now().plusDays(7);
		this.gia = 1.0f;
	}
	private boolean kiemTraNgaySanXuat(LocalDate ngaySX) {
		return ngaySX!=null && !ngaySX.isAfter(LocalDate.now());
		
	}
	private boolean kiemTraNgayHetHan(LocalDate ngaySX,LocalDate ngayHH) {
		return ngaySX!=null && ngayHH !=null &&ngayHH.isAfter(ngaySX);
		
	}
	public HangThucPham(int id, String maHang, String tenHang, LocalDate ngaySX, LocalDate ngayHH, float gia) {
		
		this.id = ++demSoLuong;
		if(maHang==null|| maHang.trim().isEmpty())
			throw new IllegalArgumentException("Ma hang khong duoc de trong!");
		if(tenHang==null||tenHang.trim().isEmpty())
			throw new IllegalArgumentException("Ten hang khong duoc de trong!");
		if(gia <=0 )
			throw new IllegalArgumentException("Gia phai lon hon 0!");
		if(!kiemTraNgaySanXuat(ngaySX))
			throw new IllegalArgumentException("Ngay san xuat khong duoc lon hon ngay hien tai!");
		if(!kiemTraNgayHetHan(ngaySX, ngayHH))
			throw new IllegalArgumentException("Ngay het han phai sau ngay san xuat!");
		this.maHang = maHang;
		this.tenHang = tenHang;
		this.ngaySX = ngaySX;
		this.ngayHH = ngayHH;
		this.gia = gia;
	}
	public boolean kiemTraHetHan() {
		return LocalDate.now().isAfter(this.ngayHH);
	}
	@Override
	public String toString() {
		return String.format("| %-4d| |%-10s| %-12s| %-12s| %-10.0f|",id, maHang,tenHang,ngaySX,ngayHH,gia);
	}
}
