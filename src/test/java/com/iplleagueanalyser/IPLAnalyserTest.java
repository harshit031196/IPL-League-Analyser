package com.iplleagueanalyser;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exception.IPLAnalyserException;
import iplpojo.IPLBatsman;
import iplpojo.IPLBowler;
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
public void givenCSVBatsmenFileWithBatsmanData_WhenLoaded_ShouldReturnTheEntries() {
	iplAnalyser = new IPLAnalyser();
	try {
		int entries = iplAnalyser.loadBatsmanData(IPL_BATSMAN_FILE_PATH);
		assertEquals(100, entries);
	} catch (CSVException e) {}
}

@Test
public void givenCSVBowlerFileWithBowlerData_WhenLoaded_ShouldReturnTheEntries() {
	iplAnalyser = new IPLAnalyser();
	try {
		int entries = iplAnalyser.loadBowlerData(IPL_BOWLER_FILE_PATH);
		assertEquals(99, entries);
	} catch (CSVException e) {}
}

@Test
public void givenCSVBatsmenFileWhenLoadedToGetTopBattingAverages_ShouldReturnCorrectResult() {
	try {
		List<Double> list = iplAnalyser.getTopBattingAverages(3);
		assertEquals(83.2, list.get(0), 0.0);
	} catch (IPLAnalyserException e) {}
}

@Test
public void givenCSVBatsmenFileWhenLoadedToGetTopStrikeRatesOfTheBatsman_ShouldReturnCorrectResult() {
	try {
		List<Double> list = iplAnalyser.getTopStrikeRates(3);
		assertEquals(333.33, list.get(0), 0.0);
	} catch (IPLAnalyserException e) {}
}

@Test
public void givenCSVBatsmenFileWhenLoadedToGetTopBatsmenWithMaximumSixes_ShouldReturnCorrectResult() {
	try {
		List<IPLBatsman> topSixHitters = iplAnalyser.getBatsmenWithMaximumSixes(3);
		assertEquals("Andre Russell", topSixHitters.get(0).getPlayerName());
	} catch (IPLAnalyserException e) {}
}

@Test
public void givenCSVBatsmenFileWhenLoadedToGetTopBatsmenWithMaximumFours_ShouldReturnCorrectResult() {
	try {
		List<IPLBatsman> topFourHitters = iplAnalyser.getBatsmenWithMaximumFours(3);
		assertEquals("Shikhar Dhawan", topFourHitters.get(0).getPlayerName());
	} catch (IPLAnalyserException e) {}
}

@Test
public void givenCSVBatsmenFileWhenLoadedToGetTopBatsmenWithBestStrikeRateAndMaximumFoursAndSixes_ShouldReturnCorrectResult() {
	try {
		List<IPLBatsman> topStrikeRateWithMaximumBoundaries = iplAnalyser
				.getBatsmenWithBesStrikeRatesAndMaximumBoundaries(3);
		assertEquals("Andre Russell", topStrikeRateWithMaximumBoundaries.get(0).getPlayerName());
	} catch (IPLAnalyserException e) {}
}

@Test
public void givenCSVBatsmenFileWhenLoadedToGetTopBatsmenWhoHadBestAveragesWithBestStrikingRates_ShouldReturnCorrectResult() {
	try {
		List<IPLBatsman> bestAveragesWithBestStrikeRateCricketers = iplAnalyser
				.getBatsmenWithBestAveragesAndBestStrikeRates(3);
		assertEquals("Andre Russell", bestAveragesWithBestStrikeRateCricketers.get(0).getPlayerName());
	} catch (IPLAnalyserException e) {}
}

@Test
public void givenCSVBatsmenFileWhenLoadedToGetTopBatsmenWhoHadMaximumRunsWithBestAverages_ShouldReturnCorrectResult() {
	try {
		List<IPLBatsman> maximumRunsWithBestAveragesCricketers = iplAnalyser
				.getBatsmenWithMaximumRunsWithBestAverages(3);
		assertEquals("David Warner", maximumRunsWithBestAveragesCricketers.get(0).getPlayerName());
	} catch (IPLAnalyserException e) {}
}

@Test
public void givenCSVBowlerFileWhenLoadedToGetTopBowlingAverages_ShouldReturnCorrectResult() {
	try {
		List<Double> topBowlingAverages = iplAnalyser.getTopBowlingAverages(3);
		List<Double> expectedTopBowlingAverages = Arrays.asList(new Double[]{11.0, 14.0, 14.5});
		assertEquals(expectedTopBowlingAverages, topBowlingAverages);
	} catch (IPLAnalyserException e) {}
}

@Test
public void givenCSVBowlerFileWhenLoadedToGetTopBowlerStrikeRates_ShouldReturnCorrectResult() {
	try {
		List<Double> topBowlerStrikeRates = iplAnalyser.getTopBowlingStrikeRates(3);
		List<Double> expectedTopBowlingStrikeRates = Arrays.asList(new Double[]{8.66, 10.75, 11.0});
		assertEquals(expectedTopBowlingStrikeRates, topBowlerStrikeRates);
	} catch (IPLAnalyserException e) {}
}

@Test
public void givenAFileWhenLoadedToGetBowlersWithBestEconomyRates_ShouldReturnCorrectResult() {
	try {
		List<IPLBowler> bestEconomyRatesBowlers = iplAnalyser.getBowlersWithBestEconomyRate(3);
		assertEquals("Shivam Dube", bestEconomyRatesBowlers.get(0).getPlayerName());
	} catch(IPLAnalyserException e) {}
}

}
