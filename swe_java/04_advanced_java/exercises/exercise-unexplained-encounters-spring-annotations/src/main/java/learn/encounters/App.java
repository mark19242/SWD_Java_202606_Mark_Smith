package learn.encounters;

import learn.encounters.ui.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


@ComponentScan
@PropertySource("classpath:application.properties")
public class App {

    public static void main(String[] args) {

        // Spring scans the project and manages the application's dependencies.
        ApplicationContext context =
                new AnnotationConfigApplicationContext(App.class);

        Controller controller = context.getBean(Controller.class);
        controller.run();


    }
}
