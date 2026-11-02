package models;

import java.util.TreeMap;
import models.Smtph;

public class ReadBrandSmtph {
    
     //準備好產品清單  
    public static TreeMap<String, Smtph> readSmtph() {
        //read_product_from_file(); //從檔案或資料庫讀入產品菜單資訊
        //放所有產品  產品編號  產品物件
        TreeMap<String, Smtph> smtph_dict = new TreeMap();
        String[][] smtph_array =
        {
            {"map1401", "Apple", "iPhone 14", "27900", "iphone14.png", "Big and bigger. Wonderfull. A total powerhouse."},
            {"map1402", "Apple", "iPhone 14 Plus", "31900", "iphone14plus.png", "Big and bigger. Wonderfull. A total powerhouse."},
            {"map1411", "Apple", "iPhone 14 Pro", "34900", "iphone14pro.png", "Pro. Beyond. The ultimate iPhone."},
            {"map1412", "Apple", "iPhone 14 Pro Max", "38900", "iphone14promax.png", "Pro. Beyond. The ultimate iPhone."},
            {"map1301", "Apple", "iPhone 13 mini", "20900", "iphone13mini.png", "Your new superpower. As amazing as ever."},
            {"map1302", "Apple", "iPhone 13", "24900", "iphone13.png", "Your new superpower. As amazing as ever."},
            {"map1201", "Apple", "iPhone 12", "20900", "iphone12.png", "Blast past fast."},
            {"map0561", "Apple", "iPhone SE (3rd Gen.)", "14900", "iphonese3.png", "Love the power. Love the price.\nSerious power. Serious value."},
            {"mss2301", "Samsung", "Galaxy S23", "26900", "galaxys23.png", "Experience NOOOW. Galaxy evolved."},
            {"mss2302", "Samsung", "Galaxy S23+", "33900", "galaxys23plus.png", "Experience NOOOW. Galaxy evolved."},
            {"mss2311", "Samsung", "Galaxy S23 Ultra", "40900", "galaxys23ultra.png", "Experience NOOOW. Ultra evolved."},
            {"mss2211", "Samsung", "Galaxy S22 Ultra", "38900", "galaxys22ultra.png", "The Epic Standard.\nSamsung Galaxy S22 Ultra is the New Note."},
            {"mss2121", "Samsung", "Galaxy S21 ", "21990", "galaxys21fe.png", "Everyday Epic. The Fan Edition is back."},
            {"msz0401", "Samsung", "Galaxy Z Fold4", "56888", "galaxyzfold4.png", "Unfold Your World.\nThe Big Bang deserves a bigger screen."},
            {"msz0411", "Samsung", "Galaxy Z Flip4", "32888", "galaxyzflip4.png", "Unfold Your World. Wefies by Flip."},            
            {"msz0311", "Samsung", "Galaxy Z Flip3", "30888", "galaxyzflip3.png", "We've rethought the phone. Now it's a statement."},
            {"mgp0701", "Google", "Pixel 7", "18990", "pixel7.png", "Simply powerful. Super helpful."},
            {"mgp0711", "Google", "Pixel 7 Pro", "22990", "pixel7pro.png", "The all-pro Google phone."},
            {"mgp0621", "Google", "Pixel 6a", "11490", "pixel6a.png", "So much Google. For less than you think."},
            {"oth0001", "Techvis Co.","Gift Voucher", "-1000", "", "Membership's Welcome Gift - $1000 Voucher."}
        };

        //一筆放入字典變數product_dict中
        for (String[] item : smtph_array) {
            Smtph smtph = new Smtph(
                    item[0], 
                    item[1], 
                    item[2], 
                    Integer.parseInt(item[3]), //價格轉為int
                    item[4], 
                    item[5]);
            //將這一筆放入字典變數product_dict中 
            smtph_dict.put(smtph.getSmtph_id(), smtph);
        }
        return smtph_dict; 
    }
}
