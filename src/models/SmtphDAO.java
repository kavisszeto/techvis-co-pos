package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SmtphDAO {

    //private Connection conn = DBConnection.getConnection();
    private Connection conn;

    // 取得所有產品
    public List<Smtph> getAllSmtphs() {

        conn = DBConnection.getConnection();
        String query = "select * from smtph";
        List<Smtph> smtph_list = new ArrayList();

        try {
            PreparedStatement ps
                    = conn.prepareStatement(query);
            ResultSet rset = ps.executeQuery();

            while (rset.next()) {
                Smtph smtph = new Smtph();
                smtph.setSmtph_id(rset.getString("smtph_id"));
                smtph.setBrand(rset.getString("brand"));
                smtph.setName(rset.getString("name"));
                smtph.setPrice(rset.getInt("price"));
                smtph.setPhoto(rset.getString("photo"));
                smtph.setDescription(rset.getString("description"));
                smtph_list.add(smtph);

                //不要斷線，一直會用到，使用持續連線的方式
                //conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("getAllsmtphs異常:" + ex.toString());
        }

        return smtph_list;
    }

    //選擇特定字串"孫大毛"或是"孫%"或是"%毛"
    public List<Smtph> searchByNameContaining(String name_str) {
        conn = DBConnection.getConnection();
        boolean success = false;
        List<Smtph> smtph_list = new ArrayList();
        //String query = String.format("select * from student where name like '%s%s'", name_str, "%");
        //String query = String.format("select * from student where name like '%s'", name_str);
        String query = "select * from smtph where name like ?"; //%果%
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, "%" + name_str + "%");  // String.format("%s%s%s","%",name_str, "%")
            ResultSet rset = state.executeQuery();
            while (rset.next()) {
                Smtph smtph = new Smtph();
                smtph.setSmtph_id(rset.getString("smtph_id"));
                smtph.setBrand(rset.getString("brand"));
                smtph.setName(rset.getString("name"));
                smtph.setPrice(rset.getInt("price"));
                smtph.setPhoto(rset.getString("photo"));
                smtph.setDescription(rset.getString("description"));
                smtph_list.add(smtph);
            }
        } catch (SQLException ex) {
            System.out.println("資料庫selectByName操作異常:" + ex.toString());
        }
        return smtph_list;
    }

    //查詢價格低於多少錢的產品
   public List<Smtph> searchByPriceLessThanEqual(int price) {
        conn = DBConnection.getConnection();
        List<Smtph> smtph_list = new ArrayList();
        String query = "select * from smtph where price <= ?";
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setInt(1, price);
            ResultSet rset = state.executeQuery();
            while (rset.next()) {
                Smtph smtph = new Smtph();
                smtph.setSmtph_id(rset.getString("smtph_id"));
                smtph.setBrand(rset.getString("brand"));
                smtph.setName(rset.getString("name"));
                smtph.setPrice(rset.getInt("price"));
                smtph.setPhoto(rset.getString("photo"));
                smtph.setDescription(rset.getString("description"));
                smtph_list.add(smtph);
            }
        } catch (SQLException ex) {
            System.out.println("資料庫selectByPrice作異常:" + ex.toString());
        }
        return smtph_list;
    }

  
   //查詢過濾filter 某個大類別的產品
   public List<Smtph> searchByBrand(String brand) {
        conn = DBConnection.getConnection();
        List<Smtph> smtph_list = new ArrayList();
        String query = "select * from smtph where brand = ?";
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, brand);
            ResultSet rset = state.executeQuery();
            while (rset.next()) {
                Smtph smtph = new Smtph();
                smtph.setSmtph_id(rset.getString("smtph_id"));
                smtph.setBrand(rset.getString("brand"));
                smtph.setName(rset.getString("name"));
                smtph.setPrice(rset.getInt("price"));
                smtph.setPhoto(rset.getString("photo"));
                smtph.setDescription(rset.getString("description"));
                smtph_list.add(smtph);
            }
        } catch (SQLException ex) {
            System.out.println("資料庫selectByCate異常:" + ex.toString());
        }
        return smtph_list;
    }
    
   
    //選擇某個product_id
    public Smtph searchById(String id) {
        conn = DBConnection.getConnection();
        boolean success = false;
        String query = "select * from smtph where smtph_id = ?";
        //String query = String.format("select * from student where student_id = '%s'", id);
        Smtph smtph = new Smtph();
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, id);
            ResultSet rset = state.executeQuery();

            while (rset.next()) {
                success = true;
                smtph.setSmtph_id(rset.getString("smtph_id"));
                smtph.setBrand(rset.getString("brand"));
                smtph.setName(rset.getString("name"));
                smtph.setPrice(rset.getInt("price"));
                smtph.setPhoto(rset.getString("photo"));
                smtph.setDescription(rset.getString("description"));
            }
        } catch (SQLException ex) {
            System.out.println("資料庫selectByID操作異常:" + ex.toString());
        }

        if (success) {
            return smtph;
        } else {
            return null;
        }

    }

  
    //新增一項產品 
    public boolean insert(Smtph product) {
        conn = DBConnection.getConnection();
        String query = "insert into smtph(smtph_id,name,brand,price,photo,description) VALUES (?,?,?,?,?,?)";
        boolean success = false;
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(1, product.getSmtph_id());
            state.setString(2, product.getName());
            state.setString(3, product.getBrand());
            state.setInt(4, product.getPrice());
            state.setString(5, product.getPhoto());
            state.setString(6, product.getDescription());

            state.execute();
            //state.executeUpdate();
            success = true;
            System.out.println("新增成功");
        } catch (SQLException ex) {
            System.out.println("insert異常:" + ex.toString());
        }
        return success;
    } 
    
    //刪除某項產品
    public boolean delete(String id) {
        conn = DBConnection.getConnection();
        String query = "delete from smtph where smtph_id =?";
        boolean sucess = false;
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);
            //statement.execute();
            sucess = statement.executeUpdate() > 0;
            if (sucess) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("Record not found.");
            }
        } catch (SQLException ex) {
            System.out.println("delete異常:\n" + ex.toString());
        }
        return sucess;
    }
    // 更新某項產品
    public void update(Smtph product) {
        conn = DBConnection.getConnection();
        String query = "update smtph set name=?, brand=?, price=?, photo= ?, description=? where smtph_id = ?";
        try {
            PreparedStatement state = conn.prepareStatement(query);
            state.setString(6, product.getSmtph_id());
            state.setString(1, product.getName());
            state.setString(2, product.getBrand());
            state.setInt(3, product.getPrice());
            state.setString(4, product.getPhoto());
            state.setString(5, product.getDescription());
            
            state.executeUpdate();
            System.out.println("更新成功");
        } catch (SQLException ex) {
            System.out.println("update異常:" + ex.toString());
        }
    }
    

    public TreeMap<String, Smtph> readSmtphByBrandToTreeMap(String cate) {
        //read_product_from_file(); //從檔案或資料庫讀入產品菜單資訊

        //***********產生資料DAO來使用
        //ProductDAO productDao = new SmtphDAO();
        //***************從檔案或資料庫讀入產品菜單資訊
        List<Smtph> smtphs = this.searchByBrand(cate);
        //List<Product> smtphs = productDao.getAllSmtphs();

        //放所有產品  產品編號  產品物件Product
        TreeMap<String, Smtph> smtph_dict = new TreeMap();

        //準備產品的字典 從資料庫中讀入
        //放入product_dict中點選產品與顯示產品比較方便
        for (Smtph smtph : smtphs) {
            //System.out.println(smtph.getBrand());
            smtph_dict.put(smtph.getSmtph_id(), smtph);
        }
        return smtph_dict;
    }
    
    //dictionary  (key, value or content)
    //Map --> dict  {'key':'value'}
    public TreeMap<String, Smtph> readAllSmtphToTreeMap() {
        //read_product_from_file(); //從檔案或資料庫讀入產品菜單資訊

        //***********產生資料DAO來使用
        //ProductDAO productDao = new SmtphDAO();
        //***************從檔案或資料庫讀入產品菜單資訊
        List<Smtph> smtphs = this.getAllSmtphs();
        //List<Product> smtphs = productDao.getAllSmtphs();

        //放所有產品  產品編號  產品物件Product
        TreeMap<String, Smtph> smtph_dict = new TreeMap();

        //準備產品的字典 從資料庫中讀入
        //放入product_dict中點選產品與顯示產品比較方便
        for (Smtph smtph : smtphs) {
            //System.out.println(smtph.getBrand());
            smtph_dict.put(smtph.getSmtph_id(), smtph);
        }

        return smtph_dict;

    }
    
    public static void main(String[] args) {

        SmtphDAO dao = new SmtphDAO();
        
        //dao.insert(new Smtph("p-c-101","咖啡","拿鐵", 120, "lataphoto.jpt","好喝的拿鐵咖啡"));
        //dao.delete("p-c-101");
        dao.update(  new Smtph("mgp0721","Google","Pixel 7a", 14990, "pixel7a.png","Built to perform. Priced just right.")   );
        
//        List<Product> smtph_list = dao.getAllSmtphs();
//        for (Smtph smtph : smtph_list) {
//            System.out.println(smtph);
//        }

        List<Smtph> smtph_list = dao.searchByBrand("Samsung");
        //List<Product> smtph_list = dao.searchByPriceLessThanEqual(80);
        //List<Product> smtph_list = dao.searchByNameContaining("果");
        for (Smtph smtph : smtph_list) {
            System.out.println(smtph);
        }
    }

}
