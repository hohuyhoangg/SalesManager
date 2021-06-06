package com.hohuyhoangg.salesmanager18110284.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryProductDTO {
    Long productId;
    Long categoryId;

    public CategoryProductDTO() {
    }

    public CategoryProductDTO(Long productId, Long categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }
    public CategoryProductDTO(ResultSet resultSet) {
        try {
            productId = resultSet.getLong("PRODUCT_ID");
            categoryId = resultSet.getLong("CATEGORY_ID");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}

