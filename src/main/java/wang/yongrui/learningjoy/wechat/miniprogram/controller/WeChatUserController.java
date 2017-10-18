/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.controller;

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

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.WeChatUser;
import wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/user")
public class WeChatUserController {

	@Autowired
	private WeChatUserService userService;

	/**
	 * @param user
	 * @return
	 */
	@PostMapping
	public ResponseEntity<WeChatUser> create(@RequestBody WeChatUser user) {
		return new ResponseEntity<>(userService.create(user), HttpStatus.CREATED);
	}

	/**
	 * @param weChatUnionId
	 * @return
	 */
	@GetMapping("/basicInfo/byWeChatUnionId/{weChatUnionId}")
	public ResponseEntity<WeChatUser> retrieveBasicInfoByWeChatUnionId(@PathVariable String weChatUnionId) {
		return new ResponseEntity<>(userService.retrieveBasicInfoByWeChatUnionId(weChatUnionId), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/basicInfo/{id}")
	public ResponseEntity<WeChatUser> retrieveBasicInfo(@PathVariable Long id) {
		return new ResponseEntity<>(userService.retrieveBasicInfo(id), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/withSetting/{id}")
	public ResponseEntity<WeChatUser> retrieveWithSetting(@PathVariable Long id) {
		return new ResponseEntity<>(userService.retrieveWithSetting(id), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/withChildren/{id}")
	public ResponseEntity<WeChatUser> retrieveWithChildren(@PathVariable Long id) {
		return new ResponseEntity<>(userService.retrieveWithChildren(id), HttpStatus.OK);
	}

	/**
	 * @param user
	 * @return
	 */
	@PatchMapping
	public ResponseEntity<WeChatUser> patchUpdate(@RequestBody WeChatUser user) {
		return new ResponseEntity<>(userService.patchUpdate(user), HttpStatus.OK);
	}

	/**
	 * @param user
	 * @return
	 */
	@PutMapping
	public ResponseEntity<WeChatUser> putUpdate(@RequestBody WeChatUser user) {
		return new ResponseEntity<>(userService.putUpdate(user), HttpStatus.OK);
	}

}
