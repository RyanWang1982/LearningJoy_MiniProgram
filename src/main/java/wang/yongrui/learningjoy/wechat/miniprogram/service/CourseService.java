/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course;

/**
 * @author Wang Yongrui
 *
 */
public interface CourseService {

	/**
	 * @param course
	 * @return
	 */
	public Course create(Course course);

	/**
	 * @param courseId
	 * @return
	 */
	public Course retrieveOne(Long courseId);

	/**
	 * @param weChatUnionId
	 * @param pageable
	 * @return
	 */
	public Page<Course> retrieveByTeacher(String weChatUnionId, Pageable pageable);

	/**
	 * @param weChatUnionId
	 * @param pageable
	 * @return
	 */
	public Page<Course> retrieveByStudent(String weChatUnionId, Pageable pageable);

}
