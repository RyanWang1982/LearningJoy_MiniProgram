/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import static wang.yongrui.learningjoy.wechat.miniprogram.util.EntityUtils.*;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.metamodel.Attribute;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.CourseBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity_;

/**
 * @author Wang Yongrui
 *
 */
@Getter
@Setter
@JsonInclude(value = Include.NON_EMPTY)
public class Course extends CourseBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Set<WeChatUser> teacherSet;

	private Set<WeChatUser> studentSet;

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

	/**
	 * @param courseEntity
	 * @param includedAttributeSet
	 */
	public Course(CourseEntity courseEntity, Set<Attribute<?, ?>> includedAttributeSet) {
		super();
		if (null != courseEntity) {
			BeanUtils.copyProperties(courseEntity, this);
			if (includedAttributeSet.contains(CourseEntity_.teacherEntitySet)) {
				setTeacherSet(getTargetSetFromSourceSet(courseEntity.getTeacherEntitySet(), WeChatUser.class));
			}
			if (includedAttributeSet.contains(CourseEntity_.studentEntitySet)) {
				setStudentSet(getTargetSetFromSourceSet(courseEntity.getStudentEntitySet(), WeChatUser.class));
			}
			if (includedAttributeSet.contains(CourseEntity_.lessonEntitySet)) {
				setLessonSet(getTargetSetFromSourceSet(courseEntity.getLessonEntitySet(), Lesson.class));
			}
		}
	}

}
