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

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.WeChatUserBasic;
import wang.yongrui.wechat.fundamental.entity.persistence.UserEntity;

/**
 * @author Wang Yongrui
 *
 */
@Entity
@DiscriminatorValue("wechat")
@Getter
@Setter
public class WeChatUserEntity extends UserEntity {

	private WeChatUserBasic weChatUserBasic;

	// Parent has children
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "USER_CHILD", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "child_id") })
	private Set<WeChatUserEntity> childEntitySet;

	// Child has parents
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "USER_PARENT", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "parent_id") })
	private Set<WeChatUserEntity> parentEntitySet;

	// Teacher owns courses
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "TEACHER_COURSE", joinColumns = { @JoinColumn(name = "teacher_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private Set<CourseEntity> teacherCourseEntity;

	// Student takes courses
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private Set<CourseEntity> studentCourseEntity;

	// User receives notices
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "USER_NOTICE", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "notice_id") })
	private Set<NoticeEntity> noticeEntitySet;

}
