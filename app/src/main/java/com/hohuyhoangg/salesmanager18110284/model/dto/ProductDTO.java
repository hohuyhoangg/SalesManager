package com.hohuyhoangg.salesmanager18110284.model.dto;

import org.apache.commons.codec.binary.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProductDTO implements Serializable {
    Long productId;

    Long sellerId;
    Long brandId;

    String productName;
    Integer quantity;
    Double productRate;
    String productOrigin;
    String productDesc;

    BigDecimal priceOrigin;
    BigDecimal priceOrder;

    String image0;
    String image1;
    String image2;
    String image3;
    String image4;

    Boolean status;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
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

    public ProductDTO(ResultSet resultSet) {
        try {
            productId = resultSet.getLong("PRODUCT_ID");
            brandId = resultSet.getLong("BRAND_ID");
            sellerId = resultSet.getLong("SELLER_ID");
            productName = resultSet.getString("PRODUCT_NAME");
            quantity = resultSet.getInt("QUANTITY");
            productRate = resultSet.getDouble("PRODUCT_RATE");
            productOrigin = resultSet.getString("PRODUCT_ORIGIN");
            productDesc = resultSet.getString("PRODUCT_DESC");
            priceOrigin = resultSet.getBigDecimal("PRICE_ORIGIN");
            priceOrder = resultSet.getBigDecimal("PRICE_ORDER");
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

    public ProductDTO(Long productId, Long sellerId, Long brandId, String productName, Integer quantity, Double productRate,
                      String productOrigin, String productDesc, BigDecimal priceOrigin, BigDecimal priceOrder, String image0,
                      String image1, String image2, String image3, String image4, Boolean status) {
        this.productId = productId;
        this.sellerId = sellerId;
        this.brandId = brandId;
        this.productName = productName;
        this.quantity = quantity;
        this.productRate = productRate;
        this.productOrigin = productOrigin;
        this.productDesc = productDesc;
        this.priceOrigin = priceOrigin;
        this.priceOrder = priceOrder;
        this.image0 = image0;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.status = status;
    }
}
