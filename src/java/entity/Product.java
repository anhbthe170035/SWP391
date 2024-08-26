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
    private String pid; // Changed from idpro to pid for consistency with SQL
    private String name;
    private String description;
    private int cid; // Changed to int
    private int brandid; // Changed to int

    public Product() {
    }

    public Product(String pid, String name, String description, int cid, int brandid) {
        this.pid = pid;
        this.name = name;
        this.description = description;
        this.cid = cid;
        this.brandid = brandid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getBrandid() {
        return brandid;
    }

    public void setBrandid(int brandid) {
        this.brandid = brandid;
    }

    @Override
    public String toString() {
        return "Product{" + "pid=" + pid + ", name=" + name + ", description=" + description + ", cid=" + cid + ", brandid=" + brandid + '}';
    }
}
