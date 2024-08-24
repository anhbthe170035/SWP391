/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Feedback {
    private int feedbackid;
    private int orderid;
    private String sku;
    private String feedback;
    private int star;

    public Feedback() {}

    public Feedback(int orderid, String sku, String feedback, int star) {
        this.orderid = orderid;
        this.sku = sku;
        this.feedback = feedback;
        this.star = star;
    }

    public Feedback(int feedbackid, int orderid, String sku, String feedback, int star) {
        this.feedbackid = feedbackid;
        this.orderid = orderid;
        this.sku = sku;
        this.feedback = feedback;
        this.star = star;
    }

    public int getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(int feedbackid) {
        this.feedbackid = feedbackid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
