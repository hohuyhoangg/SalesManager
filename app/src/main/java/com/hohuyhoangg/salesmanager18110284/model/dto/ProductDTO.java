package com.hohuyhoangg.salesmanager18110284.model.dto;

import org.apache.commons.codec.binary.StringUtils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProductDTO {
    String productId;

    String categoryId;
    String sellerId;
    Long brandId;
    String productName;
    Integer quantity;
    Double productRate;
    String productOrigin;

    BigDecimal priceOrigin;
    BigDecimal priceOrder;

    Date productCreateDate;

    String image0;
    String image1;
    String image2;
    String image3;
    String image4;
    Boolean status;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductRate() {
        return productRate;
    }

    public void setProductRate(Double productRate) {
        this.productRate = productRate;
    }

    public String getProductOrigin() {
        return productOrigin;
    }

    public void setProductOrigin(String productOrigin) {
        this.productOrigin = productOrigin;
    }

    public BigDecimal getPriceOrigin() {
        return priceOrigin;
    }

    public void setPriceOrigin(BigDecimal priceOrigin) {
        this.priceOrigin = priceOrigin;
    }

    public BigDecimal getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(BigDecimal priceOrder) {
        this.priceOrder = priceOrder;
    }

    public Date getProductCreateDate() {
        return productCreateDate;
    }

    public void setProductCreateDate(Date productCreateDate) {
        this.productCreateDate = productCreateDate;
    }

    public String getImage0() {
        return image0;
    }

    public void setImage0(String image0) {
        this.image0 = image0;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductDTO(ResultSet resultSet) {
        try {
            productId = resultSet.getString("PRODUCT_ID");
            brandId = resultSet.getLong("BRAND_ID");
            categoryId = resultSet.getString("CATEGORY_ID");
            sellerId = resultSet.getString("SELLER_ID");
            productName = resultSet.getString("PRODUCT_NAME");
            quantity = resultSet.getInt("QUANTITY");
            productRate = resultSet.getDouble("PRODUCT_RATE");
            productOrigin = resultSet.getString("PRODUCT_ORIGIN");
            priceOrigin = resultSet.getBigDecimal("PRICE_ORIGIN");
            priceOrder = resultSet.getBigDecimal("PRICE_ORDER");
            productCreateDate = resultSet.getDate("PRODUCT_CREATE_DATE");
            image0 = resultSet.getString("IMAGE_0");
            image1 = resultSet.getString("IMAGE_1");
            image2 = resultSet.getString("IMAGE_2");
            image3 = resultSet.getString("IMAGE_3");
            image4 = resultSet.getString("IMAGE_4");
            status = resultSet.getBoolean("STATUS");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public ProductDTO() {
    }

    public ProductDTO(String productId, String categoryId, String sellerId, Long brandId, String productName, Integer quantity, Double productRate,
                      String productOrigin, BigDecimal priceOrigin, BigDecimal priceOrder, Date productCreateDate, String image0, String image1,
                      String image2, String image3, String image4, Boolean status) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.sellerId = sellerId;
        this.brandId = brandId;
        this.productName = productName;
        this.quantity = quantity;
        this.productRate = productRate;
        this.productOrigin = productOrigin;
        this.priceOrigin = priceOrigin;
        this.priceOrder = priceOrder;
        this.productCreateDate = productCreateDate;
        this.image0 = image0;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.status = status;
    }
}
