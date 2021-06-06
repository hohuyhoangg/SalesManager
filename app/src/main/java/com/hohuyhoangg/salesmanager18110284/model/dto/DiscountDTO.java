package com.hohuyhoangg.salesmanager18110284.model.dto;

public class DiscountDTO {
    Integer id;
    String code;
    String title;
    Long discountMoney;

    public DiscountDTO() {
    }


    public DiscountDTO(Integer id, String code, String title, Long discountMoney) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.discountMoney = discountMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Long discountMoney) {
        this.discountMoney = discountMoney;
    }
}
