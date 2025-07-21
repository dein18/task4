import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import task4.cofig.AppConfig;
import task4.model.User;
import task4.service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.create(User.builder().name("Вася").build());
        userService.create(User.builder().name("Петя").build());
        userService.create(User.builder().name("Коля").build());

        User user = userService.getUser(1L);
        List<User> users = userService.getUsers();

        userService.update(new User(2L, "Ваня"));
        userService.delete(1L);

        context.close();
    }
}
