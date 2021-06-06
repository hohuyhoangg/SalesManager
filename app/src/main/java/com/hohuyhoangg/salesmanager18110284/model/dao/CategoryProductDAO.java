package com.hohuyhoangg.salesmanager18110284.model.dao;

import com.hohuyhoangg.salesmanager18110284.db.DatabaseUtils;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataGet;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataUpdateAutoIncrement;
import com.hohuyhoangg.salesmanager18110284.model.dto.BillDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryProductDAO implements IDataGet<Long, CategoryProductDTO>, IDataUpdateAutoIncrement<Long, CategoryProductDTO> {
    @Override
    public ArrayList<CategoryProductDTO> gets() {
        return null;
    }

    public ArrayList<CategoryProductDTO> getListCategoryByProductId(Long id) {

        ArrayList<CategoryProductDTO> result = new ArrayList<>();

        String query = "SELECT * FROM CATEGORY_PRODUCT WHERE PRODUCT_ID = " + id + ";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                CategoryProductDTO model = new CategoryProductDTO(resultSet);
                result.add(model);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ArrayList<CategoryProductDTO> getListProductByCategoryId(Long id) {

        ArrayList<CategoryProductDTO> result = new ArrayList<>();

        String query = "SELECT * FROM CATEGORY_PRODUCT WHERE CATEGORY_ID = " + id + ";";

        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                CategoryProductDTO model = new CategoryProductDTO(resultSet);
                result.add(model);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }


    @Override
    public CategoryProductDTO getById(Long id) {
        return null;
    }

    @Override
    public Long insert(CategoryProductDTO dto) {
        return null;
    }

    @Override
    public int update(CategoryProductDTO dto) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    private static CategoryProductDAO instance = null;

    private CategoryProductDAO() {
    }

    public static CategoryProductDAO getInstance() {
        if (instance == null) {
            instance = new CategoryProductDAO();
        }
        return instance;
    }
}
