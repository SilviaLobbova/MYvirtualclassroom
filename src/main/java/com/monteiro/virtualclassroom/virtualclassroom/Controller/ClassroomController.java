package com.monteiro.virtualclassroom.virtualclassroom.controller;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.*;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@Controller
public class ClassroomController {
    ClassroomDao classroomDao = new ClassroomDao();

    @GetMapping("/")
    public String homePageRender(Model model, HttpSession session) throws IOException, SQLException {
        System.out.println("GET /HomePage (ClassroomController)");
        List<Classroom> classroomList;
        long n = ClassroomDao.getClassroomCount();
        int id = 0;
        classroomList = ClassroomDao.getClassroomList();
        //sorting the classroom's list by alphabetical order
        classroomList.sort(Comparator.comparing(Classroom::getClassroom_name));
        System.out.println("classes count" + n);
        System.out.println("classrooms :" + classroomList);
        model.addAttribute("classrooms", classroomList);

        if ((classroomList.isEmpty()) && (session.getAttribute("user") == null)) {
            model.addAttribute("adminAccess", true);
        } else if ((classroomList.isEmpty()) && (session.getAttribute("user") != null)) {
            model.addAttribute("adminClassList", true);
            model.addAttribute("adminAddClass", true);
        } else if ((!classroomList.isEmpty()) && (session.getAttribute("user") != null)) {
            model.addAttribute("adminAddClass", true);
        }
        return "HomePage";
    }

    @RequestMapping(value = "/studentFrame/{className}", method = RequestMethod.GET)
    public String getStudentsOfTheClass(@PathVariable("className") String className, Model model) throws IOException, SQLException {
        System.out.println("enters the studentFrame Controller");
        model.addAttribute("studentFrameActive", true);
        List<User> studentsList;
        Classroom myClass = classroomDao.getClassroomByName(className);
        studentsList = UserDao.getStudentsList(myClass);
        System.out.println("List of students" + studentsList);
        model.addAttribute("students", studentsList);
        return "fragments/studentFrame";
    }

    @RequestMapping(value = "/deleteStudent")
    public String deleteStudentFromClassroomList(int studentDelete) throws IOException, SQLException {
        User user = UserDao.getUser(studentDelete);
        List<Answer> answers = AnswerDao.getUserAnswersList(user.getUser_id());
        for (Answer answer : answers) {
            AnswerDao.deleteAnswer(answer);
        }
        UserDao.deleteUser(user);
        return "redirect:/";
    }

    @PostMapping("/addClassroom")
    @ResponseBody
    public String createClassroom(@RequestParam String classroomName) throws IOException, SQLException {
        Classroom newClassroom = new Classroom(classroomName);
        if (classroomName.equals("")) {
            return ("empty");
        } else if (classroomDao.getClassroomByName(classroomName) != null) {
            return ("exists");
        } else {
            ClassroomDao.saveClassroom(newClassroom);
            return ("success");
        }
    }

    @PostMapping("/deleteClassroom")
    public String deleteClassroomFromList(@RequestParam String classDelete) throws IOException, SQLException {
        Classroom classroom = classroomDao.getClassroomByName(classDelete);
        List<User> users = UserDao.getStudentsList(classroom);
        List<Question> questions = QuestionDao.getQuestionList(classroom.getId_classroom());
        List<Information> infos = InformationDao.getInformationList(classroom.getId_classroom());

        for (User user : users) {
            UserDao.deleteUser(user);
        }
        for (Question question : questions) {
            List<Option> options = OptionDao.getOptionList(question.getId_question());
            List<Answer> answers = AnswerDao.getAnswersList(question.getId_question());

            for (Answer answer : answers) {
                AnswerDao.deleteAnswer(answer);
            }
            for (Option option : options) {
                OptionDao.deleteOption(option);
            }
            QuestionDao.deleteQuestion(question);
        }
        for (Information value : infos) {
            InformationDao.deleteInformation(value);
        }
        ClassroomDao.deleteClassroom(classroom);
        return "redirect:/";
    }

    @RequestMapping(value = "/updateClassroom")
    @ResponseBody
    public String updateClassroomFromList(@RequestParam String classNameModify, @RequestParam String newClassroomName) throws IOException, SQLException {
        if (newClassroomName.equals("")) {
            return ("emptyClass");
        } else {
            ClassroomDao.updateClassroomName(classNameModify, newClassroomName);
            return ("successClass");
        }
    }

    @RequestMapping("/LoginClass")
    public String loginClassRender(HttpSession session, @RequestParam(value = "id") long parameter) throws IOException, SQLException {
        Classroom myClass = classroomDao.getClassroom(parameter);
        addClassroomInSession(myClass, session);
        System.out.println("GET /LoginPage (LoginClassController)");
        //return html page
        return "LoginPage";
    }

    private void addClassroomInSession(Classroom classroom, HttpSession session) {
        session.setAttribute("classroom", classroom);
    }

}
