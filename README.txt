FyleEncryptor

1 Run the program, specifying the operation (encrypt or decrypt), the path to the source file and the path to the output file:

2 The program will prompt for the following parameters:

- Operation: Enter 'encrypt' to encrypt or 'decrypt' to decrypt.
- Source File Path: Enter the full path to the file you want to process.
- Path to output file: specify the full path and name of the file where the result will be saved.

Examples of using:

- For encryption:

   ```
   Enter 'encrypt' or 'decrypt': encrypt
   Enter input file path: input.txt
   Enter output file path: encrypted.txt
   ```

- For decryption:

   ```
   Enter 'encrypt' or 'decrypt': decrypt
   Enter input file path: encrypted.txt
   Enter output file path: decrypted.txt
   ```

3. After completing the operation, the program will display a message about success or possible errors.

## Peculiarities

- The program uses the AES encryption algorithm with a key length of 128 bits.
- The secret key is saved in a separate file with the extension ".key".
- Supports both file encryption and decryption operation.

## Important

- Make sure you handle the keys safely and store them in a safe place, as without the key you will not be able to decrypt the files. Keys are saved in files with the extension ".key" next to the encrypted files.

## License

This project is distributed under the MIT license. See the LICENSE file for details.