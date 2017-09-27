/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.LessonBasic;

/**
 * @author Wang Yongrui
 *
 */
public class Lesson extends LessonBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Set<StudentPerformance> studentPerformanceSet;

	@Getter
	@Setter
	private Set<Homework> homeworkSet;

}
