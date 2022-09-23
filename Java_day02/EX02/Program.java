
public class Program {
    // --current-folder=C:\\рабочий_стол\\JAVA_Project\\Java-Piscine-42\\src\\java_day02\\EX02\\MAIN
    public static void main(String[] args) {
        Menu mainMenu = new Menu();
        if (args.length == 1) {
            String[] promtArgs = args[0].split("=");
            if (!promtArgs[0].equals("--current-folder")) {
                System.out.println("Error: Wrong arguments\n");
                System.exit(-1);
            }
            mainMenu.setDirectory(promtArgs[1]);
        }
        else if(args.length != 0){
            System.out.println("Error: Wrong arguments\n");
            System.exit(-1);
        }
        mainMenu.startMenu();
    }
}