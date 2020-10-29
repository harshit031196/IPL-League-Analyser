package exception;

public class IPLAnalyserException extends Exception {
	
	public ExceptionType type;

	public enum ExceptionType {
		NO_DATA_FOUND;
	}

	public IPLAnalyserException(String message, ExceptionType exceptionType) {
		super(message);
		this.type = exceptionType;
	}
}
