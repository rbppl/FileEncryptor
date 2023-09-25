package org.example;
import javax.crypto.*;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 'encrypt' or 'decrypt': ");
        String operation = scanner.nextLine();

        System.out.print("Enter input file path: ");
        String inputFile = scanner.nextLine();

        System.out.print("Enter output file path: ");
        String outputFile = scanner.nextLine();

        scanner.close();

        try {
            if ("encrypt".equalsIgnoreCase(operation)) {
                encryptFile(inputFile, outputFile);
                System.out.println("File encrypted successfully.");
            } else if ("decrypt".equalsIgnoreCase(operation)) {
                decryptFile(inputFile, outputFile);
                System.out.println("File decrypted successfully.");
            } else {
                System.out.println("Invalid operation. Use 'encrypt' or 'decrypt'.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encryptFile(String inputFile, String outputFile) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // 128-bit key
        SecretKey secretKey = keyGenerator.generateKey();

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (InputStream inputStream = new FileInputStream(inputFile);
             OutputStream outputStream = new FileOutputStream(outputFile)) {
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                cipherOutputStream.write(buffer, 0, bytesRead);
            }

            cipherOutputStream.close();
        }

        // Сохраняем ключ в файл
        try (ObjectOutputStream keyOut = new ObjectOutputStream(new FileOutputStream(outputFile + ".key"))) {
            keyOut.writeObject(secretKey);
        }
    }

    public static void decryptFile(String inputFile, String outputFile) throws Exception {
        // Читаем ключ из файла
        try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(inputFile + ".key"))) {
            SecretKey secretKey = (SecretKey) keyIn.readObject();

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            try (InputStream inputStream = new FileInputStream(inputFile);
                 OutputStream outputStream = new FileOutputStream(outputFile)) {
                CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = cipherInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                cipherInputStream.close();
            }
        }
    }
}
