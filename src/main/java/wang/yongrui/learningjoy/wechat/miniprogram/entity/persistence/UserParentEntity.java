/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.UserParentBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "USER_PARENT")
@IdClass(UserParentKey.class)
@Getter
@Setter
public class UserParentEntity extends UserParentBasic {

	@ManyToOne
	@Id
	private WeChatUserEntity user;

	@ManyToOne
	@Id
	private WeChatUserEntity parent;

}
