package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {

    final Stage stage;
    final FileChooser fileChooser;

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

    String strIV;

    public Controller() {
        stage = new Stage();
        fileChooser = new FileChooser();
        strIV = "0";
    }

    @FXML
    public void initialize() {
        textFieldIV.setText(strIV);
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

    public void doEncrypt(ActionEvent actionEvent) {

    }

    public void doDecrypt(ActionEvent actionEvent) {

    }

    public void doChangeIV(ActionEvent actionEvent) {
        strIV = textFieldIV.getText();
    }

    public void doResetIV(ActionEvent actionEvent) {
        textFieldIV.setText(strIV);
    }
}
