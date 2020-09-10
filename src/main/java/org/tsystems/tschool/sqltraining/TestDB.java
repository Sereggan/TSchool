package org.tsystems.tschool.sqltraining;

import java.sql.*;

public class TestDB {

    public static void main(String[] args) throws Exception{
        String dbURL = "jdbc:mysql://localhost:3306/marketdb?user=root&password=root&serverTimezone=Europe/Moscow";
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection(dbURL);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * from trains.table_trains");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            final String name = resultSet.getString(3);
            int number = resultSet.getInt(2);
            System.out.printf("%-3d %-10d %-10s%n",id,number,name);
        }
        resultSet.close();

        statement.close();
        connection.close();
    }
}
