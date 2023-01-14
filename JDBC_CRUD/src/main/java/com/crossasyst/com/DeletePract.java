package com.crossasyst.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeletePract {
    private final String url="jdbc:postgresql://localhost:5432/demo_db";
    private final String user = "postgres";
    private final String password = "root";
    public static void main(String[] args) {
      Connection conn=null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo_db", "postgres", "root");
            PreparedStatement pstm = conn.prepareStatement("Delete from test where id=21");
           int i= pstm.executeUpdate();
            System.out.println("Deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
