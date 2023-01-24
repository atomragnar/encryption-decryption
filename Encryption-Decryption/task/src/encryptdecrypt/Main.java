package encryptdecrypt;

public class Main {

    static String operation = "";
    static String algorithm = "";
    static int key = 0;
    static String message = "";
    static String filePathIn = "";
    static String filePathOut = "";
    static boolean isOutputToFile = false;
    static boolean isInputToFile = false;


    public static void main(String[] args) {

        //
        readCommandLineArgs(args);
        Message textmessage = new Message(key, message, filePathIn);
        textmessage.setCipherOperation(operation, algorithm);
        textmessage.setOutput(filePathOut);
        textmessage.cryptMessage();

    }

    public static void readCommandLineArgs(String[] args) {
        boolean hasDataCommand = false;
        for (int i = 0; i < args.length; i += 2) {
            if (args[i].equals("-mode")) {
                if (args.length > i + 1) {
                    operation = args[i + 1];
                }
            } else if (args[i].equals("-key")) {
                if (args.length > i + 1) {
                    key = Integer.parseInt(args[i + 1]);
                }
            } else if (args[i].equals("-data")) {
                if (args.length > i + 1) {
                    message = args[i + 1];
                    hasDataCommand = true;
                    if (!filePathIn.equals("")){
                        filePathIn = "";
                    }
                }
            } else if (args[i].equals("-in")) {
                if (args.length > i + 1 && !hasDataCommand) {
                    filePathIn = args[i + 1];
                }
            } else if (args[i].equals("-out")) {
                if (args.length > i + 1) {
                    filePathOut = args[i + 1];
                    isOutputToFile = true;
                }
            } else if (args[i].equals("-alg")) {
                if (args.length > i + 1) {
                    algorithm = args[i + 1];
                }

            }
        }
    }

}