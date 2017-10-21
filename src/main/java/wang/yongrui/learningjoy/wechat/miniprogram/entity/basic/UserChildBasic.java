/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.basic;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.fundamental.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class UserChildBasic extends AuditingEntity {

	private String relationshipFromParentToChild;

}
