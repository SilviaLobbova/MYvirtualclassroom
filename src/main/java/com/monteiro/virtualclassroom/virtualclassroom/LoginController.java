package com.monteiro.virtualclassroom.virtualclassroom;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;


@Controller
public class LoginController{

    //render login page
    @GetMapping("/LoginPage")
    public String loginRender() {
        System.out.println("GET /LoginPage (LoginController)");
        //return html page
        return "LoginPage";
    }

    //checking for credentials
    @PostMapping("/LoginPage")
    public String handleLoginRequest(
            @RequestParam String login,
            @RequestParam String login_psw,
            //we are also sending back the model from the DispatcherServlet controller to the view
            Model model) throws IOException, SQLException {

        System.out.println("POST /LoginPage (LoginController)");
        //System.out.println(user_email);
        User myMail = UserDao.getUser(login, login_psw);
        if(myMail == null){
            model.addAttribute("invalidCredentials", true);
            return "LoginPage";
        }
        else if (myMail.getUser_email().equals(login) && myMail.getUser_password().equals(login_psw)){
            return "TeacherPage";
        } else {
            model.addAttribute("invalidCredentials", true);
            return "LoginPage";
        }
    }


    @GetMapping("/TeacherPage")
    public String teacherPageRender () {
        return "TeacherPage";
    }
}
