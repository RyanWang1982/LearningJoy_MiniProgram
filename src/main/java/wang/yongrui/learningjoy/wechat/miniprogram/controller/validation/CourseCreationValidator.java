/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.controller.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course;

/**
 * @author Wang Yongrui
 *
 */
public class CourseCreationValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Course.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Course targetCourse = (Course) target;
		if (null == targetCourse.getName() || StringUtils.isBlank(targetCourse.getName())) {
			errors.reject("course.creation.name", null, "course.creation.name.invalid.message");
		}
		if (null == targetCourse.getAddress() || StringUtils.isBlank(targetCourse.getAddress())) {
			errors.reject("course.creation.address", null, "course.creation.address.invalid.message");
		}
	}

}
