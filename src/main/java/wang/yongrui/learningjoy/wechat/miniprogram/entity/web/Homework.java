/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import static wang.yongrui.learningjoy.wechat.miniprogram.util.EntityUtils.*;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.HomeworkBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.HomeworkEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonInclude(value = Include.NON_EMPTY)
public class Homework extends HomeworkBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private WeChatUser student;

	@Getter
	@Setter
	private Set<FileInfo> fileSet;

	/**
	 * 
	 */
	public Homework() {
		super();
	}

	/**
	 * @param homeworkEntity
	 */
	public Homework(HomeworkEntity homeworkEntity) {
		super();
		if (null != homeworkEntity) {
			BeanUtils.copyProperties(homeworkEntity, this);
			if (null != homeworkEntity.getStudentEntity()) {
				WeChatUser student = new WeChatUser();
				BeanUtils.copyProperties(homeworkEntity.getStudentEntity(), student);
			}
			setFileSet(getTargetSetFromSourceSet(homeworkEntity.getFileEntitySet(), FileInfo.class));
		}
	}

}
