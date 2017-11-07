/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<ErrorInfo> processValidationError(MethodArgumentNotValidException ex) {
		List<ObjectError> objectErrorList = ex.getBindingResult().getAllErrors();
		List<ErrorInfo> errorInfoList = new ArrayList<>();
		for (ObjectError objectError : objectErrorList) {
			Locale currentLocale = LocaleContextHolder.getLocale();
			String message = messageSource.getMessage(objectError.getDefaultMessage(), objectError.getArguments(),
					currentLocale);
			errorInfoList.add(new ErrorInfo(objectError.getCode(), message));
		}

		return errorInfoList;
	}

}
