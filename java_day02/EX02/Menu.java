import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Menu {
    private String directory;
    private final Scanner console;

    {
        console = new Scanner(System.in);
    }

    public Menu() {
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    private static long getFileFolderSize(File dir) {
        long size = 0;
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += getFileFolderSize(file);
                }
            }
        } else if (dir.isFile()) {
            size += dir.length();
        }
        return size;
    }

    private void ls() {
        File currentDir = new File(directory);
        File[] directoryFiles = currentDir.listFiles();

        if (directoryFiles != null) {
            for (File file : directoryFiles) {
                System.out.println(file.getName() + " " + getFileFolderSize(file) / 1024 + " KB");
            }
        }
    }

    private void cd(String nameDirectory) throws ExceptionPath {
        Path current = Paths.get(directory);
        Path destination;
        String[] outDirectory = nameDirectory.split("/");
        if (outDirectory[0].equals("..")) {
            destination = current.getParent();
        } else {
            destination = current.resolve(Paths.get(nameDirectory));
            if (!Files.isDirectory(destination)) {
                throw new ExceptionPath();
            }
        }
        directory = destination.toAbsolutePath().toString();
    }

    private void mv(String fromPath, String toPath) {
        Path current = Paths.get(directory);
        Path from = current.resolve(Paths.get(fromPath));
        Path to = current.resolve(Paths.get(toPath));
        String[] outDirectory = toPath.split("/");

        if (!Files.isDirectory(from) && !outDirectory[0].equals("..")) {
            File oldName = new File(from.toString());
            File newName = new File(to.toString());
            oldName.renameTo(newName);
        }
        else if(outDirectory[0].equals("..")){
            try {
                Path toFile = current.getParent().resolve(outDirectory[1]).resolve(from.getFileName());
                Files.move(from, toFile);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void startMenu() {
        System.out.print(directory + "\n-> ");
        String commandLine = console.nextLine();
        while (!commandLine.equals("exit")) {
            try {
                String[] args = commandLine.split(" ");
                switch (args[0]) {
                    case "ls" -> ls();
                    case "cd" -> {
                        cd(args[1]);
                        System.out.println(directory);
                    }
                    case "mv" -> mv(args[1], args[2]);
                }
            } catch (ExceptionPath ex) {
                System.out.println(ex);
            }
            System.out.print("-> ");
            commandLine = console.nextLine();
        }
        System.exit(-1);
    }
}
