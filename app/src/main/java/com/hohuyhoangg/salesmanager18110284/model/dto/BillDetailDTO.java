package com.hohuyhoangg.salesmanager18110284.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDetailDTO {
    Long billId;
    Long productId;
    Integer quantity;

    public BillDetailDTO() {
    }

    public BillDetailDTO(Long billId, Long productId, Integer quantity) {
        this.billId = billId;
        productId = productId;
        this.quantity = quantity;
    }

    public BillDetailDTO (ResultSet resultSet) {
        try {
            billId = resultSet.getLong("BILL_ID");
            productId = resultSet.getLong("PRODUCT_ID");
            quantity = resultSet.getInt("QUANTITY");
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
