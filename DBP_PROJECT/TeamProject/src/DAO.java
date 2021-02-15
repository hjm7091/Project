import java.sql.*;
import java.util.Vector;

public class DAO {
	private static final String DRIVER = "org.mariadb.jdbc.Driver";
	private static final String URL = "jdbc:mariadb://127.0.0.1:3306/teamProject";
	private static final String USER = "root";
	private static final String PASS = "system";
	
	public static Connection getConn() {
		Connection con = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USER,PASS);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public static int insertStudent(Students_data students_data) {
		Connection con = null;
		Statement stmt = null;
		
		int result = 0;
		
		try {
			con = getConn();
			stmt = con.createStatement();
			String sql = "INSERT INTO students VALUES(" + students_data.getStudent_id() +", '"+ students_data.getPassword() + "')";
			result = stmt.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	public static void createKNU_id(Students_data students_data) {
		Connection con = null;
		Statement stmt = null;
		
		try {
			con = getConn();
			stmt = con.createStatement();
			String sql = "CREATE TABLE knu_" + students_data.getStudent_id() + "(checked_course_id int primary key)ENGINE=InnoDB DEFAULT CHARSET=utf8";
			stmt.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int Login(Students_data students_data) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			con = getConn();
			stmt = con.createStatement();
			String sql = "SELECT * FROM students WHERE student_id = " + students_data.getStudent_id() + " AND password = '" + students_data.getPassword() + "'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next())
				result = 1;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static Vector search_id(String search_course_id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Vector data = new Vector();
		
		try {
			con = getConn();
			String sql = "SELECT *, @rownum := @rownum+1 as num FROM course,(SELECT @rownum := 0) AS R WHERE course_id LIKE '%" + search_course_id + "%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String classification = rs.getString("classification");
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				String course_name_eng = rs.getString("course_name_eng");
				String credit = rs.getString("credit");
				String professor_name = rs.getString("professor_name");
				String classroom = rs.getString("classroom");
				String time = rs.getString("time");
				
				Vector row = new Vector();
				row.add(num);
				row.add(classification);
				row.add(course_id);
				row.add(course_name);
				row.add(course_name_eng);
				row.add(credit);
				row.add(professor_name);
				row.add(classroom);
				row.add(time);
				
				data.add(row);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static Vector search_name(String search_course_name) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Vector data = new Vector();
		
		try {
			con = getConn();
			String sql = "SELECT *, @rownum := @rownum+1 as num  FROM course,(SELECT @rownum := 0) AS R WHERE course_name LIKE '%" + search_course_name + "%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String classification = rs.getString("classification");
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				String course_name_eng = rs.getString("course_name_eng");
				String credit = rs.getString("credit");
				String professor_name = rs.getString("professor_name");
				String classroom = rs.getString("classroom");
				String time = rs.getString("time");
				
				Vector row = new Vector();
				row.add(num);
				row.add(classification);
				row.add(course_id);
				row.add(course_name);
				row.add(course_name_eng);
				row.add(credit);
				row.add(professor_name);
				row.add(classroom);
				row.add(time);
				
				data.add(row);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static int insertCourse(String id) {
		Connection con = null;
		Statement stmt = null;
		int result = 0;
		
		try {
			con = getConn();
			stmt = con.createStatement();
			String sql = "INSERT INTO knu_" + loginFrame.user_id + " VALUES(" + id + ")";
			result = stmt.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
	public static Vector getBasicCourseList() {
		Vector data = new Vector();
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		int all = 0;
		int count = 0;
		
		try {
			con = getConn();
			String sql = "SELECT * "
					+ "FROM (SELECT C.* "
						+ "FROM course AS C JOIN cs_curriculm AS CS ON C.course_id = CS.cours_id "
						+ "	WHERE CS.classification LIKE '기초') AS A "
					+ "LEFT JOIN "
						+ "(SELECT @checking := cours_id AS checking "
						+ "FROM cs_curriculm AS CS2, knu_" + loginFrame.user_id + " "
						+ "WHERE CS2.cours_id LIKE checked_course_id) AS B "
					+ "ON A.course_id = B.checking";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String classification = rs.getString("classification");
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				String course_name_eng = rs.getString("course_name_eng");
				String credit = rs.getString("credit");
				String professor_name = rs.getString("professor_name");
				String classroom = rs.getString("classroom");
				String time = rs.getString("time");
				String checking = rs.getString("checking");
				
				
				all += 1;
				if(checking != null) {
					count += 1;
				}
				
				Vector row = new Vector();
				row.add(classification);
				row.add(course_id);
				row.add(course_name);
				row.add(course_name_eng);
				row.add(credit);
				row.add(professor_name);
				row.add(classroom);
				row.add(time);
				if(checking != null) row.add('O');
				else row.add('X');
				
				data.add(row);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static int checkBasic() {
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		int all = 0;
		int count = 0;
		int result = 0;
		
		try {
			con = getConn();
			String sql = "SELECT * "
					+ "FROM (SELECT C.* "
						+ "FROM course AS C JOIN cs_curriculm AS CS ON C.course_id = CS.cours_id "
						+ "	WHERE CS.classification LIKE '기초') AS A "
					+ "LEFT JOIN "
						+ "(SELECT @checking := cours_id AS checking "
						+ "FROM cs_curriculm AS CS2, knu_" + loginFrame.user_id + " "
						+ "WHERE CS2.cours_id LIKE checked_course_id) AS B "
					+ "ON A.course_id = B.checking";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String checking = rs.getString("checking");
				
				all += 1;
				if(checking != null) {
					count += 1;
				}
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(all == count) {
			result = 1;
		}
		else if(all > count) {
			result = 0;
		}
		
		return result;
	}
	
	public static Vector getBalancedCourseList() {
		Vector data = new Vector();
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT A.* "
					+ "FROM course AS A JOIN knu_" + loginFrame.user_id + " AS B ON A.course_id = B.checked_course_id "
					+ "WHERE A.classification LIKE '%균형%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String classification = rs.getString("classification");
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				String course_name_eng = rs.getString("course_name_eng");
				String credit = rs.getString("credit");
				String professor_name = rs.getString("professor_name");
				String classroom = rs.getString("classroom");
				String time = rs.getString("time");
				
				Vector row = new Vector();
				row.add(classification);
				row.add(course_id);
				row.add(course_name);
				row.add(course_name_eng);
				row.add(credit);
				row.add(professor_name);
				row.add(classroom);
				row.add(time);
				row.add('O');
				
				data.add(row);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static int checkBalanced1() {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT A.* "
					+ "FROM course AS A JOIN knu_" + loginFrame.user_id + " AS B ON A.course_id = B.checked_course_id "
					+ "WHERE A.classification LIKE '%(언어와문학)%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String course_id = rs.getString("course_id");
				
				result = 1;
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static int checkBalanced2() {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT A.* "
					+ "FROM course AS A JOIN knu_" + loginFrame.user_id + " AS B ON A.course_id = B.checked_course_id "
					+ "WHERE A.classification LIKE '%(역사와철학)%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String course_id = rs.getString("course_id");
				
				result = 1;
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static int checkBalanced3() {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT A.* "
					+ "FROM course AS A JOIN knu_" + loginFrame.user_id + " AS B ON A.course_id = B.checked_course_id "
					+ "WHERE A.classification LIKE '%(사회와문화)%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String course_id = rs.getString("course_id");
				
				result = 1;
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static int checkBalanced4() {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT A.* "
					+ "FROM course AS A JOIN knu_" + loginFrame.user_id + " AS B ON A.course_id = B.checked_course_id "
					+ "WHERE A.classification LIKE '%(과학과기술)%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String course_id = rs.getString("course_id");
				
				result = 1;
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static int checkBalanced5() {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT A.* "
					+ "FROM course AS A JOIN knu_" + loginFrame.user_id + " AS B ON A.course_id = B.checked_course_id "
					+ "WHERE A.classification LIKE '%(수리적사고)%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String course_id = rs.getString("course_id");
				
				result = 1;
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static int checkBalanced6() {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT A.* "
					+ "FROM course AS A JOIN knu_" + loginFrame.user_id + " AS B ON A.course_id = B.checked_course_id "
					+ "WHERE A.classification LIKE '%(예술과건강)%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String course_id = rs.getString("course_id");
				
				result = 1;
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	public static Vector getSpecialCourseList() {
		Vector data = new Vector();
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT A.* "
					+ "FROM course AS A JOIN knu_" + loginFrame.user_id + " AS B ON A.course_id = B.checked_course_id "
					+ "WHERE A.classification LIKE '%특화%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String classification = rs.getString("classification");
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				String course_name_eng = rs.getString("course_name_eng");
				String credit = rs.getString("credit");
				String professor_name = rs.getString("professor_name");
				String classroom = rs.getString("classroom");
				String time = rs.getString("time");
				
				Vector row = new Vector();
				row.add(classification);
				row.add(course_id);
				row.add(course_name);
				row.add(course_name_eng);
				row.add(credit);
				row.add(professor_name);
				row.add(classroom);
				row.add(time);
				row.add('O');
				
				data.add(row);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static int checkSpecial() {
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			con = getConn();
			String sql = "SELECT * "
					+ "FROM course AS A JOIN knu_" + loginFrame.user_id + " AS B ON A.course_id = B.checked_course_id "
					+ "WHERE A.classification LIKE '%특화%'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String course_id = rs.getString("course_id");
				
				result = 1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	
	public static Vector getCollegeCourseList() {
		Vector data = new Vector();
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT * "
						+ "FROM (SELECT C.* "
								+ "FROM course AS C JOIN cs_curriculm AS CS ON C.course_id =CS.cours_id "
								+ "WHERE C.classification LIKE '대학별') AS A "
						+ "LEFT JOIN "
						+ "(SELECT checked_course_id "
							+ "FROM cs_curriculm join knu_" + loginFrame.user_id + " ON cours_id = checked_course_id "
							+ "WHERE cs_curriculm.classification LIKE '%대학%') AS B "
						+ "ON A.course_id = B.checked_course_id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String classification = rs.getString("classification");
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				String course_name_eng = rs.getString("course_name_eng");
				String credit = rs.getString("credit");
				String professor_name = rs.getString("professor_name");
				String classroom = rs.getString("classroom");
				String time = rs.getString("time");
				String checking = rs.getString("checked_course_id");
				
				Vector row = new Vector();
				row.add(classification);
				row.add(course_id);
				row.add(course_name);
				row.add(course_name_eng);
				row.add(credit);
				row.add(professor_name);
				row.add(classroom);
				row.add(time);
				if(checking != null) row.add('O');
				else row.add('X');
				
				data.add(row);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static int checkCollege1() {
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		

		int result = 0;
		
		try {
			con = getConn();
			String sql = "SELECT * "
					+ "FROM cs_curriculm JOIN knu_" + loginFrame.user_id + " ON cours_id = checked_course_id "
					+ "WHERE classification = '대학별(필수)'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String cousre_id = rs.getString("checked_course_id");
				
				if(cousre_id != null) {
					result += 1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return result;
	}
	
	public static int checkCollege2() {
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		

		int result = 0;
		
		try {
			con = getConn();
			String sql = "SELECT * "
					+ "FROM cs_curriculm JOIN knu_" + loginFrame.user_id + " ON cours_id = checked_course_id "
					+ "WHERE classification = '대학별(선택)'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String cousre_id = rs.getString("checked_course_id");
				
				if(cousre_id != null) {
					result += 1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	
	public static Vector getEssentialCourseList() {
		Vector data = new Vector();
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT * "
					+ "FROM (SELECT C.* "
						+ "FROM course AS C JOIN cs_curriculm AS CS ON C.course_id = CS.cours_id "
						+ "	WHERE CS.classification LIKE '전필') AS A "
					+ "LEFT JOIN "
						+ "(SELECT @checking := cours_id AS checking "
						+ "FROM cs_curriculm AS CS2, knu_" + loginFrame.user_id + " "
						+ "WHERE CS2.cours_id LIKE checked_course_id) AS B "
					+ "ON A.course_id = B.checking";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String classification = rs.getString("classification");
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				String course_name_eng = rs.getString("course_name_eng");
				String credit = rs.getString("credit");
				String professor_name = rs.getString("professor_name");
				String classroom = rs.getString("classroom");
				String time = rs.getString("time");
				String checking = rs.getString("checking");
				
				Vector row = new Vector();
				row.add(classification);
				row.add(course_id);
				row.add(course_name);
				row.add(course_name_eng);
				row.add(credit);
				row.add(professor_name);
				row.add(classroom);
				row.add(time);
				if(checking != null) row.add('O');
				else row.add('X');
				
				data.add(row);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	
	public static int checkEssential() {
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		

		int result = 0;
		
		try {
			con = getConn();
			String sql = "SELECT * "
					+ "FROM cs_curriculm JOIN knu_" + loginFrame.user_id + " ON cours_id = checked_course_id "
					+ "WHERE classification = '전필'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String cousre_id = rs.getString("checked_course_id");
				
				if(cousre_id != null) {
					result += 1;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	public static Vector getChoiceCourseList() {
		Vector data = new Vector();
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT * "
					+ "FROM (SELECT C.* "
						+ "FROM course AS C JOIN cs_curriculm AS CS ON C.course_id = CS.cours_id "
						+ "	WHERE CS.classification LIKE '전선') AS A "
					+ "LEFT JOIN "
						+ "(SELECT @checking := cours_id AS checking "
						+ "FROM cs_curriculm AS CS2, knu_" + loginFrame.user_id + " "
						+ "WHERE CS2.cours_id LIKE checked_course_id) AS B "
					+ "ON A.course_id = B.checking";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String classification = rs.getString("classification");
				String course_id = rs.getString("course_id");
				String course_name = rs.getString("course_name");
				String course_name_eng = rs.getString("course_name_eng");
				String credit = rs.getString("credit");
				String professor_name = rs.getString("professor_name");
				String classroom = rs.getString("classroom");
				String time = rs.getString("time");
				String checking = rs.getString("checking");
				
				Vector row = new Vector();
				row.add(classification);
				row.add(course_id);
				row.add(course_name);
				row.add(course_name_eng);
				row.add(credit);
				row.add(professor_name);
				row.add(classroom);
				row.add(time);
				if(checking != null) row.add('O');
				else row.add('X');
				
				data.add(row);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	
	public static int checkChoice() {
		
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		

		int result = 0;
		
		try {
			con = getConn();
			String sql = "SELECT SUBSTRING_INDEX(credit, '-' , 1) credit "
					+ "FROM course AS a JOIN "
					+ "(SELECT * "
					+ "FROM cs_curriculm JOIN knu_" + loginFrame.user_id + " ON "
							+ "cours_id = checked_course_id "
							+ "WHERE classification = '전선') AS b "
					+ "ON a.course_id = b.cours_id";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				String credit = rs.getString("credit");
				
				if(credit != null) {
					result += Integer.parseInt(credit);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}

}


