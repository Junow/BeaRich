package bearich;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      int test = LoginTest("root","wnsgh159");

   }
   public static int LoginTest(String id, String pw) {
     
	   int flag = 0;
       String driverName="com.mysql.jdbc.Driver";//DB 드라이버 ?
      String dbURL="jdbc:mysql://localhost:3306/userinfo";// DB 주
      
      
      Connection con=null; 
      PreparedStatement pstmt =null;
      ResultSet rs =null;
      String sql=null;
      String getPass =null;
      try {
         Class.forName(driverName);// 모
         con=DriverManager.getConnection(dbURL,"root","wnsgh159");//DB 연결 
         System.out.println("Driver connection success!!");
         sql="select PASSWORD from info where ID=?"; //쿼리문 세팅 aka 총알 
         pstmt = con.prepareStatement(sql);// 쿼리문을 디비에 aka 탄집  
         
         pstmt.setString(1, id); // 첫번째 물음표에 값넣기  
         
         rs=pstmt.executeQuery();//쿼리문 실행해서 re에저장 aka 발사  
      
         while(rs.next()) {//rs 가 있는동안  
            getPass = rs.getString("PASSWORD");// rs에서 비밀번호 가져와서 get pass 에 저장 
            if(getPass.equals(pw)) {// get pass가 비밀번호랑 같은지 확인.
               flag=1;// 리턴
            }
         }
         rs.close();
         pstmt.close();
         con.close();
         
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         try {
            if(pstmt!=null)
            pstmt.close();
         } catch (SQLException s) {
            // TODO Auto-generated catch block
            s.printStackTrace();
         }
         try{
            if(con!=null)
               con.close();
         }
         catch(SQLException se){
            se.printStackTrace();
         }
         
      }
      return flag;
   }

}
