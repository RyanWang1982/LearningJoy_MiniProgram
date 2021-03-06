/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import static org.springframework.beans.BeanUtils.*;
import static wang.yongrui.learningjoy.wechat.miniprogram.util.EntityUtils.*;

import java.util.Collection;
import java.util.Set;

import javax.persistence.metamodel.Attribute;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.WeChatUserBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.UserChildEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.UserParentEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity_;
import wang.yongrui.wechat.fundamental.entity.basic.UserBasic;

/**
 * @author Wang Yongrui
 *
 */
@Getter
@Setter
@JsonIgnoreProperties(value = { "authorities", "password", "accountNonExpired", "accountNonLocked",
		"credentialsNonExpired", "enabled" })
@JsonInclude(value = Include.NON_EMPTY)
public class WeChatUser extends UserBasic implements UserDetails {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private WeChatUserBasic weChatInfo;

	private UserSetting userSetting;

	private Set<UserChild> childSet;

	private Set<UserParent> parentSet;

	private Set<Course> teacherCourseSet;

	private Set<Course> studentCourseSet;

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
			copyProperties(userEntity, this);
		}
	}

	/**
	 * @param userEntity
	 * @param includedAttributeSet
	 */
	public WeChatUser(WeChatUserEntity userEntity, Set<Attribute<?, ?>> includedAttributeSet) {
		super();
		if (null != userEntity) {
			copyProperties(userEntity, this);
			if (includedAttributeSet.contains(WeChatUserEntity_.userSettingEntity)
					&& null != userEntity.getUserSettingEntity()) {
				UserSetting userSetting = new UserSetting();
				copyProperties(userEntity.getUserSettingEntity(), userSetting);
				setUserSetting(userSetting);
			}
			if (includedAttributeSet.contains(WeChatUserEntity_.childEntitySet)) {
				setChildSet(getObjectSetFromEntitySetWithHeritage(userEntity.getChildEntitySet(), UserChildEntity.class,
						UserChild.class));
			}
			if (includedAttributeSet.contains(WeChatUserEntity_.parentEntitySet)) {
				setParentSet(getObjectSetFromEntitySetWithHeritage(userEntity.getParentEntitySet(),
						UserParentEntity.class, UserParent.class));
			}
			if (includedAttributeSet.contains(WeChatUserEntity_.teacherCourseEntitySet)) {
				setTeacherCourseSet(getTargetSetFromSourceSet(userEntity.getTeacherCourseEntitySet(), Course.class));
			}
			if (includedAttributeSet.contains(WeChatUserEntity_.studentCourseEntitySet)) {
				setStudentCourseSet(getTargetSetFromSourceSet(userEntity.getStudentCourseEntitySet(), Course.class));
			}
			if (includedAttributeSet.contains(WeChatUserEntity_.noticeEntitySet)) {
				setNoticeSet(getTargetSetFromSourceSet(userEntity.getNoticeEntitySet(), Notice.class));
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
