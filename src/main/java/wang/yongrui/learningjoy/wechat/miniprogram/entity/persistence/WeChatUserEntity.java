/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.WeChatUserBasic;
import wang.yongrui.wechat.fundamental.entity.persistence.UserEntity;

/**
 * @author Wang Yongrui
 *
 */
@Entity
@DiscriminatorValue("wechat")
@Getter
@Setter
public class WeChatUserEntity extends UserEntity {

	private WeChatUserBasic weChatUserBasic;

}
