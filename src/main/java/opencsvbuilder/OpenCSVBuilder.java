package opencsvbuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder<E> {

	public Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass, String filePath) throws CSVException {
			return getCSVBean(reader, csvClass, filePath).iterator();
	}

	private boolean isCorrectDelimiter(String filePath) throws CSVException {
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));) {
			String[] headerColumns = bufferedReader.readLine().split(",");
			if (headerColumns.length < 5) {
				return false;
			}
		} catch (IOException e) {
			throw new CSVException("File Problem Occured", CSVException.ExceptionType.FILE_PROBLEM);
		}
		return true;
	}

	private CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass, String filePath) throws CSVException {
		try {
			if (!((filePath.split("\\.")[1]).equals("csv"))) {
				throw new CSVException("Incorrect file type", CSVException.ExceptionType.INCORRECT_TYPE);
			}
			if (!isCorrectDelimiter(filePath)) {
				throw new CSVException("File contains Invalid Delimiter",
						CSVException.ExceptionType.INCORRECT_DELIMITER);
			}
			CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withType(csvClass)
					.withIgnoreLeadingWhiteSpace(true).build();
			return csvToBean;
		} catch (IllegalStateException e) {
			throw new CSVException("Illegal State Encountered", CSVException.ExceptionType.ILLEGAL_STATE);
		}
	}

	@Override
	public List<E> getCSVFileList(Reader reader, Class<E> csvClass, String filepath) throws CSVException {
		return getCSVBean(reader, csvClass, filepath).parse();

	}
}
