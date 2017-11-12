/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.entity.web;

import static wang.yongrui.learningjoy.wechat.miniprogram.util.EntityUtils.*;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.metamodel.Attribute;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.StudentPerformanceBasic;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.StudentPerformanceEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.StudentPerformanceEntity_;

/**
 * @author Wang Yongrui
 *
 */
@Getter
@Setter
@JsonInclude(value = Include.NON_EMPTY)
public class StudentPerformance extends StudentPerformanceBasic implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Lesson lesson;

	private WeChatUser student;

	private Set<FileInfo> fileSet;

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
			setFileSet(getTargetSetFromSourceSet(studentPerformanceEntity.getFileEntitySet(), FileInfo.class));
		}
	}

	/**
	 * @param studentPerformanceEntity
	 * @param includedAttributeSet
	 */
	public StudentPerformance(StudentPerformanceEntity studentPerformanceEntity,
			Set<Attribute<?, ?>> includedAttributeSet) {
		super();
		if (null != studentPerformanceEntity) {
			BeanUtils.copyProperties(studentPerformanceEntity, this);
			if (includedAttributeSet.contains(StudentPerformanceEntity_.lessonEntity)) {
				Lesson lesson = new Lesson();
				BeanUtils.copyProperties(studentPerformanceEntity.getLessonEntity(), lesson);
				setLesson(lesson);
			}
			if (includedAttributeSet.contains(StudentPerformanceEntity_.studentEntity)) {
				WeChatUser student = new WeChatUser();
				BeanUtils.copyProperties(studentPerformanceEntity.getStudentEntity(), student);
				setStudent(student);
			}
			setFileSet(getTargetSetFromSourceSet(studentPerformanceEntity.getFileEntitySet(), FileInfo.class));
		}
	}

}
