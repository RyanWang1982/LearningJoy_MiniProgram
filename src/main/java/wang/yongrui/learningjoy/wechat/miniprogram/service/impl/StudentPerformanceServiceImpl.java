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
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.LessonEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.LessonEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.StudentPerformanceEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.StudentPerformanceEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.FileInfo;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.StudentPerformance;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.StudentPerformanceCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.FileInfoRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.StudentPerformanceRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.WeChatUserRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.service.FileInfoService;
import wang.yongrui.learningjoy.wechat.miniprogram.service.StudentPerformanceService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class StudentPerformanceServiceImpl implements StudentPerformanceService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private WeChatUserRepository userRepository;

	@Autowired
	private FileInfoRepository fileInfoRepository;

	@Autowired
	private FileInfoService fileInfoService;

	@Autowired
	private StudentPerformanceRepository studentPerformanceRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.
	 * StudentPerformanceService#create(wang.yongrui.learningjoy.wechat.
	 * miniprogram.entity.web.StudentPerformance)
	 */
	@Override
	public StudentPerformance create(StudentPerformance studentPerformance) {
		StudentPerformanceEntity studentPerformanceEntity = new StudentPerformanceEntity();
		copyProperties(studentPerformance, studentPerformanceEntity);

		WeChatUserEntity studentEntity = userRepository.findOne(studentPerformance.getStudent().getId());
		studentPerformanceEntity.setStudentEntity(studentEntity);

		Set<Long> ids = new HashSet<>();
		for (FileInfo fileInfo : studentPerformance.getFileInfoSet()) {
			ids.add(fileInfo.getId());
		}
		Set<FileInfoEntity> fileInfoEntitySet = new HashSet<>();
		fileInfoEntitySet.addAll(fileInfoRepository.findAll(ids));
		studentPerformanceEntity.setFileInfoEntitySet(fileInfoEntitySet);
		fileInfoService.moveFromTempToUserRoot(ids);

		studentPerformanceEntity = studentPerformanceRepository.saveAndFlush(studentPerformanceEntity);
		entityManager.refresh(studentPerformanceEntity);

		return retrieve(studentPerformanceEntity.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.
	 * StudentPerformanceService#retrieve(java.lang.Long)
	 */
	@Override
	public StudentPerformance retrieve(Long id) {
		return new StudentPerformance(studentPerformanceRepository.findOne(id));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.
	 * StudentPerformanceService#retrieveAllByCriteria(wang.yongrui.learningjoy.
	 * wechat.miniprogram.entity.web.criteria.StudentPerformanceCriteria)
	 */
	@Override
	public Set<StudentPerformance> retrieveAllByCriteria(StudentPerformanceCriteria studentPerformanceCriteria) {
		List<StudentPerformanceEntity> allStudentPerformanceEntityList = studentPerformanceRepository
				.findAll((root, criteriaQuery, criteriaBuilder) -> {
					criteriaQuery.distinct(true);
					Join<LessonEntity, CourseEntity> courseJoin = root.join(StudentPerformanceEntity_.lessonEntity)
							.join(LessonEntity_.courseEntity);
					Join<StudentPerformanceEntity, LessonEntity> lessonJoin = root
							.join(StudentPerformanceEntity_.lessonEntity);
					Join<StudentPerformanceEntity, WeChatUserEntity> studentJoin = root
							.join(StudentPerformanceEntity_.studentEntity);

					Predicate restrictions = criteriaBuilder.conjunction();
					if (null != studentPerformanceCriteria.getCourseId()) {
						restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
								.equal(courseJoin.get(CourseEntity_.id), studentPerformanceCriteria.getCourseId()));
					}
					if (null != studentPerformanceCriteria.getLessonId()) {
						restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
								.equal(lessonJoin.get(LessonEntity_.id), studentPerformanceCriteria.getLessonId()));
					}
					if (null != studentPerformanceCriteria.getStudentId()) {
						restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThan(
								studentJoin.get(WeChatUserEntity_.id), studentPerformanceCriteria.getStudentId()));
					}
					if (null != studentPerformanceCriteria.getStartDate()) {
						restrictions = criteriaBuilder.and(restrictions,
								criteriaBuilder.greaterThan(root.get(StudentPerformanceEntity_.lastModifiedDate),
										studentPerformanceCriteria.getStartDate()));
					}
					if (null != studentPerformanceCriteria.getEndDate()) {
						restrictions = criteriaBuilder.and(restrictions,
								criteriaBuilder.lessThan(root.get(StudentPerformanceEntity_.lastModifiedDate),
										studentPerformanceCriteria.getEndDate()));
					}
					return restrictions;
				});

		Set<StudentPerformance> allStudentPerformanceSet = new LinkedHashSet<>();
		for (StudentPerformanceEntity eachStudentPerformanceEntity : allStudentPerformanceEntityList) {
			allStudentPerformanceSet.add(new StudentPerformance(eachStudentPerformanceEntity));
		}

		return allStudentPerformanceSet;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.
	 * StudentPerformanceService#patchUpdate(wang.yongrui.learningjoy.wechat.
	 * miniprogram.entity.web.StudentPerformance)
	 */
	@Override
	public StudentPerformance patchUpdate(StudentPerformance studentPerformance) {
		StudentPerformanceEntity studentPerformanceEntity = studentPerformanceRepository
				.findOne(studentPerformance.getId());
		updateProperties(studentPerformance, studentPerformanceEntity, false);
		studentPerformanceEntity = studentPerformanceRepository.saveAndFlush(studentPerformanceEntity);
		return new StudentPerformance(studentPerformanceEntity);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.
	 * StudentPerformanceService#putUpdate(wang.yongrui.learningjoy.wechat.
	 * miniprogram.entity.web.StudentPerformance)
	 */
	@Override
	public StudentPerformance putUpdate(StudentPerformance studentPerformance) {
		StudentPerformanceEntity studentPerformanceEntity = studentPerformanceRepository
				.findOne(studentPerformance.getId());
		updateProperties(studentPerformance, studentPerformanceEntity, true);
		studentPerformanceEntity = studentPerformanceRepository.saveAndFlush(studentPerformanceEntity);
		return new StudentPerformance(studentPerformanceEntity);
	}

}
