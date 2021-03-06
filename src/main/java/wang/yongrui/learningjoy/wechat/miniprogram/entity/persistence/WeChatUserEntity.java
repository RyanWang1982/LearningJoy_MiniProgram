/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.WeChatUserBasic;
import wang.yongrui.wechat.fundamental.entity.persistence.UserEntity;

/**
 * @author Wang Yongrui
 *
 */
@Entity
@DiscriminatorValue("WeChat")
@Getter
@Setter
public class WeChatUserEntity extends UserEntity {

	private WeChatUserBasic weChatInfo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_setting_id")
	private UserSettingEntity userSettingEntity;

	// Parent has children
	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<UserChildEntity> childEntitySet;

	// Child has parents
	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<UserParentEntity> parentEntitySet;

	// Teacher owns courses
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "TEACHER_COURSE", joinColumns = { @JoinColumn(name = "teacher_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private Set<CourseEntity> teacherCourseEntitySet;

	// Student takes courses
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private Set<CourseEntity> studentCourseEntitySet;

	// User receives notices
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "USER_NOTICE", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "notice_id") })
	private Set<NoticeEntity> noticeEntitySet;

}
