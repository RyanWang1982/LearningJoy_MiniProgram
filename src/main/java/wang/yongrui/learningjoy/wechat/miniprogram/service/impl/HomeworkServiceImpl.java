/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import static org.springframework.beans.BeanUtils.*;
import static wang.yongrui.learningjoy.wechat.miniprogram.util.PatchBeanUtils.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.FileInfoEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.HomeworkEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.HomeworkEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.LessonEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.LessonEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity_;
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
		return new Homework(homeworkRepository.findOne(id));
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
		List<HomeworkEntity> allHomeworkEntityList = homeworkRepository
				.findAll((root, criteriaQuery, criteriaBuilder) -> {
					criteriaQuery.distinct(true);
					Join<LessonEntity, CourseEntity> courseJoin = root.join(HomeworkEntity_.lessonEntity)
							.join(LessonEntity_.courseEntity);
					Join<HomeworkEntity, LessonEntity> lessonJoin = root.join(HomeworkEntity_.lessonEntity);
					Join<HomeworkEntity, WeChatUserEntity> studentJoin = root.join(HomeworkEntity_.studentEntity);

					Predicate restrictions = criteriaBuilder.conjunction();
					if (null != homeworkCriteria.getCourseId()) {
						restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
								.equal(courseJoin.get(CourseEntity_.id), homeworkCriteria.getCourseId()));
					}
					if (null != homeworkCriteria.getLessonId()) {
						restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
								.equal(lessonJoin.get(LessonEntity_.id), homeworkCriteria.getLessonId()));
					}
					if (null != homeworkCriteria.getStudentId()) {
						restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
								.greaterThan(studentJoin.get(WeChatUserEntity_.id), homeworkCriteria.getStudentId()));
					}
					if (null != homeworkCriteria.getStartDate()) {
						restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
								.greaterThan(root.get(HomeworkEntity_.deadline), homeworkCriteria.getStartDate()));
					}
					if (null != homeworkCriteria.getEndDate()) {
						restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
								.lessThan(root.get(HomeworkEntity_.deadline), homeworkCriteria.getEndDate()));
					}
					return restrictions;
				});

		Set<Homework> allHomeworkSet = new LinkedHashSet<>();
		for (HomeworkEntity eachHomeworkEntity : allHomeworkEntityList) {
			allHomeworkSet.add(new Homework(eachHomeworkEntity));
		}

		return allHomeworkSet;
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
		HomeworkEntity homeworkEntity = homeworkRepository.findOne(homework.getId());
		updateProperties(homework, homeworkEntity, false);
		homeworkEntity = homeworkRepository.saveAndFlush(homeworkEntity);
		return new Homework(homeworkEntity);
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
		HomeworkEntity homeworkEntity = homeworkRepository.findOne(homework.getId());
		updateProperties(homework, homeworkEntity, true);
		homeworkEntity = homeworkRepository.saveAndFlush(homeworkEntity);
		return new Homework(homeworkEntity);
	}

}
