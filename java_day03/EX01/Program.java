public class Program {
    public static void main(String[] args) {
        if(args.length != 1){
            System.err.println("Error: wrong args length");
            System.exit(-1);
        }
        String[] input = args[0].split("=");
        if(!input[0].equals("--count")){
            System.err.println("Error: wrong flag");
            System.exit(-1);
        }
        try{
            Store store = new Store();
            int count = Integer.parseInt(input[1]);

            Egg eggThread = new Egg(count, store);
            Hen henThread = new Hen(count, store);

            new Thread(eggThread).start();
            new Thread(henThread).start();
            new Thread(eggThread).join();
            new Thread(henThread).join();

        } catch (NumberFormatException e) {
            System.out.println("Not a integer");
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
