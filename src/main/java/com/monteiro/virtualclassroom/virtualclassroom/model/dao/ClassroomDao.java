package com.monteiro.virtualclassroom.virtualclassroom.model.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Classroom;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Information;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Question;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;

import java.io.IOException;
import java.sql.SQLException;

import static com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt.*;
import static com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt.BDD_PSW;

public class ClassroomDao {
    public ClassroomDao(){
    }

    // save classroom method
    public static void saveClassroom(Classroom classroom) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        // instantiate the dao with the connection source
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);

            Dao<Classroom, String> clashClassroomDao = DaoManager.createDao(connectionSource, Classroom.class); //creates a new dao object
            clashClassroomDao.createOrUpdate(classroom);
        } finally {
            connectionSource.close();
        }
    }

    // retrieve classroom method
    public static Classroom getClassroom(int classroomId) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);

            Dao<Classroom, String> clashClassroomDao = DaoManager.createDao(connectionSource, Classroom.class);//creates a new dao object

            return clashClassroomDao.queryBuilder().where().eq("id_classroom", classroomId).queryForFirst();

        }  finally {
            connectionSource.close();
        }
    }
//    public static int getClassroomCount() throws SQLException, IOException {
//        JdbcConnectionSource connectionSource = null;
//        try {
//            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
//
//            Dao<Classroom, String> clashClassroomDao = DaoManager.createDao(connectionSource, Classroom.class);//creates a new dao object
//           QueryBuilder query = clashClassroomDao.queryBuilder("Select");
//            int Ju = clashClassroomDao.countOf (query);
//            return Ju;
//
//        }  finally {
//            connectionSource.close();
//        }
//    }

    // delete classroom method
    public static void deleteClassroom(int id) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            // initiate the dao with the connection source
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
            Dao<Classroom, String> classroom = DaoManager.createDao(connectionSource, Classroom.class);

            /*                    ----delete call----              */
            // DAO setting
            DeleteBuilder<Classroom, String> deleteBuilder = classroom.deleteBuilder();
            // request initialization
            deleteBuilder.where().eq("id_classroom", id);
            // request execution
            deleteBuilder.delete();
        } finally {
            connectionSource.close();
        }
    }

    // update classroom
    public static void updateQuestion(int id,String targetColumn, String newValue) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            // initiate the DAO with the connection source
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
            Dao<Classroom, String>  classroomUpdate = DaoManager.createDao(connectionSource, Classroom.class);

            /*                      ----update call----                 */
            // DAO setting
            UpdateBuilder<Classroom,String > updateBuilder = classroomUpdate.updateBuilder();
            // set the criteria
            updateBuilder.where().eq("id_classroom", id);
            // update the value of the target fields
            updateBuilder.updateColumnValue(targetColumn, newValue);
            // update execution
            updateBuilder.update();
        } finally {
            connectionSource.close();
        }
    }
}