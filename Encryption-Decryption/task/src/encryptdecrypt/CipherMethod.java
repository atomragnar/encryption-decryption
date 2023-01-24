package encryptdecrypt;

public interface CipherMethod {
    public static CipherMethod CipherMethodFactory(String operation, String algorithm) {

        if (operation.equals("enc")) {
            if (algorithm.equals("shift")) {
                return new EncryptShift();
            } else if (algorithm.equals("unicode")) {
                return new EncryptUnicode();
            }
        } else if (operation.equals("dec")) {
            if (algorithm.equals("shift")) {
                return new DecryptShift();
            } else if (algorithm.equals("unicode")) {
                return new DecryptUnicode();
            }
        }
        return null;
    }

    public String cryptMessage(String message, int key);

}

abstract class ShiftCipher implements CipherMethod {

    public static final int UNICODE_VALUE_A = 97;
    public static final int UNICODE_VALUE_Z = 122;

    private static final int UNICODE_VALUE_UPPER_A = 65;
    private static final int UNICODE_VALUE_UPPER_Z = 90;

    static boolean checkUpperCase(char c) {
        if (c >= UNICODE_VALUE_UPPER_A && c <= UNICODE_VALUE_UPPER_Z) {
            return true;
        }
        return false;
    }

}

class EncryptShift extends ShiftCipher {

    public String cryptMessage(String message, int key) {
        if (key == 0) {
            return message;
        }
        StringBuilder sb = new StringBuilder();
        int length = message.length();
        char tempChar;
        boolean isUpper;
        for (int i = 0; i < length; i++) {
            tempChar = message.charAt(i);
            isUpper = checkUpperCase(tempChar);
            if (isUpper) {
                tempChar = (String.valueOf(tempChar).toLowerCase()).charAt(0);
            }
            if (tempChar >= UNICODE_VALUE_A && tempChar <= UNICODE_VALUE_Z) {
                if (isUpper) {
                    sb.append((String.valueOf(encryptChar(tempChar, key)).toUpperCase()).charAt(0));
                } else {
                    sb.append(encryptChar(tempChar, key));
                }
            } else {
                sb.append(tempChar);
            }
        }
        return sb.toString();
    }

    // for the tasks in the beginning.
    private static char encryptChar(char c, int key) {
        int temp = c + key;
        if (temp > UNICODE_VALUE_Z) {
            temp = key - 26;
            return encryptChar(c, temp);
        }
        return (char) (c + key);
    }
}



class DecryptShift extends ShiftCipher {

    public String cryptMessage(String message, int key) {
        if (key == 0) {
            return message;
        }
        StringBuilder sb = new StringBuilder();
        int length = message.length();
        char tempChar;
        boolean isUpper;
        for (int i = 0; i < length; i++) {
            tempChar = message.charAt(i);
            isUpper = checkUpperCase(tempChar);
            if (isUpper) {
                tempChar = (String.valueOf(tempChar).toLowerCase()).charAt(0);
            }
            if (tempChar >= UNICODE_VALUE_A && tempChar <= UNICODE_VALUE_Z) {
                if (isUpper) {
                    sb.append((String.valueOf(decryptChar(tempChar, key)).toUpperCase()).charAt(0));
                } else {
                    sb.append(decryptChar(tempChar, key));
                }
            } else {
                sb.append(tempChar);
            }
        }
        return sb.toString();
    }

    private static char decryptChar(char c, int key) {
        int temp = c - key;
        if (temp < UNICODE_VALUE_A) {
            temp = key - 26;
            return decryptChar(c, temp);
        }
        return (char) (c - key);
    }

}


abstract class UnicodeCipher implements CipherMethod {

}

class EncryptUnicode extends UnicodeCipher {

    public String cryptMessage(String text, int key) {
        if (key == 0) {
            return text;
        } else {
            StringBuilder sb = new StringBuilder();
            int length = text.length();
            for (int i = 0; i < length; i++) {
                sb.append((char) (text.charAt(i) + key));
            }
            return sb.toString();
        }
    }

}

class DecryptUnicode extends UnicodeCipher {

    public String cryptMessage(String text, int key) {
        if (key == 0) {
            return text;
        } else {
            StringBuilder sb = new StringBuilder();
            int length = text.length();
            for (int i = 0; i < length; i++) {
                sb.append((char) (text.charAt(i) - key));
            }
            return sb.toString();
        }
    }

}