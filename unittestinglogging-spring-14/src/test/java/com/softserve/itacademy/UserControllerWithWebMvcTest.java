package com.softserve.itacademy;

import com.softserve.itacademy.controller.UserController;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.User;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserControllerWithWebMvcTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    @Test
    public void getAllUsersTest() throws Exception {
        List<User> expected = userService.getAll();

        mockMvc.perform(MockMvcRequestBuilders.get("/users/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
                .andExpect(MockMvcResultMatchers.model().attribute("users", expected));
    }

    @Test
    public void CreateUserWithErrorTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                .param("email","test@mail.com")
                .param("firstName", "firstname")
                .param("lastName", "lastname"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void CreateUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create")
                        .param("email","Test@mail.com")
                        .param("firstName", "Firstname")
                        .param("lastName", "Lastname")
                        .param("password", "pass"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void RedirectToCreateUserPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/create"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ReadExitingUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + 5 + "/read"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void ReadNotExitingUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + 444 + "/read"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void DeleteExitingUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + 6L +"/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void UpdateExitingUserPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + 5L + "/update"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void UpdateExitingUserWithNewUserRoleTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/update", 5L)
                        .param("firstName", "Firstname")
                        .param("lastName", "Lastname")
                        .param("email", "valid@mail.com")
                        .param("oldPassword", "oldPass")
                        .param("newPassword", "newPass")
                        .param("password", "pass")
                        .param("roleId", "2")
                )
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    public void UpdateExitingUserWithNewAdminRoleTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/update", 4L)
                        .param("firstName", "Firstname")
                        .param("lastName", "Lastname")
                        .param("email", "valide@mail.com")
                        .param("oldPassword", "oldPass")
                        .param("newPassword", "newPass")
                        .param("password", "pass")
                        .param("roleId", "1")
                )
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    public void UpdateExitingUserIncorrectParamTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/update", 1L)
                        .param("firstName", "InValidName")
                        .param("lastName", "Lastname")
                        .param("email", "valid@mail.com")
                        .param("oldPassword", "oldPass")
                        .param("newPassword", "newPass")
                        .param("password", "pass")
                        .param("roleId", "2")
                )
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    public void UpdateNotExitingUserPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/" + 444 + "/update"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
