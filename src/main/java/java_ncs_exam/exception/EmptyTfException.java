package java_ncs_exam.exception;

@SuppressWarnings("serial")
public class EmptyTfException extends RuntimeException {

	public EmptyTfException() {
		super("공란 존재");
	}

}
