/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wang.yongrui.learningjoy.wechat.miniprogram.controller.validation.CourseCreationValidator;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.CourseCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private CourseService courseService;

	@InitBinder
	public void initBainder(DataBinder binder) {
		if ("POST".equals(request.getMethod()) && "/course".equals(request.getServletPath())) {
			binder.replaceValidators(new CourseCreationValidator());
		}
	}

	/**
	 * @param course
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Course> create(@Validated @RequestBody Course course) {
		return new ResponseEntity<>(courseService.create(course), HttpStatus.CREATED);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Course> retrieve(@PathVariable Long id) {
		return new ResponseEntity<>(courseService.retrieve(id), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/withTeachers")
	public ResponseEntity<Course> retrieveWithTeachers(@PathVariable Long id) {
		return new ResponseEntity<>(courseService.retrieveWithTeachers(id), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}/withStudents")
	public ResponseEntity<Course> retrieveWithStudents(@PathVariable Long id) {
		return new ResponseEntity<>(courseService.retrieveWithStudents(id), HttpStatus.OK);
	}

	/**
	 * @param courseCriteria
	 * @param pageable
	 * @return
	 */
	@PostMapping("/pagination")
	public ResponseEntity<Page<Course>> retrievePagination(@RequestBody CourseCriteria courseCriteria,
			Pageable pageable) {
		return new ResponseEntity<>(courseService.retrievePagination(courseCriteria, pageable), HttpStatus.OK);
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
	 * @param id
	 * @param teacherIdSet
	 * @return
	 */
	@DeleteMapping("/{id}/relationWithTeachers")
	public ResponseEntity<Course> deleteRelationWithTeachers(@PathVariable Long id,
			@RequestBody Set<Long> teacherIdSet) {
		return new ResponseEntity<>(courseService.deleteRelationWithTeachers(id, teacherIdSet), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @param studentIdSet
	 * @return
	 */
	@DeleteMapping("/{id}/relationWithStudents")
	public ResponseEntity<Course> deleteRelationWithStudents(@PathVariable Long id,
			@RequestBody Set<Long> studentIdSet) {
		return new ResponseEntity<>(courseService.deleteRelationWithStudents(id, studentIdSet), HttpStatus.OK);
	}

}
