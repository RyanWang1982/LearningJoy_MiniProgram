/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.CourseBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity;

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

	/**
	 * 
	 */
	public Course() {
		super();
	}

	/**
	 * @param courseEntity
	 */
	public Course(CourseEntity courseEntity) {
		super();
		if (null != courseEntity) {
			BeanUtils.copyProperties(courseEntity, this);
		}
	}

}
