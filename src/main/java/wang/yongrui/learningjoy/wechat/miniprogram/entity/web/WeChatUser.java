/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.util.Collection;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.WeChatUserBasic;
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
