/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.LessonBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "LESSON")
@Getter
@Setter
public class LessonEntity extends LessonBasic {

	@ManyToOne
	@JoinColumn(name = "course_id")
	private CourseEntity courseEntity;

	// One lesson should only be taught by one teacher
	@OneToOne
	@JoinColumn(name = "teacher_id")
	private WeChatUserEntity teacherEntity;

	@OneToMany(mappedBy = "lessonEntity")
	private Set<StudentPerformanceEntity> studentPerformanceEntitySet;

	@OneToMany(mappedBy = "lessonEntity")
	private Set<HomeworkEntity> homeworkEntitySet;

}
