/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.HomeworkBasic;

/**
 * @author Wang Yongrui
 *
 */
@Entity(name = "HOMEWORK")
@Getter
@Setter
public class HomeworkEntity extends HomeworkBasic {

	@OneToOne
	private WeChatUserEntity studentEntity;

	@OneToMany
	@JoinTable(name = "HOMEWORK_FILE", joinColumns = { @JoinColumn(name = "homework_id") }, inverseJoinColumns = {
			@JoinColumn(name = "file_id") })
	private Set<FileEntity> fileEntitySet;

}
