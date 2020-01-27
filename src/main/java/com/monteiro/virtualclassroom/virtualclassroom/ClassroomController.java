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
import java.util.List;

@Controller
public class ClassroomController {

    @GetMapping("/")
    public String homePageRender(Model model) throws IOException, SQLException {
        System.out.println("GET /HomePage (ClassroomController)");
        List<Classroom> classroomList = new ArrayList<>();
        long n = ClassroomDao.getClassroomCount();
        int id;
        Classroom myClass;
        for(id=1;id<=n;id++){
            myClass = ClassroomDao.getClassroom(id);
            classroomList.add(myClass);
        }
        model.addAttribute("classrooms", classroomList);
        if(classroomList.isEmpty()){
            model.addAttribute("adminAccess", true);
        }

        return "HomePage";
    }

    @GetMapping("/ClassroomList")
    public String ClassroomListRender(Model model, HttpSession session) throws IOException, SQLException {
        homePageRender(model);
        if((boolean)session.getAttribute("is_Admin")==true){
            model.addAttribute("adminClassList", true);
        }

        return "HomePage";
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
