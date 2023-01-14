package com.crossasyst.com;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Log4j2
public class JdbcConnection {
    public static  void main(String[] args){
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo_db","postgres","root");
            PreparedStatement ps=conn.prepareStatement("insert into test values(21,'suchit')");
            int i=ps.executeUpdate();
            log.info("success");
        }catch (Exception e){
            log.info(e);
        }

    }
}
