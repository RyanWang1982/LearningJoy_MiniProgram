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
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.UserChildBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "USER_CHILD")
@IdClass(UserChildKey.class)
@Getter
@Setter
public class UserChildEntity extends UserChildBasic {

	@ManyToOne
	@Id
	private WeChatUserEntity user;

	@ManyToOne
	@Id
	private WeChatUserEntity child;

}
