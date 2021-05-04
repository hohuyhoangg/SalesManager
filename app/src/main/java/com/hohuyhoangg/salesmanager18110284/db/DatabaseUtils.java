package com.hohuyhoangg.salesmanager18110284.db;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DatabaseUtils {
    public static void setParameters(PreparedStatement prepStmt, List<Object> parameters) {
        if (parameters == null) {
            return;
        }
        try {
            for (int i = 0; i < parameters.size(); i++) {
                Object para = parameters.get(i);

                if (para instanceof Boolean) prepStmt.setBoolean(i + 1, (Boolean) para);
                else if (para instanceof Byte) prepStmt.setByte(i + 1, (Byte) para);
                else if (para instanceof Integer) prepStmt.setInt(i + 1, (Integer) para);
                else if (para instanceof Float) prepStmt.setFloat(i + 1, (Float) para);
                else if (para instanceof Long) prepStmt.setLong(i + 1, (Long) para);
                else if (para instanceof Double) prepStmt.setDouble(i + 1, (Double) para);
                else if (para instanceof BigDecimal) prepStmt.setBigDecimal(i + 1, (BigDecimal) para);
                else if (para instanceof String) prepStmt.setString(i + 1, (String) para);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static List<Object> setQuery(String query, List<Object> parameter) {
        List<Object> result = new ArrayList<>();
        result.add(query);
        if (parameter != null) {
            result.addAll(parameter);
        }
        return result;
    }

    public static ResultSet executeQuery(String query, List<Object> parameter) {
        ResultSet rs = null;
        try {
            rs = new AsyncTaskExecuteQuery().execute(setQuery(query, parameter)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static Integer executeUpdate(String query, List<Object> parameter) {
        Integer result = 0;
        try {
            result = new com.hohuyhoangg.salesmanager18110284.db.AsyncTaskExecuteUpdate().execute(setQuery(query, parameter)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object executeUpdateAutoIncrement(String query, List<Object> parameter) {
        Object result = null;
        try {
            result = new AsyncTaskExecuteUpdateAutoIncrement().execute(setQuery(query, parameter)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
