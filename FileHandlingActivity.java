import java.io.*;

public class FileHandlingActivity {
    public static void main(String[] args) {
        // Your code here

        // a. Create main directory
        File main = new File("FileManipulationChallenge");
        if (!main.exists()) {
            main.mkdir();
        }

        // b. Create three text files
        try {
            for (int i = 1; i <= 3; i++) {
                File file = new File(main, "myfile" + i + ".txt");
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error in creating file.");
        }

        // c. Write messages to files
        try {
            for (int i = 1; i <= 3; i++) {
                File file = new File(main, "myfile" + i + ".txt");
                FileWriter writer = new FileWriter(file);
                writer.write("This is message #" + i + " inside " + file.getName() + "\n");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }

        // d. Read and display file contents
        try {
            for (int i = 1; i <= 3; i++) {
                File file = new File(main, "myfile" + i + ".txt");
                System.out.print(file.getName() + ": ");
                try (FileReader reader = new FileReader(file)) {
                    System.out.print((char) reader.read());
                } catch (IOException e) {
                    System.out.println("Error reading.");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error reading files.");
        }

        // e. Create backup directory
        File backup = new File(main, "backup");
        if (!backup.exists()) {
            backup.mkdir();
        }

        // f. Copy contents to backup file
        try {
            for (int i = 1; i <= 3; i++) {
                File source = new File(main, "myfile" + i + ".txt");
                File backupF = new File(backup, "myfile" + i + "bu.txt");

                try (FileReader reader = new FileReader(source);
                        FileWriter writer = new FileWriter(backupF)) {
                    while (reader.ready()) {
                        writer.write(reader.read());
                    }
                } catch (IOException e) {

                }
            }
        } catch (Exception e) {

        }

        // g. List all files in both directories
        System.out.println("Main directory files: ");
        File[] mainF = main.listFiles();
        if (mainF != null) {
            for (File f : mainF) {
                System.out.println(f.getName());
            }
        }

        System.out.println("Backup directory files: ");
        File[] backupF = backup.listFiles();
        if (backupF != null) {
            for (File f : backupF) {
                System.out.println(f.getName());
            }
        }
    }
}