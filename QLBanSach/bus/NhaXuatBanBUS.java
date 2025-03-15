///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package bus;
//
///**
// *
// * @author LAPTOP
// */
//
//import dao.NhaXuatBanDAO;
//import dto.NhaXuatBanDTO;
//import java.util.List;
//
//public class NhaXuatBanBUS {
//    private NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
//
//    // Lấy danh sách Nhà Xuất Bản
//    public List<NhaXuatBanDTO> layDanhSachNXB() {
//        return nxbDAO.getAllNXB();
//    }
//
//    // Kiểm tra định dạng số điện thoại
//    private boolean isValidPhoneNumber(String sdt) {
//        return sdt.matches("^0\\d{9}$"); // Ví dụ: 10 số, bắt đầu bằng 0
//    }
//
//    // Thêm Nhà Xuất Bản
//    public boolean themNXB(String maNXB, String diachi, String sdt, String tenNXB) {
//        if (maNXB.isEmpty() || diachi.isEmpty() || sdt.isEmpty() || tenNXB.isEmpty()) {
//            System.out.println("Lỗi: Không được để trống thông tin.");
//            return false;
//        }
//        if (!isValidPhoneNumber(sdt)) {
//            System.out.println("Lỗi: Số điện thoại không hợp lệ.");
//            return false;
//        }
//        if (nxbDAO.getNXBById(maNXB) != null) {
//            System.out.println("Lỗi: Mã NXB đã tồn tại.");
//            return false;
//        }
//
//        NhaXuatBanDTO nxb = new NhaXuatBanDTO(maNXB, diachi, sdt, tenNXB);
//        return nxbDAO.themNXB(nxb);
//    }
//
//    // Cập nhật Nhà Xuất Bản
//    public boolean capNhatNXB(String maNXB, String diachi, String sdt, String tenNXB) {
//        if (maNXB.isEmpty() || diachi.isEmpty() || sdt.isEmpty() || tenNXB.isEmpty()) {
//            System.out.println("Lỗi: Không được để trống thông tin.");
//            return false;
//        }
//        if (!isValidPhoneNumber(sdt)) {
//            System.out.println("Lỗi: Số điện thoại không hợp lệ.");
//            return false;
//        }
//        if (nxbDAO.getNXBById(maNXB) == null) {
//            System.out.println("Lỗi: Không tìm thấy NXB cần cập nhật.");
//            return false;
//        }
//
//        NhaXuatBanDTO nxb = new NhaXuatBanDTO(maNXB, diachi, sdt, tenNXB);
//        return nxbDAO.updateNXB(nxb);
//    }
//
//    // Xóa Nhà Xuất Bản
//    public boolean xoaNXB(String maNXB) {
//        if (maNXB.isEmpty()) {
//            System.out.println("Lỗi: Mã NXB không được để trống.");
//            return false;
//        }
//        if (nxbDAO.getNXBById(maNXB) == null) {
//            System.out.println("Lỗi: Không tìm thấy NXB cần xóa.");
//            return false;
//        }
//        if (nxbDAO.hasRelatedBooks(maNXB)) { // Kiểm tra nếu NXB đang được dùng ở bảng sách
//            System.out.println("Lỗi: Không thể xóa NXB vì có sách liên kết.");
//            return false;
//        }
//
//        return nxbDAO.deleteNXB(maNXB);
//    }
//}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bus;
import dao.NhaXuatBanDAO;
import dto.NhaXuatBanDTO;
import java.util.ArrayList;
public class NhaXuatBanBUS {
     NhaXuatBanDAO nxbDAO = new NhaXuatBanDAO();
    
    public ArrayList<NhaXuatBanDTO> getAllNhaXuatBan(){
        return nxbDAO.getALL();
    }
    
    public String addNhaXuatBan(NhaXuatBanDTO nxb){
        if(nxbDAO.has(nxb.getMaNXB()))
            return "Mã nhà nhà xuất bản đã tồn tại";
        if(nxbDAO.add(nxb))
            return "Thêm nhà xuất bản thành công";
        return "Thêm thất bại";
    }
    
    public String deleteNhaXuatBan(String manxb){
        if(nxbDAO.delete(manxb))
            return "Xóa nhà xuất bản thành công";
        return "Xóa nhà xuất bản thất bại";
    }
    
    public String updateNhaXuatBan(NhaXuatBanDTO nxb){
        if(nxbDAO.update(nxb))
            return "Cập nhật nhà xuất bản thành công";
        return "Cập nhật nhà xuất bản thất bại";
    }
  
    public NhaXuatBanDTO getByName(String tennxb) {
    	return nxbDAO.getByName(tennxb);
    }
   
}
