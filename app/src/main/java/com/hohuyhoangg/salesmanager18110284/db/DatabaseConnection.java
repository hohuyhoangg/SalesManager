package com.hohuyhoangg.salesmanager18110284.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Config JDBC Connection and get JDBC Connection
 */
public class DatabaseConnection {
   final static String url = "jdbc:mysql://192.168.0.104:3306/salesManager?useUnicode=true&characterEncoding=utf-8";
   final static String username = "hohuyhoang";
   final static String password = "hohuyhoang";


//   final static String url = "jdbc:mysql://us-cdbr-east-03.cleardb.com:3306/heroku_98680369f041189?useUnicode=true&characterEncoding=utf-8";
//   final static String username = "b41f97dd689cb7";
//   final static String password = "6166fd45";

   /**
    * Get MySql JDBC Connection
    *
    * @return Connection to MySql database or null
    */
   public static Connection getConnection() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         return DriverManager.getConnection(url, username, password);
      } catch (SQLException | ClassNotFoundException e) {
         e.printStackTrace();
      }
      return null;
   }
}
