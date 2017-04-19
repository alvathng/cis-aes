package AES;

import java.io.File;

public interface AESService {
    void encryptFile(File plaintext, File key, File output);
    void decryptFile(File ciphertext, File key, File output);
}
