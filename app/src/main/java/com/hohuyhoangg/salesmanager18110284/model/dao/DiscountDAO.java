package com.hohuyhoangg.salesmanager18110284.model.dao;

import com.hohuyhoangg.salesmanager18110284.model.dto.DiscountDTO;

import java.util.ArrayList;
import java.util.List;

public class DiscountDAO {
    private static DiscountDAO instance = null;

    private DiscountDAO() {
    }

    public static DiscountDAO getInstance() {
        if (instance == null) {
            instance = new DiscountDAO();
        }
        return instance;
    }

    public List<DiscountDTO> getInitData(){
        List<DiscountDTO> lists = new ArrayList<DiscountDTO>();
        lists.add(new DiscountDTO(1,"THANG5","Quà tặng tháng 5",50000L));
        lists.add(new DiscountDTO(2,"FREESHIP","Freeship",30000L));
        lists.add(new DiscountDTO(3,"FREESHIP6","Quà tặng tháng 5",30000L));
        lists.add(new DiscountDTO(4,"FREESHIP7","Quà tặng tháng 5",30000L));
        lists.add(new DiscountDTO(5,"FREESHIP8","Quà tặng tháng 5",30000L));
        lists.add(new DiscountDTO(6,"FREESHIP9","Quà tặng tháng 5",30000L));
        return lists;
    }
}
