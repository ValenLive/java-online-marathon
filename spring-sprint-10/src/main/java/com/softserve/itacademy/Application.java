package com.softserve.itacademy;

import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = annotationConfigContext.getBean(UserService.class);
        ToDoService toDoService = annotationConfigContext.getBean(ToDoService.class);
        TaskService taskService = annotationConfigContext.getBean(TaskService.class);
        annotationConfigContext.close();
    }
}
