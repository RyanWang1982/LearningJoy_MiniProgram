/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.UserNoticeBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.UserNoticeEntity;

/**
 * @author Wang Yongrui
 *
 */
public class UserNotice extends UserNoticeBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private WeChatUser user;

	@Getter
	@Setter
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
