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

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.StudentPerformance;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.StudentPerformanceCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.service.StudentPerformanceService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/studentPerformance")
public class StudentPerformanceController {

	@Autowired
	private StudentPerformanceService studentPerformanceService;

	/**
	 * @param studentPerformance
	 * @return
	 */
	@PostMapping
	public ResponseEntity<StudentPerformance> create(@RequestBody StudentPerformance studentPerformance) {
		return new ResponseEntity<>(studentPerformanceService.create(studentPerformance), HttpStatus.CREATED);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<StudentPerformance> retrieve(@PathVariable Long id) {
		return new ResponseEntity<>(studentPerformanceService.retrieve(id), HttpStatus.OK);
	}

	/**
	 * @param studentPerformanceCriteria
	 * @return
	 */
	@PostMapping("/retrieval")
	public ResponseEntity<Set<StudentPerformance>> retrieveAllByCriteria(
			@RequestBody StudentPerformanceCriteria studentPerformanceCriteria) {
		return new ResponseEntity<>(studentPerformanceService.retrieveAllByCriteria(studentPerformanceCriteria),
				HttpStatus.OK);
	}

	/**
	 * @param studentPerformance
	 * @return
	 */
	@PatchMapping
	public ResponseEntity<StudentPerformance> patchUpdate(@RequestBody StudentPerformance studentPerformance) {
		return new ResponseEntity<>(studentPerformanceService.patchUpdate(studentPerformance), HttpStatus.OK);
	}

	/**
	 * @param studentPerformance
	 * @return
	 */
	@PutMapping
	public ResponseEntity<StudentPerformance> putUpdate(@RequestBody StudentPerformance studentPerformance) {
		return new ResponseEntity<>(studentPerformanceService.putUpdate(studentPerformance), HttpStatus.OK);
	}

}
