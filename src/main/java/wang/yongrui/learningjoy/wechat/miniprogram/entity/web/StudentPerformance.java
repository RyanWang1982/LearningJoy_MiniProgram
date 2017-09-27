/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.StudentPerformanceBasic;

/**
 * @author Wang Yongrui
 *
 */
public class StudentPerformance extends StudentPerformanceBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private WeChatUser student;

	@Getter
	@Setter
	private Set<File> fileSet;

}
