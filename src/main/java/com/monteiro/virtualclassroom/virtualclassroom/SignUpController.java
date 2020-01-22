package com.monteiro.virtualclassroom.virtualclassroom;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    // render sign up Page
    @GetMapping("/SignUpPage")
    public String signUpRender() {
        System.out.println("GET /SignUpPage (SignUpController)");
        return "SignUpPage"; //view
    }
    @PostMapping ("/SignUpPage")
    public String handleSignUpRequest(
            @RequestParam String user_name,
            @RequestParam String user_lastname,
            @RequestParam String user_email,
            @RequestParam String user_password,
            Model model) throws Exception {

        System.out.println("POST /SignUpPage (SignUpController)");

        if((user_name.isEmpty())||(user_lastname.isEmpty())||(user_password.isEmpty())||(user_email.isEmpty()) ){
            System.out.println("missing");
            System.out.println(user_password);
            model.addAttribute("emptyField", true);
            return "SignUpPage";
        }
        else{
            System.out.println(user_lastname);
            User newOne = new User(user_name, user_lastname, user_email, user_password, true, 8);
            UserDao.saveUser(newOne);
            return "LoginPage";
        }
    }
}
