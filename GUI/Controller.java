package GUI;

import AES.AESService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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
        aesService = null; //todo
    }

    public void initConfig(Config config) {
        this.config = config;
        this.textFieldIV.setText(Converter.byteToHexString(config.getIV()));
    }

    @FXML
    public void initialize() {

    }

    public void doChoosePlainTextInEncrypt(ActionEvent actionEvent) {
        filePlainTextInEncrypt = fileChooser.showOpenDialog(stage);
        textFieldEncryptPlainText.setText(filePlainTextInEncrypt.getAbsolutePath());
    }

    public void doChooseKeyInEncrypt(ActionEvent actionEvent) {
        fileKeyInEncrypt = fileChooser.showOpenDialog(stage);
        textFieldEncryptKey.setText(fileKeyInEncrypt.getAbsolutePath());
    }

    public void doChooseCipherTextInEncrypt(ActionEvent actionEvent) {
        fileCipherTextInEncrypt = fileChooser.showSaveDialog(stage);
        textFieldEncryptCipherText.setText(fileCipherTextInEncrypt.getAbsolutePath());
    }

    public void doChoosePlainTextInDecrypt(ActionEvent actionEvent) {
        filePlainTextInDecrypt = fileChooser.showSaveDialog(stage);
        textFieldDecryptPlainText.setText(filePlainTextInEncrypt.getAbsolutePath());
    }

    public void doChooseKeyInDecrypt(ActionEvent actionEvent) {
        fileKeyInDecrypt = fileChooser.showOpenDialog(stage);
        textFieldDecryptKey.setText(fileKeyInDecrypt.getAbsolutePath());
    }

    public void doChooseCipherTextInDecrypt(ActionEvent actionEvent) {
        fileCipherTextInDecrypt = fileChooser.showOpenDialog(stage);
        textFieldDecryptCipherText.setText(fileCipherTextInDecrypt.getAbsolutePath());
    }

    public void doChangeIV(ActionEvent actionEvent) {
        this.config.setIV(Converter.stringToByteArray(textFieldIV.getText()));
        this.configManager.saveConfig(this.config);
    }

    public void doResetIV(ActionEvent actionEvent) {
        this.textFieldIV.setText(Converter.byteToHexString(config.getIV()));
    }

    public void doEncrypt(ActionEvent actionEvent) {
        aesService.encryptFile(filePlainTextInEncrypt, fileKeyInEncrypt, fileCipherTextInEncrypt);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Encrypt Success");
        alert.setContentText(
                String.format("Encryption success, encrypted file is outputted to %s",
                fileCipherTextInEncrypt.getAbsolutePath()));
        alert.getDialogPane().setStyle("-fx-pref-height: 200px;");
        alert.showAndWait();
    }

    public void doDecrypt(ActionEvent actionEvent) {
        aesService.decryptFile(fileCipherTextInDecrypt, fileKeyInDecrypt, filePlainTextInDecrypt);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Decrypt Success");
        alert.setContentText(
                String.format("Decryption success, decrypted file is outputted to %s",
                        filePlainTextInDecrypt.getAbsolutePath()));
        alert.getDialogPane().setStyle("-fx-pref-height: 200px;");
        alert.showAndWait();
    }
}
