package com.hohuyhoangg.salesmanager18110284.db;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AsyncTaskExecuteUpdateAutoIncrement extends AsyncTask<List<Object>, Void, Object> {
    @Override
    protected Object doInBackground(List<Object>... input) {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        Object result = null;

        if (connection == null) {
            return null;
        }
        try {
            prepStmt = connection.prepareStatement(input[0].get(0).toString(), Statement.RETURN_GENERATED_KEYS);
            input[0].remove(0);
            if (input[0] != null) {
                DatabaseUtils.setParameters(prepStmt, input[0]);
            }
            if (prepStmt.executeUpdate() != 0) {
                rs = prepStmt.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getObject(1);
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            if (prepStmt != null) {
                try {
                    prepStmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }
}
