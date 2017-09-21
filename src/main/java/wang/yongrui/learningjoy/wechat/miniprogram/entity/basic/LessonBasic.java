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
public class LessonBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	private Calendar date;

	private String address;

	private Integer duration;

}
