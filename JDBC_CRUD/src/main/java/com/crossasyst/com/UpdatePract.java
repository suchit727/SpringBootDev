package com.crossasyst.com;

import java.sql.*;

public class UpdatePract {
    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo_db","postgres","root");
            String ud="update test set id=3 where name='suchit'";
            PreparedStatement ps=conn.prepareStatement(ud);
            int i=ps.executeUpdate();
            if(i==1){
                System.out.println("updated ");
            }else{
                System.out.println("not updated");
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}
