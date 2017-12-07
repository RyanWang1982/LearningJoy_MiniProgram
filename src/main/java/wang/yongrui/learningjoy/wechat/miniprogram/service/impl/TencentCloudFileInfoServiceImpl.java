/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.request.MoveFileRequest;
import com.qcloud.cos.request.UploadFileRequest;
import com.qcloud.cos.sign.Credentials;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.enumeration.FileType;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.FileInfoEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.FileInfo;
import wang.yongrui.learningjoy.wechat.miniprogram.property.TencentCloudProperty;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.FileInfoRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.service.FileInfoService;

/**
 * @author Wang Yongrui
 *
 */
@Service("tencentCloudImpl")
@Transactional
public class TencentCloudFileInfoServiceImpl implements FileInfoService {

	@Autowired
	private TencentCloudProperty tencentCloudProperty;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.FileInfoService#
	 * create(org.springframework.web.multipart.MultipartFile, java.lang.String)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public FileInfo create(MultipartFile file, String weChatUnionId) {
		long appId = tencentCloudProperty.getCloudAPIAppId();
		String secretId = tencentCloudProperty.getCloudAPISecretId();
		String secretKey = tencentCloudProperty.getCloudAPISecretKey();
		String bucketName = tencentCloudProperty.getCloudAPICosBucket();
		Credentials credentials = new Credentials(appId, secretId, secretKey);

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setRegion("cd");
		COSClient cosClient = new COSClient(clientConfig, credentials);

		FileInfo fileInfo = null;
		try {
			String fileCosPath = "/" + weChatUnionId + "/temp/" + file.getOriginalFilename();
			UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, fileCosPath, file.getBytes());
			String uploadFileRet = cosClient.uploadFile(uploadFileRequest);
			Map<String, Object> tencentCloudCosJsonMap = new HashMap<String, Object>();
			tencentCloudCosJsonMap = new ObjectMapper().readValue(uploadFileRet, tencentCloudCosJsonMap.getClass());
			if (null != tencentCloudCosJsonMap.get("code")
					&& tencentCloudCosJsonMap.get("code").equals(new Integer(0))) {
				FileInfoEntity fileInfoEntity = new FileInfoEntity();
				fileInfoEntity.setName(file.getOriginalFilename());
				fileInfoEntity.setType(FileType.Audio);
				fileInfoEntity.setPath(fileCosPath);
				fileInfoEntity.setUrl((String) ((Map) tencentCloudCosJsonMap.get("data")).get("source_url"));
				fileInfoEntity = fileInfoRepository.saveAndFlush(fileInfoEntity);
				fileInfo = new FileInfo(fileInfoEntity);
			} else {
				throw new RuntimeException("Error occurs, when uploading file to Tencent Cloud: " + fileCosPath
						+ "; errorCode: " + tencentCloudCosJsonMap.get("code") + "; errorMessage: "
						+ tencentCloudCosJsonMap.get("message"));
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return fileInfo;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.FileInfoService#
	 * moveFromTempToUserRoot(java.util.Set)
	 */
	@Override
	public Boolean moveFromTempToUserRoot(Set<Long> fileIdSet) {
		long appId = tencentCloudProperty.getCloudAPIAppId();
		String secretId = tencentCloudProperty.getCloudAPISecretId();
		String secretKey = tencentCloudProperty.getCloudAPISecretKey();
		String bucketName = tencentCloudProperty.getCloudAPICosBucket();
		Credentials credentials = new Credentials(appId, secretId, secretKey);

		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setRegion("cd");
		COSClient cosClient = new COSClient(clientConfig, credentials);

		List<FileInfoEntity> fileInfoEntityList = fileInfoRepository.findAll(fileIdSet);
		for (FileInfoEntity eachFileInfoEntity : fileInfoEntityList) {
			String cosFilePath = eachFileInfoEntity.getPath();
			String dstCosFilePath = cosFilePath.replace("/temp/", "/");
			MoveFileRequest moveRequest = new MoveFileRequest(bucketName, cosFilePath, dstCosFilePath);
			String moveFileResult = cosClient.moveFile(moveRequest);
			eachFileInfoEntity.setPath(dstCosFilePath);
			fileInfoRepository.saveAndFlush(eachFileInfoEntity);
		}

		return true;
	}

}
