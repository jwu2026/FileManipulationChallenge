import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandlingActivity {
    public static void main(String[] args) {
        debugFileOperation();

        // a. Create main directory
        File main = new File("JavaFileSystem");
        if (!main.exists()) {
            main.mkdir();
        }

        // b. Create three text files
        try {
            File file = new File(main, "notes.txt");
            file.createNewFile();
            File file1 = new File(main, "data.txt");
            file1.createNewFile();
            File file2 = new File(main, "log.txt");
            file2.createNewFile();

        } catch (IOException e) {
            System.out.println("Error in creating file.");
        }

        // c. Write messages to files
        try {
            String[] files = { "notes.txt", "data.txt", "log.txt" };
            for (int i = 0; i < files.length; i++) {
                File file = new File(main, files[i]);
                FileWriter writer = new FileWriter(file);
                writer.write("There are " + (i + 1) + " chicken(s) in the file");
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }

        // d. Read and display file contents
        try {
            String[] files = { "notes.txt", "data.txt", "log.txt" };
            for (String name : files) {
                File file = new File(main, name);
                System.out.print(file.getName() + ": ");
                try (FileReader reader = new FileReader(file)) {
                    while (reader.ready()) {
                        System.out.print((char) reader.read());
                    }
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
            String[] files = { "notes.txt", "data.txt", "log.txt" };
            for (String name : files) {
                File source = new File(main, name);
                File backupF = new File(backup, name.replace(".txt", "b.txt"));
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

    public static void debugFileOperation() {
        try {
            // Creating a file with an invalid name (forward slash is invalid for file names
            // on many OS)
            File file = new File("fileName.txt");

            // Attempting to write to the invalid file
            FileWriter writer = new FileWriter(file);
            writer.write("Will this fail?");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}