package com.hohuyhoangg.salesmanager18110284.model.dao;

import com.hohuyhoangg.salesmanager18110284.db.DatabaseUtils;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataGet;
import com.hohuyhoangg.salesmanager18110284.interfaces.IDataUpdateAutoIncrement;
import com.hohuyhoangg.salesmanager18110284.model.dto.UserDTO;
import com.hohuyhoangg.salesmanager18110284.utils.FormatUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UserDAO implements IDataGet<Long, UserDTO>, IDataUpdateAutoIncrement<Long, UserDTO> {
    @Override
    public ArrayList<UserDTO> gets() {
        ArrayList<UserDTO> result = new ArrayList<>();

        String query = "SELECT * FROM USER;";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        if (resultSet == null) {
            return result;
        }

        try {
            while (resultSet.next()) {
                UserDTO userModel = new UserDTO(resultSet);
                result.add(userModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    @Override
    public UserDTO getById(Long id) {
        String query = "SELECT * FROM USER WHERE USER_ID = " + id + ";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        try {
            if (resultSet != null && resultSet.next()) {
                return new UserDTO(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public UserDTO getByEmail(String email) {
        String query = "SELECT * FROM USER WHERE EMAIL = "+ "'" + email + "'" +";";
        ResultSet resultSet = DatabaseUtils.executeQuery(query, null);

        try {
            if (resultSet != null && resultSet.next()) {
                return new UserDTO(resultSet);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Long insert(UserDTO dto) {
        String sql = "INSERT INTO USER(LAST_NAME, FIRST_NAME, GENDER, DATE_OF_BIRTH, IMAGE, PHONE_NUMBER, EMAIL, USER_NAME, PASSWORD, USER_TYPE, STATUS)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        List<Object> parameters = Arrays.asList(
                dto.getLastName(),
                dto.getFirstName(),
                dto.getGender(),
                dto.getDateOfBirth(),
                dto.getImagePath(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getEmail(),
                dto.getPassword(),
                dto.getUserType(),
                dto.getStatus()
        );
        return (Long) DatabaseUtils.executeUpdateAutoIncrement(sql, parameters);
    }

    @Override
    public int update(UserDTO dto) {
        String sql = "UPDATE USER SET LAST_NAME = ?, FIRST_NAME = ?, GENDER = ?, DATE_OF_BIRTH = ?, IMAGE = ?, PHONE_NUMBER = ?, EMAIL = ?, USER_NAME = ?, PASSWORD = ?, USER_TYPE = ?, STATUS = ? WHERE USER_ID = ?";
        List<Object> parameters = Arrays.asList(
                dto.getLastName(),
                dto.getFirstName(),
                dto.getGender(),
                dto.getDateOfBirth(),
                dto.getImagePath(),
                dto.getPhoneNumber(),
                dto.getEmail(),
                dto.getUserName(),
                dto.getPassword(),
                dto.getUserType(),
                dto.getStatus(),
                dto.getUserId()
        );
        return DatabaseUtils.executeUpdate(sql, parameters);
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM USER WHERE USER_ID = ?";
        List<Object> parameters = Collections.singletonList(id);
        return DatabaseUtils.executeUpdate(sql, parameters);
    }

    private static UserDAO instance = null;

    private UserDAO() {
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }
}