package iplpojo;

import com.opencsv.bean.CsvBindByName;

public class IPLBatsman {

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
	@CsvBindByName(column = "NO", required = true)
	private int noBalls;
	@CsvBindByName(column = "Runs", required = true)
	private int runs;
	@CsvBindByName(column = "HS", required = true)
	private String highestScore;
	@CsvBindByName(column = "BF", required = true)
	private int ballsPlayes;
	@CsvBindByName(column = "SR", required = true)
	private double strikeRate;
	@CsvBindByName(column = "100", required = true)
	private int hundreds;
	@CsvBindByName(column = "50", required = true)
	private int fifties;
	@CsvBindByName(column = "4s", required = true)
	private int fours;
	@CsvBindByName(column = "6s", required = true)
	private int sixes;
	
	public String getPlayerName() {
		return playerName;
	}
	
	public String getAverage() {
		if(average.equals("-")) {
			average = "0.0";
		}
		return average;
	}
	
	public int getPos() {
		return pos;
	}
	
	public int getMatches() {
		return matches;
	}
	
	public int getInnings() {
		return innings;
	}
	
	public int getNoBalls() {
		return noBalls;
	}
	
	public int getRuns() {
		return runs;
	}
	
	public String getHighestScore() {
		return highestScore;
	}
	
	public int getBallsPlayes() {
		return ballsPlayes;
	}
	
	public double getStrikeRate() {
		return strikeRate;
	}
	
	public int getHundreds() {
		return hundreds;
	}
	
	public int getFifties() {
		return fifties;
	}
	
	public int getFours() {
		return fours;
	}
	
	public int getSixes() {
		return sixes;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((average == null) ? 0 : average.hashCode());
		result = prime * result + ballsPlayes;
		result = prime * result + fifties;
		result = prime * result + fours;
		result = prime * result + ((highestScore == null) ? 0 : highestScore.hashCode());
		result = prime * result + hundreds;
		result = prime * result + innings;
		result = prime * result + matches;
		result = prime * result + noBalls;
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		result = prime * result + pos;
		result = prime * result + runs;
		result = prime * result + sixes;
		long temp;
		temp = Double.doubleToLongBits(strikeRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IPLBatsman other = (IPLBatsman) obj;
		if (average == null) {
			if (other.average != null)
				return false;
		} else if (!average.equals(other.average))
			return false;
		if (ballsPlayes != other.ballsPlayes)
			return false;
		if (fifties != other.fifties)
			return false;
		if (fours != other.fours)
			return false;
		if (highestScore == null) {
			if (other.highestScore != null)
				return false;
		} else if (!highestScore.equals(other.highestScore))
			return false;
		if (hundreds != other.hundreds)
			return false;
		if (innings != other.innings)
			return false;
		if (matches != other.matches)
			return false;
		if (noBalls != other.noBalls)
			return false;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} else if (!playerName.equals(other.playerName))
			return false;
		if (pos != other.pos)
			return false;
		if (runs != other.runs)
			return false;
		if (sixes != other.sixes)
			return false;
		if (Double.doubleToLongBits(strikeRate) != Double.doubleToLongBits(other.strikeRate))
			return false;
		return true;
	}
	
	public double performanceFactor() {
		return sixes * 0.5 + fours * 0.3 + strikeRate * 0.2;
	}
}
