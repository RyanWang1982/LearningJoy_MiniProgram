/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.basic;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Calendar dateTime;

	private String address;

	private Integer duration;

}
