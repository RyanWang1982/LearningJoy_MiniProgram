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
public enum CourseStatus implements BasicEnum {

	Preparing("Preparing"), Started("Started"), Closed("Closed"), Finished("Finished");

	private String description;

	/**
	 * @param description
	 */
	private CourseStatus(String description) {
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
