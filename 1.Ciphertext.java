import java.util.Scanner;

public class Ciphertext {
    public static void main(String[] args) {
        System.out.println("Enter The plaintext");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println("Enter the key");
        int key = sc.nextInt();
        sc.nextLine();
        
        // Encryption
        String encryptedText = encrypt(s, key);
        System.out.println("Encrypted text: " + encryptedText);
        
        // Decryption
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted text: " + decryptedText);
    }

    public static String encrypt(String plaintext, int key) {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char currchar = plaintext.charAt(i);
            if (currchar >= 'a' && currchar <= 'z') {
                char encrypt = (char) ('a' + (currchar - 'a' + key) % 26);
                msg.append(encrypt);
            } else if (currchar >= 'A' && currchar <= 'Z') {
                char encrypt = (char) ('A' + (currchar - 'A' + key) % 26);
                msg.append(encrypt);
            } else {
                msg.append(currchar);
            }
        }
        return msg.toString();
    }

    public static String decrypt(String ciphertext, int key) {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char currchar = ciphertext.charAt(i);
            if (currchar >= 'a' && currchar <= 'z') {
                char decrypt = (char) ('a' + (currchar - 'a' - key + 26) % 26);
                msg.append(decrypt);
            } else if (currchar >= 'A' && currchar <= 'Z') {
                char decrypt = (char) ('A' + (currchar - 'A' - key + 26) % 26);
                msg.append(decrypt);
            } else {
                msg.append(currchar);
            }
        }
        return msg.toString();
    }
}


