package AES;

import java.io.File;

public class AESServiceImpl implements AESService {

    private byte[] nonce;

    public AESServiceImpl() {

    }

    @Override
    public void encryptFile(File plaintext, File key, File output) {

    }

    @Override
    public void decryptFile(File ciphertext, File key, File output) {

    }

    public byte[] getNonce() {
        return nonce;
    }

    public void setNonce(byte[] nonce) {
        this.nonce = nonce;
    }
}
