/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.FileInfo;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.WeChatUser;
import wang.yongrui.learningjoy.wechat.miniprogram.service.FileInfoService;

/**
 * @author Wang Yongrui
 *
 */
@RestController
@RequestMapping("/file")
public class FileInfoController {

	@Autowired
	private HttpSession session;

	@Autowired
	private FileInfoService fileInfoService;

	@PostMapping
	public ResponseEntity<FileInfo> create(@RequestParam(value = "file") MultipartFile file) {
		WeChatUser weChatUser = (WeChatUser) session.getAttribute("CURRENT_USER");

		return new ResponseEntity<>(fileInfoService.create(file, weChatUser.getWeChatInfo().getUnionId()),
				HttpStatus.CREATED);
	}

}
