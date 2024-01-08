package HakSa;

import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class DbUtil {

   // 창바꾸기
   public static void changeScene(ActionEvent event, String fxmlFile, String title, String userID, int width,
         int heigth) {
      Parent root = null;
      try {
         root = FXMLLoader.load(DbUtil.class.getResource(fxmlFile));
      } catch (IOException e) {
         e.printStackTrace();
      }
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      stage.setTitle(title);
      stage.setScene(new Scene(root, width, heigth));
      stage.show();
   }

   // 학생 회원가입을 위한
   public static void signupUser(ActionEvent event, String userID, String userPW) {
      Connection con = null;
      PreparedStatement ptmt_Insert = null;
      PreparedStatement ptmt_Userexist = null;
      PreparedStatement ptmt_usertostudent = null;
      ResultSet resultset = null;

      try {
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "4598");
         ptmt_Userexist = con.prepareStatement("SELECT * FROM user_student WHERE userID = ?");
         ptmt_Userexist.setString(1, userID);
         resultset = ptmt_Userexist.executeQuery();

         if (resultset.isBeforeFirst()) {
            System.out.println("이미 userID가 존재합니다");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("이미 존재하는 userID 입니다.");
            alert.setContentText("이 아이디를 사용할 수 없습니다.");
            alert.show();
         } else {
            ptmt_Insert = con.prepareStatement("INSERT INTO user_student(userID,userPW) VALUES(?,?)");
            ptmt_Insert.setString(1, userID);
            ptmt_Insert.setString(2, userPW);
            ptmt_Insert.executeUpdate();
            System.out.println("데이터베이스에 ID,PW 추가 성공");
            ptmt_usertostudent = con.prepareStatement(
                  "INSERT INTO student(st_id) SELECT userID FROM user_student WHERE userID = ?");
            ptmt_usertostudent.setString(1, userID);
            ptmt_usertostudent.executeUpdate();
            System.out.println("student테이블로 학번 옮기기 성공");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (resultset != null) {
            try {
               resultset.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if (ptmt_Userexist != null) {
            try {
               ptmt_Userexist.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if (ptmt_Insert != null) {
            try {
               ptmt_Insert.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if (con != null) {
            try {
               con.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
   }

   // 관리자 로그인 버튼
   public static void loginUser_admin(ActionEvent event, String userID, String userPW) {
      Connection con = null;
      PreparedStatement ptmt = null;
      ResultSet resultset = null;
      try {
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "4598");
         ptmt = con.prepareStatement("SELECT userPW FROM user_admin WHERE userID = ?");
         ptmt.setString(1, userID);
         resultset = ptmt.executeQuery();

         if (!resultset.isBeforeFirst()) {
            System.out.println("userID를 DB에서 찾을 수 없습니다.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("userID가 존재하지 않습니다.");
            alert.setContentText("관리자로그인 실패");
            alert.show();

         } else {
            while (resultset.next()) {
               String retrivePW = resultset.getString("userPW");
               if (retrivePW.equals(userPW)) {
                  changeScene(event, "Manager_Admin.fxml", "환영합니다!!!!!!!!!", null, 1100, 600);
               } else {
                  System.out.println("비밀번호가 일치하지 않습니다");
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setHeaderText("비밀번호가 일치하지 않습니다.");
                  alert.setContentText("관리자로그인 실패");
                  alert.show();
               }
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (resultset != null) {
            try {
               resultset.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if (ptmt != null) {
            try {
               ptmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if (con != null) {
            try {
               con.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
   }

   // 학생 로그인 버튼
   public static void loginUser_student(ActionEvent event, String userID, String userPW) {
      Connection con = null;
      PreparedStatement ptmt = null;
      ResultSet resultset = null;
      try {
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "4598");
         ptmt = con.prepareStatement("SELECT * FROM user_student WHERE userID = ?");
         ptmt.setString(1, userID);
         resultset = ptmt.executeQuery();
         if (!resultset.isBeforeFirst()) {
            System.out.println("userID(학번(을))를 DB에서 찾을 수 없습니다.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("userID(학번(이))가 존재하지 않습니다.");
            alert.setContentText("학생 로그인 실패");
            alert.show();
         } else {
            while (resultset.next()) {
               String retrivePW = resultset.getString("userPW");
               if (retrivePW.equals(userPW)) {
                  changeScene(event, "Manager_Student.fxml", "환영합니다", null, 1100, 600);
               } else {
                  System.out.println("비밀번호가 일치하지 않습니다");
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setHeaderText("비밀번호가 일치하지 않습니다.");
                  alert.setContentText("학생 로그인 실패");
                  alert.show();
               }
            }
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         if (resultset != null) {
            try {
               resultset.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if (ptmt != null) {
            try {
               ptmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if (con != null) {
            try {
               con.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      }
   }

}