package bearich;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadService {

	public static String[] LoadWallet(String id){
		
		String driverName="com.mysql.jdbc.Driver";//DB driver? 
		String dbURL="jdbc:mysql://localhost:3306/userinfo";// DB 주소  


		String[] userInformation = new String[11];
		Connection con=null; 
		PreparedStatement pstmt =null;
		String sql=null;
		ResultSet rs =null;
	
		try {
			String BTC = null;
			String ETH = null;
			String DASH = null;
			String LTC = null;
			String ETC = null;
			String XRP = null;
			String BCH = null;
			String XMR = null;
			String ZEC = null;
			String QTUM = null;
			String MONEY = null;
			
			Class.forName(driverName);//모름 
			con=DriverManager.getConnection(dbURL,"root","wnsgh159");//DB 연결 
			System.out.println("Driver connection success!!");
			sql="select BTC,ETH,DASH,LTC,ETC,XRP,BCH,XMR,ZEC,QTUM,MONEY from info where ID = ?"; // 쿼리문 세팅 aka 총알 
			pstmt = con.prepareStatement(sql);// 쿼리문을 디비에 aka 탄집 
			
			pstmt.setString(1, id);// 첫번쨰 물음표에 값 넣기 

			rs = pstmt.executeQuery();// 쿼리문 실행해서 rs에 저장,aka 발사 

			while(rs.next()) {//rs 가 있을떄까찌 
	            BTC = rs.getString("BTC");
	            ETH = rs.getString("ETH");
	            DASH = rs.getString("DASH");
	            LTC = rs.getString("LTC");
	            ETC = rs.getString("ETC");
	            XRP = rs.getString("XRP");
	            BCH = rs.getString("BCH");
	            XMR = rs.getString("XMR");
	            ZEC = rs.getString("ZEC");
	            QTUM = rs.getString("QTUM");
	            MONEY = rs.getString("MONEY");
	            
	            
	            userInformation[0] = BTC;
	            userInformation[1] = ETH;
	            userInformation[2] = DASH;
	            userInformation[3] = LTC;
	            userInformation[4] = ETC;
	            userInformation[5] = XRP;
	            userInformation[6] = BCH;
	            userInformation[7] = XMR;
	            userInformation[8] = ZEC;
	            userInformation[9] = QTUM;
	            userInformation[10] = MONEY;
	            
	            
	            return userInformation;
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
		return userInformation;

	}

}
