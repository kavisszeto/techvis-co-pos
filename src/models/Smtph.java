package models;

public class Smtph {

    private String smtph_id;
    private String brand;
    private String name;
    private int price;
    private String photo;
    //private String imgSrc; //之前的教材用的名稱

    private String description;

    public Smtph(String smtph_id, String brand, String name, int price, String photo, String description) {
        this.smtph_id = smtph_id;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.photo = photo;
        this.description = description;
    }

    public Smtph() {
    }

    public String getSmtph_id() {
        return smtph_id;
    }

    public void setSmtph_id(String smtph_id) {
        this.smtph_id = smtph_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Smtph{" + "smtph_id=" + smtph_id + ", brand=" + brand + ", name=" + name + ", price=" + price + ", photo=" + photo + ", description=" + description + '}';
    }

}