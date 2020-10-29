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
	
	private <E> List<E> getSortedList(List<E> list, Comparator<E> comparator, int noOfTopPlayers){
		return list.stream()
				   .sorted(comparator)
				   .limit(noOfTopPlayers)
				   .collect(Collectors.toList());
	}
	
	public List<IPLBatsman> getBatsmenWithMaximumSixes(int noOfTopPlayers) throws IPLAnalyserException{
		if(batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<IPLBatsman> comparator = Comparator.comparing(IPLBatsman::getSixes)
													  .reversed();
		return getSortedList(batsmanList, comparator, noOfTopPlayers);
	}
	
	public List<IPLBatsman> getBatsmenWithMaximumFours(int noOfTopPlayers) throws IPLAnalyserException{
		if(batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<IPLBatsman> comparator = Comparator.comparing(IPLBatsman::getFours)
													  .reversed();
		return getSortedList(batsmanList, comparator, noOfTopPlayers);
	}
	
	public List<IPLBatsman> getBatsmenWithBestStrikeRatesAndMaximumBoundaries(int noOfTopPlayers)
			throws IPLAnalyserException {
		if (batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<IPLBatsman> comparator = Comparator.comparing(IPLBatsman::performanceFactor).reversed();
		return getSortedList(batsmanList, comparator, noOfTopPlayers);
	}
	
	public List<IPLBatsman> getBatsmenWithBestAveragesAndBestStrikeRates(int noOfTopPlayers)
			throws IPLAnalyserException {
		if (batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Function<IPLBatsman, Double> toGetAverage = p -> Double.parseDouble(p.getAverage());
		Comparator<IPLBatsman> comparatorForAverageRuns = Comparator.comparing(toGetAverage).reversed();
		List<IPLBatsman> sortedListByAverages = getSortedList(batsmanList, comparatorForAverageRuns, noOfTopPlayers);
		return getSortedList(sortedListByAverages, Comparator.comparing(IPLBatsman::getStrikeRate).reversed(),
				noOfTopPlayers);
	}
	
	public List<IPLBatsman> getBatsmenWithMaximumRunsWithBestAverages(int noOfTopPlayers) throws IPLAnalyserException {
		if(batsmanList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Function<IPLBatsman, Double> toGetAverage = p -> Double.parseDouble(p.getAverage());
		Comparator<IPLBatsman> comparator = Comparator.comparing(IPLBatsman::getRuns).reversed();
		List<IPLBatsman> sortedListByRuns = getSortedList(batsmanList, comparator, noOfTopPlayers);
		return getSortedList(sortedListByRuns, Comparator.comparing(toGetAverage).reversed(), noOfTopPlayers);
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
		Comparator<IPLBowler> comparator = Comparator.comparing(IPLBowler::getEconomy);
		return getSortedList(bowlerList, comparator, noOfTopBowlers);
	}
	
	public List<IPLBowler> getBowlerWithBestStrikeRateWith5wAnd4w(int noOfTopBowlers) throws IPLAnalyserException {
		if (bowlerList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Function<IPLBowler, Integer> getSumOf5wAnd4w = iplBowler -> iplBowler.getFiveWickets() +
																	iplBowler.getFourWickets();
		Comparator<IPLBowler> comparator = Comparator.comparing(getSumOf5wAnd4w).reversed();
		List<IPLBowler> sortedByStrikeRate = getSortedList(bowlerList, Comparator.comparing(IPLBowler::getStrikeRate),
															bowlerList.size());
		return getSortedList(sortedByStrikeRate, comparator, noOfTopBowlers);
	}
	
	public List<IPLBowler> getBowlerWithBestBowlingAveragesWithBestStrikingRates(int noOfTopBowlers) 
																						throws IPLAnalyserException{
		if (bowlerList.size() == 0) {
			throw new IPLAnalyserException("No Data Available", IPLAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Function<IPLBowler, Double> toGetAverage = p -> Double.parseDouble(p.getAverage());
		Comparator<IPLBowler> comparator = Comparator.comparing(toGetAverage);
		List<IPLBowler> sortedByAverage = getSortedList(bowlerList, comparator,
														noOfTopBowlers);
		Function<IPLBowler, Double> toGetStrikeRate = p -> Double.parseDouble(p.getStrikeRate());
		return getSortedList(sortedByAverage, Comparator.comparing(toGetStrikeRate), noOfTopBowlers);
	}
}
