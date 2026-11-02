package models;

public class Order
{
    private String order_no;
    private String order_date;
    private int total_price;
    private String customer_name;
    private String customer_address;
    private String customer_phno;
    
    public Order() {
    }
    
    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getCustomer_phno() {
        return customer_phno;
    }

    public void setCustomer_phno(String customer_phno) {
        this.customer_phno = customer_phno;
    }
    
    
    
}

