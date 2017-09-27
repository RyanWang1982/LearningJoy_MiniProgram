/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.basic;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class UserSettingBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private boolean teacherFunctionEnabled;

	private boolean parentFunctionEnabled;

	private boolean studentFunctionEnabled;

	private String courseReminderRule;

	private String lessonReminderRule;

	private String noticeReminderRule;

}
