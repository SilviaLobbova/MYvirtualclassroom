package com.monteiro.virtualclassroom.virtualclassroom;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Option;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Question;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.OptionDao;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.QuestionDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class C_QuestionController {
    // render CreateQuestion Page
    @GetMapping("/CreateQuestionPage")
    public String signUpRender() {
        System.out.println("GET /CreateQuestion (CreateQuestionController)");
        return "CreateQuestionPage"; //view
    }

    @PostMapping("/CreateQuestionPage")
    public String handleCreateQuestionRequest(
            @RequestParam String question_content,
            Integer id_classroom,
            @RequestParam String option,
            @RequestParam String option_content, //ArrayList<String>
            Integer id_question,
            Model model) throws Exception {

        System.out.println("POST /CreateQuestion (CreateQuestionController)");

        // Verify if a field is empty on the question head
        if( question_content.isEmpty() || (option.isEmpty()) || (option_content == null) ) {
            System.out.println("missing");
            System.out.println("question content " + question_content);
            System.out.println("option choice " + option);
            System.out.println("option content " + option_content);
            model.addAttribute("emptyField", true);
            return "CreateQuestionPage";
        }
        else {
            System.out.println(question_content+ ", " + option);
            id_classroom = 1; // temporary !!! Retrieve from session
            Question newQuestion = new Question(question_content, id_classroom , option);
            QuestionDao.saveQuestion(newQuestion);
            id_question = newQuestion.getId_question();
            System.out.println("writing question successful");
            Option newOption = new Option(option_content,id_question);
            System.out.println(option_content);
            OptionDao.saveOption(newOption);
            System.out.println("writing options successful");
            return "TeacherPage";
        }
    }
}
