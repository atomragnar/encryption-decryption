package encryptdecrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public interface Input {

    public static Input inputFactory(String type, String filePath) {
        if (type.equals("file")) {
            return new InputMethodFile(filePath);
        } else {
            return new InputMethodCLI(filePath);
        }

    }

    public String readInput();
}

class InputMethodFile implements Input {

    File file;
    String filename;

    public InputMethodFile(String filePath) {
        this.filename = filePath;
    }

    public String readInput() {
        this.file = new File(this.filename);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file.getPath()))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            } catch (IOException e) {
                System.out.println("Error");
                return null;
            }
        } else {
            System.out.println("File does not exist");
            return null;
        }
    }

}

class InputMethodCLI implements Input {

    String text;

    public InputMethodCLI(String message) {
        this.text = message;
    }

    public String readInput() {
        return this.text;
    }

}