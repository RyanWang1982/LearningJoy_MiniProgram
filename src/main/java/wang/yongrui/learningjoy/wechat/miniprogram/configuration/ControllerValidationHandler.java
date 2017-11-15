/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.configuration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import wang.yongrui.learningjoy.wechat.miniprogram.controller.validation.ErrorInfo;

/**
 * @author Wang Yongrui
 *
 */
@ControllerAdvice
public class ControllerValidationHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass());;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<ErrorInfo> processValidationError(MethodArgumentNotValidException exception) {
		List<ObjectError> objectErrorList = exception.getBindingResult().getAllErrors();
		List<ErrorInfo> errorInfoList = new ArrayList<>();
		Long time = Calendar.getInstance().getTimeInMillis();
		Locale currentLocale = LocaleContextHolder.getLocale();
		for (ObjectError objectError : objectErrorList) {
			String message = messageSource.getMessage(objectError.getDefaultMessage(), objectError.getArguments(),
					currentLocale);
			errorInfoList.add(new ErrorInfo(objectError.getCode(), message, time));
		}

		return errorInfoList;
	}

	@ExceptionHandler(Throwable.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorInfo processErrorAndException(Throwable errorAndException) {
		Long time = Calendar.getInstance().getTimeInMillis();
		StringBuffer errorMessage = new StringBuffer();
		errorMessage.append("User: ").append(request.getRemoteUser()).append(", ");
		errorMessage.append("Request Path: ").append(request.getServletPath()).append(", ");
		errorMessage.append("Request Method: ").append(request.getMethod()).append(", ");
		errorMessage.append("Request time: ").append(time).append("; \n");
		errorMessage.append(errorAndException.getMessage());
		for (StackTraceElement eachStackTraceElement : errorAndException.getStackTrace()) {
			errorMessage.append("\n\tat ").append(eachStackTraceElement);
		}

		logger.error(errorMessage.toString());

		Locale currentLocale = LocaleContextHolder.getLocale();
		String message = messageSource.getMessage("system.error.message", new Object[] { time.toString() },
				currentLocale);
		ErrorInfo errorInfo = new ErrorInfo("system.error", message, time);

		return errorInfo;
	}

}
