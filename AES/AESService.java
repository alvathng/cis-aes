package AES;

import java.io.File;

public interface AESService {
    static void encryptFile(File plaintext, File key, File output);
    static void decryptFile(File ciphertext, File key, File output);
}
