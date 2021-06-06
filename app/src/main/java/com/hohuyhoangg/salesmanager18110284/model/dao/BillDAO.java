package com.hohuyhoangg.salesmanager18110284.model.dao;

import com.hohuyhoangg.salesmanager18110284.db.DatabaseUtils;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataGet;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataUpdateAutoIncrement;
import com.hohuyhoangg.salesmanager18110284.model.dto.BillDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillDAO implements IDataGet<Long, BillDTO>, IDataUpdateAutoIncrement<Long, BillDTO> {

    @Override
    public ArrayList<BillDTO> gets() {

        ArrayList<BillDTO> result = new ArrayList<>();

        String query = "SELECT * FROM BILL;";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                BillDTO billModel = new BillDTO(resultSet);
                result.add(billModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public BillDTO getById(Long id) {
        String query = "SELECT * FROM BILL WHERE BILL_ID = " + id + ";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        try {
            if (resultSet != null && resultSet.next()) {
                return new BillDTO(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Long insert(BillDTO dto) {
        String sql = "INSERT INTO BILL(USER_ID, PRICE, PRICE_PRODUCT, PRICE_DELIVERY, PRICE_DISCOUNT, DATE)" +
                "VALUES (?, ?, ?, ?, ?, ?);";

        List<Object> parameters = Arrays.asList(
                dto.getUserId(),
                dto.getPrice(),
                dto.getPriceProduct(),
                dto.getPriceDelivery(),
                dto.getPriceDiscount(),
                dto.getDate()
        );
        return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
    }

    @Override
    public int update(BillDTO dto) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }
    private static BillDAO instance = null;

    private BillDAO() {
    }

    public static BillDAO getInstance() {
        if (instance == null) {
            instance = new BillDAO();
        }
        return instance;
    }
}
