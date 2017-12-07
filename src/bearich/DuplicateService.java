package bearich;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DuplicateService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static boolean duplicateCheck(String id){
		
		String driverName="com.mysql.jdbc.Driver";//DB 드라이? 
		String dbURL="jdbc:mysql://localhost:3306/userinfo";// DB 주


		Connection con=null; 
		PreparedStatement pstmt =null;
		String sql=null;
		ResultSet rs =null;
		String getPass =null;
		boolean flag=false;
//		boolean tmp=false;
		try {
			Class.forName(driverName);//모름 
			con=DriverManager.getConnection(dbURL,"root","wnsgh159");//DB연결 
			System.out.println("Driver connection success!!");
			sql="select count(ID) from info where ID = ?"; //쿼리문 세팅 aka총
			pstmt = con.prepareStatement(sql);// 쿼리문을 디비에 aka탄

			pstmt.setString(1, id);//  첫번쨰 물음표에 값넣음 

			rs = pstmt.executeQuery();// 쿼리문 실행해서 rs에 저장 aka 발사  

			while(rs.next()) {//rs가 있을떄까지 
	            getPass = rs.getString("count(ID)");// rs에서 비밀번호 가져와서 getpass에 저
	            if(getPass.equals("1")) {// getpass   (아이디 중복되는지 체크 )
	               flag=true;//리턴
	               return flag;
	            
	            }
	            else
	            	return flag;
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
