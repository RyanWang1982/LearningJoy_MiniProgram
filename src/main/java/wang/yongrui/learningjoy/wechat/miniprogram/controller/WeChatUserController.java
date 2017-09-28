/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@GetMapping("/{weChatUnionId}/basicInfo")
	public ResponseEntity<WeChatUser> getUserBasicInfo(@PathVariable String weChatUnionId) {
		return new ResponseEntity<>(userService.retrieveBasicInfo(weChatUnionId), HttpStatus.OK);
	}

}
