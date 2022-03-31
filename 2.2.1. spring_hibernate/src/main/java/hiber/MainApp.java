package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("BMW", 3)));

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(new Car("Toyota", 200));
      userService.add(user2);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car car = new Car("Lada", 2114);
      user3.setCar(car);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("CarModel = "+user.getCar().getModel());
         System.out.println("CarSeries = "+user.getCar().getSeries());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("BMW", 3));

      context.close();
   }
}
