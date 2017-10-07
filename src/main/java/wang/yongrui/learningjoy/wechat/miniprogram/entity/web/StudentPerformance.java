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
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.StudentPerformanceBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.StudentPerformanceEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonInclude(value = Include.NON_EMPTY)
public class StudentPerformance extends StudentPerformanceBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private WeChatUser student;

	@Getter
	@Setter
	private Set<File> fileSet;

	/**
	 * 
	 */
	public StudentPerformance() {
		super();
	}

	/**
	 * @param studentPerformanceEntity
	 */
	public StudentPerformance(StudentPerformanceEntity studentPerformanceEntity) {
		super();
		if (null != studentPerformanceEntity) {
			BeanUtils.copyProperties(studentPerformanceEntity, this);
			if (null != studentPerformanceEntity.getStudentEntity()) {
				WeChatUser student = new WeChatUser();
				BeanUtils.copyProperties(studentPerformanceEntity.getStudentEntity(), student);
			}
			setFileSet(getObjectSetFromEntitySet(studentPerformanceEntity.getFileEntitySet(), File.class));
		}
	}

}
