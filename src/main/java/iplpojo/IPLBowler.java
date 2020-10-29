package iplpojo;

import com.opencsv.bean.CsvBindByName;

public class IPLBowler {
	
	@CsvBindByName(column = "POS", required = true)
	private int pos;
	@CsvBindByName(column = "PLAYER", required = true)
	private String playerName;
	@CsvBindByName(column = "Avg", required = true)
	private String average;
	@CsvBindByName(column = "Mat", required = true)
	private int matches;
	@CsvBindByName(column = "Inns", required = true)
	private int innings;
	@CsvBindByName(column = "Ov", required = true)
	private double overs;
	@CsvBindByName(column = "Runs", required = true)
	private int runs;
	@CsvBindByName(column = "Wkts", required = true)
	private int wickets;
	@CsvBindByName(column = "BBI", required = true)
	private int bestBowlingInInnings;
	@CsvBindByName(column = "SR", required = true)
	private String strikeRate;
	@CsvBindByName(column = "Econ", required = true)
	private double economy;
	@CsvBindByName(column = "4w", required = true)
	private int fourWickets;
	@CsvBindByName(column = "5w", required = true)
	private int fiveWickets;
	
	public int getPos() {
		return pos;
	}
	public String getPlayerName() {
		return playerName;
	}
	public String getAverage() {
		if(average.equals("-")) {
			average = "0.0";
		}
		return average;
	}
	public int getMatches() {
		return matches;
	}
	public int getInnings() {
		return innings;
	}
	public double getOvers() {
		return overs;
	}
	public int getRuns() {
		return runs;
	}
	public int getWickets() {
		return wickets;
	}
	public int getBestBowlingInInnings() {
		return bestBowlingInInnings;
	}
	public String getStrikeRate() {
		if(strikeRate.equals("-")) {
			strikeRate = "0.0";
		}
		return strikeRate;
	}
	public double getEconomy() {
		return economy;
	}
	public int getFourWickets() {
		return fourWickets;
	}
	public int getFiveWickets() {
		return fiveWickets;
	}
}
