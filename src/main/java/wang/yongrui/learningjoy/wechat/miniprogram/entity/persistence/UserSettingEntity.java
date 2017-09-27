/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.UserSettingBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "USER_SETTING")
@Getter
@Setter
public class UserSettingEntity extends UserSettingBasic {

}
