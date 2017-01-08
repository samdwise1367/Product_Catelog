
package com.samdwise.to;

/**
 *
 * @author samdwise
 */
public class ProductTO {
    private int id;
    private String name;
    private float price;
    private String addDate;
    private byte[] image;
    
    public ProductTO(int id, String name, float price, String addDate, byte[] image){
        this.id = id;
        this.name = name;
        this.price = price;
        this.addDate = addDate;
        this.image = image;
    }
    
     public ProductTO(String name, float price, String addDate, byte[] image){
        this.name = name;
        this.price = price;
        this.addDate = addDate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    
    
}
