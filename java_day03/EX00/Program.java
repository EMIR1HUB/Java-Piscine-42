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
            int count = Integer.parseInt(input[1]);

            Egg eggThread = new Egg(count);
            Hen henThread = new Hen(count);

            eggThread.start();
            henThread.start();
            eggThread.join();
            henThread.join();

            for(int i = 0; i < count; i++){
                System.out.println("Human");
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a integer");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
