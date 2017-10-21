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
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.UserParentBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.UserParentEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonInclude(value = Include.NON_EMPTY)
public class UserParent extends UserParentBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private WeChatUser parent;

	/**
	 *
	 */
	public UserParent() {
		super();
	}

	/**
	 * @param userParentEntity
	 */
	public UserParent(UserParentEntity userParentEntity) {
		super();
		if (null != userParentEntity) {
			BeanUtils.copyProperties(userParentEntity, this);
			setParent(new WeChatUser(userParentEntity.getParent()));
		}
	}

}
