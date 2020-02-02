package com.monteiro.virtualclassroom.virtualclassroom;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Classroom;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.ClassroomDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class ClassroomController {

    @GetMapping("/")
    public String homePageRender(Model model, HttpSession session) throws IOException, SQLException {
        System.out.println("GET /HomePage (ClassroomController)");
        List<Classroom> classroomList = new ArrayList<>();
        long n = ClassroomDao.getClassroomCount();
        int id = 0;
        classroomList = ClassroomDao.getClassroomRowsList(id, n);
        //sorting the classroom's list by alphabetical order
        classroomList.sort(Comparator.comparing(Classroom::getClassroom_name));
        System.out.println(n);
        System.out.println(classroomList);
        model.addAttribute("classrooms", classroomList);

        if((classroomList.isEmpty() ) && (session.getAttribute("login_first")==null)){
            model.addAttribute("adminAccess", true);
        }
        else if((classroomList.isEmpty() ) && (session.getAttribute("login_first")!=null)){
            model.addAttribute("adminClassList", true);
            model.addAttribute("adminAddClass", true);
        }
        else if((!classroomList.isEmpty()) && (session.getAttribute("login_first")!=null)){
            model.addAttribute("adminAddClass", true);
        }
        return "HomePage";
    }

//    @GetMapping("/ClassID")
//    public String getUrl(@RequestParam(value = "id") long classroomId){
//        return "redirect:/";
//    }
    @PostMapping("/addClassroom")
    public String createClassroom(@RequestParam String classroomName) throws IOException, SQLException {
        Classroom newClass = new Classroom (classroomName);
        if (!classroomName.equals("")){
            ClassroomDao.saveClassroom(newClass);
        }
        return "redirect:/";
    }
    @PostMapping("/deleteClassroom")
    public String deleteClassroomFromList(@RequestParam String classDelete) throws IOException, SQLException {
        System.out.println(classDelete);
        ClassroomDao.deleteClassroom(classDelete);
        return "redirect:/";
    }
    @RequestMapping(value = "/updateClassroom")
    public String updateClassroomfromList(@RequestParam String classNameModify, @RequestParam String newClassroomName) throws IOException, SQLException {
        System.out.println(classNameModify);
        System.out.println(newClassroomName);
        if(!newClassroomName.equals("")){
            System.out.println("le nom n'est pas vide");
            ClassroomDao.updateClassroomName(classNameModify, newClassroomName);
        }
        System.out.println("le nom est vide");
        return "redirect:/";
    }

    @RequestMapping("/LoginClass")
    public String loginClassRender(HttpSession session, @RequestParam(value = "id") long parameter) throws IOException, SQLException {
        Classroom myClass = ClassroomDao.getClassroom(parameter);
        addClassroomInSession(myClass, session);
        System.out.println(session.getAttribute("classroomID"));
        System.out.println("GET /LoginPage (LoginClassController)");
        //return html page
        return "LoginPage";
    }
    private void addClassroomInSession(Classroom classroom, HttpSession session){
        session.setAttribute("classroomID",classroom.getId_classroom());
    }

}
