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
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.LessonBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.LessonEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.LessonEntity_;

/**
 * @author Wang Yongrui
 *
 */
@JsonInclude(value = Include.NON_EMPTY)
public class Lesson extends LessonBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Course course;

	@Getter
	@Setter
	private WeChatUser teacher;

	@Getter
	@Setter
	private Set<StudentPerformance> studentPerformanceSet;

	@Getter
	@Setter
	private Set<Homework> homeworkSet;

	/**
	 *
	 */
	public Lesson() {
		super();
	}

	/**
	 * @param lessonEntity
	 */
	public Lesson(LessonEntity lessonEntity) {
		super();
		if (null != lessonEntity) {
			BeanUtils.copyProperties(lessonEntity, this);
		}
	}

	/**
	 * @param lessonEntity
	 * @param includedAttributeSet
	 */
	public Lesson(LessonEntity lessonEntity, Set<Attribute<?, ?>> includedAttributeSet) {
		super();
		if (null != lessonEntity) {
			BeanUtils.copyProperties(lessonEntity, this);
			if (includedAttributeSet.contains(LessonEntity_.courseEntity)) {
				Course course = new Course();
				BeanUtils.copyProperties(lessonEntity.getCourseEntity(), course);
				setCourse(course);
			}
			if (includedAttributeSet.contains(LessonEntity_.teacherEntity)) {
				WeChatUser teacher = new WeChatUser();
				BeanUtils.copyProperties(lessonEntity.getTeacherEntity(), teacher);
				setTeacher(teacher);
			}
			if (includedAttributeSet.contains(LessonEntity_.studentPerformanceEntitySet)) {
				setStudentPerformanceSet(getTargetSetFromSourceSet(lessonEntity.getStudentPerformanceEntitySet(),
						StudentPerformance.class));
			}
			if (includedAttributeSet.contains(LessonEntity_.homeworkEntitySet)) {
				setHomeworkSet(getTargetSetFromSourceSet(lessonEntity.getHomeworkEntitySet(), Homework.class));
			}
		}
	}

}
