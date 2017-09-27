/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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

	@ManyToMany
	@JoinTable(name = "LESSON_PERFORMANCE", joinColumns = { @JoinColumn(name = "lesson_id") }, inverseJoinColumns = {
			@JoinColumn(name = "student_preformance_id") })
	private Set<StudentPerformanceEntity> studentPerformanceEntitySet;

	@ManyToMany
	@JoinTable(name = "LESSON_HOMEWORK", joinColumns = { @JoinColumn(name = "lesson_id") }, inverseJoinColumns = {
			@JoinColumn(name = "homework_id") })
	private Set<HomeworkEntity> homeworkEntitySet;

}
