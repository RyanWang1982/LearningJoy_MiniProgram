/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.CourseBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonInclude(value = Include.NON_EMPTY)
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
			if (CollectionUtils.isNotEmpty(courseEntity.getTeacherEntitySet())) {
				Set<WeChatUser> teacherSet = new LinkedHashSet<>();
				for (WeChatUserEntity teacherEntity : courseEntity.getTeacherEntitySet()) {
					WeChatUser teacher = new WeChatUser();
					BeanUtils.copyProperties(teacherEntity, teacher);
					teacherSet.add(teacher);
				}
				setTeacherSet(teacherSet);
			}
		}
	}

}
