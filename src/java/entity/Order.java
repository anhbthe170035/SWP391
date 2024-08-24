package entity;

import java.sql.Date;

public class Order {

    private int id;
    private Date orderdate;
    private int totalprice;
    private String username;
    private int status;
    private Date shipdate;
    private String fromaddress;
    private String toaddress;

    public Order() {
    }

    public Order(int id, Date orderdate, int totalprice, String username, int status, Date shipdate, String fromaddress, String toaddress) {
        this.id = id;
        this.orderdate = orderdate;
        this.totalprice = totalprice;
        this.username = username;
        this.status = status;
        this.shipdate = shipdate;
        this.fromaddress = fromaddress;
        this.toaddress = toaddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getShipdate() {
        return shipdate;
    }

    public void setShipdate(Date shipdate) {
        this.shipdate = shipdate;
    }

    public String getFromaddress() {
        return fromaddress;
    }

    public void setFromaddress(String fromaddress) {
        this.fromaddress = fromaddress;
    }

    public String getToaddress() {
        return toaddress;
    }

    public void setToaddress(String toaddress) {
        this.toaddress = toaddress;
    }

}
