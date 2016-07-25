
package com.prgguru.jersey;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;
//Path: http://localhost/<appln-folder-name>/latestsongs
@Path("/songs")
public class LatestSongs {
	
	@GET
	@Path("/latestbollywood")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLatestBollywood()
	{
		try {
			return Utility.constructLatestSongJSON("latestSongs", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/device")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDeviceId(@QueryParam("did") String did)
	{
		String msg = "";
		try {
			if(DBConnection.insertDeviceId(did)){
				JSONObject obj = new JSONObject();
				obj.put("success", true);
				msg = obj.toString();
			}
			else{
				JSONObject obj = new JSONObject();
				obj.put("success", false);
				msg = obj.toString();
			}
			return msg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/totalinstalls")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTotalInstalls()
	{
		String msg = "";
		try {
			int count = DBConnection.getTotalInstalls();
			JSONObject obj = new JSONObject();
			obj.put("totalinstall", count);
			msg = obj.toString();
			return msg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/sharelink")
	@Produces(MediaType.APPLICATION_JSON)
	public String getShareAbleLink()
	{
		String msg = "";
		try {
			String link = DBConnection.getShareableLink();
			JSONObject obj = new JSONObject();
			obj.put("link", link);
			msg = obj.toString();
			return msg;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/updatelink")
	@Produces(MediaType.APPLICATION_JSON)
	public String getUpdateLink()
	{
		String msg = "";
		try {
			String link = DBConnection.getUpdateLink();
			
			return link;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	@GET
	@Path("/latestindi")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLatestIndiBollywood()
	{
		try {
			return Utility.constructLatestIndiSongJSON("latestIndiSongs", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GET
	@Path("/latestmovielyrics")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLatestBollywoodLyrics()
	{
		try {
			return Utility.constructLatestMovieLyricsJSON("latestIndiSongs", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GET
	@Path("/movielyrics")
	@Produces(MediaType.APPLICATION_JSON)
	public String movielyrics(@QueryParam("mname") String mname)
	{
		try {
			return Utility.getMovieLyricsObject(mname, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GET
	@Path("/latestpunjabi")
	@Produces(MediaType.APPLICATION_JSON)
	public String getLatestPunjabiBollywood()
	{
		try {
			return Utility.constructLatestPunjabiSongJSON("latestPunjabiSongs", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/bollywoodyearwise")
	@Produces(MediaType.APPLICATION_JSON)
	public String bollywoodyearwise(@QueryParam("year") String year)
	{
		try {
			return Utility.constructYearWiseSongJSON(year);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GET
	@Path("/bollywoodsearchsong")
	@Produces(MediaType.APPLICATION_JSON)
	public String bollywoodsearchsong(@QueryParam("sname") String sname)
	{
		try {
			return Utility.findBollywoodSong(sname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GET
	@Path("/bollywoodsearchsonglyrics")
	@Produces(MediaType.APPLICATION_JSON)
	public String bollywoodsearchsonglyrics(@QueryParam("sname") String sname)
	{
		try {
			return Utility.findBollywoodSongLyrics(sname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/punjabisearchsong")
	@Produces(MediaType.APPLICATION_JSON)
	public String punjabisearchsong(@QueryParam("sname") String sname)
	{
		try {
			return Utility.findPunjabiSong(sname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GET
	@Path("/bollywoodcharwiseyear")
	@Produces(MediaType.APPLICATION_JSON)
	public String bollywoodcharwiseyear(@QueryParam("schar") String schar,@QueryParam("year") String year)
	{
		try {
			return Utility.constructSCharWiseSongJSON(schar,year);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/bollywoodstartchar")
	@Produces(MediaType.APPLICATION_JSON)
	public String bollywoodstartchar(@QueryParam("schar") String schar)
	{
		try {
			return Utility.constructSCharWiseSongJSON(schar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@GET
	@Path("/bollywoodmovie")
	@Produces(MediaType.APPLICATION_JSON)
	public String bollywoodmovie(@QueryParam("mname") String mname)
	{
		try {
			return Utility.constructMovieSongJson(mname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/punjabistartchar")
	@Produces(MediaType.APPLICATION_JSON)
	public String punjabistartchar(@QueryParam("schar") String schar)
	{
		try {
			return Utility.constructSCharWisePunjabiSongJSON(schar,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GET
	@Path("/charwiseLyrics")
	@Produces(MediaType.APPLICATION_JSON)
	public String charWiseLyrics(@QueryParam("schar") String schar)
	{
		try {
			return Utility.constructSCharWiseLyricsJSON(schar);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	// HTTP Get Method
	@GET 
	// Path: http://localhost/<appln-folder-name>/register/doregister
	@Path("/doregister")  
	// Produces JSON as response
	@Produces(MediaType.APPLICATION_JSON) 
	// Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&username=abc&password=xyz
	public String doLogin(@QueryParam("name") String name, @QueryParam("username") String uname, @QueryParam("password") String pwd){
		String response = "";
		//System.out.println("Inside doLogin "+uname+"  "+pwd);
		int retCode = registerUser(name, uname, pwd);
		System.out.println("Hello World");
		if(retCode == 0){
			response = Utility.constructJSON("register",true);
		}else if(retCode == 1){
			response = Utility.constructJSON("register",false, "You are already registered");
		}else if(retCode == 2){
			response = Utility.constructJSON("register",false, "Special Characters are not allowed in Username and Password");
		}else if(retCode == 3){
			response = Utility.constructJSON("register",false, "Error occured");
		}
		return response;
				
	}
	
	private int registerUser(String name, String uname, String pwd){
		System.out.println("Inside checkCredentials");
		int result = 3;
		if(Utility.isNotNull(uname) && Utility.isNotNull(pwd)){
			System.out.println("Hello world 1");
			try {
				System.out.println("hello world 2");
				if(DBConnection.insertUser(name, uname, pwd)){
					System.out.println("RegisterUSer if");
					result = 0;
				}
			} catch(SQLException sqle){
				System.out.println("RegisterUSer catch sqle");
				//When Primary key violation occurs that means user is already registered
				if(sqle.getErrorCode() == 1062){
					result = 1;
				} 
				//When special characters are used in name,username or password
				else if(sqle.getErrorCode() == 1064){
					System.out.println(sqle.getErrorCode());
					result = 2;
				}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Inside checkCredentials catch e ");
				result = 3;
			}
		}else{
			System.out.println("Inside checkCredentials else");
			result = 3;
		}
			
		return result;
	}
	
}
