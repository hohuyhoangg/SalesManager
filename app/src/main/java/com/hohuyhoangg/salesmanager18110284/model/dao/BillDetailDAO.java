package com.hohuyhoangg.salesmanager18110284.model.dao;

import com.hohuyhoangg.salesmanager18110284.db.DatabaseUtils;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataGet;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataUpdateAutoIncrement;
import com.hohuyhoangg.salesmanager18110284.model.dto.BillDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.BillDetailDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BillDetailDAO implements IDataGet<Long, BillDetailDTO>, IDataUpdateAutoIncrement<Long, BillDetailDTO> {
    @Override
    public ArrayList<BillDetailDTO> gets() {
        ArrayList<BillDetailDTO> result = new ArrayList<>();

        String query = "SELECT * FROM BILL_DETAIL;";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                BillDetailDTO billDetailModel = new BillDetailDTO(resultSet);
                result.add(billDetailModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public BillDetailDTO getById(Long id) {
        String query = "SELECT * FROM BILL_DETAIL WHERE BILL_ID = " + id + ";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        try {
            if (resultSet != null && resultSet.next()) {
                return new BillDetailDTO(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Long insert(BillDetailDTO dto) {
        String sql = "INSERT INTO BILL_DETAIL(BILL_ID, PRODUCT_ID, QUANTITY)" +
                "VALUES (?, ?, ?);";

        List<Object> parameters = Arrays.asList(
                dto.getBillId(),
                dto.getProductId(),
                dto.getQuantity()
        );
        return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
    }

    @Override
    public int update(BillDetailDTO dto) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    private static BillDetailDAO instance = null;

    private BillDetailDAO() {
    }

    public static BillDetailDAO getInstance() {
        if (instance == null) {
            instance = new BillDetailDAO();
        }
        return instance;
    }
}
