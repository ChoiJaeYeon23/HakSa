package HakSa;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Login_Select_Controller implements Initializable {

   @FXML
   private Button button_admin;
   @FXML
   private Button button_student;

   @Override
   public void initialize(URL location, ResourceBundle resources) {

      button_admin.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            DbUtil.changeScene(event, "Login_Admin.fxml", "관리자 로그인", null,400,500);
         }
      });
      
      button_student.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            DbUtil.changeScene(event, "Login_Student.fxml", "학생 로그인", null,400,500);
         }
      });

   }
}