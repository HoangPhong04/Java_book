package dao;

import Config.Constant;
import dto.TaiKhoanDTO;
import java.sql.*;
import java.util.ArrayList;

public class TaiKhoanDAO implements DAOInterface<TaiKhoanDTO> {
    Constant conn = new Constant();

    @Override
    public ArrayList<TaiKhoanDTO> getALL() {
        ArrayList<TaiKhoanDTO> arr = new ArrayList<>();
        try {
            conn.openConnection();
            String query = "SELECT * FROM TaiKhoan WHERE trangThai = 1";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO();
                tk.setUseName(rs.getString("useName"));
                tk.setMatKhau(rs.getString("matKhau"));
                tk.setSdt(rs.getString("sdt"));
                tk.setMaNV(rs.getString("maNV"));
                tk.setMaKH(rs.getString("maKH"));
                tk.setMaNhomQuyen(rs.getInt("maNhomQuyen"));
                tk.setTrangThai(rs.getInt("trangThai"));
                arr.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConnection();
        }
        return arr;
    }

    @Override
    public boolean has(String useName) {
        boolean result = false;
        try {
            conn.openConnection();
            String query = "SELECT * FROM TaiKhoan WHERE useName = ?";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.setString(1, useName);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConnection();
        }
        return result;
    }

    @Override
    public boolean add(TaiKhoanDTO tk) {
        boolean result = false;
        try {
            conn.openConnection();
            String query = "INSERT INTO TaiKhoan VALUES (?,?,?,?,?,?,?)";  
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.setString(1, tk.getUseName());
            ps.setString(2, tk.getMatKhau());
            ps.setString(3, tk.getSdt());
            ps.setString(4, tk.getMaNV());
            ps.setString(5, tk.getMaKH());
            ps.setInt(6, tk.getMaNhomQuyen());
            ps.setInt(7, tk.getTrangThai());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConnection();
        }
        return result;
    }

    @Override
    public boolean delete(String useName) {
        boolean result = false;
        try {
            conn.openConnection();
            String query = "UPDATE TaiKhoan SET trangThai = 0 WHERE useName = ?";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.setString(1, useName);
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConnection();
        }
        return result;
    }

    @Override
    public boolean update(TaiKhoanDTO tk) {
        boolean result = false;
        try {
            conn.openConnection();
            String query = "UPDATE TaiKhoan SET matKhau = ?, sdt = ?, maNV = ?, maKH = ? WHERE useName = ?";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.setString(1, tk.getMatKhau());
            ps.setString(2, tk.getSdt());
            ps.setString(3, tk.getMaNV());
            ps.setString(4, tk.getMaKH());
            ps.setString(5, tk.getUseName());
            ps.setInt(6, tk.getMaNhomQuyen());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConnection();
        }
        return result;
    }

    @Override
    public TaiKhoanDTO getByID(String useName) {
        TaiKhoanDTO tk = null;
        try {
            conn.openConnection();
            String query = "SELECT * FROM TaiKhoan WHERE useName = ?";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.setString(1, useName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tk = new TaiKhoanDTO();
                tk.setUseName(rs.getString("useName"));
                tk.setMatKhau(rs.getString("matKhau"));
                tk.setSdt(rs.getString("sdt"));
                tk.setMaNV(rs.getString("maNV"));
                tk.setMaKH(rs.getString("maKH"));
                tk.setMaNhomQuyen(rs.getInt("maNhomQuyen"));
                tk.setTrangThai(rs.getInt("trangThai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConnection();
        }
        return tk;
    }

    @Override
    public ArrayList<TaiKhoanDTO> search(String searchContent) {
        ArrayList<TaiKhoanDTO> arr = new ArrayList<>();
        try {
            conn.openConnection();
            String query = "SELECT * FROM TaiKhoan WHERE "
                         + "useName LIKE ? OR "
                         + "matKhau LIKE ? OR"
                         + "sdt LIKE ? OR "
                         + "maNV LIKE ? OR "
                         + "maKH LIKE ? OR "
                         + "maNhomQuyen ? ";
            PreparedStatement ps = conn.getConnection().prepareStatement(query);
            ps.setString(1, "%" + searchContent + "%");
            ps.setString(2, "%" + searchContent + "%");
            ps.setString(3, "%" + searchContent + "%");
            ps.setString(4, "%" + searchContent + "%");
            ps.setString(5, "%" + searchContent + "%");
            ps.setString(6, "%" + searchContent + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoanDTO tk = new TaiKhoanDTO();
                tk.setUseName(rs.getString("useName"));
                tk.setMatKhau(rs.getString("matKhau"));
                tk.setSdt(rs.getString("sdt"));
                tk.setMaNV(rs.getString("maNV"));
                tk.setMaKH(rs.getString("maKH"));
                tk.setMaNhomQuyen(rs.getInt("maNhomQuyen"));
                arr.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.closeConnection();
        }
        return arr;
    }
}
