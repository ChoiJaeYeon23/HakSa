package HakSa;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Login_Admin_Controller implements Initializable {

   @FXML
   private Button button_Login;
   @FXML
   private TextField tf_ID;
   @FXML
   private TextField tf_PW;
   @FXML
   private Button button_back;

   @Override
   public void initialize(URL location, ResourceBundle resource) {

      button_Login.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            DbUtil.loginUser_admin(event, tf_ID.getText(), tf_PW.getText());
         }
      });

      button_back.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            DbUtil.changeScene(event, "Select.fxml", "로그인 모드 선택", null, 400, 500);
         }
      });

      button_back.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            DbUtil.changeScene(event, "Select.fxml", "로그인 모드 선택", null, 400, 500);
         }
      });
   }
}