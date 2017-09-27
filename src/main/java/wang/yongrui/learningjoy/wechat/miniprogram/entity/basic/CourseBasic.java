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
import wang.yongrui.wechat.fundamental.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class CourseBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	private String grade;

	private String address;

	private Calendar startDate;

	private Calendar endDate;

	private String time;

	private String recurringRule;// Using the way as @Scheduled

	private Integer recurringTimes;

	private Integer duration;

}
