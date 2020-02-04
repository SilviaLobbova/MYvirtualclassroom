package com.monteiro.virtualclassroom.virtualclassroom;

// imports
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Option;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Question;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.OptionDao;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.QuestionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AnswerController {

    // render CreateQuestion Page
    @GetMapping("/userConnected")

        public String handleDisplayQuestionRequest(
                String question_content,
                String option,
                Question question,
                HttpSession session,
                Model model) throws Exception {

            System.out.println("GET /DisplayQuestion (AnswerController)");

            // get the selected class stored in the session
            long classroomId = (long) session.getAttribute("classroomID");
            // creation of the list question
            int startRow = 0;

            // creation of a list which will be used by thymeleaf and store the result of the function call in the list
            List<Question> questionList = QuestionDao.getAllQuestionFromId(classroomId, startRow, QuestionDao.getQuestionCount());

            // add to the model
            model.addAttribute("questions", questionList);

        for (Question value : questionList) {
            // store the id_question from the current displayed question
            int questionId = value.getId_question();
            System.out.println("questionId: " + questionId);
//            System.out.println("isRadio" + questionList.get().getIsRadio());
            value.setOptions(OptionDao.getAllOptionsFromQuestion(questionId, startRow, OptionDao.getOptionCount()));
            // creation of a list which will be used by thymeleaf and store the result of the function call in the list
            List<Option> optionList = OptionDao.getAllOptionsFromQuestion(questionId, startRow, OptionDao.getOptionCount());
        }
        return "TeacherPage"; //view
        }
    }

//    @PostMapping("/TeacherPage") use the save answer

//}


