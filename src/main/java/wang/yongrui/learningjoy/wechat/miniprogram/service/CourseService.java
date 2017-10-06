/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service;

import java.util.Set;

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
	 * @param teacherId
	 * @param pageable
	 * @return
	 */
	public Page<Course> retrieveByTeacher(Long teacherId, Pageable pageable);

	/**
	 * @param studentId
	 * @param pageable
	 * @return
	 */
	public Page<Course> retrieveByStudent(Long studentId, Pageable pageable);

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
	 * @param courseId
	 * @return
	 */
	public Course close(Long courseId);

	/**
	 * @param teacherIdSet
	 * @return
	 */
	public Course deleteRelationOfTeachers(Set<Long> teacherIdSet);

	/**
	 * @param studentIdSet
	 * @return
	 */
	public Course deleteRelationOfStudents(Set<Long> studentIdSet);

}
