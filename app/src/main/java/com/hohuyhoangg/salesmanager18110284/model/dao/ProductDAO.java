package com.hohuyhoangg.salesmanager18110284.model.dao;


import com.hohuyhoangg.salesmanager18110284.db.DatabaseUtils;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataGet;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataUpdateAutoIncrement;
import com.hohuyhoangg.salesmanager18110284.model.dto.ProductDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductDAO implements IDataGet<Long, ProductDTO>, IDataUpdateAutoIncrement<Long, ProductDTO> {

    @Override
    public ArrayList<ProductDTO> gets() {
        ArrayList<ProductDTO> result = new ArrayList<>();

        String query = "SELECT * FROM PRODUCT;";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                ProductDTO productModel = new ProductDTO(resultSet);
                result.add(productModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ArrayList<ProductDTO> getListProductBySellerId(Long id) {
        ArrayList<ProductDTO> result = new ArrayList<>();

        String query = "SELECT * FROM `PRODUCT` WHERE SELLER_ID = "+ id +";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                ProductDTO productModel = new ProductDTO(resultSet);
                result.add(productModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ArrayList<ProductDTO> searchLike(String text) {
        ArrayList<ProductDTO> result = new ArrayList<>();

        String query = "SELECT * FROM `PRODUCT` WHERE PRODUCT_NAME LIKE '%"+ text + "%';";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                ProductDTO productModel = new ProductDTO(resultSet);
                result.add(productModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ArrayList<ProductDTO> getListProductByProductId(Long id) {
        ArrayList<ProductDTO> result = new ArrayList<>();

        String query = "SELECT * FROM `PRODUCT` AS A, ((SELECT DISTINCT PRODUCT_ID FROM `CATEGORY_PRODUCT` AS B " +
                "WHERE CATEGORY_ID IN (SELECT CATEGORY_ID FROM `CATEGORY_PRODUCT` WHERE PRODUCT_ID =" + id + "))) AS " +
                "B WHERE A.PRODUCT_ID = B.PRODUCT_ID;";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                ProductDTO productModel = new ProductDTO(resultSet);
                result.add(productModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ArrayList<ProductDTO> getListProductByUserId(Long id) {
        ArrayList<ProductDTO> result = new ArrayList<>();

        String query = "SELECT * FROM `PRODUCT` AS A, (SELECT DISTINCT PRODUCT_ID FROM `BILL_DETAIL` WHERE BILL_ID IN (SELECT BILL_ID FROM `BILL` " +
                "WHERE USER_ID = "+ id + ")) AS B WHERE A.PRODUCT_ID = B.PRODUCT_ID;";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                ProductDTO productModel = new ProductDTO(resultSet);
                result.add(productModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ArrayList<ProductDTO> getListProductByBrandId(Long id) {
        ArrayList<ProductDTO> result = new ArrayList<>();

        String query = "SELECT * FROM `PRODUCT` WHERE BRAND_ID = "+ id + ";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                ProductDTO productModel = new ProductDTO(resultSet);
                result.add(productModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ArrayList<ProductDTO> getListProductByCategoryId(Long id) {
        ArrayList<ProductDTO> result = new ArrayList<>();

        String query = "SELECT * FROM `PRODUCT` WHERE PRODUCT_ID IN (SELECT PRODUCT_ID FROM `CATEGORY_PRODUCT` WHERE CATEGORY_ID = "+ id + ")";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                ProductDTO productModel = new ProductDTO(resultSet);
                result.add(productModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }
    @Override
    public ProductDTO getById(Long id) {
        String query = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = " + id + ";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        try {
            if (resultSet != null && resultSet.next()) {
                return new ProductDTO(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Long insert(ProductDTO dto) {
        String sql = "INSERT INTO PRODUCT(BRAND_ID, SELLER_ID, PRODUCT_NAME, PRODUCT_RATE, PRODUCT_ORIGIN, PRODUCT_DESC, QUANTITY, " +
                "PRICE_ORIGIN, PRICE_ORDER, IMAGE_0, STATUS)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        List<Object> parameters = Arrays.asList(
                dto.getBrandId(),
                dto.getSellerId(),
                dto.getProductName(),
                dto.getProductRate(),
                dto.getProductOrigin(),
                dto.getProductDesc(),
                dto.getQuantity(),
                dto.getPriceOrigin(),
                dto.getPriceOrder(),
                dto.getImage0(),
                dto.getStatus()
        );
        return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
    }
    @Override
    public int update(ProductDTO dto) {
        String sql = "UPDATE USER SET BRAND_ID = ?, SELLER_ID = ?, PRODUCT_NAME = ?, PRODUCT_RATE = ?, PRODUCT_ORIGIN = ?, PRODUCT_DESC = ?, QUANTITY = ?, " +
                "PRICE_ORIGIN = ?, PRICE_ORDER = ?, IMAGE_0 = ?, IMAGE_1 = ?, IMAGE_2 = ?, IMAGE_3 = ?, IMAGE_4, STATUS = ? WHERE PRODUCT_ID = ?";
        List<Object> parameters = Arrays.asList(
                dto.getBrandId(),
                dto.getSellerId(),
                dto.getProductName(),
                dto.getProductRate(),
                dto.getProductOrigin(),
                dto.getProductDesc(),
                dto.getQuantity(),
                dto.getPriceOrigin(),
                dto.getPriceOrder(),
                dto.getImage0(),
                dto.getImage1(),
                dto.getImage2(),
                dto.getImage3(),
                dto.getImage4(),
                dto.getStatus(),
                dto.getProductId()
        );
        return DatabaseUtils.executeUpdate(sql, parameters);
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM PRODUCT WHERE PRODUCT_ID = ?";
        List<Object> parameters = Collections.singletonList(id);
        return DatabaseUtils.executeUpdate(sql, parameters);
    }

    private static ProductDAO instance = null;

    private ProductDAO() {
    }

    public static ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }
}
