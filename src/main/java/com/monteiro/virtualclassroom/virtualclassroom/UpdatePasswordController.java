package com.monteiro.virtualclassroom.virtualclassroom;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

@Controller
//@SessionAttributes("login")
public class UpdatePasswordController {
//    @Autowired // add @service in the corresponding DAO
    UserDao dao = new UserDao();

    // render password update form
    @GetMapping("/UpdatePassword")
    public String updatePwdRender() {
        System.out.println("get UpdatePsw html");
        return "UpdatePassword"; //view
    }

    @PostMapping("/UpdatePassword")
    public String handleUpdatePswRequest (
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            @RequestParam String repeatPassword,
            HttpSession session,
            Model model) throws IOException, SQLException {

        System.out.println("handleUpdateRequest - UpdatePasswordController");

        // retrieve target user in the db
        User myUser = dao.getUser(session.getAttribute("login").toString(), session.getAttribute("login_psw").toString());
//        System.out.println("user_name: " + myUser.getUser_name() + " user_lastname: " + myUser.getUser_lastname());
//        if(oldPassword != newPassword){
//            UserDao.updateUser("user_password",oldPassword, newPassword);
//        }return "ProfilePage";

        if (myUser == null) {
            // display error msg
            model.addAttribute("invalidCredentials", true);
            return "UpdatePassword";
        } else if (myUser.getUser_password().equals(currentPassword)) {
//            System.out.println("currentPassword value: " + currentPassword);
//            System.out.println("myUser password == currentPassword = true");
            if(newPassword.isEmpty()) {
                // display emptyField msg
                model.addAttribute("emptyField", true);
            } else if(newPassword.equals(repeatPassword)) {
//                System.out.println("comparison between newPassword & repeatPassword = true");
//                System.out.println("newPassword value: " + newPassword);
//                System.out.println("repeatPassword value: " + repeatPassword);
                // update function call
                dao.updatePwdUser(myUser.getUser_email(), newPassword);
                return "ProfilePage";
            } else {
//                System.out.println("comparison between newPassword & repeatPassword = false");
                // display errorPwd msg
                model.addAttribute("differentPassword", true);
            }
            return "UpdatePassword";
        } else {
//            System.out.println(" myUser password == currentPassword = false ");
            // display errorPwd msg
            model.addAttribute("invalidPassword", true);
            return "UpdatePassword";
        }
    }
}

