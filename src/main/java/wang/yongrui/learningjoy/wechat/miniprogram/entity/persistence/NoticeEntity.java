/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.NoticeBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "NOTICE")
@Getter
@Setter
public class NoticeEntity extends NoticeBasic {

	@OneToOne
	@JoinColumn(name = "sender_id")
	private WeChatUserEntity senderEntity;

	@OneToMany(mappedBy = "user")
	private Set<UserNoticeEntity> recipientNoticeEntitySet;

}
