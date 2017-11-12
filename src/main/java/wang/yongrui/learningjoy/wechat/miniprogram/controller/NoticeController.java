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

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Notice;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.NoticeCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.service.NoticeService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	/**
	 * @param notice
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Notice> create(@RequestBody Notice notice) {
		return new ResponseEntity<>(noticeService.create(notice), HttpStatus.CREATED);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Notice> retrieve(@PathVariable Long id) {
		return new ResponseEntity<>(noticeService.retrieve(id), HttpStatus.OK);
	}

	/**
	 * @param noticeCriteria
	 * @return
	 */
	@PostMapping("/retrieval")
	public ResponseEntity<Set<Notice>> retrieveAllByCriteria(@RequestBody NoticeCriteria noticeCriteria) {
		return new ResponseEntity<>(noticeService.retrieveAllByCriteria(noticeCriteria), HttpStatus.OK);
	}

	/**
	 * @param notice
	 * @return
	 */
	@PatchMapping
	public ResponseEntity<Notice> patchUpdate(@RequestBody Notice notice) {
		return new ResponseEntity<>(noticeService.patchUpdate(notice), HttpStatus.OK);
	}

	/**
	 * @param notice
	 * @return
	 */
	@PutMapping
	public ResponseEntity<Notice> putUpdate(@RequestBody Notice notice) {
		return new ResponseEntity<>(noticeService.putUpdate(notice), HttpStatus.OK);
	}

}
