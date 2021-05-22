package com.hohuyhoangg.salesmanager18110284.model.dao;


import com.hohuyhoangg.salesmanager18110284.db.DatabaseUtils;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataGet;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataUpdateAutoIncrement;
import com.hohuyhoangg.salesmanager18110284.model.dto.BrandDTO;
import com.hohuyhoangg.salesmanager18110284.model.dto.CategoryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CategoryDAO implements IDataGet<Long, CategoryDTO>, IDataUpdateAutoIncrement<Long, CategoryDTO> {
    @Override
    public ArrayList<CategoryDTO> gets() {
        ArrayList<CategoryDTO> result = new ArrayList<>();

        String query = "SELECT * FROM CATEGORY;";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                CategoryDTO categoryModel = new CategoryDTO(resultSet);
                result.add(categoryModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public CategoryDTO getById(Long id) {
        String query = "SELECT *  FROM CATEGORY WHERE CATEGORY_ID = " + id + ";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        try {
            if (resultSet != null && resultSet.next()) {
                return new CategoryDTO(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Long insert(CategoryDTO dto) {
        String sql = "INSERT INTO USER(CATEGORY_TITLE, IMAGE, STATUS)" +
                "VALUES (?, ?, ?);";

        List<Object> parameters = Arrays.asList(
                dto.getTitle(),
                dto.getImagePath(),
                dto.getStatus()
        );
        return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
    }

    @Override
    public int update(CategoryDTO dto) {
        String sql = "UPDATE CATEGORY SET CATEGORY_TITLE = ?, IMAGE = ?, STATUS = ? WHERE CATEGORY_ID = ?";
        List<Object> parameters = Arrays.asList(
                dto.getTitle(),
                dto.getImagePath(),
                dto.getStatus(),
                dto.getCategoryId()
        );
        return DatabaseUtils.executeUpdate(sql, parameters);
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM CATEGORY WHERE CATEGORY_ID = ?";
        List<Object> parameters = Collections.singletonList(id);
        return DatabaseUtils.executeUpdate(sql, parameters);
    }

    private static CategoryDAO instance = null;

    private CategoryDAO() {
    }

    public static CategoryDAO getInstance() {
        if (instance == null) {
            instance = new CategoryDAO();
        }
        return instance;
    }
}
