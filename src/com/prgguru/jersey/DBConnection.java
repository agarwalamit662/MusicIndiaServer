package com.prgguru.jersey;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;



public class DBConnection {
	/**
	 * Method to create DB Connection
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public static Connection createConnection() throws Exception {
		Connection con = null;
		
		try {
			Class.forName(Constants.dbClass);
			con = DriverManager.getConnection(Constants.dbUrl, Constants.dbUser, Constants.dbPwd);
		} catch (Exception e) {
			throw e;
		} finally {
			return con;
		}
	}
    /**
     * Method to check whether uname and pwd combination are correct
     * 
     * @param uname
     * @param pwd
     * @return
     * @throws Exception
     */
	public static int getTotalInstalls() throws Exception{
		
		boolean isUserAvailable = false;
		int count = 0;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT COUNT(*) FROM myappusers";
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				count = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return count;
		
	}
	
public static String getShareableLink() throws Exception{
		
		boolean isUserAvailable = false;
		//int count = 0;
		String url = "NA";
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT url FROM sharemyapp where indexoflink = 1";
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				url = rs.getString(1);
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return url;
		
	}


public static String getUpdateLink() throws Exception{
	
	String json = "";
	
	boolean isUserAvailable = false;
	//int count = 0;
	String url = "NA";
	int version = 0;
	Connection dbConn = null;
	try {
		try {
			dbConn = DBConnection.createConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stmt = dbConn.createStatement();
		String query = "SELECT updatelink,version FROM updatemyapp where indexoflink = 1";
		
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			
			url = rs.getString(1);
			version = rs.getInt(2);
		}
		
		JSONObject obj = new JSONObject();
		obj.put("link", url);
		obj.put("version",version);
		json = obj.toString();
		
	} catch (SQLException sqle) {
		throw sqle;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		if (dbConn != null) {
			dbConn.close();
		}
		throw e;
	} finally {
		if (dbConn != null) {
			dbConn.close();
		}
	}
	return json;
	
}
	
	public static boolean checkLogin(String uname, String pwd) throws Exception {
		boolean isUserAvailable = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM user WHERE username = '" + uname
					+ "' AND password=" + "'" + pwd + "'";
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				isUserAvailable = true;
			}
		} catch (SQLException sqle) {
			throw sqle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return isUserAvailable;
	}
	/**
	 * Method to insert uname and pwd in DB
	 * 
	 * @param name
	 * @param uname
	 * @param pwd
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public static boolean insertUser(String name, String uname, String pwd) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into user(name, username, password) values('"+name+ "',"+"'"
					+ uname + "','" + pwd + "')";
			
			int records = stmt.executeUpdate(query);
			//System.out.println(records);
			//When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			//e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}
	
	public static boolean insertDeviceId(String did) throws SQLException, Exception {
		boolean insertStatus = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "INSERT into myappusers(deviceid, rating, comments) values('"+did+ "',0,'')";
			
			int records = stmt.executeUpdate(query);
			//System.out.println(records);
			//When record is successfully inserted
			if (records > 0) {
				insertStatus = true;
			}
		} catch (SQLException sqle) {
			//sqle.printStackTrace();
			throw sqle;
		} catch (Exception e) {
			//e.printStackTrace();
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		return insertStatus;
	}
	
	
	public static boolean getSaveLastYearSuccessful(int year) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM save_last_year_successful WHERE lastyear = '" + String.valueOf(year)
					+ "' AND wassavesuccessful = " + "'" + "1" + "' AND record_id = '1' and id = 1";
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	public static boolean updateIsLatestToZero() throws SQLException{
		
		System.out.println(Calendar.getInstance().getTime().toString()+Calendar.getInstance().getTime().toString()+"In update Latest to Zero Method");
		
		boolean retValue = false;
		
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "UPDATE lyricsmovies set ISLATEST = 0";
			
			int check = 0; 
			check =	stmt.executeUpdate(query);
			
			if(check > 0) {
				
				retValue = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		System.out.println(Calendar.getInstance().getTime().toString()+"Exiting update Latest to Zero Method");
		
		return retValue;
		
	}
	
	
public static boolean updateIsLatestToOne(String MOVIENAME) throws SQLException{
		
		
		boolean retValue = false;
		
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "UPDATE lyricsmovies set ISLATEST = 1 where MOVIENAME = '"+MOVIENAME + "'";
			System.out.println(Calendar.getInstance().getTime().toString()+"Updated ISLATEST for movie : "+MOVIENAME);
			int check = 0; 
			check =	stmt.executeUpdate(query);
			
			if(check > 0) {
				
				retValue = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return retValue;
		
	}
	
	public static boolean setSaveLastYearSuccessful(int year) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "UPDATE save_last_year_successful set lastyear = '" + String.valueOf(year) + "' , wassavesuccessful = '1' where record_id = '1'";
			
			int check = 0; 
			check =	stmt.executeUpdate(query);
			
			if(check > 0) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	
	public static boolean getSaveThisYearSuccessful(String todaydate) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM save_this_year_successful WHERE todayDate = '" + String.valueOf(todaydate)
					+ "' AND wassavesuccessful = " + "'" + "1" + "' AND record_id = '1' and id = 1";
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	public static boolean setSaveThisYearSuccessful(String todayDate) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "UPDATE save_this_year_successful set todayDate = '" + String.valueOf(todayDate) + "' , wassavesuccessful = '1' where record_id = '1'";
			
			int check = 0; 
			check =	stmt.executeUpdate(query);
			
			if(check > 0) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}

	
	
	public static boolean getSaveThisYearLyricsSuccessful(String todaydate) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM save_this_year_lyrics_successful WHERE todayDate = '" + String.valueOf(todaydate)
					+ "' AND wassavesuccessful = " + "'" + "1" + "' AND record_id = '1' and id = 1";
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	public static boolean setSaveThisYearLyricsSuccessful(String todayDate) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "UPDATE save_this_year_lyrics_successful set todayDate = '" + String.valueOf(todayDate) + "' , wassavesuccessful = '1' where record_id = '1'";
			
			int check = 0; 
			check =	stmt.executeUpdate(query);
			
			if(check > 0) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	
	public static boolean getSaveThisYearPunjabiSuccessful(String todaydate) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM save_this_year_punjabi_successful WHERE todayDate = '" + String.valueOf(todaydate)
					+ "' AND wassavesuccessful = " + "'" + "1" + "' AND record_id = '1' and id = 1";
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	public static boolean setSaveThisYearPunjabiSuccessful(String todayDate) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "UPDATE save_this_year_punjabi_successful set todayDate = '" + String.valueOf(todayDate) + "' , wassavesuccessful = '1' where record_id = '1'";
			
			int check = 0; 
			check =	stmt.executeUpdate(query);
			
			if(check > 0) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	
	
	public static boolean getSaveThisYearPopSuccessful(String todaydate) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM save_this_year_pop_successful WHERE todayDate = '" + String.valueOf(todaydate)
					+ "' AND wassavesuccessful = " + "'" + "1" + "' AND record_id = '1' and id = 1";
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	public static boolean setSaveThisYearPopSuccessful(String todayDate) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "UPDATE save_this_year_pop_successful set todayDate = '" + String.valueOf(todayDate) + "' , wassavesuccessful = '1' where record_id = '1'";
			
			int check = 0; 
			check =	stmt.executeUpdate(query);
			
			if(check > 0) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	
	public static boolean getSaveNewYearSuccessful(String todaydate) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM save_new_year_successful WHERE todayDate = '" + String.valueOf(todaydate)
					+ "' AND wassavesuccessful = " + "'" + "1" + "' AND record_id = '1' and id = 1";
			
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	public static boolean setSaveNewYearSuccessful(String todayDate) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "UPDATE save_new_year_successful set todayDate = '" + String.valueOf(todayDate) + "' , wassavesuccessful = '1' where record_id = '1'";
			
			int check = 0; 
			check =	stmt.executeUpdate(query);
			
			if(check > 0) {
				
				wassavesuccess = true;
			}
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	
	public static boolean insertandUpdateMovieObject(int movieNumber, 
			String MOVIESTARTCHAR, 
			String MOVIENAME, 
			String releaseyear , 
			String MUSIC_DIRECTOR, 
			String ACTORS, 
			String SINGERS, 
			String DIRECTOR, 
			String URLS) throws SQLException{
		
		if(MOVIESTARTCHAR == null){
			MOVIESTARTCHAR = "NA";
		}
		if(MOVIENAME == null){
			MOVIENAME = "NA";
		}
		if(MUSIC_DIRECTOR == null){
			MUSIC_DIRECTOR = "NA";
		}
		if(ACTORS == null){
			ACTORS = "NA";
		}
		if(SINGERS == null){
			SINGERS = "NA";
		}
		if(DIRECTOR == null){
			DIRECTOR = "NA";
		}
		if(URLS == null){
			URLS = "NA";
		}
		boolean wassavesuccess = false;
		Connection dbConn = null;
		
		MOVIENAME = MOVIENAME.toUpperCase();
		MOVIENAME = MOVIENAME.replaceAll("MOVIE", "");
		MOVIENAME = MOVIENAME.replaceAll("MOVIES", "");
		MOVIENAME = MOVIENAME.replaceAll("MP3", "");
		MOVIENAME = MOVIENAME.replaceAll("SONGSMP3.COM", "");
		MOVIENAME = MOVIENAME.replaceAll("SONGSMP3.INFO", "");
		MOVIENAME = MOVIENAME.replaceAll("SONGSMP3.NET", "");
		MOVIENAME = MOVIENAME.replaceAll("SONGS", "");
		MOVIENAME = MOVIENAME.replaceAll("SONG", "");
		
		ACTORS = ACTORS.toUpperCase();
		ACTORS = ACTORS.replaceAll("SONGSMP3.COM", "");
		
		SINGERS = SINGERS.toUpperCase();
		SINGERS = SINGERS.replaceAll("SONGSMP3.COM", "");
		
		DIRECTOR = DIRECTOR.toUpperCase();
		DIRECTOR =DIRECTOR.replaceAll("SONGSMP3.COM", "");
		
		MUSIC_DIRECTOR = MUSIC_DIRECTOR.toUpperCase();
		MUSIC_DIRECTOR = MUSIC_DIRECTOR.replaceAll("SONGSMP3.COM", "");
		
		
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String queryRs = 	"INSERT INTO bollywoodmoviessongs( MOVIENUMBER, MOVIESTARTCHAR, MOVIENAME , RELEASE_DATE , MUSIC_DIRECTOR , ACTORS , SINGERS , DIRECTOR , URLS )  VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? ) "
					+ " ON DUPLICATE KEY UPDATE MOVIESTARTCHAR = ? , MOVIENAME = ? , RELEASE_DATE = ? , "
					+ " MUSIC_DIRECTOR = ? , ACTORS = ? , SINGERS = ? , DIRECTOR  = ? , URLS =  ? ";
			
			//Statement stmt = dbConn.createStatement();

			PreparedStatement ps =  dbConn.prepareStatement(queryRs);
			ps.setInt(1, movieNumber);
			ps.setString(2, MOVIESTARTCHAR);
			ps.setString(3, MOVIENAME);
			ps.setString(4, releaseyear);
			ps.setString(5, MUSIC_DIRECTOR);
			ps.setString(6, ACTORS);
			ps.setString(7, SINGERS);
			ps.setString(8, DIRECTOR);
			ps.setString(9, URLS);
			String one = MOVIESTARTCHAR, two = MOVIENAME, three =  releaseyear, four = MUSIC_DIRECTOR,five = ACTORS,six = SINGERS,seven = DIRECTOR, eight = URLS ;
			ps.setString(10, one);
			ps.setString(11, two);
			ps.setString(12, three);
			ps.setString(13, four);
			ps.setString(14, five);
			ps.setString(15, six);
			ps.setString(16, seven);
			ps.setString(17, eight);
			System.out.println(Calendar.getInstance().getTime().toString()+MOVIENAME);
			
		/*	String query = 	"INSERT INTO bollywoodmoviessongs (MOVIENUMBER, MOVIESTARTCHAR, MOVIENAME, RELEASE_DATE, MUSIC_DIRECTOR, ACTORS, SINGERS,DIRECTOR,URLS) "
		+ " VALUES ('"+movieNumber+"' ,'"+MOVIESTARTCHAR+"','"+MOVIENAME+"','"+releaseyear+"','"+MUSIC_DIRECTOR+"','"+ACTORS+"','"+SINGERS+"','"+DIRECTOR+"', '"+URLS+"') "
		+ " ON DUPLICATE KEY UPDATE MOVIESTARTCHAR = '"+MOVIESTARTCHAR+"' , MOVIENAME = '"+MOVIENAME+"' , RELEASE_DATE = '"+releaseyear+"', "
				+ " MUSIC_DIRECTOR = '"+MUSIC_DIRECTOR+"', ACTORS = '"+ACTORS+"', SINGERS =  '"+SINGERS+"', DIRECTOR  = '"+DIRECTOR+"' , URLS =  '"+URLS+"'";*/
		
			//ResultSet rs = preparedStatement.executeQuery(queryRs);
			
			int check = 0; 
			
			check = ps.executeUpdate();
					//stmt.executeUpdate(query);
			if(check > 0){
				wassavesuccess = true;
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	public static int getMaxBollywoodSongs() throws SQLException{
		
		Connection dbConn = null;
		int count = 0;
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String qry = "SELECT COUNT(*) from moviesongs";
			PreparedStatement psMOVIENUMBER = dbConn.prepareStatement(qry);
			ResultSet rs = psMOVIENUMBER.executeQuery();
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return count;
		
	}
	
	public static int getMaxPunjabiSongs() throws SQLException{
		
		Connection dbConn = null;
		int count = 0;
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String qry = "SELECT COUNT(*) from punjabisongs";
			PreparedStatement psMOVIENUMBER = dbConn.prepareStatement(qry);
			ResultSet rs = psMOVIENUMBER.executeQuery();
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return count;
		
	}
	
	public static String getMaxMovieNumberURL() throws SQLException{
		
		String url = "NA";
		Connection dbConn = null;
		int count = 0;
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String qry = "SELECT MOVIENUMBER,MOVIENAME,URLS FROM bollywoodmoviessongs having MOVIENUMBER = (Select MAX(MOVIENUMBER) from bollywoodmoviessongs)";
			PreparedStatement psMOVIENUMBER = dbConn.prepareStatement(qry);
			ResultSet rs = psMOVIENUMBER.executeQuery();
			
			while(rs.next()){
				count = rs.getInt(1);
				url = rs.getString(3);
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return url;
		
	}
	
	
	public static int insertandUpdateMovieLyricsObject(int movieNumber, 
			String MOVIESTARTCHAR, 
			String MOVIENAME, 
			String URLS,
			int ISLATEST) throws SQLException{
		
		if(MOVIESTARTCHAR == null){
			MOVIESTARTCHAR = "NA";
		}
		if(MOVIENAME == null){
			MOVIENAME = "NA";
		}
		if(URLS == null){
			URLS = "NA";
		}
		
		
		boolean wassavesuccess = false;
		Connection dbConn = null;
		int MOVIENUMBER = 0;
		MOVIENAME = MOVIENAME.toUpperCase();
		
		
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String qry = "SELECT MOVIENUMBER from lyricsmovies where MOVIENAME = ?";
			PreparedStatement psMOVIENUMBER = dbConn.prepareStatement(qry);
			psMOVIENUMBER.setString(1, MOVIENAME);
			ResultSet rs = psMOVIENUMBER.executeQuery();
			
			while(rs.next()){
				MOVIENUMBER = rs.getInt(1);
			}
		//	System.out.println("MOVIENUMBER IS: "+MOVIENUMBER);
			String queryRs = "";
			if(MOVIENUMBER != 0){
				//queryRs = "UPDATE lyricsmovies set MOVIESTARTCHAR = ? , MOVIENAME = ?,URLS = ?,ISLATEST = ? where MOVIENUMBER = ?";
			}
			else{
				queryRs = 	"INSERT INTO lyricsmovies( MOVIESTARTCHAR, MOVIENAME ,URLS,ISLATEST )  VALUES( ? , ? , ? , ?) ";
				System.out.println(Calendar.getInstance().getTime().toString()+"New Lyrics inserted for :" +MOVIENAME);
			
			
			//Statement stmt = dbConn.createStatement();
				
			PreparedStatement ps =  dbConn.prepareStatement(queryRs);
			
			
			if(MOVIENUMBER != 0){
				//ps.setInt(5, MOVIENUMBER);
			}
			else{
				ps.setString(1, MOVIESTARTCHAR);
				ps.setString(2, MOVIENAME);
				ps.setString(3, URLS);
				ps.setInt(4, ISLATEST);
			}
			
			
			//System.out.println(MOVIENAME);
			
		/*	String query = 	"INSERT INTO bollywoodmoviessongs (MOVIENUMBER, MOVIESTARTCHAR, MOVIENAME, RELEASE_DATE, MUSIC_DIRECTOR, ACTORS, SINGERS,DIRECTOR,URLS) "
		+ " VALUES ('"+movieNumber+"' ,'"+MOVIESTARTCHAR+"','"+MOVIENAME+"','"+releaseyear+"','"+MUSIC_DIRECTOR+"','"+ACTORS+"','"+SINGERS+"','"+DIRECTOR+"', '"+URLS+"') "
		+ " ON DUPLICATE KEY UPDATE MOVIESTARTCHAR = '"+MOVIESTARTCHAR+"' , MOVIENAME = '"+MOVIENAME+"' , RELEASE_DATE = '"+releaseyear+"', "
				+ " MUSIC_DIRECTOR = '"+MUSIC_DIRECTOR+"', ACTORS = '"+ACTORS+"', SINGERS =  '"+SINGERS+"', DIRECTOR  = '"+DIRECTOR+"' , URLS =  '"+URLS+"'";*/
		
			//ResultSet rs = preparedStatement.executeQuery(queryRs);
			
			int check = 0; 
			
			check = ps.executeUpdate();
					//stmt.executeUpdate(query);
			if(check > 0){
				wassavesuccess = true;
				
				rs = psMOVIENUMBER.executeQuery();
				
				while(rs.next()){
					MOVIENUMBER = rs.getInt(1);
				}
			}
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return MOVIENUMBER;
	}
	
	
	public static int insertandUpdateMovieLyricsSongsObject(MOVIELYRICS movieObject, 
			String SONGNAME, 
			String LYRICS 
			) throws SQLException{
		
		if(SONGNAME == null){
			SONGNAME = "NA";
		}
		if(LYRICS == null){
			LYRICS = "NA";
		}
		
		
		boolean wassavesuccess = false;
		Connection dbConn = null;
		int MOVIENUMBER = 0;
		
		SONGNAME = SONGNAME.toUpperCase();
		MOVIENUMBER = movieObject.getMOVIENUMBER();
		int SONG_ID = 0;
		
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String qry = "SELECT SONG_ID from songslyrics where SONGNAME = ? AND MOVIE_NUMBER = ?";
			PreparedStatement psMOVIENUMBER = dbConn.prepareStatement(qry);
			psMOVIENUMBER.setString(1, SONGNAME);
			psMOVIENUMBER.setInt(2, MOVIENUMBER);
			ResultSet rs = psMOVIENUMBER.executeQuery();
			
			while(rs.next()){
				SONG_ID = rs.getInt(1);
			}
			String queryRs = "";
			if(SONG_ID != 0){
				//queryRs = "UPDATE songslyrics set SONGNAME = ? , LYRICS = ? where MOVIE_NUMBER = ? AND SONG_ID = ?";
			}
			else{
				queryRs = 	"INSERT INTO songslyrics( SONGNAME, LYRICS,MOVIE_NUMBER  )  VALUES( ? , ? , ?) ";
					
				System.out.println(Calendar.getInstance().getTime().toString()+"New Song Lyrics added : "+SONGNAME);
			
			//Statement stmt = dbConn.createStatement();
			
			PreparedStatement ps =  dbConn.prepareStatement(queryRs);
			
			
			
			if(SONG_ID != 0){
				//ps.setInt(4, SONG_ID);
			}
			else{
				ps.setString(1, SONGNAME);
				ps.setString(2, LYRICS);
				ps.setInt(3, MOVIENUMBER);
			}
			
			//System.out.println(Calendar.getInstance().getTime().toString()+SONGNAME);
			
		/*	String query = 	"INSERT INTO bollywoodmoviessongs (MOVIENUMBER, MOVIESTARTCHAR, MOVIENAME, RELEASE_DATE, MUSIC_DIRECTOR, ACTORS, SINGERS,DIRECTOR,URLS) "
		+ " VALUES ('"+movieNumber+"' ,'"+MOVIESTARTCHAR+"','"+MOVIENAME+"','"+releaseyear+"','"+MUSIC_DIRECTOR+"','"+ACTORS+"','"+SINGERS+"','"+DIRECTOR+"', '"+URLS+"') "
		+ " ON DUPLICATE KEY UPDATE MOVIESTARTCHAR = '"+MOVIESTARTCHAR+"' , MOVIENAME = '"+MOVIENAME+"' , RELEASE_DATE = '"+releaseyear+"', "
				+ " MUSIC_DIRECTOR = '"+MUSIC_DIRECTOR+"', ACTORS = '"+ACTORS+"', SINGERS =  '"+SINGERS+"', DIRECTOR  = '"+DIRECTOR+"' , URLS =  '"+URLS+"'";*/
		
			//ResultSet rs = preparedStatement.executeQuery(queryRs);
			
			int check = 0; 
			
			check = ps.executeUpdate();
					//stmt.executeUpdate(query);
			if(check > 0){
				wassavesuccess = true;
				
				
			}
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return MOVIENUMBER;
	}
	
	public static boolean insertandUpdateIndiPopObject(int movieNumber, String MOVIESTARTCHAR, String MOVIENAME, String releaseyear , String MUSIC_DIRECTOR, String ACTORS, String SINGERS, String DIRECTOR, String URLS) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		
		if(MOVIESTARTCHAR == null){
			MOVIESTARTCHAR = "NA";
		}
		if(MOVIENAME == null){
			MOVIENAME = "NA";
		}
		if(MUSIC_DIRECTOR == null){
			MUSIC_DIRECTOR = "NA";
		}
		if(ACTORS == null){
			ACTORS = "NA";
		}
		if(SINGERS == null){
			SINGERS = "NA";
		}
		if(DIRECTOR == null){
			DIRECTOR = "NA";
		}
		if(URLS == null){
			URLS = "NA";
		}
		
		
		
		MOVIENAME = MOVIENAME.toUpperCase();
		MOVIENAME = MOVIENAME.replaceAll("MOVIE", "");
		MOVIENAME = MOVIENAME.replaceAll("MOVIES", "");
		MOVIENAME = MOVIENAME.replaceAll("MP3", "");
		MOVIENAME = MOVIENAME.replaceAll("SONGSMP3.COM", "");
		MOVIENAME = MOVIENAME.replaceAll("SONGS", "");
		MOVIENAME = MOVIENAME.replaceAll("SONG", "");
		
		ACTORS = ACTORS.toUpperCase();
		ACTORS = ACTORS.replaceAll("SONGSMP3.COM", "");
		
		SINGERS = SINGERS.toUpperCase();
		SINGERS = SINGERS.replaceAll("SONGSMP3.COM", "");
		
		DIRECTOR = DIRECTOR.toUpperCase();
		DIRECTOR =DIRECTOR.replaceAll("SONGSMP3.COM", "");
		
		MUSIC_DIRECTOR = MUSIC_DIRECTOR.toUpperCase();
		MUSIC_DIRECTOR = MUSIC_DIRECTOR.replaceAll("SONGSMP3.COM", "");
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String queryRs = 	"INSERT INTO bollywoodindipopsongs( MOVIENUMBER, MOVIESTARTCHAR, MOVIENAME , RELEASE_DATE , MUSIC_DIRECTOR , ACTORS , SINGERS , DIRECTOR , URLS )  VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? ) "
					+ " ON DUPLICATE KEY UPDATE MOVIESTARTCHAR = ? , MOVIENAME = ? , RELEASE_DATE = ? , "
					+ " MUSIC_DIRECTOR = ? , ACTORS = ? , SINGERS = ? , DIRECTOR  = ? , URLS =  ? ";
			
			//Statement stmt = dbConn.createStatement();

			PreparedStatement ps =  dbConn.prepareStatement(queryRs);
			ps.setInt(1, movieNumber);
			ps.setString(2, MOVIESTARTCHAR);
			ps.setString(3, MOVIENAME);
			ps.setString(4, releaseyear);
			ps.setString(5, MUSIC_DIRECTOR);
			ps.setString(6, ACTORS);
			ps.setString(7, SINGERS);
			ps.setString(8, DIRECTOR);
			ps.setString(9, URLS);
			String one = MOVIESTARTCHAR, two = MOVIENAME, three =  releaseyear, four = MUSIC_DIRECTOR,five = ACTORS,six = SINGERS,seven = DIRECTOR, eight = URLS ;
			ps.setString(10, one);
			ps.setString(11, two);
			ps.setString(12, three);
			ps.setString(13, four);
			ps.setString(14, five);
			ps.setString(15, six);
			ps.setString(16, seven);
			ps.setString(17, eight);
			
		/*	String query = 	"INSERT INTO bollywoodmoviessongs (MOVIENUMBER, MOVIESTARTCHAR, MOVIENAME, RELEASE_DATE, MUSIC_DIRECTOR, ACTORS, SINGERS,DIRECTOR,URLS) "
		+ " VALUES ('"+movieNumber+"' ,'"+MOVIESTARTCHAR+"','"+MOVIENAME+"','"+releaseyear+"','"+MUSIC_DIRECTOR+"','"+ACTORS+"','"+SINGERS+"','"+DIRECTOR+"', '"+URLS+"') "
		+ " ON DUPLICATE KEY UPDATE MOVIESTARTCHAR = '"+MOVIESTARTCHAR+"' , MOVIENAME = '"+MOVIENAME+"' , RELEASE_DATE = '"+releaseyear+"', "
				+ " MUSIC_DIRECTOR = '"+MUSIC_DIRECTOR+"', ACTORS = '"+ACTORS+"', SINGERS =  '"+SINGERS+"', DIRECTOR  = '"+DIRECTOR+"' , URLS =  '"+URLS+"'";*/
		
			//ResultSet rs = preparedStatement.executeQuery(queryRs);
			
			int check = 0; 
			
			check = ps.executeUpdate();
					//stmt.executeUpdate(query);
			if(check > 0){
				wassavesuccess = true;
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	public static boolean insertandUpdatePunjabiObject(int movieNumber, String MOVIESTARTCHAR, String MOVIENAME, String releaseyear , String MUSIC_DIRECTOR, String ACTORS, String SINGERS, String DIRECTOR, String URLS) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		
		if(MOVIESTARTCHAR == null){
			MOVIESTARTCHAR = "NA";
		}
		if(MOVIENAME == null){
			MOVIENAME = "NA";
		}
		if(MUSIC_DIRECTOR == null){
			MUSIC_DIRECTOR = "NA";
		}
		if(ACTORS == null){
			ACTORS = "NA";
		}
		if(SINGERS == null){
			SINGERS = "NA";
		}
		if(DIRECTOR == null){
			DIRECTOR = "NA";
		}
		if(URLS == null){
			URLS = "NA";
		}
		
		MOVIENAME = MOVIENAME.toUpperCase();
		MOVIENAME = MOVIENAME.replaceAll("MOVIE", "");
		MOVIENAME = MOVIENAME.replaceAll("MOVIES", "");
		MOVIENAME = MOVIENAME.replaceAll("MP3", "");
		MOVIENAME = MOVIENAME.replaceAll("SONGSMP3.COM", "");
		MOVIENAME = MOVIENAME.replaceAll("SONGS", "");
		MOVIENAME = MOVIENAME.replaceAll("SONG", "");
		
		ACTORS = ACTORS.toUpperCase();
		ACTORS = ACTORS.replaceAll("SONGSMP3.COM", "");
		
		SINGERS = SINGERS.toUpperCase();
		SINGERS = SINGERS.replaceAll("SONGSMP3.COM", "");
		
		DIRECTOR = DIRECTOR.toUpperCase();
		DIRECTOR =DIRECTOR.replaceAll("SONGSMP3.COM", "");
		
		MUSIC_DIRECTOR = MUSIC_DIRECTOR.toUpperCase();
		MUSIC_DIRECTOR = MUSIC_DIRECTOR.replaceAll("SONGSMP3.COM", "");
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String queryRs = 	"INSERT INTO punjabialbums( MOVIENUMBER, MOVIESTARTCHAR, MOVIENAME , RELEASE_DATE , MUSIC_DIRECTOR , ACTORS , SINGERS , DIRECTOR , URLS )  VALUES( ? , ? , ? , ? , ? , ? , ? , ? , ? ) "
					+ " ON DUPLICATE KEY UPDATE MOVIESTARTCHAR = ? , MOVIENAME = ? , RELEASE_DATE = ? , "
					+ " MUSIC_DIRECTOR = ? , ACTORS = ? , SINGERS = ? , DIRECTOR  = ? , URLS =  ? ";
			
			//Statement stmt = dbConn.createStatement();

			PreparedStatement ps =  dbConn.prepareStatement(queryRs);
			ps.setInt(1, movieNumber);
			ps.setString(2, MOVIESTARTCHAR);
			ps.setString(3, MOVIENAME);
			ps.setString(4, releaseyear);
			ps.setString(5, MUSIC_DIRECTOR);
			ps.setString(6, ACTORS);
			ps.setString(7, SINGERS);
			ps.setString(8, DIRECTOR);
			ps.setString(9, URLS);
			String one = MOVIESTARTCHAR, two = MOVIENAME, three =  releaseyear, four = MUSIC_DIRECTOR,five = ACTORS,six = SINGERS,seven = DIRECTOR, eight = URLS ;
			ps.setString(10, one);
			ps.setString(11, two);
			ps.setString(12, three);
			ps.setString(13, four);
			ps.setString(14, five);
			ps.setString(15, six);
			ps.setString(16, seven);
			ps.setString(17, eight);
			
		/*	String query = 	"INSERT INTO bollywoodmoviessongs (MOVIENUMBER, MOVIESTARTCHAR, MOVIENAME, RELEASE_DATE, MUSIC_DIRECTOR, ACTORS, SINGERS,DIRECTOR,URLS) "
		+ " VALUES ('"+movieNumber+"' ,'"+MOVIESTARTCHAR+"','"+MOVIENAME+"','"+releaseyear+"','"+MUSIC_DIRECTOR+"','"+ACTORS+"','"+SINGERS+"','"+DIRECTOR+"', '"+URLS+"') "
		+ " ON DUPLICATE KEY UPDATE MOVIESTARTCHAR = '"+MOVIESTARTCHAR+"' , MOVIENAME = '"+MOVIENAME+"' , RELEASE_DATE = '"+releaseyear+"', "
				+ " MUSIC_DIRECTOR = '"+MUSIC_DIRECTOR+"', ACTORS = '"+ACTORS+"', SINGERS =  '"+SINGERS+"', DIRECTOR  = '"+DIRECTOR+"' , URLS =  '"+URLS+"'";*/
		
			//ResultSet rs = preparedStatement.executeQuery(queryRs);
			
			int check = 0; 
			
			check = ps.executeUpdate();
					//stmt.executeUpdate(query);
			if(check > 0){
				wassavesuccess = true;
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	
	public static boolean insertandUpdateMovieSongObject(int MOVIE_NUMBER, 
			String SONGNAME, 
			String SINGERS, 
			int SONG_ID ,
			String SONGLINK_128KBPS,
			String SONGLINK_128KBPS_CONV, 
			String WORKING_LINK) throws SQLException{
		
		if(SONGNAME == null){
			SONGNAME = "NA";
		}
		if(SINGERS == null){
			SINGERS = "NA";
		}
		if(SONGLINK_128KBPS == null){
			SONGLINK_128KBPS = "NA";
		}
		if(SONGLINK_128KBPS_CONV == null){
			SONGLINK_128KBPS_CONV = "NA";
		}
		if(WORKING_LINK == null){
			WORKING_LINK = "NA";
		}
		
		
		
		boolean wassavesuccess = false;
		Connection dbConn = null;
		
		SONGNAME = SONGNAME.toUpperCase();
		SONGNAME = SONGNAME.replaceAll("MP3", "");
		SONGNAME = SONGNAME.replaceAll("SONGSMP3.COM", "");
		SONGNAME = SONGNAME.replaceAll("SONGS", "");
		
		SINGERS = SINGERS.toUpperCase();
		SINGERS = SINGERS.replaceAll("MP3", "");
		SINGERS = SINGERS.replaceAll("SONGSMP3.COM", "");
		SINGERS = SINGERS.replaceAll("SONGS", "");
		
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Statement stmt = dbConn.createStatement();
			
			String queryRs = "INSERT INTO moviesongs (MOVIE_NUMBER, SONG_ID, SONGNAME, SINGERS, SONGLINK_128KBPS,SONGLINK_128KBPS_CONV,WORKING_LINK) "
					+ " VALUES (?,?,?,?,?,?,?) "
					+ " ON DUPLICATE KEY UPDATE SONGNAME = ? , SINGERS = ? , SONGLINK_128KBPS = ? , SONGLINK_128KBPS_CONV = ? , WORKING_LINK = ? ";
			
			PreparedStatement ps = dbConn.prepareStatement(queryRs);
			ps.setInt(1, MOVIE_NUMBER);
			ps.setInt(2, SONG_ID);
			ps.setString(3, SONGNAME);
			ps.setString(4, SINGERS);
			ps.setString(5, SONGLINK_128KBPS);
			ps.setString(6, SONGLINK_128KBPS_CONV);
			ps.setString(7, WORKING_LINK);
			ps.setString(8, SONGNAME);
			ps.setString(9, SINGERS);
			ps.setString(10, SONGLINK_128KBPS);
			ps.setString(11, SONGLINK_128KBPS_CONV);
			ps.setString(12, WORKING_LINK);
			/*
			String query = 	"INSERT INTO moviesongs (MOVIE_NUMBER, SONG_ID, SONGNAME, SINGERS, SONGLINK_128KBPS) "
		+ " VALUES ('"+MOVIE_NUMBER+"' ,'"+SONG_ID+"','"+SONGNAME+"','"+SINGERS+"','"+SONGLINK_128KBPS+"') "
		+ " ON DUPLICATE KEY UPDATE SONGNAME = '"+SONGNAME+"' , SINGERS = '"+SINGERS+"' , SONGLINK_128KBPS = '"+SONGLINK_128KBPS+"'";
				*/
		
			int check = 0; 
			check = ps.executeUpdate();
					//stmt.executeUpdate(query);
			if(check > 0){
				wassavesuccess = true;
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	public static boolean insertandUpdateIndiPopSongObject(int MOVIE_NUMBER, String SONGNAME, String SINGERS, int SONG_ID ,String SONGLINK_128KBPS,String SONGLINK_128KBPS_CONV, String WORKING_LINK) throws SQLException{
		boolean wassavesuccess = false;
		
		if(SONGNAME == null){
			SONGNAME = "NA";
		}
		if(SINGERS == null){
			SINGERS = "NA";
		}
		if(SONGLINK_128KBPS == null){
			SONGLINK_128KBPS = "NA";
		}
		if(SONGLINK_128KBPS_CONV == null){
			SONGLINK_128KBPS_CONV = "NA";
		}
		if(WORKING_LINK == null){
			WORKING_LINK = "NA";
		}
		
		
		
		Connection dbConn = null;
		SONGNAME = SONGNAME.toUpperCase();
		SONGNAME = SONGNAME.replaceAll("MP3", "");
		SONGNAME = SONGNAME.replaceAll("SONGSMP3.COM", "");
		SONGNAME = SONGNAME.replaceAll("SONGS", "");
		
		SINGERS = SINGERS.toUpperCase();
		SINGERS = SINGERS.replaceAll("MP3", "");
		SINGERS = SINGERS.replaceAll("SONGSMP3.COM", "");
		SINGERS = SINGERS.replaceAll("SONGS", "");
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Statement stmt = dbConn.createStatement();
			
			String queryRs = "INSERT INTO indipopsongs (MOVIE_NUMBER, SONG_ID, SONGNAME, SINGERS, SONGLINK_128KBPS,SONGLINK_128KBPS_CONV,WORKING_LINK) "
					+ " VALUES (?,?,?,?,?,?,?) "
					+ " ON DUPLICATE KEY UPDATE SONGNAME = ? , SINGERS = ? , SONGLINK_128KBPS = ? , SONGLINK_128KBPS_CONV = ? , WORKING_LINK = ? ";
			
			PreparedStatement ps = dbConn.prepareStatement(queryRs);
			ps.setInt(1, MOVIE_NUMBER);
			ps.setInt(2, SONG_ID);
			ps.setString(3, SONGNAME);
			ps.setString(4, SINGERS);
			ps.setString(5, SONGLINK_128KBPS);
			ps.setString(6, SONGLINK_128KBPS_CONV);
			ps.setString(7, WORKING_LINK);
			ps.setString(8, SONGNAME);
			ps.setString(9, SINGERS);
			ps.setString(10, SONGLINK_128KBPS);
			ps.setString(11, SONGLINK_128KBPS_CONV);
			ps.setString(12, WORKING_LINK);
			/*
			String query = 	"INSERT INTO moviesongs (MOVIE_NUMBER, SONG_ID, SONGNAME, SINGERS, SONGLINK_128KBPS) "
		+ " VALUES ('"+MOVIE_NUMBER+"' ,'"+SONG_ID+"','"+SONGNAME+"','"+SINGERS+"','"+SONGLINK_128KBPS+"') "
		+ " ON DUPLICATE KEY UPDATE SONGNAME = '"+SONGNAME+"' , SINGERS = '"+SINGERS+"' , SONGLINK_128KBPS = '"+SONGLINK_128KBPS+"'";
				*/
		
			int check = 0; 
			check = ps.executeUpdate();
					//stmt.executeUpdate(query);
			if(check > 0){
				wassavesuccess = true;
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	public static boolean insertandUpdatePunjabiSongObject(int MOVIE_NUMBER, String SONGNAME, String SINGERS, int SONG_ID ,String SONGLINK_128KBPS,String SONGLINK_128KBPS_CONV, String WORKING_LINK) throws SQLException{
		boolean wassavesuccess = false;
		Connection dbConn = null;
		
		
		if(SONGNAME == null){
			SONGNAME = "NA";
		}
		if(SINGERS == null){
			SINGERS = "NA";
		}
		if(SONGLINK_128KBPS == null){
			SONGLINK_128KBPS = "NA";
		}
		if(SONGLINK_128KBPS_CONV == null){
			SONGLINK_128KBPS_CONV = "NA";
		}
		if(WORKING_LINK == null){
			WORKING_LINK = "NA";
		}
		
		
		
		SONGNAME = SONGNAME.toUpperCase();
		SONGNAME = SONGNAME.replaceAll("MP3", "");
		SONGNAME = SONGNAME.replaceAll("SONGSMP3.COM", "");
		SONGNAME = SONGNAME.replaceAll("SONGS", "");
		
		SINGERS = SINGERS.toUpperCase();
		SINGERS = SINGERS.replaceAll("MP3", "");
		SINGERS = SINGERS.replaceAll("SONGSMP3.COM", "");
		SINGERS = SINGERS.replaceAll("SONGS", "");
		
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//Statement stmt = dbConn.createStatement();
			
			String queryRs = "INSERT INTO punjabisongs (MOVIE_NUMBER, SONG_ID, SONGNAME, SINGERS, SONGLINK_128KBPS,SONGLINK_128KBPS_CONV,WORKING_LINK) "
					+ " VALUES (?,?,?,?,?,?,?) "
					+ " ON DUPLICATE KEY UPDATE SONGNAME = ? , SINGERS = ? , SONGLINK_128KBPS = ? , SONGLINK_128KBPS_CONV = ? , WORKING_LINK = ? ";
			
			PreparedStatement ps = dbConn.prepareStatement(queryRs);
			ps.setInt(1, MOVIE_NUMBER);
			ps.setInt(2, SONG_ID);
			ps.setString(3, SONGNAME);
			ps.setString(4, SINGERS);
			ps.setString(5, SONGLINK_128KBPS);
			ps.setString(6, SONGLINK_128KBPS_CONV);
			ps.setString(7, WORKING_LINK);
			ps.setString(8, SONGNAME);
			ps.setString(9, SINGERS);
			ps.setString(10, SONGLINK_128KBPS);
			ps.setString(11, SONGLINK_128KBPS_CONV);
			ps.setString(12, WORKING_LINK);
			/*
			String query = 	"INSERT INTO moviesongs (MOVIE_NUMBER, SONG_ID, SONGNAME, SINGERS, SONGLINK_128KBPS) "
		+ " VALUES ('"+MOVIE_NUMBER+"' ,'"+SONG_ID+"','"+SONGNAME+"','"+SINGERS+"','"+SONGLINK_128KBPS+"') "
		+ " ON DUPLICATE KEY UPDATE SONGNAME = '"+SONGNAME+"' , SINGERS = '"+SINGERS+"' , SONGLINK_128KBPS = '"+SONGLINK_128KBPS+"'";
				*/
		
			int check = 0; 
			check = ps.executeUpdate();
					//stmt.executeUpdate(query);
			if(check > 0){
				wassavesuccess = true;
			}
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		return wassavesuccess;
	}
	
	
	public static void getInsertSongData() throws Exception{
		
		String data = "INSERT INTO `moviesongs` (`MOVIE_NUMBER`, `SONGNAME`, `SINGERS`, `SONGLINK_128KBPS`, `SONG_ID`, `SONGLINK_128KBPS_CONV`, `WORKING_LINK`) VALUES ";
		
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		int MOVIE_NUMBER;
		String SONGNAME;
		String SINGERSSONGS;
		String SONGLINK_128KBPS;
		int SONG_ID;
		String SONGLINK_128KBPS_CONV;
		String WORKING_LINK;
		
		String sqlsongs= "SELECT S.MOVIE_NUMBER,S.SONGNAME,S.SINGERS,S.SONGLINK_128KBPS,S.SONG_ID,S.SONGLINK_128KBPS_CONV,S.WORKING_LINK from moviesongs as S where S.WORKING_LINK = '1'";
		PreparedStatement ps = dbConn.prepareStatement(sqlsongs);
		
		ResultSet rs = ps.executeQuery();
		
		StringBuffer buffer = new StringBuffer();
		buffer = buffer.append(data+"\r");
		while(rs.next())
		{
			String temp = "(";
			MOVIE_NUMBER = rs.getInt(1) ;
			
			SONGNAME = ", '"+rs.getString(2).replaceAll("[']", "")+"' , ";
			SINGERSSONGS = "'"+rs.getString(3).replaceAll("[']", "")+"' , ";
			SONGLINK_128KBPS = "'"+rs.getString(4).replaceAll("[']", "")+"' , ";
			SONG_ID = rs.getInt(5);
			SONGLINK_128KBPS_CONV = " , '"+rs.getString(6).replaceAll("[']", "")+"' , ";
			WORKING_LINK = "'"+rs.getString(7).replaceAll("[']", "")+"' ) , \r";
			temp = temp+ String.valueOf(MOVIE_NUMBER) + SONGNAME
					+ SINGERSSONGS + SONGLINK_128KBPS + String.valueOf(SONG_ID)+
					SONGLINK_128KBPS_CONV + WORKING_LINK ;
			
			buffer = buffer.append(temp);
			
		}
		String fName = "C:/Users/amitagarwal3/eclipseprojects/useraccount/src/com/prgguru/jersey/moviesongsexportdata.txt";
		File f = new File(fName);
		String dataFile = buffer.toString(); 
		DataOutputStream out = new DataOutputStream(new FileOutputStream(f));
		out.writeBytes(dataFile);
		out.close();
		
		}
		catch(Exception er){
			
		}
		
	}
	
	public static JSONObject getLatestMoviesandSongsJson() throws Exception
	{
		int curryear = Calendar.getInstance().get(Calendar.YEAR);
    	String thisyear = String.valueOf(curryear);
    	String lastyear = String.valueOf(curryear-1);
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from bollywoodmoviessongs as M where M.RELEASE_DATE in (?,?) order by M.MOVIENUMBER desc, M.RELEASE_DATE desc limit 30";
			String sqlsongs= "SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER from moviesongs as S where S.MOVIE_NUMBER = ? and S.WORKING_LINK = '1'";
			
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setString(1, thisyear);
			ps.setString(2, lastyear);
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();
			while(rs.next())
			{
				JSONObject mobj = new JSONObject();
				MOVIENUMBER = rs.getInt(1);
				MOVIESTARTCHAR = rs.getString(2);
				MOVIENAME = rs.getString(3);
				RELEASE_DATE = rs.getString(4);
				MUSIC_DIRECTOR = rs.getString(5);
				ACTORS = rs.getString(6);
				SINGERS = rs.getString(7);
				DIRECTOR = rs.getString(8);
				URLS = rs.getString(9);
				if(MOVIENUMBER == 0)
					continue;
				if(MOVIESTARTCHAR == null){
					MOVIESTARTCHAR = "NA";
				}
				if(MOVIENAME == null){
					MOVIENAME = "NA";
				}
				if(MUSIC_DIRECTOR == null){
					MUSIC_DIRECTOR = "NA";
				}
				if(ACTORS == null){
					ACTORS = "NA";
				}
				if(SINGERS == null){
					SINGERS = "NA";
				}
				if(DIRECTOR == null){
					DIRECTOR = "NA";
				}
				if(URLS == null){
					URLS = "NA";
				}
				
				mobj.put("mid", MOVIENUMBER);
				mobj.put("char", MOVIESTARTCHAR);
				mobj.put("mname", MOVIENAME);
				mobj.put("date", RELEASE_DATE);
				mobj.put("mdirector", MUSIC_DIRECTOR);
				mobj.put("actors", ACTORS);
				mobj.put("singers", SINGERS);
				mobj.put("director", DIRECTOR);
				mobj.put("url", URLS);
				
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					WORKING_LINK = rsSongs.getString(1);
					SONGLINK_128KBPS_CONV = rsSongs.getString(2);
					SONG_ID = rsSongs.getInt(3);
					SONGLINK_128KBPS = rsSongs.getString(4);
					SINGERSSONGS = rsSongs.getString(5);
					SONGNAME = rsSongs.getString(6);
					
					if(SONG_ID == 0)
						continue;
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(SINGERS == null){
						SINGERS = "NA";
					}
					if(SONGLINK_128KBPS == null){
						SONGLINK_128KBPS = "NA";
					}
					if(SONGLINK_128KBPS_CONV == null){
						SONGLINK_128KBPS_CONV = "NA";
					}
					if(WORKING_LINK == null){
						WORKING_LINK = "NA";
					}
					
					jobj.put("sid", SONG_ID);
					jobj.put("working", WORKING_LINK);
					jobj.put("workinglink", SONGLINK_128KBPS_CONV);
					jobj.put("orglink", SONGLINK_128KBPS);
					jobj.put("singers", SINGERSSONGS);
					jobj.put("sname", SONGNAME);
					ja.put(jobj);
				}
				mobj.put("msongs", ja);
				ma.put(mobj);
				
			}
			finalObj.put("movies", ma);
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	
	public static JSONObject getLatestPunjabiMoviesandSongsJson() throws Exception
	{
		int curryear = Calendar.getInstance().get(Calendar.YEAR);
    	String thisyear = String.valueOf(curryear);
    	String lastyear = String.valueOf(curryear-1);
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from punjabialbums as M order by M.MOVIENUMBER desc limit 30";
			String sqlsongs= "SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER from punjabisongs as S where S.MOVIE_NUMBER = ? and S.WORKING_LINK = '1'";
			
			PreparedStatement ps = dbConn.prepareStatement(sql);
			/*ps.setString(1, thisyear);
			ps.setString(2, lastyear);*/
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();
			while(rs.next())
			{
				JSONObject mobj = new JSONObject();
				MOVIENUMBER = rs.getInt(1);
				if(MOVIENUMBER == 0)
					continue;
				MOVIESTARTCHAR = rs.getString(2);
				MOVIENAME = rs.getString(3);
				RELEASE_DATE = rs.getString(4);
				MUSIC_DIRECTOR = rs.getString(5);
				ACTORS = rs.getString(6);
				SINGERS = rs.getString(7);
				DIRECTOR = rs.getString(8);
				URLS = rs.getString(9);
				
				if(MOVIESTARTCHAR == null){
					MOVIESTARTCHAR = "NA";
				}
				if(MOVIENAME == null){
					MOVIENAME = "NA";
				}
				if(MUSIC_DIRECTOR == null){
					MUSIC_DIRECTOR = "NA";
				}
				if(ACTORS == null){
					ACTORS = "NA";
				}
				if(SINGERS == null){
					SINGERS = "NA";
				}
				if(DIRECTOR == null){
					DIRECTOR = "NA";
				}
				if(URLS == null){
					URLS = "NA";
				}
				
				mobj.put("mid", MOVIENUMBER);
				mobj.put("char", MOVIESTARTCHAR);
				mobj.put("mname", MOVIENAME);
				mobj.put("date", RELEASE_DATE);
				mobj.put("mdirector", MUSIC_DIRECTOR);
				mobj.put("actors", ACTORS);
				mobj.put("singers", SINGERS);
				mobj.put("director", DIRECTOR);
				mobj.put("url", URLS);
				
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					WORKING_LINK = rsSongs.getString(1);
					SONGLINK_128KBPS_CONV = rsSongs.getString(2);
					SONG_ID = rsSongs.getInt(3);
					if(SONG_ID == 0)
						continue;
					SONGLINK_128KBPS = rsSongs.getString(4);
					SINGERSSONGS = rsSongs.getString(5);
					SONGNAME = rsSongs.getString(6);
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(SINGERS == null){
						SINGERS = "NA";
					}
					if(SONGLINK_128KBPS == null){
						SONGLINK_128KBPS = "NA";
					}
					if(SONGLINK_128KBPS_CONV == null){
						SONGLINK_128KBPS_CONV = "NA";
					}
					if(WORKING_LINK == null){
						WORKING_LINK = "NA";
					}
					
					jobj.put("sid", SONG_ID);
					jobj.put("working", WORKING_LINK);
					jobj.put("workinglink", SONGLINK_128KBPS_CONV);
					jobj.put("orglink", SONGLINK_128KBPS);
					jobj.put("singers", SINGERSSONGS);
					jobj.put("sname", SONGNAME);
					ja.put(jobj);
				}
				mobj.put("msongs", ja);
				ma.put(mobj);
				
			}
			finalObj.put("movies", ma);
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	public static JSONObject getLatestIndiPopSongsJson() throws Exception
	{
		int curryear = Calendar.getInstance().get(Calendar.YEAR);
    	String thisyear = String.valueOf(curryear);
    	String lastyear = String.valueOf(curryear-1);
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			//String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from bollywoodmoviessongs as M where M.RELEASE_DATE in (?,?) order by M.MOVIENUMBER desc, M.RELEASE_DATE desc limit 30";
			String sqlsongs="SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER,M.URLS  from indipopsongs as S LEFT JOIN bollywoodindipopsongs as M ON S.MOVIE_NUMBER = M.MOVIENUMBER order by S.SONG_ID desc"; 
					//"SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER from indipopsongs as S order by S.SONG_ID desc";
			
			/*PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setString(1, thisyear);
			ps.setString(2, lastyear);
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();*/
			
				JSONObject mobj = new JSONObject();
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				//psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					WORKING_LINK = rsSongs.getString(1);
					SONGLINK_128KBPS_CONV = rsSongs.getString(2);
					SONG_ID = rsSongs.getInt(3);
					if(SONG_ID == 0)
						continue;
					SONGLINK_128KBPS = rsSongs.getString(4);
					SINGERSSONGS = rsSongs.getString(5);
					SONGNAME = rsSongs.getString(6);
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(SINGERSSONGS == null){
						SINGERSSONGS = "NA";
					}
					if(SONGLINK_128KBPS == null){
						SONGLINK_128KBPS = "NA";
					}
					if(SONGLINK_128KBPS_CONV == null){
						SONGLINK_128KBPS_CONV = "NA";
					}
					if(WORKING_LINK == null){
						WORKING_LINK = "NA";
					}
					
					
					jobj.put("sid", SONG_ID);
					jobj.put("working", WORKING_LINK);
					jobj.put("workinglink", SONGLINK_128KBPS_CONV);
					jobj.put("orglink", SONGLINK_128KBPS);
					jobj.put("singers", SINGERSSONGS);
					jobj.put("sname", SONGNAME);
					if(rsSongs.getString(8) == null){
						jobj.put("urls", "NA");
					}
					else{
						jobj.put("urls", rsSongs.getString(8));
					}
					ja.put(jobj);
				}
				finalObj.put("msongs", ja);
				
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	
	public static JSONObject getLatestMovieLyricsJson() throws Exception
	{
		int curryear = Calendar.getInstance().get(Calendar.YEAR);
    	String thisyear = String.valueOf(curryear);
    	String lastyear = String.valueOf(curryear-1);
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String URLS;
			int ISLATEST;
			
			int SONG_ID;
			String SONGNAME;
			int MOVIE_NUMBER;
			String LYRICS;
			String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME,M.URLS,M.ISLATEST from lyricsmovies as M where M.ISLATEST = 1 limit 100";
			String sqlsongs= "SELECT S.SONG_ID,S.SONGNAME,S.MOVIE_NUMBER,S.LYRICS from songslyrics as S where S.MOVIE_NUMBER = ?" ;
			
			PreparedStatement ps = dbConn.prepareStatement(sql);
			/*ps.setString(1, thisyear);
			ps.setString(2, lastyear);*/
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();
			while(rs.next())
			{
				JSONObject mobj = new JSONObject();
				MOVIENUMBER = rs.getInt(1);
				if(MOVIENUMBER == 0)
					continue;
				MOVIESTARTCHAR = rs.getString(2);
				MOVIENAME = rs.getString(3);
				URLS = rs.getString(4);
				ISLATEST = rs.getInt(5);
				
				if(MOVIESTARTCHAR == null){
					MOVIESTARTCHAR = "NA";
				}
				if(MOVIENAME == null){
					MOVIENAME = "NA";
				}
				if(URLS == null){
					URLS = "NA";
				}
				
				mobj.put("mid", MOVIENUMBER);
				mobj.put("char", MOVIESTARTCHAR);
				mobj.put("mname", MOVIENAME);
				mobj.put("url", URLS);
				mobj.put("islatest", ISLATEST);
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					SONG_ID = rsSongs.getInt(1);
					if(SONG_ID == 0)
						continue;
					SONGNAME = rsSongs.getString(2);
					MOVIE_NUMBER = rsSongs.getInt(3);
					if(MOVIE_NUMBER == 0)
						continue;
					LYRICS = rsSongs.getString(4);
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(LYRICS == null){
						LYRICS = "NA";
					}
					
					
					
					jobj.put("sid", SONG_ID);
					jobj.put("sname", SONGNAME);
					jobj.put("mid", MOVIE_NUMBER);
					jobj.put("lyrics", LYRICS);
					ja.put(jobj);
				}
				mobj.put("msongs", ja);
				ma.put(mobj);
				
			}
			finalObj.put("movies", ma);
				
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	public static JSONObject getMovieObjectLyricsJson(String mname) throws Exception
	{
		int curryear = Calendar.getInstance().get(Calendar.YEAR);
    	String thisyear = String.valueOf(curryear);
    	String lastyear = String.valueOf(curryear-1);
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String URLS;
			int ISLATEST;
			
			int SONG_ID;
			String SONGNAME;
			int MOVIE_NUMBER;
			String LYRICS;
			String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME,M.URLS,M.ISLATEST from lyricsmovies as M where M.MOVIENAME LIKE ? limit 100";
			String sqlsongs= "SELECT S.SONG_ID,S.SONGNAME,S.MOVIE_NUMBER,S.LYRICS from songslyrics as S where S.MOVIE_NUMBER = ?" ;
			
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setString(1, "%"+mname+"%");
			//ps.setString(2, lastyear);*/
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();
			while(rs.next())
			{
				JSONObject mobj = new JSONObject();
				MOVIENUMBER = rs.getInt(1);
				if(MOVIENUMBER == 0)
					continue;
				MOVIESTARTCHAR = rs.getString(2);
				MOVIENAME = rs.getString(3);
				URLS = rs.getString(4);
				ISLATEST = rs.getInt(5);
				
				if(MOVIESTARTCHAR == null){
					MOVIESTARTCHAR = "NA";
				}
				if(MOVIENAME == null){
					MOVIENAME = "NA";
				}
				if(URLS == null){
					URLS = "NA";
				}
				
				mobj.put("mid", MOVIENUMBER);
				mobj.put("char", MOVIESTARTCHAR);
				mobj.put("mname", MOVIENAME);
				mobj.put("url", URLS);
				mobj.put("islatest", ISLATEST);
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					SONG_ID = rsSongs.getInt(1);
					if(SONG_ID == 0)
						continue;
					SONGNAME = rsSongs.getString(2);
					MOVIE_NUMBER = rsSongs.getInt(3);
					if(MOVIE_NUMBER == 0)
						continue;
					LYRICS = rsSongs.getString(4);
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(LYRICS == null){
						LYRICS = "NA";
					}
					
					jobj.put("sid", SONG_ID);
					jobj.put("sname", SONGNAME);
					jobj.put("mid", MOVIE_NUMBER);
					jobj.put("lyrics", LYRICS);
					ja.put(jobj);
				}
				mobj.put("msongs", ja);
				ma.put(mobj);
				
			}
			finalObj.put("movies", ma);
				
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	
	public static JSONObject getYearWiseMoviesandSongsJson(String year) throws Exception
	{
		
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from bollywoodmoviessongs as M where M.RELEASE_DATE in (?) order by M.MOVIESTARTCHAR ";
			String sqlsongs= "SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER from moviesongs as S where S.MOVIE_NUMBER = ? AND S.WORKING_LINK = '1'";
			
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setString(1, year);
			
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();
			while(rs.next())
			{
				JSONObject mobj = new JSONObject();
				MOVIENUMBER = rs.getInt(1);
				MOVIESTARTCHAR = rs.getString(2);
				MOVIENAME = rs.getString(3);
				RELEASE_DATE = rs.getString(4);
				MUSIC_DIRECTOR = rs.getString(5);
				ACTORS = rs.getString(6);
				SINGERS = rs.getString(7);
				DIRECTOR = rs.getString(8);
				URLS = rs.getString(9);
				
				if(MOVIENUMBER == 0)
					continue;
				if(MOVIESTARTCHAR == null){
					MOVIESTARTCHAR = "NA";
				}
				if(MOVIENAME == null){
					MOVIENAME = "NA";
				}
				if(MUSIC_DIRECTOR == null){
					MUSIC_DIRECTOR = "NA";
				}
				if(ACTORS == null){
					ACTORS = "NA";
				}
				if(SINGERS == null){
					SINGERS = "NA";
				}
				if(DIRECTOR == null){
					DIRECTOR = "NA";
				}
				if(URLS == null){
					URLS = "NA";
				}
				mobj.put("mid", MOVIENUMBER);
				mobj.put("char", MOVIESTARTCHAR);
				mobj.put("mname", MOVIENAME);
				mobj.put("date", RELEASE_DATE);
				mobj.put("mdirector", MUSIC_DIRECTOR);
				mobj.put("actors", ACTORS);
				mobj.put("singers", SINGERS);
				mobj.put("director", DIRECTOR);
				mobj.put("url", URLS);
				
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					WORKING_LINK = rsSongs.getString(1);
					SONGLINK_128KBPS_CONV = rsSongs.getString(2);
					SONG_ID = rsSongs.getInt(3);
					SONGLINK_128KBPS = rsSongs.getString(4);
					SINGERSSONGS = rsSongs.getString(5);
					SONGNAME = rsSongs.getString(6);
					
					if(SONG_ID == 0)
						continue;
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(SINGERS == null){
						SINGERS = "NA";
					}
					if(SONGLINK_128KBPS == null){
						SONGLINK_128KBPS = "NA";
					}
					if(SONGLINK_128KBPS_CONV == null){
						SONGLINK_128KBPS_CONV = "NA";
					}
					if(WORKING_LINK == null){
						WORKING_LINK = "NA";
					}
					
					jobj.put("sid", SONG_ID);
					jobj.put("working", WORKING_LINK);
					jobj.put("workinglink", SONGLINK_128KBPS_CONV);
					jobj.put("orglink", SONGLINK_128KBPS);
					jobj.put("singers", SINGERSSONGS);
					jobj.put("sname", SONGNAME);
					ja.put(jobj);
				}
				mobj.put("msongs", ja);
				ma.put(mobj);
				
			}
			finalObj.put("movies", ma);
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	
	public static JSONObject getPunjabiSong(String sname) throws Exception
	{
		
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			//String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from bollywoodmoviessongs as M where M.RELEASE_DATE in (?) order by M.MOVIESTARTCHAR ";
			//String sqlsongs= "SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER from punjabisongs as S where S.SONGNAME LIKE ? ";
			//String sqlsongs="SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER,M.MOVIENAME,M.URLS from punjabisongs as S, punjabialbums as M where S.SONGNAME LIKE ? AND S.MOVIE_NUMBER = M.MOVIENUMBER ORDER BY S.MOVIE_NUMBER desc";
			String sqlsongs="SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER,M.MOVIENAME,M.URLS from punjabisongs as S, punjabialbums as M where S.MOVIE_NUMBER = M.MOVIENUMBER AND (S.SONGNAME LIKE ? OR M.MOVIENAME LIKE ?) ORDER BY S.MOVIE_NUMBER desc";
			PreparedStatement ps = dbConn.prepareStatement(sqlsongs);
			ps.setString(1, "%"+sname+"%");
			ps.setString(2, "%"+sname+"%");
			
			ResultSet rsSongs = ps.executeQuery();
			
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					WORKING_LINK = rsSongs.getString(1);
					SONGLINK_128KBPS_CONV = rsSongs.getString(2);
					SONG_ID = rsSongs.getInt(3);
					SONGLINK_128KBPS = rsSongs.getString(4);
					SINGERSSONGS = rsSongs.getString(5);
					SONGNAME = rsSongs.getString(6);
					MOVIENAME = rsSongs.getString(8);
					URLS = rsSongs.getString(9);
					if(MOVIENAME == null){
						MOVIENAME = "NA";
					}
					
					if(URLS == null){
						URLS = "NA";
					}
					
					if(SONG_ID == 0)
						continue;
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(SINGERSSONGS == null){
						SINGERSSONGS = "NA";
					}
					if(SONGLINK_128KBPS == null){
						SONGLINK_128KBPS = "NA";
					}
					if(SONGLINK_128KBPS_CONV == null){
						SONGLINK_128KBPS_CONV = "NA";
					}
					if(WORKING_LINK == null){
						WORKING_LINK = "NA";
					}
					jobj.put("sid", SONG_ID);
					jobj.put("working", WORKING_LINK);
					jobj.put("workinglink", SONGLINK_128KBPS_CONV);
					jobj.put("orglink", SONGLINK_128KBPS);
					jobj.put("singers", SINGERSSONGS);
					jobj.put("sname", SONGNAME);
					jobj.put("mname", MOVIENAME);
					jobj.put("urls", URLS);
					ja.put(jobj);
				}
			finalObj.put("msongs", ja);
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	

	public static JSONObject getBollywoodSong(String sname) throws Exception
	{
		
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			//String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from bollywoodmoviessongs as M where M.RELEASE_DATE in (?) order by M.MOVIESTARTCHAR ";
			//String sqlsongs="SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER,M.MOVIENAME,M.URLS from moviesongs as S, bollywoodmoviessongs as M where S.SONGNAME LIKE ? AND S.MOVIE_NUMBER = M.MOVIENUMBER ORDER BY S.MOVIE_NUMBER desc"; 
					//"SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER from moviesongs as S where S.SONGNAME LIKE ? ";
			String sqlsongs="SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER,M.MOVIENAME,M.URLS from moviesongs as S, bollywoodmoviessongs as M where S.MOVIE_NUMBER = M.MOVIENUMBER AND ( S.SONGNAME LIKE ? OR M.MOVIENAME LIKE ?) ORDER BY S.MOVIE_NUMBER desc";
			PreparedStatement ps = dbConn.prepareStatement(sqlsongs);
			ps.setString(1, "%"+sname+"%");
			ps.setString(2, "%"+sname+"%");
			ResultSet rsSongs = ps.executeQuery();
			
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					WORKING_LINK = rsSongs.getString(1);
					SONGLINK_128KBPS_CONV = rsSongs.getString(2);
					SONG_ID = rsSongs.getInt(3);
					SONGLINK_128KBPS = rsSongs.getString(4);
					SINGERSSONGS = rsSongs.getString(5);
					SONGNAME = rsSongs.getString(6);
					MOVIENAME = rsSongs.getString(8);
					URLS = rsSongs.getString(9);
					
					
					if(MOVIENAME == null){
						MOVIENAME = "NA";
					}
					
					if(URLS == null){
						URLS = "NA";
					}
					
					if(SONG_ID == 0)
						continue;
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(SINGERSSONGS == null){
						SINGERSSONGS = "NA";
					}
					if(SONGLINK_128KBPS == null){
						SONGLINK_128KBPS = "NA";
					}
					if(SONGLINK_128KBPS_CONV == null){
						SONGLINK_128KBPS_CONV = "NA";
					}
					if(WORKING_LINK == null){
						WORKING_LINK = "NA";
					}
					
					jobj.put("sid", SONG_ID);
					jobj.put("working", WORKING_LINK);
					jobj.put("workinglink", SONGLINK_128KBPS_CONV);
					jobj.put("orglink", SONGLINK_128KBPS);
					jobj.put("singers", SINGERSSONGS);
					jobj.put("sname", SONGNAME);
					jobj.put("mname", MOVIENAME);
					jobj.put("urls", URLS);
					ja.put(jobj);
				}
			finalObj.put("msongs", ja);
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	
	public static JSONObject getBollywoodSongLyrics(String sname) throws Exception
	{
		
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			String LYRICS;
			//String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from bollywoodmoviessongs as M where M.RELEASE_DATE in (?) order by M.MOVIESTARTCHAR ";
			String sqlsongs= "SELECT S.SONG_ID,S.SONGNAME,S.MOVIE_NUMBER,S.LYRICS,M.MOVIENAME,M.URLS from songslyrics as S, lyricsmovies as M where S.SONGNAME LIKE ? and S.MOVIE_NUMBER = M.MOVIENUMBER";
			
			PreparedStatement ps = dbConn.prepareStatement(sqlsongs);
			ps.setString(1, "%"+sname+"%");
			
			ResultSet rsSongs = ps.executeQuery();
			
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					SONG_ID = rsSongs.getInt(1);
					SONGNAME = rsSongs.getString(2);
					MOVIE_NUMBER = rsSongs.getInt(3);
					LYRICS = rsSongs.getString(4);
					MOVIENAME = rsSongs.getString(5);
					URLS = rsSongs.getString(6);
					
					if(MOVIE_NUMBER == 0)
						continue;
					if(MOVIENAME == null){
						MOVIENAME = "NA";
					}
					if(URLS == null){
						URLS = "NA";
					}
					if(SONG_ID == 0)
						continue;
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(LYRICS == null){
						LYRICS = "NA";
					}
					jobj.put("sid", SONG_ID);
					jobj.put("sname", SONGNAME);
					jobj.put("mid", MOVIE_NUMBER);
					jobj.put("lyrics", LYRICS);
					jobj.put("mname", MOVIENAME);
					jobj.put("urls", URLS);
					ja.put(jobj);
				}
			finalObj.put("msongs", ja);
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	
	public static JSONObject getSCharWiseMoviesandSongsJson(String schar,String year) throws Exception
	{
		
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from bollywoodmoviessongs as M where  M.MOVIESTARTCHAR in (?) and M.RELEASE_DATE BETWEEN ? AND ? order by M.MOVIENUMBER desc,M.RELEASE_DATE desc ";
			String sqlsongs= "SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER from moviesongs as S where S.MOVIE_NUMBER = ? and S.WORKING_LINK = '1'";
			
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setString(1, schar);
			ps.setString(2, year);
			ps.setString(3, String.valueOf(Integer.parseInt(year)+9));
			
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();
			while(rs.next())
			{
				JSONObject mobj = new JSONObject();
				MOVIENUMBER = rs.getInt(1);
				MOVIESTARTCHAR = rs.getString(2);
				MOVIENAME = rs.getString(3);
				RELEASE_DATE = rs.getString(4);
				MUSIC_DIRECTOR = rs.getString(5);
				ACTORS = rs.getString(6);
				SINGERS = rs.getString(7);
				DIRECTOR = rs.getString(8);
				URLS = rs.getString(9);
				
				if(MOVIENUMBER == 0)
					continue;
				if(MOVIESTARTCHAR == null){
					MOVIESTARTCHAR = "NA";
				}
				if(MOVIENAME == null){
					MOVIENAME = "NA";
				}
				if(MUSIC_DIRECTOR == null){
					MUSIC_DIRECTOR = "NA";
				}
				if(ACTORS == null){
					ACTORS = "NA";
				}
				if(SINGERS == null){
					SINGERS = "NA";
				}
				if(DIRECTOR == null){
					DIRECTOR = "NA";
				}
				if(URLS == null){
					URLS = "NA";
				}
				
				mobj.put("mid", MOVIENUMBER);
				mobj.put("char", MOVIESTARTCHAR);
				mobj.put("mname", MOVIENAME);
				mobj.put("date", RELEASE_DATE);
				mobj.put("mdirector", MUSIC_DIRECTOR);
				mobj.put("actors", ACTORS);
				mobj.put("singers", SINGERS);
				mobj.put("director", DIRECTOR);
				mobj.put("url", URLS);
				
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					WORKING_LINK = rsSongs.getString(1);
					SONGLINK_128KBPS_CONV = rsSongs.getString(2);
					SONG_ID = rsSongs.getInt(3);
					SONGLINK_128KBPS = rsSongs.getString(4);
					SINGERSSONGS = rsSongs.getString(5);
					SONGNAME = rsSongs.getString(6);
					
					if(SONG_ID == 0)
						continue;
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(SINGERS == null){
						SINGERS = "NA";
					}
					if(SONGLINK_128KBPS == null){
						SONGLINK_128KBPS = "NA";
					}
					if(SONGLINK_128KBPS_CONV == null){
						SONGLINK_128KBPS_CONV = "NA";
					}
					if(WORKING_LINK == null){
						WORKING_LINK = "NA";
					}
					
					jobj.put("sid", SONG_ID);
					jobj.put("working", WORKING_LINK);
					jobj.put("workinglink", SONGLINK_128KBPS_CONV);
					jobj.put("orglink", SONGLINK_128KBPS);
					jobj.put("singers", SINGERSSONGS);
					jobj.put("sname", SONGNAME);
					ja.put(jobj);
				}
				mobj.put("msongs", ja);
				ma.put(mobj);
				
			}
			finalObj.put("movies", ma);
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	public static JSONObject getSCharWiseMoviesandSongsJson(String schar) throws Exception
	{
		
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from bollywoodmoviessongs as M where  M.MOVIESTARTCHAR in (?) order by M.MOVIENUMBER desc,M.RELEASE_DATE desc ";
			String sqlsongs= "SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER from moviesongs as S where S.MOVIE_NUMBER = ? and S.WORKING_LINK = '1'";
			
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setString(1, schar);
			
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();
			while(rs.next())
			{
				JSONObject mobj = new JSONObject();
				MOVIENUMBER = rs.getInt(1);
				MOVIESTARTCHAR = rs.getString(2);
				MOVIENAME = rs.getString(3);
				RELEASE_DATE = rs.getString(4);
				MUSIC_DIRECTOR = rs.getString(5);
				ACTORS = rs.getString(6);
				SINGERS = rs.getString(7);
				DIRECTOR = rs.getString(8);
				URLS = rs.getString(9);
				
				if(MOVIENUMBER == 0)
					continue;
				if(MOVIESTARTCHAR == null){
					MOVIESTARTCHAR = "NA";
				}
				if(MOVIENAME == null){
					MOVIENAME = "NA";
				}
				if(MUSIC_DIRECTOR == null){
					MUSIC_DIRECTOR = "NA";
				}
				if(ACTORS == null){
					ACTORS = "NA";
				}
				if(SINGERS == null){
					SINGERS = "NA";
				}
				if(DIRECTOR == null){
					DIRECTOR = "NA";
				}
				if(URLS == null){
					URLS = "NA";
				}
				
				
				mobj.put("mid", MOVIENUMBER);
				mobj.put("char", MOVIESTARTCHAR);
				mobj.put("mname", MOVIENAME);
				mobj.put("date", RELEASE_DATE);
				mobj.put("mdirector", MUSIC_DIRECTOR);
				mobj.put("actors", ACTORS);
				mobj.put("singers", SINGERS);
				mobj.put("director", DIRECTOR);
				mobj.put("url", URLS);
				
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					WORKING_LINK = rsSongs.getString(1);
					SONGLINK_128KBPS_CONV = rsSongs.getString(2);
					SONG_ID = rsSongs.getInt(3);
					SONGLINK_128KBPS = rsSongs.getString(4);
					SINGERSSONGS = rsSongs.getString(5);
					SONGNAME = rsSongs.getString(6);
					
					if(SONG_ID == 0)
						continue;
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(SINGERS == null){
						SINGERS = "NA";
					}
					if(SONGLINK_128KBPS == null){
						SONGLINK_128KBPS = "NA";
					}
					if(SONGLINK_128KBPS_CONV == null){
						SONGLINK_128KBPS_CONV = "NA";
					}
					if(WORKING_LINK == null){
						WORKING_LINK = "NA";
					}
					
					jobj.put("sid", SONG_ID);
					jobj.put("working", WORKING_LINK);
					jobj.put("workinglink", SONGLINK_128KBPS_CONV);
					jobj.put("orglink", SONGLINK_128KBPS);
					jobj.put("singers", SINGERSSONGS);
					jobj.put("sname", SONGNAME);
					ja.put(jobj);
				}
				mobj.put("msongs", ja);
				ma.put(mobj);
				
			}
			finalObj.put("movies", ma);
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	
	public static JSONObject getMovieSongObjectJson(String mname) throws Exception
	{
		
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from bollywoodmoviessongs as M where  M.MOVIENAME LIKE ? ";
			String sqlsongs= "SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER from moviesongs as S where S.MOVIE_NUMBER = ? and S.WORKING_LINK = '1'";
			
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setString(1, "%"+mname+"%");
			
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();
			while(rs.next())
			{
				JSONObject mobj = new JSONObject();
				MOVIENUMBER = rs.getInt(1);
				MOVIESTARTCHAR = rs.getString(2);
				MOVIENAME = rs.getString(3);
				RELEASE_DATE = rs.getString(4);
				MUSIC_DIRECTOR = rs.getString(5);
				ACTORS = rs.getString(6);
				SINGERS = rs.getString(7);
				DIRECTOR = rs.getString(8);
				URLS = rs.getString(9);
				
				if(MOVIENUMBER == 0)
					continue;
				if(MOVIESTARTCHAR == null){
					MOVIESTARTCHAR = "NA";
				}
				if(MOVIENAME == null){
					MOVIENAME = "NA";
				}
				if(MUSIC_DIRECTOR == null){
					MUSIC_DIRECTOR = "NA";
				}
				if(ACTORS == null){
					ACTORS = "NA";
				}
				if(SINGERS == null){
					SINGERS = "NA";
				}
				if(DIRECTOR == null){
					DIRECTOR = "NA";
				}
				if(URLS == null){
					URLS = "NA";
				}
				
				
				mobj.put("mid", MOVIENUMBER);
				mobj.put("char", MOVIESTARTCHAR);
				mobj.put("mname", MOVIENAME);
				mobj.put("date", RELEASE_DATE);
				mobj.put("mdirector", MUSIC_DIRECTOR);
				mobj.put("actors", ACTORS);
				mobj.put("singers", SINGERS);
				mobj.put("director", DIRECTOR);
				mobj.put("url", URLS);
				
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					WORKING_LINK = rsSongs.getString(1);
					SONGLINK_128KBPS_CONV = rsSongs.getString(2);
					SONG_ID = rsSongs.getInt(3);
					SONGLINK_128KBPS = rsSongs.getString(4);
					SINGERSSONGS = rsSongs.getString(5);
					SONGNAME = rsSongs.getString(6);
					
					
					if(SONG_ID == 0)
						continue;
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(SINGERS == null){
						SINGERS = "NA";
					}
					if(SONGLINK_128KBPS == null){
						SONGLINK_128KBPS = "NA";
					}
					if(SONGLINK_128KBPS_CONV == null){
						SONGLINK_128KBPS_CONV = "NA";
					}
					if(WORKING_LINK == null){
						WORKING_LINK = "NA";
					}
					
					jobj.put("sid", SONG_ID);
					jobj.put("working", WORKING_LINK);
					jobj.put("workinglink", SONGLINK_128KBPS_CONV);
					jobj.put("orglink", SONGLINK_128KBPS);
					jobj.put("singers", SINGERSSONGS);
					jobj.put("sname", SONGNAME);
					ja.put(jobj);
				}
				mobj.put("msongs", ja);
				ma.put(mobj);
				
			}
			finalObj.put("movies", ma);
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	
	
	public static JSONObject getSCharWisePunjabiMoviesandSongsJson(String schar,String year) throws Exception
	{
		
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String RELEASE_DATE;
			String MUSIC_DIRECTOR;
			String ACTORS;
			String SINGERS;
			String DIRECTOR;
			String URLS;
			String WORKING_LINK;
			String SONGLINK_128KBPS_CONV;
			int SONG_ID;
			String SONGLINK_128KBPS;
			String SINGERSSONGS;
			String SONGNAME;
			int MOVIE_NUMBER;
			String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME, M.RELEASE_DATE, M.MUSIC_DIRECTOR, M.ACTORS,M.SINGERS,M.DIRECTOR,M.URLS from punjabialbums as M where  M.MOVIESTARTCHAR in (?) order by M.MOVIENUMBER desc ";
			String sqlsongs= "SELECT S.WORKING_LINK,S.SONGLINK_128KBPS_CONV,S.SONG_ID,S.SONGLINK_128KBPS,S.SINGERS,S.SONGNAME,S.MOVIE_NUMBER from punjabisongs as S where S.MOVIE_NUMBER = ? and S.WORKING_LINK = '1'";
			
			PreparedStatement ps = dbConn.prepareStatement(sql);
			ps.setString(1, schar);
			/*ps.setString(2, year);
			ps.setString(3, String.valueOf(Integer.parseInt(year)+9));*/
			
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();
			while(rs.next())
			{
				JSONObject mobj = new JSONObject();
				MOVIENUMBER = rs.getInt(1);
				MOVIESTARTCHAR = rs.getString(2);
				MOVIENAME = rs.getString(3);
				RELEASE_DATE = rs.getString(4);
				MUSIC_DIRECTOR = rs.getString(5);
				ACTORS = rs.getString(6);
				SINGERS = rs.getString(7);
				DIRECTOR = rs.getString(8);
				URLS = rs.getString(9);
				
				if(MOVIENUMBER == 0)
					continue;
				if(MOVIESTARTCHAR == null){
					MOVIESTARTCHAR = "NA";
				}
				if(MOVIENAME == null){
					MOVIENAME = "NA";
				}
				if(MUSIC_DIRECTOR == null){
					MUSIC_DIRECTOR = "NA";
				}
				if(ACTORS == null){
					ACTORS = "NA";
				}
				if(SINGERS == null){
					SINGERS = "NA";
				}
				if(DIRECTOR == null){
					DIRECTOR = "NA";
				}
				if(URLS == null){
					URLS = "NA";
				}
				
				
				mobj.put("mid", MOVIENUMBER);
				mobj.put("char", MOVIESTARTCHAR);
				mobj.put("mname", MOVIENAME);
				mobj.put("date", RELEASE_DATE);
				mobj.put("mdirector", MUSIC_DIRECTOR);
				mobj.put("actors", ACTORS);
				mobj.put("singers", SINGERS);
				mobj.put("director", DIRECTOR);
				mobj.put("url", URLS);
				
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					WORKING_LINK = rsSongs.getString(1);
					SONGLINK_128KBPS_CONV = rsSongs.getString(2);
					SONG_ID = rsSongs.getInt(3);
					SONGLINK_128KBPS = rsSongs.getString(4);
					SINGERSSONGS = rsSongs.getString(5);
					SONGNAME = rsSongs.getString(6);
					
					
					if(SONG_ID == 0)
						continue;
					
					if(SONGNAME == null){
						SONGNAME = "NA";
					}
					if(SINGERS == null){
						SINGERS = "NA";
					}
					if(SONGLINK_128KBPS == null){
						SONGLINK_128KBPS = "NA";
					}
					if(SONGLINK_128KBPS_CONV == null){
						SONGLINK_128KBPS_CONV = "NA";
					}
					if(WORKING_LINK == null){
						WORKING_LINK = "NA";
					}
					
					jobj.put("sid", SONG_ID);
					jobj.put("working", WORKING_LINK);
					jobj.put("workinglink", SONGLINK_128KBPS_CONV);
					jobj.put("orglink", SONGLINK_128KBPS);
					jobj.put("singers", SINGERSSONGS);
					jobj.put("sname", SONGNAME);
					ja.put(jobj);
				}
				mobj.put("msongs", ja);
				ma.put(mobj);
				
			}
			finalObj.put("movies", ma);
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	public static JSONObject getSCharWiseLyricsJson(String schar) throws Exception
	{
		
		int curryear = Calendar.getInstance().get(Calendar.YEAR);
    	String thisyear = String.valueOf(curryear);
    	String lastyear = String.valueOf(curryear-1);
		JSONObject finalObj = new JSONObject();
		Connection dbConn = null;
		try {
			try {
				dbConn = DBConnection.createConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int MOVIENUMBER;
			String MOVIESTARTCHAR;
			String MOVIENAME;
			String URLS;
			int ISLATEST;
			
			int SONG_ID;
			String SONGNAME;
			int MOVIE_NUMBER;
			String LYRICS;
			String sql = "select M.MOVIENUMBER, M.MOVIESTARTCHAR , M.MOVIENAME,M.URLS,M.ISLATEST from lyricsmovies as M where M.MOVIESTARTCHAR = '"+schar+"' limit 1000";
			String sqlsongs= "SELECT S.SONG_ID,S.SONGNAME,S.MOVIE_NUMBER,S.LYRICS from songslyrics as S where S.MOVIE_NUMBER = ?" ;
			
			PreparedStatement ps = dbConn.prepareStatement(sql);
			/*ps.setString(1, thisyear);
			ps.setString(2, lastyear);*/
			ResultSet rs = ps.executeQuery();
			
			JSONArray ma = new JSONArray();
			while(rs.next())
			{
				JSONObject mobj = new JSONObject();
				MOVIENUMBER = rs.getInt(1);
				MOVIESTARTCHAR = rs.getString(2);
				MOVIENAME = rs.getString(3);
				URLS = rs.getString(4);
				ISLATEST = rs.getInt(5);
				
				
				mobj.put("mid", MOVIENUMBER);
				mobj.put("char", MOVIESTARTCHAR);
				mobj.put("mname", MOVIENAME);
				mobj.put("url", URLS);
				mobj.put("islatest", ISLATEST);
				PreparedStatement psSongs = dbConn.prepareStatement(sqlsongs);
				psSongs.setInt(1, MOVIENUMBER);
				ResultSet rsSongs = psSongs.executeQuery();
				JSONArray ja = new JSONArray();
				while(rsSongs.next())
				{
					JSONObject jobj = new JSONObject();
					SONG_ID = rsSongs.getInt(1);
					SONGNAME = rsSongs.getString(2);
					MOVIE_NUMBER = rsSongs.getInt(3);
					LYRICS = rsSongs.getString(4);
					jobj.put("sid", SONG_ID);
					jobj.put("sname", SONGNAME);
					jobj.put("mid", MOVIE_NUMBER);
					jobj.put("lyrics", LYRICS);
					ja.put(jobj);
				}
				mobj.put("msongs", ja);
				ma.put(mobj);
				
			}
			finalObj.put("movies", ma);
				
			
		} 
		catch (SQLException sqle) {
			throw sqle;
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			if (dbConn != null) {
				dbConn.close();
			}
			throw e;
		} finally {
			if (dbConn != null) {
				dbConn.close();
			}
		}
		
		
		return finalObj;
	}
	
	
}
