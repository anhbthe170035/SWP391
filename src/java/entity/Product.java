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
    public String idpro;
    public String name;
    public String brand;
    public String description;
    public String img;

    public Product() {
    }

    public Product(String idpro, String name, String brand, String description, String img) {
        this.idpro = idpro;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.img = img;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Product{" + "idpro=" + idpro + ", name=" + name + ", brand=" + brand + ", description=" + description + ", img=" + img + '}';
    }
}
