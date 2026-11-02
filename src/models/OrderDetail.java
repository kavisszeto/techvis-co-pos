package models;


public class OrderDetail {

    private int id;
    private String order_no;
    private String smtph_id;
    private String smtph_brand;
    private int quantity;
    private int smtph_price;
    private String smtph_name;
    private String smtph_photo;

    public OrderDetail(String smtph_id, String smtph_brand, String smtph_name, int smtph_price, int quantity) {
        this.smtph_id = smtph_id;
        this.smtph_brand = smtph_brand;
        this.smtph_name = smtph_name;
        this.smtph_price = smtph_price;
        this.quantity = quantity;
    }

    public OrderDetail(String smtph_id, String smtph_name, int smtph_price, int quantity) {
        this.smtph_id = smtph_id;
        this.smtph_name = smtph_name;
        this.smtph_price = smtph_price;
        this.quantity = quantity;
    }
    
    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getBrand() {
        return smtph_brand;
    }

    public void setBrand(String smtph_brand) {
        this.smtph_brand = smtph_brand;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getSmtph_id() {
        return smtph_id;
    }

    public void setSmtph_id(String smtph_id) {
        this.smtph_id = smtph_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSmtph_price() {
        return smtph_price;
    }

    public void setSmtph_price(int smtph_price) {
        this.smtph_price = smtph_price;
    }

    public String getSmtph_name() {
        return smtph_name;
    }

    public void setSmtph_name(String smtph_name) {
        this.smtph_name = smtph_name;
    }

    public String getSmtph_photo() {
        return smtph_photo;
    }

    public void setSmtph_photo(String smtph_photo) {
        this.smtph_photo = smtph_photo;
    }

}
