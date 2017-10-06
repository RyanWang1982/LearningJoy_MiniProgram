/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course;
import wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	/**
	 * @param course
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Course> create(@RequestBody Course course) {
		return new ResponseEntity<>(courseService.create(course), HttpStatus.CREATED);
	}

	/**
	 * @param courseId
	 * @return
	 */
	@GetMapping("/{courseId}")
	public ResponseEntity<Course> getCourse(@PathVariable Long courseId) {
		return new ResponseEntity<>(courseService.retrieveOne(courseId), HttpStatus.OK);
	}

	/**
	 * @param teacherId
	 * @param pageable
	 * @return
	 */
	@GetMapping("/byTeacher/{teacherId}")
	public ResponseEntity<Page<Course>> getCoursePageByTeacher(@PathVariable Long teacherId, Pageable pageable) {
		return new ResponseEntity<>(courseService.retrieveByTeacher(teacherId, pageable), HttpStatus.OK);
	}

	/**
	 * @param studentId
	 * @param pageable
	 * @return
	 */
	@GetMapping("/byStudent/{studentId}")
	public ResponseEntity<Page<Course>> getCoursePageByStudent(@PathVariable Long studentId, Pageable pageable) {
		return new ResponseEntity<>(courseService.retrieveByStudent(studentId, pageable), HttpStatus.OK);
	}

	/**
	 * @param course
	 * @return
	 */
	@PatchMapping
	public ResponseEntity<Course> patchUpdate(@RequestBody Course course) {
		return new ResponseEntity<>(courseService.patchUpdate(course), HttpStatus.OK);
	}

	/**
	 * @param course
	 * @return
	 */
	@PutMapping
	public ResponseEntity<Course> putUpdate(@RequestBody Course course) {
		return new ResponseEntity<>(courseService.putUpdate(course), HttpStatus.OK);
	}

	/**
	 * @param courseId
	 * @return
	 */
	@DeleteMapping("/{courseId}")
	public ResponseEntity<Course> closeCourse(@PathVariable Long courseId) {
		return new ResponseEntity<>(courseService.close(courseId), HttpStatus.OK);
	}

	/**
	 * @param teacherIdSet
	 * @return
	 */
	@DeleteMapping("/relationOfTeachers")
	public ResponseEntity<Course> deleteRelationOfTeachers(@RequestBody Set<Long> teacherIdSet) {
		return new ResponseEntity<>(courseService.deleteRelationOfTeachers(teacherIdSet), HttpStatus.OK);
	}

	/**
	 * @param studentIdSet
	 * @return
	 */
	@DeleteMapping("/relationOfStudents")
	public ResponseEntity<Course> deleteRelationOfStudents(@RequestBody Set<Long> studentIdSet) {
		return new ResponseEntity<>(courseService.deleteRelationOfStudents(studentIdSet), HttpStatus.OK);
	}

}
