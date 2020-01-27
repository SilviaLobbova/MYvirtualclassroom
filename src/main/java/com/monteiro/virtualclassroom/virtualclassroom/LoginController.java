package com.monteiro.virtualclassroom.virtualclassroom;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Classroom;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.ClassroomDao;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@Controller
//@SessionAttributes("login_first", "login", "login_last", "login_psw")

public class LoginController{

    //render login page
    @GetMapping("/LoginPage")
    public String loginRender(){
        System.out.println("GET /LoginPage (LoginController)");
        //return html page
        return "LoginPage";
    }


    //checking for credentials
    @PostMapping("/LoginPage")
    public String handleLoginRequest(
            @RequestParam String login_name,
            @RequestParam String login_password,
            //we are also sending back the model from the DispatcherServlet controller to the view
            User user,
            HttpSession session,
            Model model) throws IOException, SQLException {

        System.out.println("POST /LoginPage (LoginController)");
        //System.out.println(user_email);
        User myMail = UserDao.getUser(login_name, login_password);
        System.out.println(myMail);
        long id_classroom = (long) session.getAttribute("classroomID");
        System.out.println(id_classroom);
        if(myMail == null){
            model.addAttribute("invalidCredentials", true);
            return "LoginPage";
        }
        else if((myMail.getIsAdmin() == true)||(id_classroom == 0)){
            if (myMail.getUser_email().equals(login_name) &&
                    myMail.getUser_password().equals(login_password)){
                addUserInSession(myMail,session);
            }
            System.out.println("empty classroomList - admin post method");
            System.out.println("render admin post method");
            return "redirect:/adminConnected";
        }
        else if(myMail.getIsAdmin()==false){
            if (myMail.getUser_email().equals(login_name) &&
                    myMail.getUser_password().equals(login_password)&&
                    myMail.get_classroom()==(id_classroom)){
                addUserInSession(myMail,session);
                System.out.println("getting logged in");
            }
            System.out.println("render user post method");
            return "redirect:/userConnected";
        }
        System.out.println("here I am"); System.out.println(session.getAttribute("login"));
        System.out.println(session.getAttribute("login_psw"));
        System.out.println(session.getAttribute("login_first"));
        System.out.println(session.getAttribute("login_last"));
        System.out.println(session.getAttribute("is_Admin"));
        System.out.println(session.getAttribute("classroom"));
        return "TeacherPage";
    }
    private void addUserInSession(User user, HttpSession session){
            session.setAttribute("login", user.getUser_email());
            session.setAttribute("login_psw", user.getUser_password());
            session.setAttribute("login_first", user.getUser_name());
            session.setAttribute("login_last", user.getUser_lastname());
            session.setAttribute("is_Admin", user.getIsAdmin());
            session.setAttribute("classroom", user.get_classroom());
    }

    @GetMapping("/TeacherPage")
    public String teacherPageRender () {
        System.out.println("what happens then");
        return "TeacherPage";
    }
    @GetMapping("/adminConnected")
    public String adminPageRender(Model model, HttpSession session) {
        if((boolean)(session.getAttribute("is_Admin")) == false){
            System.out.println( "You don't have an admin account");
            model.addAttribute("NotAdmin", true);
        }
            System.out.println("My Admin");
//            model.addAttribute("userIsAdmin",true);
            return "TeacherPage";
        }
    @GetMapping("/userConnected")
        public String userPageRender(Model model) {
            System.out.println("my not admin");
//            model.addAttribute("userIsNotAdmin",true);
            return "TeacherPage";
        }

    @RequestMapping(value = "/Logout")
    public String logout(HttpSession session) {
        System.out.println("in logout");
        session.invalidate();
        return "redirect:/";
    }

}
