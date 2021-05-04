package com.hohuyhoangg.salesmanager18110284.db;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AsyncTaskExecuteUpdate extends AsyncTask<List<Object>, Void, Integer> {
    @Override
    protected Integer doInBackground(List<Object>... input) {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement prepStmt = null;
        Integer result = 0;

        if (connection == null) {
            return null;
        }
        try {
            prepStmt = connection.prepareStatement(input[0].get(0).toString());
            input[0].remove(0);
            if (input[0] != null) {
                DatabaseUtils.setParameters(prepStmt, input[0]);
            }
            result = prepStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prepStmt != null) {
                try {
                    prepStmt.close();
                } catch (SQLException ex) {
                    // ignore
                }
            }
        }
        return result;
    }
}
