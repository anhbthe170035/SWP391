/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author JoringeN
 */
public class ProductDetail {
    private String sku, pid, color, cpu, cpu_specs, ram, ram_max, gpu, gpu2, storage, monitor, status,name, brand,description,img;
    private int price, sale;

    public ProductDetail() {
    }

    public ProductDetail(String sku, String pid, String color, String cpu, String cpu_specs, String ram, String ram_max, String gpu, String gpu2, String storage, String monitor, String status, String name, String brand, String description, String img, int price, int sale) {
        this.sku = sku;
        this.pid = pid;
        this.color = color;
        this.cpu = cpu;
        this.cpu_specs = cpu_specs;
        this.ram = ram;
        this.ram_max = ram_max;
        this.gpu = gpu;
        this.gpu2 = gpu2;
        this.storage = storage;
        this.monitor = monitor;
        this.status = status;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.img = img;
        this.price = price;
        this.sale = sale;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getCpu_specs() {
        return cpu_specs;
    }

    public void setCpu_specs(String cpu_specs) {
        this.cpu_specs = cpu_specs;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRam_max() {
        return ram_max;
    }

    public void setRam_max(String ram_max) {
        this.ram_max = ram_max;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getGpu2() {
        return gpu2;
    }

    public void setGpu2(String gpu2) {
        this.gpu2 = gpu2;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

}
