/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import static wang.yongrui.learningjoy.wechat.miniprogram.util.EntityUtils.*;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.LessonBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.LessonEntity;

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
			setStudentPerformanceSet(
					getTargetSetFromSourceSet(lessonEntity.getStudentPerformanceEntitySet(), StudentPerformance.class));
			setHomeworkSet(getTargetSetFromSourceSet(lessonEntity.getHomeworkEntitySet(), Homework.class));
		}
	}

}
