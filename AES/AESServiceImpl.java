package AES;

import java.io.*;
import java.nio.file.Files;

/**
 * Implementation of Contract with GUI
 */
public class AESServiceImpl implements AESService {

    private byte[] nonce;

    private byte[] readKey(File keyFile) throws IOException {
        String keyStr = Files.readAllLines(keyFile.toPath()).get(0);
        return Utils.hexToByte(keyStr);
    }

    /**
     * encrypt a file using AES encryption with PKCS#7 and CTR mode
     * @param plaintext
     * @param key
     * @param output
     * @throws Exception
     */
    @Override
    public void encryptFile(File plaintext, File key, File output) throws Exception {
        byte[] plainTextBytes = Files.readAllBytes(plaintext.toPath());
        byte[] keyBytes = readKey(key);
        byte[] nonceBytes = getNonce().clone();
        byte[] cipherTextBytes =  AES.encrypt(plainTextBytes, keyBytes, nonceBytes);
        Files.write(output.toPath(), cipherTextBytes);
    }

    /**
     * decrypt a file using AES encryption with PKCS#7 and CTR mode
     * @param ciphertext
     * @param key
     * @param output
     * @throws Exception
     */
    @Override
    public void decryptFile(File ciphertext, File key, File output) throws Exception {
        byte[] cipherTextBytes = Files.readAllBytes(ciphertext.toPath());
        byte[] keyBytes = readKey(key);
        byte[] nonceBytes = getNonce().clone();

        byte[] plainTextBytes =  AES.decrypt(cipherTextBytes, keyBytes, nonceBytes);
        Files.write(output.toPath(), plainTextBytes);
    }

    public byte[] getNonce() {
        return nonce;
    }

    public void setNonce(byte[] nonce) {
        this.nonce = nonce;
    }
}
