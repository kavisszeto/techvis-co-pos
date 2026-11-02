package models;

import java.util.List;
import java.util.TreeMap;

public class ReadBrandSmtphFromDB {

    //dictionary  (key, value or content)
    //Map --> dict  {'key':'value'}
    public static TreeMap<String, Smtph> readSmtph() {
        //read_product_from_file(); //從檔案或資料庫讀入產品菜單資訊

        //***********產生資料DAO來使用
        SmtphDAO smtphDao = new SmtphDAO();
        //***************從檔案或資料庫讀入產品菜單資訊
        List<Smtph> smtphs = smtphDao.getAllSmtphs();

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

        System.out.println(ReadBrandSmtphFromDB.readSmtph());

    }

}
