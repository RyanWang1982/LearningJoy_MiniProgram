/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.basic;

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
public class HomeworkBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private ScoreType scoreType;

	private Integer score;

	private String good;

	private String poor;

	private String attention;

}
