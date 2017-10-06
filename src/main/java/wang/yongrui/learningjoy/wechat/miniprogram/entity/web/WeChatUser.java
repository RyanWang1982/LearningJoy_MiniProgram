/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.WeChatUserBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.NoticeEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.wechat.fundamental.entity.basic.UserBasic;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "authorities", "password", "accountNonExpired", "accountNonLocked",
		"credentialsNonExpired", "enabled" })
@JsonInclude(value = Include.NON_EMPTY)
public class WeChatUser extends UserBasic implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private WeChatUserBasic weChatInfo;

	@Getter
	@Setter
	private UserSetting userSetting;

	@Getter
	@Setter
	private Set<WeChatUser> childSet;

	@Getter
	@Setter
	private Set<WeChatUser> parentSet;

	@Getter
	@Setter
	private Set<Course> teacherCourseSet;

	@Getter
	@Setter
	private Set<Course> studentCourseSet;

	@Getter
	@Setter
	private Set<Notice> noticeSet;

	/**
	 * 
	 */
	public WeChatUser() {
		super();
	}

	/**
	 * @param userEntity
	 */
	public WeChatUser(WeChatUserEntity userEntity) {
		super();
		if (null != userEntity) {
			BeanUtils.copyProperties(userEntity, this);
			if (null != userEntity.getUserSettingEntity()) {
				UserSetting userSetting = new UserSetting();
				BeanUtils.copyProperties(userEntity.getUserSettingEntity(), userSetting);
			}

			if (CollectionUtils.isNotEmpty(userEntity.getChildEntitySet())) {
				Set<WeChatUser> childSet = new LinkedHashSet<>();
				for (WeChatUserEntity childEntity : userEntity.getChildEntitySet()) {
					WeChatUser child = new WeChatUser();
					BeanUtils.copyProperties(childEntity, child);
					childSet.add(child);
				}
				setChildSet(childSet);
			}
			if (CollectionUtils.isNotEmpty(userEntity.getParentEntitySet())) {
				Set<WeChatUser> parentSet = new LinkedHashSet<>();
				for (WeChatUserEntity parentEntity : userEntity.getParentEntitySet()) {
					WeChatUser parent = new WeChatUser();
					BeanUtils.copyProperties(parentEntity, parent);
					parentSet.add(parent);
				}
				setParentSet(parentSet);
			}

			if (CollectionUtils.isNotEmpty(userEntity.getTeacherCourseEntitySet())) {
				Set<Course> teacherCourseSet = new LinkedHashSet<>();
				for (CourseEntity teacherCourseEntity : userEntity.getTeacherCourseEntitySet()) {
					Course teacherCourse = new Course();
					BeanUtils.copyProperties(teacherCourseEntity, teacherCourse);
					teacherCourseSet.add(teacherCourse);
				}
				setTeacherCourseSet(teacherCourseSet);
			}
			if (CollectionUtils.isNotEmpty(userEntity.getStudentCourseEntitySet())) {
				Set<Course> studentCourseSet = new LinkedHashSet<>();
				for (CourseEntity studentCourseEntity : userEntity.getStudentCourseEntitySet()) {
					Course studentCourse = new Course();
					BeanUtils.copyProperties(studentCourseEntity, studentCourse);
					studentCourseSet.add(studentCourse);
				}
				setStudentCourseSet(studentCourseSet);
			}

			if (CollectionUtils.isNotEmpty(userEntity.getNoticeEntitySet())) {
				Set<Notice> noticeSet = new LinkedHashSet<>();
				for (NoticeEntity noticeEntity : userEntity.getNoticeEntitySet()) {
					Notice notice = new Notice();
					BeanUtils.copyProperties(noticeEntity, notice);
					noticeSet.add(notice);
				}
				setNoticeSet(noticeSet);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities(
	 * )
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return false;
	}

}
