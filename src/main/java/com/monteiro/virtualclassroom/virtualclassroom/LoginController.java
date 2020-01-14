package com.monteiro.virtualclassroom.virtualclassroom;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {

    //render login page
    @GetMapping("/LoginPage")
    public String loginRender() {
        //return html page
        return "LoginPage";
    }

    //    //checking for login credentials
//    @GetMapping(value="/LoginValue")
//    public String loginForm() {
//        return "LoginPage";
//    }
    //checking for credentials
    @PostMapping("/LoginPage")
    public String loginFormValue(@ModelAttribute User user, Model model) {
        String user_email = user.getUser_email();
        String user_password = user.getUser_password();
        //UserDao.getUser(user_email,user_password);
        String credentials = "admin";
        if (credentials.equals(user_email)&& credentials.equals(user_password)) {
          System.out.println("Login Successful");
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
