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
public enum Priority implements BasicEnum {

	Low("Low"), Medium("Medium"), High("High");

	private String description;

	/**
	 * @param description
	 */
	private Priority(String description) {
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
