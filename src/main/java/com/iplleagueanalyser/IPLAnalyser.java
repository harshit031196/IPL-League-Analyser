package com.iplleagueanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import exception.IPLAnalyserException;
import iplpojo.IPLBatsman;
import iplpojo.IPLBowler;
import opencsvbuilder.CSVBuilderFactory;
import opencsvbuilder.CSVException;
import opencsvbuilder.ICSVBuilder;

public class IPLAnalyser {
	private List<IPLBatsman> batsmanList = null;
	private List<IPLBowler> bowlerList = null;

	public IPLAnalyser() {
		batsmanList = new ArrayList<IPLBatsman>();
		bowlerList = new ArrayList<IPLBowler>();
	}
	public int loadBatsmanData(String filePath) throws CSVException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<IPLBatsman> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			batsmanList.addAll(csvBuilder.getCSVFileList(reader, IPLBatsman.class, filePath)
					   .stream()
					   .distinct()
					   .collect(Collectors.toList()));
			return batsmanList.size();
		} catch (IOException e) {
			throw new CSVException("File Problem encountered", CSVException.ExceptionType.FILE_PROBLEM);
		}
	}
	
	public int loadBowlerData(String filePath) throws CSVException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<IPLBowler> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			bowlerList.addAll(csvBuilder.getCSVFileList(reader, IPLBowler.class, filePath)
					  .stream()
					  .distinct()
					  .collect(Collectors.toList()));
			return bowlerList.size();
		} catch (IOException e) {
			throw new CSVException("File Problem encountered", CSVException.ExceptionType.FILE_PROBLEM);
		}
	}
	
	public List<Double> getTopBattingAverages(int noOfTopAverages) throws IPLAnalyserException {
		if(batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		List<Double> topBatsmanAverageList = batsmanList.stream()
						.map(player -> Double.parseDouble(player.getAverage()))
						.sorted(Comparator.reverseOrder())
						.limit(noOfTopAverages)
						.collect(Collectors.toList());
		return topBatsmanAverageList;
	}
	
	public List<Double> getTopStrikeRates(int noOfTopStrikes) throws IPLAnalyserException{
		if(batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		List<Double> topBatsmanStrikeRateList = batsmanList.stream()
						.map(player -> player.getStrikeRate())
						.sorted(Comparator.reverseOrder())
						.limit(noOfTopStrikes)
						.collect(Collectors.toList());
		return topBatsmanStrikeRateList;
	}
	
	public List<IPLBatsman> getBatsmenWithMaximumSixes(int noOfTopPlayers) throws IPLAnalyserException{
		if(batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<IPLBatsman> comparator = Comparator.comparing(IPLBatsman::getSixes)
													  .reversed();
		return batsmanList.stream()
						  .sorted(comparator)
						  .limit(noOfTopPlayers)
						  .collect(Collectors.toList());
	}
	
	public List<IPLBatsman> getBatsmenWithMaximumFours(int noOfTopPlayers) throws IPLAnalyserException{
		if(batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<IPLBatsman> comparator = Comparator.comparing(IPLBatsman::getFours)
													  .reversed();
		return batsmanList.stream()
						  .sorted(comparator)
						  .limit(noOfTopPlayers)
						  .collect(Collectors.toList());
	}
	
	public List<IPLBatsman> getBatsmenWithBesStrikeRatesAndMaximumBoundaries(int noOfTopPlayers) throws IPLAnalyserException{
		if(batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<IPLBatsman> comparator = Comparator.comparing(IPLBatsman::performanceFactor)
													  .reversed();
		return batsmanList.stream()
						  .sorted(comparator)
						  .limit(noOfTopPlayers)
						  .collect(Collectors.toList());
	}
	
	public List<IPLBatsman> getBatsmenWithBestAveragesAndBestStrikeRates(int noOfTopPlayers) throws IPLAnalyserException {
		if(batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Function<IPLBatsman, Double> toGetAverage = p -> Double.parseDouble(p.getAverage());
		Comparator<IPLBatsman> comparatorForAverageRuns = Comparator.comparing(toGetAverage)
																	.reversed();
		return batsmanList.stream()
						  .sorted(comparatorForAverageRuns)
						  .limit(noOfTopPlayers)
						  .sorted(Comparator.comparing(IPLBatsman::getStrikeRate).reversed())
						  .collect(Collectors.toList());
	}
	
	public List<IPLBatsman> getBatsmenWithMaximumRunsWithBestAverages(int noOfTopPlayers) throws IPLAnalyserException {
		if(batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Function<IPLBatsman, Double> toGetAverage = p -> Double.parseDouble(p.getAverage());
		Comparator<IPLBatsman> comparator = Comparator.comparing(IPLBatsman::getRuns)
				  									  .reversed();
		return batsmanList.stream()
						  .sorted(comparator)
						  .limit(noOfTopPlayers)
						  .sorted(Comparator.comparing(toGetAverage).reversed())
						  .collect(Collectors.toList());
	}
	
	public List<Double> getTopBowlingAverages(int noOfTopAverages) throws IPLAnalyserException{
		if(bowlerList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		return bowlerList.stream()
						 .map(bowler -> Double.parseDouble(bowler.getAverage()))
						 .sorted()
						 .limit(noOfTopAverages)
						 .collect(Collectors.toList());
	}
	
	public List<Double> getTopBowlingStrikeRates(int noOfTopStrikeRates) throws IPLAnalyserException{
		if(bowlerList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		return bowlerList.stream()
				 		 .map(bowler -> Double.parseDouble(bowler.getStrikeRate()))
				 		 .sorted()
				 		 .limit(noOfTopStrikeRates)
				 		 .collect(Collectors.toList());
	}
	
	public List<IPLBowler> getBowlersWithBestEconomyRate(int noOfTopBowlers) throws IPLAnalyserException{
		if(bowlerList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		return bowlerList.stream()
						 .sorted(Comparator.comparing(IPLBowler::getEconomy))
						 .limit(noOfTopBowlers)
						 .collect(Collectors.toList());
	}
}
