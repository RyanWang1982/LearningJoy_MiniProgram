/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import static org.springframework.beans.BeanUtils.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.FileInfoEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.HomeworkEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.FileInfo;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Homework;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.HomeworkCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.FileInfoRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.HomeworkRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.WeChatUserRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.service.FileInfoService;
import wang.yongrui.learningjoy.wechat.miniprogram.service.HomeworkService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class HomeworkServiceImpl implements HomeworkService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private WeChatUserRepository userRepository;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Autowired
	private FileInfoService fileInfoService;

	@Autowired
	private HomeworkRepository homeworkRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.HomeworkService#
	 * create(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Homework)
	 */
	@Override
	public Homework create(Homework homework) {
		HomeworkEntity homeworkEntity = new HomeworkEntity();
		copyProperties(homework, homeworkEntity);

		WeChatUserEntity studentEntity = userRepository.findOne(homework.getStudent().getId());
		homeworkEntity.setStudentEntity(studentEntity);

		Set<Long> ids = new HashSet<>();
		for (FileInfo fileInfo : homework.getFileInfoSet()) {
			ids.add(fileInfo.getId());
		}
		Set<FileInfoEntity> fileInfoEntitySet = new HashSet<>();
		fileInfoEntitySet.addAll(fileInfoRepository.findAll(ids));
		homeworkEntity.setFileInfoEntitySet(fileInfoEntitySet);
		fileInfoService.moveFromTempToUserRoot(ids);

		homeworkEntity = homeworkRepository.saveAndFlush(homeworkEntity);
		entityManager.refresh(homeworkEntity);

		return retrieve(homeworkEntity.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.HomeworkService#
	 * retrieve(java.lang.Long)
	 */
	@Override
	public Homework retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.HomeworkService#
	 * retrieveAllByCriteria(wang.yongrui.learningjoy.wechat.miniprogram.entity.
	 * web.criteria.HomeworkCriteria)
	 */
	@Override
	public Set<Homework> retrieveAllByCriteria(HomeworkCriteria homeworkCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.HomeworkService#
	 * patchUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.
	 * Homework)
	 */
	@Override
	public Homework patchUpdate(Homework homework) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.HomeworkService#
	 * putUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.
	 * Homework)
	 */
	@Override
	public Homework putUpdate(Homework homework) {
		// TODO Auto-generated method stub
		return null;
	}

}
