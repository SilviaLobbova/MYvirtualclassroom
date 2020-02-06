package com.monteiro.virtualclassroom.virtualclassroom;


import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Classroom;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Information;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.ClassroomDao;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.InformationDao;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class InformationController {
    @PostMapping("/addInformation")
    public String createInformation(@RequestParam String infoName, String infoLink, HttpSession session) throws IOException, SQLException {
        long classroomId = (long) session.getAttribute("classroomID");
        Information newInfo = new Information (infoName, infoLink, classroomId);
        if (!infoName.equals("") && !infoLink.equals("")){
            InformationDao.saveInformation(newInfo);
        }
        return "redirect:/adminConnected";
    }

}
