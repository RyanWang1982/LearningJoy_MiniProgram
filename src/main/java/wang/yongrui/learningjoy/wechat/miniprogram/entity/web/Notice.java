/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import static wang.yongrui.learningjoy.wechat.miniprogram.util.EntityUtils.*;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.NoticeBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.NoticeEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonInclude(value = Include.NON_EMPTY)
public class Notice extends NoticeBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private WeChatUser sender;

	@Getter
	@Setter
	private Set<WeChatUser> recipientSet;

	/**
	 * 
	 */
	public Notice() {
		super();
	}

	/**
	 * @param noticeEntity
	 */
	public Notice(NoticeEntity noticeEntity) {
		super();
		if (null != noticeEntity) {
			BeanUtils.copyProperties(noticeEntity, this);
			if (null != noticeEntity.getSenderEntity()) {
				WeChatUser sender = new WeChatUser();
				BeanUtils.copyProperties(noticeEntity.getSenderEntity(), sender);
			}
			setRecipientSet(getTargetSetFromSourceSet(noticeEntity.getRecipientEntitySet(), WeChatUser.class));
		}
	}

}
