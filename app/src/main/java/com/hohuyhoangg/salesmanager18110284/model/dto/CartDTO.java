package com.hohuyhoangg.salesmanager18110284.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDTO {
    Long userId;
    Long productId;
    Integer quantity;

    public CartDTO (ResultSet resultSet) {
        try {
            userId = resultSet.getLong("USER_ID");
            productId = resultSet.getLong("PRODUCT_ID");
            quantity = resultSet.getInt("QUANTITY");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public CartDTO() {
    }

    public CartDTO(Long userId, Long productId, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
