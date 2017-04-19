package GUI;

import AES.AESService;
import AES.AESServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Controller {

    final Stage stage;
    final FileChooser fileChooser;
    final ConfigManager configManager;
    final AESService aesService;
    Config config;

    @FXML TextField textFieldEncryptPlainText;
    @FXML TextField textFieldEncryptKey;
    @FXML TextField textFieldEncryptCipherText;
    @FXML TextField textFieldDecryptPlainText;
    @FXML TextField textFieldDecryptKey;
    @FXML TextField textFieldDecryptCipherText;
    @FXML TextField textFieldIV;
    @FXML Label textFieldEncryptKeyInfo;
    @FXML Label textFieldDecryptKeyInfo;

    File filePlainTextInEncrypt;
    File fileKeyInEncrypt;
    File fileCipherTextInEncrypt;
    File filePlainTextInDecrypt;
    File fileKeyInDecrypt;
    File fileCipherTextInDecrypt;

    public Controller() {
        stage = new Stage();
        fileChooser = new FileChooser();
        configManager = new ConfigManager();
        aesService = new AESServiceImpl();
    }

    private void setIV(byte[] iv) {
        this.textFieldIV.setText(Converter.byteToHexString(iv));
        ((AESServiceImpl) this.aesService).setNonce(iv);
    }

    public void initConfig(Config config) {
        this.config = config;
        this.setIV(config.getIV());
    }

    @FXML
    public void initialize() {

    }

    private String generateKeyInfo(File fileKey) {
        try {
            String key = Files.readAllLines(fileKey.toPath()).get(0);
            return String.format("Key: %s\nKey Length: %d bit", key, key.length() * 4);
        } catch (IOException e) {
            return "Key invalid";
        }
    }

    public void doChoosePlainTextInEncrypt(ActionEvent actionEvent) {
        filePlainTextInEncrypt = fileChooser.showOpenDialog(stage);
        if (filePlainTextInEncrypt != null) {
            textFieldEncryptPlainText.setText(filePlainTextInEncrypt.getAbsolutePath());
        }
    }

    public void doChooseKeyInEncrypt(ActionEvent actionEvent) {
        fileKeyInEncrypt = fileChooser.showOpenDialog(stage);
        if (fileKeyInEncrypt != null) {
            textFieldEncryptKey.setText(fileKeyInEncrypt.getAbsolutePath());
            textFieldEncryptKeyInfo.setText(generateKeyInfo(fileKeyInEncrypt));
        }
    }

    public void doChooseCipherTextInEncrypt(ActionEvent actionEvent) {
        fileCipherTextInEncrypt = fileChooser.showSaveDialog(stage);
        if (fileCipherTextInEncrypt != null) {
            textFieldEncryptCipherText.setText(fileCipherTextInEncrypt.getAbsolutePath());
        }
    }

    public void doChoosePlainTextInDecrypt(ActionEvent actionEvent) {
        filePlainTextInDecrypt = fileChooser.showSaveDialog(stage);
        if (filePlainTextInDecrypt != null) {
            textFieldDecryptPlainText.setText(filePlainTextInDecrypt.getAbsolutePath());
        }
    }

    public void doChooseKeyInDecrypt(ActionEvent actionEvent) {
        fileKeyInDecrypt = fileChooser.showOpenDialog(stage);
        if (fileKeyInDecrypt != null) {
            textFieldDecryptKey.setText(fileKeyInDecrypt.getAbsolutePath());
            textFieldDecryptKeyInfo.setText(generateKeyInfo(fileKeyInDecrypt));
        }
    }

    public void doChooseCipherTextInDecrypt(ActionEvent actionEvent) {
        fileCipherTextInDecrypt = fileChooser.showOpenDialog(stage);
        if (fileCipherTextInDecrypt != null) {
            textFieldDecryptCipherText.setText(fileCipherTextInDecrypt.getAbsolutePath());
        }
    }

    public void doChangeIV(ActionEvent actionEvent) {
        this.config.setIV(Converter.stringToByteArray(textFieldIV.getText()));
        this.configManager.saveConfig(this.config);
        this.setIV(Converter.stringToByteArray(textFieldIV.getText()));
    }

    public void doResetIV(ActionEvent actionEvent) {
        this.textFieldIV.setText(Converter.byteToHexString(config.getIV()));
    }

    public void doEncrypt(ActionEvent actionEvent) {
        try {
            aesService.encryptFile(filePlainTextInEncrypt, fileKeyInEncrypt, fileCipherTextInEncrypt);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Encrypt Success");
            alert.setContentText(
                    String.format("Encryption success, encrypted file is outputted to %s",
                            fileCipherTextInEncrypt.getAbsolutePath()));
            alert.getDialogPane().setStyle("-fx-pref-height: 200px;");
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Encrypt failed");
            alert.setContentText("Encryption failed, File IO error found");
            alert.getDialogPane().setStyle("-fx-pref-height: 200px;");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Encrypt failed");
            alert.setContentText("Encryption failed, Wrong length of key");
            alert.getDialogPane().setStyle("-fx-pref-height: 200px;");
            alert.showAndWait();
        }
    }

    public void doDecrypt(ActionEvent actionEvent) {
        try {
            aesService.decryptFile(fileCipherTextInDecrypt, fileKeyInDecrypt, filePlainTextInDecrypt);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Decrypt Success");
            alert.setContentText(
                    String.format("Decryption success, decrypted file is outputted to %s",
                            filePlainTextInDecrypt.getAbsolutePath()));
            alert.getDialogPane().setStyle("-fx-pref-height: 200px;");
            alert.showAndWait();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Decrypt failed");
            alert.setContentText("Decryption failed, File IO error found");
            alert.getDialogPane().setStyle("-fx-pref-height: 200px;");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Decrypt failed");
            alert.setContentText("Decryption failed, Nonce used might be invalid");
            alert.getDialogPane().setStyle("-fx-pref-height: 200px;");
            alert.showAndWait();
        }

    }
}
