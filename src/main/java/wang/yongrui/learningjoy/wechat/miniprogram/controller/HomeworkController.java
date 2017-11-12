/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Homework;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.HomeworkCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.service.HomeworkService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/homework")
public class HomeworkController {

	@Autowired
	private HomeworkService homeworkService;

	/**
	 * @param homework
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Homework> create(@RequestBody Homework homework) {
		return new ResponseEntity<>(homeworkService.create(homework), HttpStatus.CREATED);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Homework> retrieve(@PathVariable Long id) {
		return new ResponseEntity<>(homeworkService.retrieve(id), HttpStatus.OK);
	}

	/**
	 * @param homeworkCriteria
	 * @return
	 */
	@PostMapping("/retrieval")
	public ResponseEntity<Set<Homework>> retrieveAllByCriteria(@RequestBody HomeworkCriteria homeworkCriteria) {
		return new ResponseEntity<>(homeworkService.retrieveAllByCriteria(homeworkCriteria), HttpStatus.OK);
	}

	/**
	 * @param homework
	 * @return
	 */
	@PatchMapping
	public ResponseEntity<Homework> patchUpdate(@RequestBody Homework homework) {
		return new ResponseEntity<>(homeworkService.patchUpdate(homework), HttpStatus.OK);
	}

	/**
	 * @param homework
	 * @return
	 */
	@PutMapping
	public ResponseEntity<Homework> putUpdate(@RequestBody Homework homework) {
		return new ResponseEntity<>(homeworkService.putUpdate(homework), HttpStatus.OK);
	}

}
