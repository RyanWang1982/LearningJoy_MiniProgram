/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.UserNoticeBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.UserNoticeEntity;

/**
 * @author Wang Yongrui
 *
 */
@Getter
@Setter
@JsonInclude(value = Include.NON_EMPTY)
public class UserNotice extends UserNoticeBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private WeChatUser user;

	private WeChatUser claimedBy;

	/**
	 *
	 */
	public UserNotice() {
		super();
	}

	/**
	 * @param userNoticeEntity
	 */
	public UserNotice(UserNoticeEntity userNoticeEntity) {
		super();
		if (null != userNoticeEntity) {
			BeanUtils.copyProperties(userNoticeEntity, this);
			setUser(new WeChatUser(userNoticeEntity.getUser()));
			setClaimedBy(new WeChatUser(userNoticeEntity.getClaimedBy()));
		}
	}

}
