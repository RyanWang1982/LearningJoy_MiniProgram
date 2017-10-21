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
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.UserChildBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.UserChildEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonInclude(value = Include.NON_EMPTY)
public class UserChild extends UserChildBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private WeChatUser child;

	/**
	 *
	 */
	public UserChild() {
		super();
	}

	/**
	 * @param userChildEntity
	 */
	public UserChild(UserChildEntity userChildEntity) {
		super();
		if (null != userChildEntity) {
			BeanUtils.copyProperties(userChildEntity, this);
			setChild(new WeChatUser(userChildEntity.getChild()));
		}
	}

}
