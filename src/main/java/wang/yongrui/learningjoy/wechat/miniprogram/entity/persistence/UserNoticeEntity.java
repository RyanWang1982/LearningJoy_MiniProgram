/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.UserNoticeBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "USER_NOTICE")
@IdClass(UserNoticeKey.class)
@Getter
@Setter
public class UserNoticeEntity extends UserNoticeBasic {

	@ManyToOne
	@Id
	private WeChatUserEntity user;

	@ManyToOne
	@Id
	private NoticeEntity notice;

	@ManyToOne
	@JoinColumn(name = "claimed_by")
	private WeChatUserEntity claimedBy;

}
