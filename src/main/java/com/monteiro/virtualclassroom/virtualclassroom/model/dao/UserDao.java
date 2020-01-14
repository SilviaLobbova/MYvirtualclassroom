package com.monteiro.virtualclassroom.virtualclassroom.model.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Classroom;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt.*;

public class UserDao {

        public UserDao(){
        }

        public static void saveUser(User user) throws SQLException, IOException {
                JdbcConnectionSource connectionSource = null;
                // instantiate the dao with the connection source
                try {
                connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
                Dao<User, String> clashUserDao = DaoManager.createDao(connectionSource, User.class); //creates a new dao object
                clashUserDao.createOrUpdate(user);
                } finally {
                        connectionSource.close();
                }
        }
        public static User getUser(String email, String password) throws SQLException, IOException {
                JdbcConnectionSource connectionSource = null;
                try {
                        connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);

                        Dao<User, String> clashUserDao = DaoManager.createDao(connectionSource, User.class);
                        Dao<Classroom, String> clashClassroomDao = DaoManager.createDao(connectionSource, Classroom.class);//creates a new dao object

                       User returnedUser = clashUserDao.queryBuilder().where().
                               eq("user_email", email).eq("user_password", password).queryForFirst();
                        return returnedUser;
                }  finally {
                        connectionSource.close();
                }
        }

        //while (rs.hasNext()) { // will traverse through all rows
        //        String firstName = rs.getString("first_name");
         //       String lastName = rs.getString("last_name");

        public static List<User> readAll(){

                try {
                        JdbcConnectionSource connectionSource = null;
                        connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
                        Dao<User, String> readTable = DaoManager.createDao(connectionSource, User.class);
                        return readTable.queryForAll();
                }
               catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                }

        }

};
