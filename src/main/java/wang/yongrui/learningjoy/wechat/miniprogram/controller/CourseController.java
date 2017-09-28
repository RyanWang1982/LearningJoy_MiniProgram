/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping
	public ResponseEntity<Course> create(@RequestBody Course course) {
		return new ResponseEntity<>(courseService.create(course), HttpStatus.CREATED);
	}

	@GetMapping("/{courseId}")
	public ResponseEntity<Course> getCourser(@PathVariable Long courseId) {
		return new ResponseEntity<>(courseService.retrieveOne(courseId), HttpStatus.OK);
	}

	@GetMapping("/byTeacher/{weChatUnionId}")
	public ResponseEntity<Page<Course>> getCourseSetByTeacher(@PathVariable String weChatUnionId, Pageable pageable) {
		return new ResponseEntity<>(courseService.retrieveByTeacher(weChatUnionId, pageable), HttpStatus.OK);
	}

	@GetMapping("/byStudent/{weChatUnionId}")
	public ResponseEntity<Page<Course>> getCourseSetByStudent(@PathVariable String weChatUnionId, Pageable pageable) {
		return new ResponseEntity<>(courseService.retrieveByStudent(weChatUnionId, pageable), HttpStatus.OK);
	}

}
