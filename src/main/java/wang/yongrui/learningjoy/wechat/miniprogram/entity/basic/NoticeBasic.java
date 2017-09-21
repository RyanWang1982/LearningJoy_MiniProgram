/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.basic;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.enumeration.Priority;
import wang.yongrui.wechat.fundamental.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class NoticeBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String subject;

	private String description;

	private Calendar deadline;

	private Priority priority;

}
