/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.CourseBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "COURSE")
@Getter
@Setter
public class CourseEntity extends CourseBasic {

	// Course is owned by teachers
	@ManyToMany
	@JoinTable(name = "TEACHER_COURSE", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = {
			@JoinColumn(name = "teacher_id") })
	private Set<WeChatUserEntity> teacherEntitySet;

	// Course is taken by students
	@ManyToMany
	@JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "course_id") }, inverseJoinColumns = {
			@JoinColumn(name = "student_id") })
	private Set<WeChatUserEntity> studentEntitySet;

	// Course has lessons
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "courseEntity")
	private Set<LessonEntity> lessonEntitySet;

}
