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
public enum FileType implements BasicEnum {

	Audio("Audio"), Picture("Picture"), Video("Video"), Text("Text");

	private String description;

	/**
	 * @param description
	 */
	private FileType(String description) {
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
