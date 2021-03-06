/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.basic;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
public class StudentPerformanceBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private ScoreType scoreType;

	@Column(nullable = false)
	private Integer score;

	private String good;

	private String improvable;

	private String attention;

}
