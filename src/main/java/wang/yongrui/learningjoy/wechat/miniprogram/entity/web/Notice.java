/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import java.io.Serializable;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.NoticeBasic;

/**
 * @author Wang Yongrui
 *
 */
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

}
