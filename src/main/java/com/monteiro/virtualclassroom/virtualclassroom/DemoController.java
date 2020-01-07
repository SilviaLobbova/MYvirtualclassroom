package com.monteiro.virtualclassroom.virtualclassroom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.Session;
import java.util.Arrays;
import java.util.List;

//https://www.mkyong.com/spring-boot/spring-boot-hello-world-example-thymeleaf/
@Controller
public class DemoController {

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String main(Model model) {
        System.out.println("/DemoController.main ");

        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);

        return "HomePage"; //view
    }

    // /hello?name=toto
   @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam( name = "name", required = false, defaultValue = "")
                    String name, Model model)
       { System.out.println("/DemoController.mainWithParam name=" + name);

        model.addAttribute("message", name);

        return "welcome"; //view
    }

    //render login page
    @GetMapping("/LoginPage")
    public String loginRender(){
        return "LoginPage";
    }
    // render sign up Page
    @GetMapping("/SignUpPage")
    public String signUpRender() {
        return "SignUpPage"; //view
    }

    // render profile page
    @GetMapping("/Profile")
    public String profileRender() {
        return "Profile"; //view
    }

    // render password update form
    @GetMapping("/UpdatePassword")
    public String updatePwdRender() {
        return "UpdatePassword"; //view
    }

    @GetMapping("/TeacherPage")
    public String teacherPageRender() {
        return "TeacherPage";
    }

}