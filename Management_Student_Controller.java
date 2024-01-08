package HakSa;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Management_Student_Controller implements Initializable {

   @FXML
   private TextField tf_stid;
   @FXML
   private TextField tf_stgender;
   @FXML
   private TextField tf_stname;
   @FXML
   private TextField tf_stage;
   @FXML
   private TextField tf_stdepart;
   @FXML
   private TextField tf_staddress;
   @FXML
   private TextField tf_ststate;
   @FXML
   private Button button_revise;
   @FXML
   private Button button_logout;

   // 수정
   public void StudentRevise(String userID) {
      String updateData = "UPDATE student "
            + "SET st_name=?, st_depart=?, st_gen=?, st_age=?, st_address=?, st_state=? " + "WHERE st_id=?";

      String fs_id = null;
      Connection con = null;
      PreparedStatement ptmt = null;
      ResultSet rs = null;
      try {
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "4598");

         // 빈칸 체크 및 에러
         if (tf_stid.getText().isEmpty() || tf_stname.getText().isEmpty() || tf_stdepart.getText().isEmpty()
               || tf_stgender.getText().isEmpty() || tf_stage.getText().isEmpty()
               || tf_staddress.getText().isEmpty() || tf_ststate.getText().isEmpty()) {
            Alert alert;
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("빈칸을 채워주세요");
            alert.showAndWait();
         } else {
            // stdID가 있는지 체크
            String checkData = "SELECT st_id FROM student WHERE st_id = '" + tf_stid.getText() + "'";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(checkData);
            if (result.next()) {
               PreparedStatement prepare = con.prepareStatement(updateData);
               prepare.setString(1, tf_stname.getText());
               prepare.setString(2, tf_stdepart.getText());
               prepare.setString(3, tf_stgender.getText());
               prepare.setString(4, tf_stage.getText());
               prepare.setString(5, tf_staddress.getText());
               prepare.setString(6, tf_ststate.getText());
               prepare.setString(7, tf_stid.getText());
               prepare.executeUpdate();

               String insertStudentGrade = "INSERT INTO student_grade "
                     + "(st_id,st_name,`score(1-1)`,`score(1-2)`,`score(2-1)`,`score(2-2)`,`score(3-1)`,`score(3-2)`,`score(4-1)`,`score(4-2)`) "
                     + "VALUES(?,?,?,?,?,?,?,?,?,?)";

               prepare = con.prepareStatement(insertStudentGrade);
               prepare.setString(1, tf_stid.getText());
               prepare.setString(2, tf_stname.getText());
               prepare.setString(3, "0.0");
               prepare.setString(4, "0.0");
               prepare.setString(5, "0.0");
               prepare.setString(6, "0.0");
               prepare.setString(7, "0.0");
               prepare.setString(8, "0.0");
               prepare.setString(9, "0.0");
               prepare.setString(10, "0.0");

               prepare.executeUpdate();

               Alert alert;
               alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("알림");
               alert.setHeaderText(null);
               alert.setContentText("수정되었습니다.");
               alert.showAndWait();
            } else {
               Alert alert;
               alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText(null);
               alert.setContentText(tf_stid.getText() + "는 존재하지 않습니다.");
               alert.showAndWait();
            }

         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public void initialize(URL url, ResourceBundle resources) {

      // 로그아웃버튼 누르면 select 화면으로 넘어가기
      button_logout.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            DbUtil.changeScene(event, "Select.fxml", "로그인 모드 선택", null, 400, 500);
         }
      });
      button_revise.setOnAction(new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent event) {
            StudentRevise(tf_stid.getText());
         }
      });
   }
}