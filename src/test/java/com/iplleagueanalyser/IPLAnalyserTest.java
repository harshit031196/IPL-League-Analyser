package com.iplleagueanalyser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exception.IPLAnalyserException;
import opencsvbuilder.CSVException;

public class IPLAnalyserTest {
	private static final String IPL_BATSMAN_FILE_PATH = "WP DP Data_01 IPL2019FactsheetMostRuns - WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private static final String IPL_BOWLER_FILE_PATH = "WP DP Data_02 IPL2019FactsheetMostWkts - WP DP Data_02 IPL2019FactsheetMostWkts.csv";

	private IPLAnalyser iplAnalyser;
	
	@Before
	public void setup() {
		iplAnalyser = new IPLAnalyser();
		try {
			iplAnalyser.loadBatsmanData(IPL_BATSMAN_FILE_PATH);
			iplAnalyser.loadBowlerData(IPL_BOWLER_FILE_PATH);
		} catch (CSVException e) {
		}
	}
	
	@Test
	public void givenAFileWithBatsmanData_WhenLoaded_ShouldReturnTheEntries() {
		iplAnalyser = new IPLAnalyser();
		try {
			int entries = iplAnalyser.loadBatsmanData(IPL_BATSMAN_FILE_PATH);
			assertEquals(100, entries);
		} catch (CSVException e) {}
	}
	
	@Test
	public void givenAFileWithBowlerData_WhenLoaded_ShouldReturnTheEntries() {
		iplAnalyser = new IPLAnalyser();
		try {
			int entries = iplAnalyser.loadBowlerData(IPL_BOWLER_FILE_PATH);
			assertEquals(99, entries);
		} catch (CSVException e) {}
	}
	
	@Test
	public void givenAFileWhenLoadedToGetTopBattingAverages_ShouldReturnCorrectResult() {
		try {
			List<Double> list = iplAnalyser.getTopBattingAverages(3);
			assertEquals(83.2, list.get(0), 0.0);
		} catch (IPLAnalyserException e) {
		}
	}
}