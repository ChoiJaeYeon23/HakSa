package HakSa;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable {

   @FXML
   private Button button_signup;
   @FXML
   private Button button_log_in;
   @FXML
   private TextField tf_ID;
   @FXML
   private TextField tf_PW;

   @Override
   public void initialize(URL location, ResourceBundle resource) {

      button_signup.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            if (!tf_ID.getText().trim().isEmpty() && !tf_PW.getText().trim().isEmpty()) {
               DbUtil.signupUser(event, tf_ID.getText(), tf_PW.getText());
               DbUtil.changeScene(event, "Login_Student.fxml", "로그인", null, 400, 500);
            } else {
               System.out.println("회원가입에 필요한 모든 정보를 입력하지 않았습니다.");
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setHeaderText("회원가입에 필요한 모든 정보를 입력해주세요.");
               alert.setContentText("회원가입 실패");
               alert.show();
            }
         }
      });

      button_log_in.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            DbUtil.changeScene(event, "Login_Student.fxml", "학생 로그인", null, 400, 500);
         }
      });
   }

}