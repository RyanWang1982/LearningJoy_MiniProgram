/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.CourseBasic;

/**
 * @author Wang Yongrui
 *
 */
public class Course extends CourseBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Set<WeChatUser> teacherSet;

	@Getter
	@Setter
	private Set<WeChatUser> studentSet;

	@Getter
	@Setter
	private Set<Lesson> lessonSet;

}
