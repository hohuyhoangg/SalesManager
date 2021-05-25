package com.hohuyhoangg.salesmanager18110284.model.dto;

public class NotificationDTO {

    String Title;
    String Description;
    int Thumbnail;

    public NotificationDTO() {
    }

    public NotificationDTO(String title, String description, int thumbnail) {
        Title = title;
        Description = description;
        Thumbnail = thumbnail;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }
}
