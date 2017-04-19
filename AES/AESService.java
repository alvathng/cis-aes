package AES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface AESService {
    void encryptFile(File plaintext, File key, File output) throws IOException;
    void decryptFile(File ciphertext, File key, File output) throws Exception;
}
