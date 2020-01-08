package com.monteiro.virtualclassroom.virtualclassroom.model.dao;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.UserBean;

import java.sql.*;

import static com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt.*;

public class UserDao {
        public void daoGetUser() {
                try {
                        Connection myConn = DriverManager.getConnection(ConstantsKt.BDD_URL, ConstantsKt.BDD_ADMIN, ConstantsKt.BDD_PSW);
                        //connectionSource = JdbcConnectionSource
                        Statement myStmt = myConn.createStatement();
                        ResultSet myRs = myStmt.executeQuery("select*from users");
                        while (myRs.next()) {
                                System.out.println(myRs.getString("user_lastname") + "," + myRs.getString("user_name"));

                        }
                } catch (SQLException e) {
                        e.printStackTrace();
                }
        }

};