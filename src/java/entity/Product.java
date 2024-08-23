/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Product {
    private String idpro;
    private String name;
    private String description;
    private String cid;
    private String brand;

    public Product() {
    }

    public Product(String idpro, String name, String description, String cid, String brand) {
        this.idpro = idpro;
        this.name = name;
        this.description = description;
        this.cid = cid;
        this.brand = brand;
    }

    public String getIdpro() {
        return idpro;
    }

    public void setIdpro(String idpro) {
        this.idpro = idpro;
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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" + "idpro=" + idpro + ", name=" + name + ", description=" + description + ", cid=" + cid + ", brand=" + brand + '}';
    }
}
