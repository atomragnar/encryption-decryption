package encryptdecrypt;

import java.io.*;

/**
 * Interface handling the output
 */

public interface Output {

    public abstract void printOutput(String outputText);


}

class OutputMethodFile implements Output {

    String filename;
    File file = null;

    public OutputMethodFile(String filename) {
        this.filename = filename;

    }

    public void printOutput(String outputText) {
        writeOutputToFile(outputText);
    }



    boolean createOutputFile() {
        this.file = new File(filename);
        try {
            file.createNewFile();
            return true;
        } catch (IOException e) {
            System.out.println("Error");
            return false;
        }
    }

    private void writeOutputToFile(String outputText) {
        if (this.createOutputFile()) {
            try (FileWriter writer = new FileWriter(this.file)) {
                if (outputText != null) {
                    writer.write(outputText);
                }
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }

}


class OutputMethodCLI implements Output {

    public OutputMethodCLI() {
        super();
    }
    public void printOutput(String outputText) {
        System.out.println(outputText);
    }


}





