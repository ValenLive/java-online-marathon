package com.softserve.itacademy;

import com.softserve.itacademy.controller.UserController;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ToDoService toDoService;

    @Test
    public void getAllToDosTests() throws Exception {
        List<ToDo> expected = toDoService.getByUserId(4);

        mockMvc.perform(MockMvcRequestBuilders.get("/todos/all/users/" + 4))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("todos"))
                .andExpect(MockMvcResultMatchers.model().attribute("todos", expected));
    }

    @Test
    public void createToDoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/todos/create/users/" + 4)
                .param("title", "")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void redirectToCreateToDoPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/create/users/" + 4))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void readToDoTasksPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + 8 + "/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void readInvalidToDoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + 1 + "/tasks"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deleteToDoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + 8 + "/delete/users/" + 4))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void deleteInvalidToDoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + 1 + "/delete/users/" + 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void redirectToUpdateToDoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + 9 + "/update/users/" + 4))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateToDoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/todos/" + 8 + "/update/users/" + 4)
                        .param("title", ""))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void updateInvalidToDoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/todos/" + 8 + "/update/users/" + 4)
                        .param("title", "UNKNOWN"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void addInvalidCollaboratorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + 12 + "/add/" + 4))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void removeInvalidCollaboratorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + 9 + "/add/" + 6))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void addCollaboratorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + 11L + "/add").param("user_id", String.valueOf(5L)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void removeCollaboratorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/todos/" + 7L + "/remove").param("user_id", String.valueOf(5L)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}
