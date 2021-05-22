package com.hohuyhoangg.salesmanager18110284.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryDTO {

    Long categoryId;
    String title;
    String imagePath;
    Boolean status;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CategoryDTO(ResultSet resultSet) {
        try {
            categoryId = resultSet.getLong("CATEGORY_ID");
            title = resultSet.getString("CATEGORY_TITLE");
            imagePath = resultSet.getString("IMAGE");
            status = resultSet.getBoolean("STATUS");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public CategoryDTO() {
    }

    public CategoryDTO(Long categoryId, String title, String imagePath, Boolean status) {
        this.categoryId = categoryId;
        this.title = title;
        this.imagePath = imagePath;
        this.status = status;
    }
}
