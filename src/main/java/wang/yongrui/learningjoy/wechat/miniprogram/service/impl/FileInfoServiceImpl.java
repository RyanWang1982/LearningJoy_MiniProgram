/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.FileInfo;
import wang.yongrui.learningjoy.wechat.miniprogram.service.FileInfoService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class FileInfoServiceImpl implements FileInfoService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.FileInfoService#
	 * create(org.springframework.web.multipart.MultipartFile)
	 */
	@Override
	public FileInfo create(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

}
