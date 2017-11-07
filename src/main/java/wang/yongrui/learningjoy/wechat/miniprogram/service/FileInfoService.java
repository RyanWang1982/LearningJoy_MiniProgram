/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service;

import org.springframework.web.multipart.MultipartFile;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.FileInfo;

/**
 * @author Wang Yongrui
 *
 */
public interface FileInfoService {

	/**
	 * @param file
	 * @return
	 */
	public FileInfo create(MultipartFile file);

}
