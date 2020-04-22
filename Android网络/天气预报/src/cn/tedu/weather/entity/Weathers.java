package cn.tedu.weather.entity;

import java.util.Date;

public class Weathers {
	private Date date;
	private String weather;
	private int temperature;
	private String wind;
	
	public Weathers(Date date, String weather, int temperature, String wind) {
		super();
		this.date = date;
		this.weather = weather;
		this.temperature = temperature;
		this.wind = wind;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}
}
