/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.CourseCriteria;

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
	 * @param id
	 * @return
	 */
	public Course retrieve(Long id);

	/**
	 * @param id
	 * @return
	 */
	public Course retrieveWithTeachers(Long id);

	/**
	 * @param id
	 * @return
	 */
	public Course retrieveWithStudents(Long id);

	/**
	 * @param courseCriteria
	 * @param pageable
	 * @return
	 */
	public Page<Course> retrievePagination(CourseCriteria courseCriteria, Pageable pageable);

	/**
	 * @param course
	 * @return
	 */
	public Course patchUpdate(Course course);

	/**
	 * @param course
	 * @return
	 */
	public Course putUpdate(Course course);

	/**
	 * @param teacherIdSet
	 * @return
	 */
	public Course deleteRelationWithTeachers(Set<Long> teacherIdSet);

	/**
	 * @param studentIdSet
	 * @return
	 */
	public Course deleteRelationWithStudents(Set<Long> studentIdSet);

}
