package com.monteiro.virtualclassroom.virtualclassroom;

import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Classroom;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.User;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.ClassroomDao;
import com.monteiro.virtualclassroom.virtualclassroom.model.dao.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

//on ne touche pas

@SpringBootApplication
public class VirtualclassroomApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(VirtualclassroomApplication.class, args);
        //User user1 = new User("Cleopatra", "Egyptienne", "cleo@eg.eg", "cesaristhebest",false, 6 );
        //UserDao.saveUser(user1);
        //User test1 = UserDao.getUser("Martin");
        //System.out.println(test1.getUser_email());
        //List<User> allUsers= UserDao.readAll();
        //for(User user: allUsers)
            //System.out.println( user.toString());

       // Classroom classroom = ClassroomDao.getClassroom(1);
       // System.out.println(classroom.getClassroom_name());
    }
}
