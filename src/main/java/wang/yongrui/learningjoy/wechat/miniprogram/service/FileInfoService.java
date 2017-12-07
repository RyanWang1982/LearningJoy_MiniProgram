/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.FileInfo;

/**
 * @author Wang Yongrui
 *
 */
public interface FileInfoService {

	/**
	 * @param file
	 * @param weChatUnionId
	 * @return
	 */
	public FileInfo create(MultipartFile file, String weChatUnionId);

	/**
	 * @param fileIdSet
	 * @return
	 */
	public Boolean moveFromTempToUserRoot(Set<Long> fileIdSet);

}
