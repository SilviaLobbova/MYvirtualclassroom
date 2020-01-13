package com.monteiro.virtualclassroom.virtualclassroom.model.dao;

// Import required packages


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.StatementExecutor;
import com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt.*;


public class UserDao {

    public  UserDao() {}

    public static void saveUser(User user) throws Exception {
        JdbcConnectionSource connectionSource = null;
        // instantiate the dao with the connection source
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);

            Dao<User, String> clashUserDao = DaoManager.createDao(connectionSource, User.class);

            clashUserDao.createOrUpdate(user);
        } finally {
            connectionSource.close();
        }
    }

    public static User getUser(String name) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
            Dao<User, String> clashUserDao = DaoManager.createDao(connectionSource, User.class); //creates a new dao object
            return clashUserDao.queryBuilder().where().eq("user_name", name).queryForFirst();
        }  finally {
            connectionSource.close();
        }
    }

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

    // delete user
    public static void deleteUser(int id) throws Exception {
        JdbcConnectionSource connectionSource = null;
        // initiate the dao with the connection source
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
            Dao<User, String> test = DaoManager.createDao(connectionSource, User.class);

            // delete function call
            DeleteBuilder<User, String> deleteBuilder = test.deleteBuilder();
            deleteBuilder.where().eq("id_user", id);
            deleteBuilder.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

