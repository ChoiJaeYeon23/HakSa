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

   // â�ٲٱ�
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

   // �л� ȸ�������� ����
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
            System.out.println("�̹� userID�� �����մϴ�");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("�̹� �����ϴ� userID �Դϴ�.");
            alert.setContentText("�� ���̵� ����� �� �����ϴ�.");
            alert.show();
         } else {
            ptmt_Insert = con.prepareStatement("INSERT INTO user_student(userID,userPW) VALUES(?,?)");
            ptmt_Insert.setString(1, userID);
            ptmt_Insert.setString(2, userPW);
            ptmt_Insert.executeUpdate();
            System.out.println("�����ͺ��̽��� ID,PW �߰� ����");
            ptmt_usertostudent = con.prepareStatement(
                  "INSERT INTO student(st_id) SELECT userID FROM user_student WHERE userID = ?");
            ptmt_usertostudent.setString(1, userID);
            ptmt_usertostudent.executeUpdate();
            System.out.println("student���̺�� �й� �ű�� ����");
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

   // ������ �α��� ��ư
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
            System.out.println("userID�� DB���� ã�� �� �����ϴ�.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("userID�� �������� �ʽ��ϴ�.");
            alert.setContentText("�����ڷα��� ����");
            alert.show();

         } else {
            while (resultset.next()) {
               String retrivePW = resultset.getString("userPW");
               if (retrivePW.equals(userPW)) {
                  changeScene(event, "Manager_Admin.fxml", "ȯ���մϴ�!!!!!!!!!", null, 1100, 600);
               } else {
                  System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setHeaderText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                  alert.setContentText("�����ڷα��� ����");
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

   // �л� �α��� ��ư
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
            System.out.println("userID(�й�(��))�� DB���� ã�� �� �����ϴ�.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("userID(�й�(��))�� �������� �ʽ��ϴ�.");
            alert.setContentText("�л� �α��� ����");
            alert.show();
         } else {
            while (resultset.next()) {
               String retrivePW = resultset.getString("userPW");
               if (retrivePW.equals(userPW)) {
                  changeScene(event, "Manager_Student.fxml", "ȯ���մϴ�", null, 1100, 600);
               } else {
                  System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setHeaderText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
                  alert.setContentText("�л� �α��� ����");
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