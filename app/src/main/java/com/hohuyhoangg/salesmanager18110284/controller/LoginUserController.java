package com.hohuyhoangg.salesmanager18110284.controller;

import com.hohuyhoangg.salesmanager18110284.model.dao.UserDAO;
import com.hohuyhoangg.salesmanager18110284.model.dto.UserDTO;

import java.util.List;

public class LoginUserController {
    private static LoginUserController instance = null;

    private LoginUserController() {
    }

    public static LoginUserController getInstance() {
        if (instance == null) {
            instance = new LoginUserController();
        }
        return instance;
    }

    public Long checkLogin(String username, String password) {
        Long id = 0L;
        try {
            List<UserDTO> users = UserDAO.getInstance().gets();
            for (UserDTO user: users) {
                if (user.getUserName().equals(username) && user.getPassword().equals(password)){
                    return user.getUserId();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return id;
    }
}
