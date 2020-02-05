package com.monteiro.virtualclassroom.virtualclassroom.model.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Answer;
import static com.monteiro.virtualclassroom.virtualclassroom.ConstantsKt.*;

public class AnswerDao {

    public AnswerDao() {}

        // save answer method
        public static void saveAnswer(Answer answer) throws Exception {
            JdbcConnectionSource connectionSource = null;
            // instantiate the dao with the connection source
            try {
                connectionSource = new JdbcConnectionSource(BDD_URL, BDD_ADMIN, BDD_PSW);

                Dao<Answer, String> answerDao = DaoManager.createDao(connectionSource, Answer.class); //creates a new dao object
                answerDao.createOrUpdate(answer);
            } finally {
                connectionSource.close();
            }

    }
}
