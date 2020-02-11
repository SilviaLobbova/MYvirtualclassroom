package com.monteiro.virtualclassroom.virtualclassroom;

// imports

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.*;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AnswerController {

    // render CreateQuestion Page
    @GetMapping("/userConnected")
    public String handleDisplayQuestionRequest(
            HttpSession session,
            Model model
    ) throws Exception {

        System.out.println("GET /DisplayQuestion (AnswerController)");

        // get the selected class stored in the session
        Classroom classroom = (Classroom) session.getAttribute("classroom");
        long classroomId = classroom.getId_classroom();

        // creation of the list question
        int startRow = 0;

        // creation of a list which will be used by thymeleaf and store the result of the function call in the list
        List<Question> questionList = QuestionDao.getAllQuestionFromId(classroomId, startRow, QuestionDao.getQuestionCount());

        // add to the model
        model.addAttribute("questions", questionList);

        List<Information> informationList = InformationDao.showInformation(classroomId);
        model.addAttribute("information", informationList);

        AnswerForm answerForm = new AnswerForm();
        model.addAttribute("answerForm", answerForm);

        for (Question value : questionList) {
            // store the id_question from the current displayed question
            int questionIdentification = value.getId_question();
            // retrieve options store in optionDao
            value.setOptions(OptionDao.getAllOptionsFromQuestion(questionIdentification, startRow, OptionDao.getOptionCount()));
        }
        return "TeacherPage"; //view
    }

    @PostMapping("/sendAnswer")  //use the save answer
    public String saveUserAnswer(
            @ModelAttribute("answerForm") AnswerForm answerForm,
            // Option selectedOption, // specify value request in tab
            HttpSession session,
            Model model) throws Exception {
        Option checkBoxOptions;
        Option radioOption = OptionDao.getOption(answerForm.getId_option());

        // get the selected class stored in the session
        Classroom classroom = (Classroom) session.getAttribute("classroom");
        long classroomId = classroom.getId_classroom();
        // get the user_id
        User userInSession = (User) session.getAttribute("user");

        if (radioOption == null) {
            for (int i = 0; i < 2; i++) {
                checkBoxOptions = OptionDao.getOption(answerForm.getMultiCheckboxSelectedValues()[i]);
                System.out.println(checkBoxOptions);
                Answer newAnswer1 = new Answer();
                newAnswer1.setOption(checkBoxOptions);
                newAnswer1.setUser(userInSession);
                AnswerDao.saveAnswer(newAnswer1);
            }
        } else {
            Answer newAnswer = new Answer();
            newAnswer.setOption(radioOption);
            newAnswer.setUser(userInSession);
            AnswerDao.saveAnswer(newAnswer);
        }

        System.out.println("Session attribute ID classroom: " + classroomId);
        System.out.println("Session attribute ID user: " + userInSession);
        // get question id
        //  System.out.println("Session attribute ID question: " + option.getQuestion().getId_question());

        return "redirect:/userConnected";
    }


    @GetMapping("/adminConnected")
    public String adminPageRender(Model model, HttpSession session) throws Exception {
        System.out.println("My Admin");

        // get the selected class stored in the session
        Classroom classroom1 = (Classroom) session.getAttribute("classroom");
        System.out.println("classroom1 " + classroom1);
        long classroomId = classroom1.getId_classroom();
        System.out.println("classroomId" + classroomId);

        // creation of the list question
        int startRow = 0;

        // creation of a list which will be used by thymeleaf and store the result of the function call in the list
        List<Question> questionList = QuestionDao.getAllQuestionFromId(classroomId, startRow, QuestionDao.getQuestionCount());
        // add to the model
        model.addAttribute("questions", questionList);

        List<Information> informationList = InformationDao.showInformation(classroomId);
        model.addAttribute("information", informationList);

        List<User> listOfUsers = UserDao.getStudentsList(classroomId);
        model.addAttribute("students", listOfUsers);

        List<Answer> listOfAnswers = AnswerDao.getAllAnswersOfUser(21);
        System.out.println("my list of answers" + listOfAnswers);
        model.addAttribute("answers", listOfAnswers);
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


