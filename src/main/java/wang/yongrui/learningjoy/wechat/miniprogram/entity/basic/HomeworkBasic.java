/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.basic;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.enumeration.ScoreType;
import wang.yongrui.wechat.fundamental.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class HomeworkBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private ScoreType scoreType;

	private Integer score;

	private String good;

	private String poor;

	private String attention;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Calendar deadline;

}
