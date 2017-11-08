/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.enumeration;

import lombok.Getter;
import wang.yongrui.wechat.fundamental.entity.enumeration.BasicEnum;

/**
 * @author Wang Yongrui
 *
 */
@Getter
public enum NoticeStatus implements BasicEnum {

	Unread("Unread"), Claimed("Claimed");

	private String description;

	/**
	 * @param description
	 */
	private NoticeStatus(String description) {
		this.description = description;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.wechat.fundamental.entity.enumeration.BasicEnum#getName()
	 */
	@Override
	public String getName() {
		return this.name();
	}

}
