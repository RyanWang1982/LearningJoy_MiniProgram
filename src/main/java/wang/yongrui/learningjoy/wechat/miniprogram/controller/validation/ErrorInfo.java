/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.controller.validation;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Wang Yongrui
 *
 */
@Getter
@Setter
public class ErrorInfo {

	private String code;

	private String message;

	private Long time = Calendar.getInstance().getTimeInMillis();

	/**
	 * @param code
	 * @param message
	 */
	public ErrorInfo(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
