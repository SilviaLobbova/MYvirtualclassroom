package com.monteiro.virtualclassroom.virtualclassroom.model.dao;

// Import required packages

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt.*;


public class UserDao {

    public  UserDao() {

    }

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

    public User getUser(String email) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
            Dao<User, String> clashUserDao = DaoManager.createDao(connectionSource, User.class); //creates a new dao object
            return clashUserDao.queryBuilder().where().eq("user_email", email).queryForFirst();
        }  finally {
            connectionSource.close();
        }
    }

    public static List<User> readAll() throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            // initiate the dao with the connection source
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
            Dao<User, String> readTable = DaoManager.createDao(connectionSource, User.class);
            return readTable.queryForAll();
        } finally {

            connectionSource.close();
        }
    }

    // delete user
    public static void deleteUser(int id) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            // initiate the dao with the connection source
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
            Dao<User, String> test = DaoManager.createDao(connectionSource, User.class);

            /*                    ----delete call----              */
            // DAO setting
            DeleteBuilder<User, String> deleteBuilder = test.deleteBuilder();
            // request initialization
            deleteBuilder.where().eq("id_user", id);
            // request execution
            deleteBuilder.delete();
        } finally {

            connectionSource.close();
        }
    }

    // update user
    public static void updateUser(int id,String targetColumn, String newValue) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            // initiate the DAO with the connection source
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
            Dao<User, String> update = DaoManager.createDao(connectionSource, User.class);

            /*                      ----update call----                 */
            // DAO setting
            UpdateBuilder<User,String > updateBuilder = update.updateBuilder();
            // set the criteria
            updateBuilder.where().eq("id_user", id);
            // update the value of the target fields
            updateBuilder.updateColumnValue(targetColumn, newValue);
            // update execution
            updateBuilder.update();
        } finally {

            connectionSource.close();
        }
    }
}

