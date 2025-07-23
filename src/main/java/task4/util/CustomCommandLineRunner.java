package task4.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import task4.model.User;
import task4.service.UserService;

@Component
@RequiredArgsConstructor
public class CustomCommandLineRunner {

    private final UserService userService;

    public void run() {
        System.out.println("Старт, выполняем CRUD: ");

        // CREATE
        userService.create(User.builder().name("Лёша").build());

        // READ
        userService.getUser(1L);
        userService.getUsers();

        // UPDATE
        userService.update(User.builder().name("Лёша_Update").build());

        // DELETE
        userService.delete(1L);

        System.out.println("CRUD завершён.");
    }
}