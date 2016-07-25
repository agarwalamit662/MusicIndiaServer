package com.prgguru.jersey;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class Utility {
	/**
	 * Null check Method
	 * 
	 * @param txt
	 * @return
	 */
	public static boolean isNotNull(String txt) {
		// System.out.println("Inside isNotNull");
		return txt != null && txt.trim().length() >= 0 ? true : false;
	}

	/**
	 * Method to construct JSON
	 * 
	 * @param tag
	 * @param status
	 * @return
	 */
	public static String constructJSON(String tag, boolean status) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString();
	}
	public static String constructLatestSongJSON(String tag, boolean status) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getLatestMoviesandSongsJson();
				
		return finalObjMovies.toString();
	}
	
	public static String constructLatestPunjabiSongJSON(String tag, boolean status) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getLatestPunjabiMoviesandSongsJson();
				
		return finalObjMovies.toString();
	}
	
	
	public static String constructLatestIndiSongJSON(String tag, boolean status) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getLatestIndiPopSongsJson();
				
		return finalObjMovies.toString();
	}
	
	public static String constructLatestMovieLyricsJSON(String tag, boolean status) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getLatestMovieLyricsJson();
				
		return finalObjMovies.toString();
	}
	
	public static String getMovieLyricsObject(String mname, boolean status) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getMovieObjectLyricsJson(mname);
				
		return finalObjMovies.toString();
	}
	
	
	
	public static String constructYearWiseSongJSON(String year) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getYearWiseMoviesandSongsJson(year);
				
		return finalObjMovies.toString();
	}
	
	public static String findBollywoodSong(String sname) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getBollywoodSong(sname);
				
		return finalObjMovies.toString();
	}
	
	public static String findBollywoodSongLyrics(String sname) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getBollywoodSongLyrics(sname);
				
		return finalObjMovies.toString();
	}
	
	public static String findPunjabiSong(String sname) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getPunjabiSong(sname);
				
		return finalObjMovies.toString();
	}
	
	public static String constructSCharWiseSongJSON(String schar,String year) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getSCharWiseMoviesandSongsJson(schar,year);
				
		return finalObjMovies.toString();
	}
	
	public static String constructSCharWiseSongJSON(String schar) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getSCharWiseMoviesandSongsJson(schar);
				
		return finalObjMovies.toString();
	}
	
	public static String constructMovieSongJson(String mname) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getMovieSongObjectJson(mname);
				
		return finalObjMovies.toString();
	}
	
	public static String constructSCharWisePunjabiSongJSON(String schar,String year) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getSCharWisePunjabiMoviesandSongsJson(schar,year);
				
		return finalObjMovies.toString();
	}
	
	public static String constructSCharWiseLyricsJSON(String schar) throws Exception{
		
		JSONObject finalObjMovies = DBConnection.getSCharWiseLyricsJson(schar);
				
		return finalObjMovies.toString();
	}

	/**
	 * Method to construct JSON with Error Msg
	 * 
	 * @param tag
	 * @param status
	 * @param err_msg
	 * @return
	 */
	public static String constructJSON(String tag, boolean status,String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("error_msg", err_msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString(); 
	}
	
}
