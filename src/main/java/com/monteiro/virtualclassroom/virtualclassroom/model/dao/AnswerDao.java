package com.monteiro.virtualclassroom.virtualclassroom.model.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Answer;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Option;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt.*;

public class AnswerDao {

    public AnswerDao() {
    }

    // save answer method
    public static void saveAnswer(Answer answer) throws Exception {
        JdbcConnectionSource connectionSource = null;
        // instantiate the dao with the connection source
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);

            Dao<Answer, String> answerDao = DaoManager.createDao(connectionSource, Answer.class); //creates a new dao object
            Dao<Option, String> optionDao = DaoManager.createDao(connectionSource, Option.class);
            Dao<User, String> userDao = DaoManager.createDao(connectionSource, User.class);

            optionDao.refresh(answer.getOption());
            userDao.refresh(answer.getUser());

            answerDao.createOrUpdate(answer);
        } finally {
            connectionSource.close();
        }
    }

    public static long getAnswerCount() throws Exception {
        JdbcConnectionSource connectionSource = null;

        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
            Dao<Answer, String> answerDao = DaoManager.createDao(connectionSource, Answer.class);
            return answerDao.queryBuilder().countOf();
        } finally {
            connectionSource.close();
        }
    }

    public static Answer getAnswer(long answerId) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);

            Dao<Answer, String> answerDao = DaoManager.createDao(connectionSource, Answer.class);//creates a new dao object

            return answerDao.queryBuilder().where().eq("id_answer", answerId).queryForFirst();

        } finally {
            connectionSource.close();
        }
    }

    public static List<Answer> getAnswers() throws SQLException, IOException {
        List<Answer> AnswerList = new ArrayList<>();
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);
            Dao<Answer, String> answerDao = DaoManager.createDao(connectionSource, Answer.class);
            Dao<Option, String> optionDao = DaoManager.createDao(connectionSource, Option.class);
            Dao<User, String> userDao = DaoManager.createDao(connectionSource, User.class);

            QueryBuilder<User, String> userQb = userDao.queryBuilder();
            QueryBuilder<Option, String> optionQb = optionDao.queryBuilder();
            AnswerList = answerDao.queryBuilder().join(userQb).join(optionQb).query();
            return AnswerList;

        } finally {
            connectionSource.close();
        }
    }

    public static List<Answer> getAllAnswersOfQuestion(int id_question, long startRow, long endRow) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);

            Dao<Answer, String> answerDao = DaoManager.createDao(connectionSource, Answer.class);//creates a new dao object

            Dao<Option, String> optionDao = DaoManager.createDao(connectionSource, Option.class);

            QueryBuilder<Option, String> optionQb = optionDao.queryBuilder();
            optionQb.where().eq("id_question", id_question);
            QueryBuilder<Answer, String> answerQb = answerDao.queryBuilder();
            // join with the order query
            List<Answer> results = answerQb.join(optionQb).offset(startRow).limit(endRow).query();
            return results;

        } finally {
            connectionSource.close();
        }
    }
}
