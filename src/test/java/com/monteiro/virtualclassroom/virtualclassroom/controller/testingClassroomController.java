package com.monteiro.virtualclassroom.virtualclassroom.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.monteiro.virtualclassroom.virtualclassroom.model.bean.Classroom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class testingClassroomController {

    @Autowired
    private MockMvc mvc;

    @Test
    public void homePageRender() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("HomePage"));
    }

    @Test
    public void getStudentsOfTheClass() throws Exception {
        Classroom classroom = new Classroom("Dev20_01");
        String className = classroom.getClassroom_name();
        List<Classroom> allClasses = Arrays.asList(classroom);
        mvc.perform(get("/studentFrame/{className}", className)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("fragments/studentFrame"));
    }


    @Test
    public void createClassroom() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/addClassroom")
                .content(asJsonString(new Classroom("Dev20_01")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.classroom_name").value("Dev20_01"));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
