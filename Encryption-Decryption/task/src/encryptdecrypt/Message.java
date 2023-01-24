package encryptdecrypt;

public class Message {

    String text;
    String outputText;
    int key;
    CipherMethod cipherOperation;
    Input inputMethod;
    Output outputMethod;


    public Message(int key, String message, String filePathIn) {
        this.key = key;
        this.setInputMethod(message, filePathIn);
        this.text = inputMethod.readInput();
    }

    void setText(String text) {
        this.text = text;
    }

    public void setCipherOperation(String operation, String algorithm) {
        this.cipherOperation = CipherMethod.CipherMethodFactory(operation, algorithm);
    }

    public void setInputMethod(String message, String filePathIn) {
        if (filePathIn.equals("")) {
            this.inputMethod = Input.inputFactory("", message);
        } else {
            this.inputMethod = Input.inputFactory("file", filePathIn);
        }
    }

    public void setOutput(String filePathOut) {
        if (filePathOut.equals("")) {
            this.outputMethod = new OutputMethodCLI();
        } else {
            this.outputMethod = new OutputMethodFile(filePathOut);
        }

    }

    public void cryptMessage() {
        this.outputText = this.cipherOperation.cryptMessage(this.text, this.key);
        this.outputMethod.printOutput(this.outputText);
    }


}


