package com.crossasyst.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ResultSetPract {
    private final String url="jdbc:postgresql://localhost:5432/demo_db";
    private final String user = "postgres";
    private final String password = "root";
    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
            Connection  conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo_db","postgres","root");
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from test");
            while(rs.next()) {
                System.out.println(rs.getInt(1)+" "+rs.getString(2));

            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
