package com.monteiro.virtualclassroom.virtualclassroom;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.UserDao;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.sql.SQLException;

@Controller
public class UpdateProfileController {
    // render profile page
    @GetMapping("/ProfilePage")
    public String profileRender() {
        System.out.println("Profile render sout");
        return "ProfilePage";
    }

    @PostMapping ("/ProfilePage")
    public String profileUpdate(
        String user_name_value,
//          String user_name_update,
        String user_lastname_value,
//          String user_lastname_update,
        String user_email_value,
//          String user_email_update,
        String user_password_value,
//          String user_password_update,
        Model model) throws IOException, SQLException {
        System.out.println("Profile render sout");
        User theOne = UserDao.getUser(user_email_value, user_password_value);
        String oldName = theOne.getUser_name();
        String oldLastName = theOne.getUser_lastname();
        if((user_name_value=="Juju")&&(user_lastname_value=="De Montmirail")&&
                (user_email_value=="monjoireSaintDenis@gmule.com")&&(user_password_value=="**********")){
            System.out.println("missing");
//           model.addAttribute("missingField", true);
            return "TeacherPage";
        }
        else if("Juju" != user_name_value){
            System.out.println(" name reussi");
            System.out.println(user_name_value);
//            System.out.println(user_name_update);
            System.out.println(oldName);
            UserDao.updateUserName("user_name", oldName, user_name_value);
            if("De Montmirail" != user_lastname_value){
                System.out.println("last name reussi");
                System.out.println(user_lastname_value);

                System.out.println(oldLastName);
                UserDao.updateUserName("user_lastName",oldLastName, user_lastname_value);
                if("monjoireSaintDenis@gmule.com" != user_email_value){
                    String oldEmail = theOne.getUser_email();
                    UserDao.updateUserName("user_email",oldEmail, user_email_value);
                    if("**********" != user_password_value){
                        String oldPassword = theOne.getUser_password();
                        UserDao.updateUserName("user_password",oldPassword, user_password_value);
                    }return "TeacherPage";
                }return "TeacherPage";
            }return "TeacherPage";
        }return "TeacherPage";
        }
//        else if("De Montmirail" != user_lastname_update){
//            System.out.println("last name reussi");
//            String oldLastName = theOne.getUser_lastname();
//            UserDao.updateUserName("user_lastName",oldLastName, user_lastname_update);
//            return "TeacherPage";
//        }
//        else if("monjoireSaintDenis@gmule.com" != user_email_update){
//            System.out.println("mail reussi");
//            String oldEmail = theOne.getUser_email();
//            UserDao.updateUserName("user_email",oldEmail, user_email_update);
//            return "TeacherPage";
//        }
//        else if("**********" != user_password_update){
//            System.out.println(" psw reussi");
//            String oldPassword = theOne.getUser_password();
//            UserDao.updateUserName("user_password",oldPassword, user_password_update);
//            return "TeacherPage";
//        }
}
