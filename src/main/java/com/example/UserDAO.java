package com.example;

import java.sql.*;
import java.util.*;

public class UserDAO {

    public static int save(User u) {

        int status=0;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
            "insert into users(name,email) values (?,?)");

            ps.setString(1,u.getName());
            ps.setString(2,u.getEmail());

            status = ps.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }

    public static List<User> getAllUsers(){

        List<User> list=new ArrayList<>();

        try{

            Connection con = DBConnection.getConnection();

            PreparedStatement ps=con.prepareStatement("select * from users");

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                User u=new User();

                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));

                list.add(u);
            }

        }catch(Exception e){e.printStackTrace();}

        return list;
    }
}
