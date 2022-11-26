import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.io.File;
import java.util.Scanner;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        JEditorPane myPane = null;
        String fileName = "ReadMe.txt";
        Scanner inFile;
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        try  // Code that might trigger the exception goes here
        {

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();  // this is a File object not a String filename

                inFile = new Scanner(target);

                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    System.out.println(line);
                }

                inFile.close();
            } else   // User did not pick a file, closed the chooser
            {
                System.out.println("Sorry, you must select a file! Terminating!");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        } catch (IOException e) // code to handle this exception
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }

    public class Tester {
        private static final String FILE_PATH = "ReadMe.txt";
        public void main(String[] args) throws IOException {
            FileUtil fileUtil = new FileUtil(FILE_PATH);
            System.out.println("No. of characters in file: " + fileUtil.getCharCount());
        }
    }

    class FileUtil {
        static BufferedReader reader = null;

        public FileUtil(String filePath) throws FileNotFoundException {
            File file = new File(filePath);
            FileInputStream fileStream = new FileInputStream(file);
            InputStreamReader input = new InputStreamReader(fileStream);
            reader = new BufferedReader(input);
        }

        public static int getCharCount() throws IOException {
            int charCount = 0;
            String data;
            while ((data = reader.readLine()) != null) {
                charCount += data.length();
            }
            return charCount;
        }

    }
}
