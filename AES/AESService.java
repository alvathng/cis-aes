package AES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface / Contract with GUI
 */
public interface AESService {
    void encryptFile(File plaintext, File key, File output) throws Exception;
    void decryptFile(File ciphertext, File key, File output) throws Exception;
}
