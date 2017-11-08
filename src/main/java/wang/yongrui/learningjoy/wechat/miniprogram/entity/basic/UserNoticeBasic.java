/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.basic;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.enumeration.NoticeStatus;
import wang.yongrui.wechat.fundamental.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class UserNoticeBasic extends AuditingEntity {

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private NoticeStatus status;

}
