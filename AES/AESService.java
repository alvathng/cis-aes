package AES;

import java.io.File;

/**
 * Created by adam on 17/04/17.
 */
public interface AESService {
    void encryptFile(File plaintext, File key, File output);
    void decryptFile(File ciphertext, File key, File output);
}
