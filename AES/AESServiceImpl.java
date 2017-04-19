package AES;

import java.io.*;
import java.nio.file.Files;

public class AESServiceImpl implements AESService {

    private byte[] nonce;

    private byte[] readKey(File keyFile) throws IOException {
        String keyStr = Files.readAllLines(keyFile.toPath()).get(0);
        return Utils.hexToByte(keyStr);
    }

    @Override
    public void encryptFile(File plaintext, File key, File output) throws IOException {
        byte[] plainTextBytes = Files.readAllBytes(plaintext.toPath());
        byte[] keyBytes = readKey(key);
        byte[] nonceBytes = getNonce().clone();

        byte[] cipherTextBytes =  AES.encrypt(plainTextBytes, keyBytes, nonceBytes);
        Files.write(output.toPath(), cipherTextBytes);
    }

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
