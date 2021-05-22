package com.hohuyhoangg.salesmanager18110284.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDTO {
    Long brandId;
    String brandName;
    String brandOrigin;
    String imagePath;

    public BrandDTO (ResultSet resultSet) {
        try {
            brandId = resultSet.getLong("BRAND_ID");
            brandName = resultSet.getString("BRAND_NAME");
            brandOrigin = resultSet.getString("BRAND_ORIGIN");
            imagePath = resultSet.getString("IMAGE");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public BrandDTO() {
    }

    public BrandDTO(Long brandId, String brandName, String brandOrigin, String imagePath) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandOrigin = brandOrigin;
        this.imagePath = imagePath;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandOrigin() {
        return brandOrigin;
    }

    public void setBrandOrigin(String brandOrigin) {
        this.brandOrigin = brandOrigin;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}

