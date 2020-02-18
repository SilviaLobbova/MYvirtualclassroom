package com.monteiro.virtualclassroom.virtualclassroom.model.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Answer;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Option;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Question;
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
            Dao<Question, String> questionDao = DaoManager.createDao(connectionSource, Question.class);

            //create the link between the foreign keys and their tables
            optionDao.refresh(answer.getOption());
            userDao.refresh(answer.getUser());
            questionDao.refresh(answer.getOption().getQuestion());

            //build the query of all answers of this user
            QueryBuilder<Answer, String> answerQb = answerDao.queryBuilder();
            answerQb.where().eq("id_user", answer.getUser().getUser_id());

            //build the query of all options for this question
            QueryBuilder<Option, String> optionQb = optionDao.queryBuilder();
            optionQb.where().eq("id_question", answer.getOption().getQuestion().getId_question());

            //join both builders and query, it creates a joined list
            List<Answer> selectedQuery = answerQb.join(optionQb).query();
            System.out.println("My joined list " + selectedQuery);

            //new user in the table, save his answer
            if (answerDao.queryBuilder().where().eq("id_user", answer.getUser().getUser_id()).countOf() == 0) {
                System.out.println("I cannot see this user in the table answers");
                answerDao.createOrUpdate(answer);
            }
            //the join done before has no result : user has not answered this very question yet
            else if (selectedQuery.size() == 0) {
                System.out.println("I can see the user and he has answered another question");
                answerDao.createOrUpdate(answer);
            }
            //update the existing answer of this user to this question
            else {
                System.out.println("There is already an answer to this question");
                UpdateBuilder<Answer, String> updateBuilder = answerDao.updateBuilder();
                // set the criteria
                updateBuilder.where().eq("id_user", answer.getUser().getUser_id());
                // update the value of the target fields
                updateBuilder.updateColumnValue("id_option", answer.getOption().getId_option());
                // update execution
                updateBuilder.update();
            }

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

    public static Answer getAnswer(int userId) throws SQLException, IOException {
        JdbcConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);

            Dao<Answer, String> answerDao = DaoManager.createDao(connectionSource, Answer.class);//creates a new dao object

            return answerDao.queryBuilder().where().eq("id_user", userId).queryForFirst();

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
