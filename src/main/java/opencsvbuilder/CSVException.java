package opencsvbuilder;

public class CSVException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6437271778102008142L;
	public ExceptionType type;

	public enum ExceptionType {
		FILE_PROBLEM, INCORRECT_TYPE, INCORRECT_DELIMITER, ILLEGAL_STATE;
	}
	
	public CSVException(String message, ExceptionType exceptionType) {
		super(message);
		this.type = exceptionType;
	}
}
