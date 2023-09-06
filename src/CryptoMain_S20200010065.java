import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

interface CryptoAlgorithm {
    String encrypt(String s);
    String decrypt(String s);
}

class SimpleCryptoAlgorithm implements CryptoAlgorithm {

    @Override
    public String encrypt(String s) {
        int key = 3;
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i))) {
                int t = ((int) s.charAt(i) + key);
                t = t % 91;
                if (t < 65) {
                    t = t + 65;
                }
                char c = (char) (t);
                s = s.substring(0, i) + c + s.substring(i + 1);
            }
        }
        return s;
    }

    @Override
    public String decrypt(String s) {
        int key = -3;
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i))) {
                int t = ((int) s.charAt(i) + key);
                t = t % 91;
                if (t < 65) {
                    t = t + 65;
                }
                char c = (char) (t);
                s = s.substring(0, i) + c + s.substring(i + 1);
            }
        }
        return s;
    }

    public String readfile(String Filename, String r) throws IOException {
        try {
            List<String> ls = Files.readAllLines(Paths.get(Filename));
            String os;
            for (String string : ls) {
                if (r.equals("-e")) {
                    os=encrypt(string.toUpperCase());
                    System.out.println(os);
                    try {
                        PrintWriter printWriter = new PrintWriter(new FileWriter("encrypted.txt"));
                        printWriter.write(os);
                        printWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (r.equals("-d")) {
                    os=decrypt(string.toUpperCase());
                    System.out.println(os);
                    try {
                        PrintWriter printWriter = new PrintWriter(new FileWriter("decrypted.txt"));
                        printWriter.write(os);
                        printWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

public class CryptoMain_S20200010065 {
    public static void main(String[] args) {
        SimpleCryptoAlgorithm s= new SimpleCryptoAlgorithm();
        switch (args[0]) {
            case "-read": {
                System.out.println("file reading!");
                switch (args[1]) {
                    case "-e": {
                        try {
                            String text = s.readfile("text.txt", "-e");
                            System.out.println(text);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                    case "-d": {
                        try {
                            String text = s.readfile("text.txt", "-d");
                            System.out.println(text);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
                break;
            }
        }
    }
}