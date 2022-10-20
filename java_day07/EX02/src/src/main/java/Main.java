import models.User;
import services.ManagerService;

public class Main {
    public static void main(String[] args) {
        ManagerService managerService = new ManagerService();
        User user = new User("Ivan", "Ptushkin", 25);
        managerService.saveUser(user);
        user.setFirstName("Niko");
        managerService.updateUser(user);

        User user2 = new User("Alex", "Ptushkin", 19);
        managerService.saveUser(user2);
        user2.setLastName("Hirsh");
        managerService.updateUser(user2);

        System.out.println(managerService.findUser(30L));
        System.out.println(managerService.findUser(31L));
    }
}
