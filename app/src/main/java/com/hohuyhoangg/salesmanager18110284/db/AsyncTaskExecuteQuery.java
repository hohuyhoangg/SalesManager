package com.hohuyhoangg.salesmanager18110284.db;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AsyncTaskExecuteQuery extends AsyncTask<List<Object>, Void, ResultSet> {
    @Override
    protected ResultSet doInBackground(List<Object>... input) {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        if (connection == null) {
            return null;
        }
        try {
            prepStmt = connection.prepareStatement(input[0].get(0).toString());
            input[0].remove(0);
            if (input[0] != null) {
                com.hohuyhoangg.salesmanager18110284.db.DatabaseUtils.setParameters(prepStmt, input[0]);
            }
            resultSet = prepStmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
