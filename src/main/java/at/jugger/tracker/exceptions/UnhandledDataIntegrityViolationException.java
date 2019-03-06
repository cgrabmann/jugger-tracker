package at.jugger.tracker.exceptions;

import at.jugger.tracker.dto.TrackerError;
import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
public class UnhandledDataIntegrityViolationException extends JuggerTrackerException {
    private final DataIntegrityViolationException dataIntegrityViolationException;

    public UnhandledDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        super();
        this.dataIntegrityViolationException = dataIntegrityViolationException;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public TrackerError.TypeEnum getErrorType() {
        return TrackerError.TypeEnum.USER_NOT_FOUND;
    }

    @Override
    public String getErrorMessage() {
        StringWriter sw = new StringWriter();
        dataIntegrityViolationException.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        return "Unhandled data integrity violation exception: " + dataIntegrityViolationException.getMessage() + "\n" + exceptionAsString;
    }
}
