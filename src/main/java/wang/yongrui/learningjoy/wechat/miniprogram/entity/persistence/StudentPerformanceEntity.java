/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.StudentPerformanceBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "STUDENT_PERFORMANCE")
@Getter
@Setter
public class StudentPerformanceEntity extends StudentPerformanceBasic {

	@ManyToOne
	@JoinColumn(name = "lesson_id")
	private LessonEntity lessonEntity;

	@OneToOne
	@JoinColumn(name = "student_id")
	private WeChatUserEntity studentEntity;

	@OneToMany
	@JoinTable(name = "STUDENT_PERFORMANCE_FILE", joinColumns = {
			@JoinColumn(name = "student_performance_id") }, inverseJoinColumns = { @JoinColumn(name = "file_id") })
	private Set<FileInfoEntity> fileEntitySet;

}
