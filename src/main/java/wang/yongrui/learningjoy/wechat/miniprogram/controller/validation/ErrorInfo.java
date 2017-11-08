/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.controller.validation;

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

	private Long time;

	/**
	 * @param code
	 * @param message
	 * @param time
	 */
	public ErrorInfo(String code, String message, Long time) {
		super();
		this.code = code;
		this.message = message;
		this.time = time;
	}

}
