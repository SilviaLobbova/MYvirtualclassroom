package com.monteiro.virtualclassroom.virtualclassroom.model.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Classroom;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;

import java.io.IOException;
import java.sql.SQLException;

import static com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt.*;
import static com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt.BDD_PSW;

public class ClassroomDao {
    public ClassroomDao(){
    }


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
}
