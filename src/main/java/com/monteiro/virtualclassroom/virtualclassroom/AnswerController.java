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
import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
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

        User user = (User) session.getAttribute("user");
        int userId = user.getUser_id();
        System.out.println("THe ID of my connected user" + userId);
        // creation of the list question
        int startRow = 0;

        // creation of a list which will be used by thymeleaf and store the result of the function call in the list
        List<Question> questionList = QuestionDao.getAllQuestionFromId(classroomId, startRow, QuestionDao.getQuestionCount());
        List<Question> selectiveQuestionList = QuestionDao.getAllQuestionsFromClassExceptOneUser(classroomId, userId, startRow, QuestionDao.getQuestionCount());
        // add to the model
        model.addAttribute("questions", questionList);
        model.addAttribute("selectedQuestions", selectiveQuestionList);
        int i;
        for (i = 0; i < selectiveQuestionList.size(); i++) {
            System.out.println("my selective list" + selectiveQuestionList.get(i).getQuestion_content());
        }


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
            long formLength = answerForm.getMultiCheckboxSelectedValues().length;
            for (int i = 0; i < formLength; i++) {
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
        questionList.sort(Comparator.comparing(Question::getId_question));
        // add to the model
        model.addAttribute("questions", questionList);

        List<Information> informationList = InformationDao.showInformation(classroomId);
        model.addAttribute("information", informationList);

        List<User> listOfUsers = UserDao.getStudentsList(classroomId);
        model.addAttribute("students", listOfUsers);

        List<Answer> listOfAnswers = AnswerDao.getAnswers();


        System.out.println("my list of answers" + listOfAnswers);
        System.out.println("one of my answers_option" + listOfAnswers.get(0).getOption().getId_option());
        System.out.println("one of my answers_user" + listOfAnswers.get(0).getUser().getUser_name());
        model.addAttribute("answers", listOfAnswers);


        for (Question value : questionList) {
            // store the id_question from the current displayed question
            int questionId = value.getId_question();
            System.out.println(questionId);
//            List<Answer> listOfAnswersFromQuestion = AnswerDao.getAllAnswersOfQuestion(questionId, id, n);
//
//            model.addAttribute("answersOfQ", listOfAnswersFromQuestion);
//            System.out.println("my list of answers of the same Question" + listOfAnswersFromQuestion);

            // retrieve options store in optionDao
            value.setOptions(OptionDao.getAllOptionsFromQuestion(questionId, startRow, OptionDao.getOptionCount()));
            value.setAnswers(AnswerDao.getAllAnswersOfQuestion(questionId, startRow, AnswerDao.getAnswerCount()));
        }
        return "TeacherPage"; //view
    }

    @PostMapping("/deleteQuestion")
    public String deleteQuestion(int questionId) throws IOException, SQLException {
        System.out.println("I try to display the question value");
        System.out.println(questionId);
        QuestionDao.deleteQuestion(questionId);

        return "redirect:/adminConnected";
    }

    @PostMapping("/updateQuestion")
    public String updateQuestion(int questionToModify, String newQuestionContent) throws IOException, SQLException {
        System.out.println("I try to display the question value");
        System.out.println(questionToModify);
        Question q = QuestionDao.getQuestion(questionToModify);
        if (!newQuestionContent.equals("") && !newQuestionContent.equals(q.getQuestion_content())) {
            QuestionDao.updateQuestion(questionToModify, newQuestionContent);
        }
        return "redirect:/adminConnected";
    }

    @PostMapping("/updateOption")
    public String updateOption(int optionToModify, String newOptionContent) throws Exception {
        System.out.println(optionToModify);
        Option o = OptionDao.getOption(optionToModify);
        if (!newOptionContent.equals("") && !newOptionContent.equals(o.getOption_content())) {
            OptionDao.updateOption(optionToModify, newOptionContent);
        }
        return "redirect:/adminConnected";
    }
}


