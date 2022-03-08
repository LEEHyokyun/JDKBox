package step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * java application과 database 연동
 * 1. jdbc driver loading - jdk 1.6버전 이상에서는 생략가능
 * 2. DB Connection - db server 연결
 * 3. PreparedStatement - 보안 등 기타 환경설정
 * 4. execute - SQL 실행
 * 5. ResultSet - SQL 실행결과확인
 * 6. close - 자원폐쇄(DB close)
 */
public class TestJDBC1 {
	public static void main(String[] args) {
		//JRE library는 경로입력을 하지 않아도, 자동 접근이 가능
		String driver = "oracle.jdbc.OracleDriver";
		String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		try {
			/*
			 * 참고로, DB구현체는 OracleConnection class로 구현되어있다.
			 */
			//지정 클래스를 클래스 로딩, static이 메모리에 적재된다(static 사용가능).
			Class.forName(driver); //jre 라이브러리는 자동적으로 접근, 경로 별도 기재X
			System.out.println("Oracle DB Driver loading");
			//Connection type 
			Connection con = DriverManager.getConnection(dbUrl, "scott", "tiger");
			System.out.println("DB Connected to "+con);
			//SQL set
			String sql = "SELECT id, password, address FROM member";
			PreparedStatement pstmt = con.prepareStatement(sql); //prepareds.. type
			ResultSet rs = pstmt.executeQuery(); //SQL 실행, Resultset type
			/*
			 * rs 자체는 속성을 indexing으로 하는 표 그 자체를 가져온다!
			 * rs.getString(index)를 통해 해당 속성값을 가져온다.
			 * rs.next() -> 다음 column.
			 */
			while(rs.next()) {
				//result 지속출력
				String id = rs.getString(1); //1 column index or Column 명
				String password = rs.getString(2); 
				String name = rs.getString(3);
				//String address = rs.getString(4);
				System.out.println(id+" "+password+" "+name+" ");
			}
			/*
			 * 사용한 자원, 연결설정 모두 해제!
			 */
			rs.close();
			pstmt.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
