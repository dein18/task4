import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import task4.cofig.AppConfig;
import task4.util.CustomCommandLineRunner;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomCommandLineRunner runner = context.getBean(CustomCommandLineRunner.class);
        runner.run();
        context.close();
    }
}
