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
import wang.yongrui.learningjoy.wechat.miniprogram.entity.enumeration.FileType;
import wang.yongrui.wechat.fundamental.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class FileInfoBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private FileType type;

	@Column(nullable = false)
	private String path;

	@Column(nullable = false)
	private String url;

	private Boolean temporary;

}
