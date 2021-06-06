package com.hohuyhoangg.salesmanager18110284.model.dao;

import com.hohuyhoangg.salesmanager18110284.db.DatabaseUtils;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataGet;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataUpdateAutoIncrement;
import com.hohuyhoangg.salesmanager18110284.model.dto.CartDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CartDAO implements IDataGet<Long, CartDTO>, IDataUpdateAutoIncrement<Long, CartDTO> {

    @Override
    public ArrayList<CartDTO> gets() {
        ArrayList<CartDTO> result = new ArrayList<>();
        String query = "SELECT * FROM CART;";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                CartDTO cartModel = new CartDTO(resultSet);
                result.add(cartModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ArrayList<CartDTO> getByUser(Long id) {
        ArrayList<CartDTO> result = new ArrayList<>();
        String query = "SELECT * FROM CART WHERE USER_ID = " + id + ";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                CartDTO cartModel = new CartDTO(resultSet);
                result.add(cartModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public CartDTO getById(Long id) {

        return null;
    }

    public CartDTO getByIdTwo(Long id, Long productId) {

        String query = "SELECT * FROM CART WHERE USER_ID = " + id + " AND PRODUCT_ID = " + productId + ";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        try {
            if (resultSet != null && resultSet.next()) {
                return new CartDTO(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Long insert(CartDTO dto) {
        String sql = "INSERT INTO CART(USER_ID, PRODUCT_ID, QUANTITY)" +
                "VALUES (?, ?, ?);";

        List<Object> parameters = Arrays.asList(
                dto.getUserId(),
                dto.getProductId(),
                dto.getQuantity()
        );
        return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
    }

    @Override
    public int update(CartDTO dto) {
        String sql = "UPDATE CART SET QUANTITY = ? WHERE USER_ID = ? AND PRODUCT_ID = ? ";
        List<Object> parameters = Arrays.asList(
                dto.getQuantity(),
                dto.getUserId(),
                dto.getProductId()
        );
        return DatabaseUtils.executeUpdate(sql, parameters);
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM CART WHERE USER_ID = ?";
        List<Object> parameters = Collections.singletonList(id);
        return DatabaseUtils.executeUpdate(sql, parameters);
    }

    public int deleteItem(CartDTO dto) {
        String sql = "DELETE FROM CART WHERE USER_ID = ? AND PRODUCT_ID = ?";

        List<Object> parameters = Arrays.asList(
                dto.getUserId(),
                dto.getProductId()
        );

        return DatabaseUtils.executeUpdate(sql, parameters);
    }
    private static CartDAO instance = null;

    private CartDAO() {
    }

    public static CartDAO getInstance() {
        if (instance == null) {
            instance = new CartDAO();
        }
        return instance;
    }
}
