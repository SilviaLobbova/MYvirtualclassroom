package com.monteiro.virtualclassroom.virtualclassroom;

// imports
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Question;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.OptionDao;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.QuestionDao;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AnswerController {

    // render CreateQuestion Page
    @GetMapping("/userConnected")

        public String handleDisplayQuestionRequest(
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
            // retrieve options store in optionDao
            value.setOptions(OptionDao.getAllOptionsFromQuestion(questionId, startRow, OptionDao.getOptionCount()));
        }
        return "TeacherPage"; //view
        }

    @PostMapping("/userConnected")  //use the save answer
    public String saveUserAnswer (
            @RequestParam int questionId,
            HttpSession session,
            Model model) throws Exception {

        System.out.println("POST /saveUserAnswer (AnswerController)");

        // get the selected class stored in the session
        long classroomId = (long) session.getAttribute("classroomID");
        // get the user_id
        int userId = (int) session.getAttribute("userID");
        // get the selected options

        // debug
        session.setAttribute("questionID", questionId);
        System.out.println("Session attribute ID classroom: " + classroomId);
        System.out.println("Session attribute ID user: " + userId);
        // get question id
        System.out.println("Session attribute ID question: " + questionId);


        return "redirect:/userConnected";
    }

    @GetMapping("/adminConnected")
    public String adminPageRender(Model model, HttpSession session) throws Exception {
        System.out.println("My Admin");

        // get the selected class stored in the session
        long classroomId = (long) session.getAttribute("classroomID");

        // creation of the list question
        int startRow = 0;

        // creation of a list which will be used by thymeleaf and store the result of the function call in the list
        List<Question> questionList = QuestionDao.getAllQuestionFromId(classroomId, startRow, QuestionDao.getQuestionCount());

        // add to the model
        model.addAttribute("questions", questionList);

        List<User>listOfUsers= UserDao.getStudentsList(classroomId);

        model.addAttribute("students", listOfUsers);
//
        for (Question value : questionList) {
            // store the id_question from the current displayed question
            int questionId = value.getId_question();
            // retrieve options store in optionDao
            value.setOptions(OptionDao.getAllOptionsFromQuestion(questionId, startRow, OptionDao.getOptionCount()));
        }
        return "TeacherPage"; //view
    }
}


