package com.prgguru.jersey;

public class SongsLyrics {

	
	public MOVIELYRICS movie;
	public String SONGNAME;
	public int SONG_ID;
	public String LYRICS;
	

	


	public MOVIELYRICS getMovie() {
		return this.movie;
	}


	public void setMovie(MOVIELYRICS movie) {
		this.movie = movie;
	}


	public String getLYRICS() {
		return LYRICS;
	}


	public void setLYRICS(String lYRICS) {
		LYRICS = lYRICS;
	}


	public String getSONGNAME() {
		return this.SONGNAME;
	}


	public void setSONGNAME(String sONGNAME) {
		this.SONGNAME = sONGNAME;
	}





	


	public int getSONG_ID() {
		return this.SONG_ID;
	}


	public void setSONG_ID(int sONG_ID) {
		this.SONG_ID = sONG_ID;
	}


	public SongsLyrics() {
		// TODO Auto-generated constructor stub
	}


	public SongsLyrics(MOVIELYRICS movie, String sONGNAME, int sONG_ID,String LYRICS) {
		super();
		this.movie = movie;
		this.SONGNAME = sONGNAME;
		this.SONG_ID = sONG_ID;
		this.LYRICS = LYRICS;
	}
	
	

}
