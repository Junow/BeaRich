package bearich;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static boolean signup(String id, String pw){
		String driverName="com.mysql.jdbc.Driver";//DB 드라이버 
		String dbURL="jdbc:mysql://localhost:3306/userinfo";// DB 주


		Connection con=null; 
		PreparedStatement pstmt =null;
		String sql=null;
		String getPass =null;
		int flag=0;
		boolean tmp=false;
		try {
			Class.forName(driverName);// 모름  
			con=DriverManager.getConnection(dbURL,"root","wnsgh159");//DB 연
			System.out.println("Driver connection success!!");
			sql="insert into info values (?,0,0,0,0,0,0,0,0,0,0,10000000,?,0)"; // 쿼리문 세팅 aka 총알  
			pstmt = con.prepareStatement(sql);// 쿼리문을 디비에 aka 탄집   

			pstmt.setString(1, id);
			pstmt.setString(2, pw);// 첫 번쨰 물음표에 값넣기 

			flag=pstmt.executeUpdate();// 쿼피문 실행해서 rs에 저장 aka 발사  

			if(flag==1){
				tmp=true;
				return tmp;
			}
			else 
				return tmp;

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
		return tmp;

	}

}
