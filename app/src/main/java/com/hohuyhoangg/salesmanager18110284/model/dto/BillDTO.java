package com.hohuyhoangg.salesmanager18110284.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDTO {
    Long billId;
    Long userId;
    String price;
    String priceProduct;
    String priceDelivery;
    String priceDiscount;
    String date;

    public BillDTO() {
    }

    public BillDTO(Long billId, Long userId, String price, String priceProduct, String priceDelivery, String priceDiscount, String date) {
        this.billId = billId;
        this.userId = userId;
        this.price = price;
        this.priceProduct = priceProduct;
        this.priceDelivery = priceDelivery;
        this.priceDiscount = priceDiscount;
        this.date = date;
    }

    public BillDTO (ResultSet resultSet) {
        try {
            billId = resultSet.getLong("BILL_ID");
            userId = resultSet.getLong("USER_ID");
            price = resultSet.getString("PRICE");
            priceProduct = resultSet.getString("PRICE_PRODUCT");
            priceDelivery = resultSet.getString("PRICE_DELIVERY");
            priceDiscount = resultSet.getString("PRICE_DISCOUNT");
            date = resultSet.getString("DATE");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public String getPriceDelivery() {
        return priceDelivery;
    }

    public void setPriceDelivery(String priceDelivery) {
        this.priceDelivery = priceDelivery;
    }

    public String getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(String priceDiscount) {
        this.priceDiscount = priceDiscount;
    }
}
