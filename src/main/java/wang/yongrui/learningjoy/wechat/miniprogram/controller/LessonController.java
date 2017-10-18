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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Lesson;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.LessonCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/lesson")
public class LessonController {

	@Autowired
	private LessonService lessonService;

	/**
	 * @param lesson
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Lesson> create(@RequestBody Lesson lesson) {
		return new ResponseEntity<>(lessonService.create(lesson), HttpStatus.CREATED);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Lesson> retrieve(@PathVariable Long id) {
		return new ResponseEntity<>(lessonService.retrieve(id), HttpStatus.OK);
	}

	/**
	 * @param lessonCriteria
	 * @param pageable
	 * @return
	 */
	@PostMapping("/pagination")
	public ResponseEntity<Page<Lesson>> retrievePagination(@RequestBody LessonCriteria lessonCriteria,
			Pageable pageable) {
		return new ResponseEntity<>(lessonService.retrievePagination(lessonCriteria, pageable), HttpStatus.OK);
	}

	/**
	 * @param lesson
	 * @return
	 */
	@PatchMapping
	public ResponseEntity<Lesson> patchUpdate(@RequestBody Lesson lesson) {
		return new ResponseEntity<>(lessonService.patchUpdate(lesson), HttpStatus.OK);
	}

	/**
	 * @param lesson
	 * @return
	 */
	@PutMapping
	public ResponseEntity<Lesson> putUpdate(@RequestBody Lesson lesson) {
		return new ResponseEntity<>(lessonService.putUpdate(lesson), HttpStatus.OK);
	}

}
