package com.monteiro.virtualclassroom.virtualclassroom;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Classroom;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.ClassroomDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassroomController {
    @GetMapping("/HomePage")
    public String homePageRender(Model model) throws IOException, SQLException {
        System.out.println("GET /HomePage (ClassroomController)");
        List<Classroom> classroomList = new ArrayList<Classroom>();
        Classroom myClass = ClassroomDao.getClassroom(1);
        Classroom myClass2 = ClassroomDao.getClassroom(2);
        System.out.println(myClass);
        System.out.println(myClass.getClassroom_name());
        System.out.println(myClass2.getClassroom_name());
        classroomList.add(myClass);
        classroomList.add(myClass2);
        model.addAttribute("classrooms", classroomList);
        return "HomePage";
    }
    @GetMapping("/firstClass")
    public String firstClassRender(){
        return "LoginPage";
    }

}
