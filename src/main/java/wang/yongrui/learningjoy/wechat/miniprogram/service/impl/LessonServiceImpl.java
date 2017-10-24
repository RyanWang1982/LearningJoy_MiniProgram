/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import static org.springframework.beans.BeanUtils.*;
import static wang.yongrui.learningjoy.wechat.miniprogram.util.PatchBeanUtils.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.Attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.LessonEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.LessonEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Lesson;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.LessonCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.CourseRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.LessonRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.WeChatUserRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class LessonServiceImpl implements LessonService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private WeChatUserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private LessonRepository lessonRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService#create(
	 * wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Lesson)
	 */
	@Override
	public Lesson create(Lesson lesson) {
		LessonEntity lessonEntity = new LessonEntity();
		copyProperties(lesson, lessonEntity);
		CourseEntity courseEntity = courseRepository.findOne(lesson.getCourse().getId());
		lessonEntity.setCourseEntity(courseEntity);
		WeChatUserEntity teacherEntity = userRepository.findOne(lesson.getTeacher().getId());
		lessonEntity.setTeacherEntity(teacherEntity);

		lessonEntity = lessonRepository.saveAndFlush(lessonEntity);
		entityManager.refresh(lessonEntity);

		return retrieve(lessonEntity.getId());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService#
	 * retrieve(java.lang.Long)
	 */
	@Override
	public Lesson retrieve(Long id) {
		LessonEntity lessonEntity = lessonRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(LessonEntity_.courseEntity, JoinType.LEFT);
			root.fetch(LessonEntity_.teacherEntity, JoinType.LEFT);
			return criteriaBuilder.equal(root.get(LessonEntity_.id), id);
		});

		Set<Attribute<?, ?>> includedAttributeSet = new HashSet<>();
		includedAttributeSet.add(LessonEntity_.courseEntity);
		includedAttributeSet.add(LessonEntity_.teacherEntity);
		return new Lesson(lessonEntity, includedAttributeSet);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService#
	 * retrievePagination(wang.yongrui.learningjoy.wechat.miniprogram.entity.web
	 * .criteria.LessonCriteria, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Lesson> retrievePagination(LessonCriteria lessonCriteria, Pageable pageable) {
		Page<LessonEntity> lessonEntityPage = lessonRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			Join<LessonEntity, CourseEntity> courseJoin = root.join(LessonEntity_.courseEntity);
			Join<LessonEntity, WeChatUserEntity> teacherJoin = root.join(LessonEntity_.teacherEntity);

			Predicate restrictions = criteriaBuilder.conjunction();
			if (null != lessonCriteria.getCourseId()) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.equal(courseJoin.get(CourseEntity_.id), lessonCriteria.getCourseId()));
			}
			if (null != lessonCriteria.getTeacherId()) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.equal(teacherJoin.get(WeChatUserEntity_.id), lessonCriteria.getTeacherId()));
			}
			if (null != lessonCriteria.getStartDate()) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.greaterThan(root.get(LessonEntity_.dateTime), lessonCriteria.getStartDate()));
			}
			if (null != lessonCriteria.getEndDate()) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.lessThan(root.get(LessonEntity_.dateTime), lessonCriteria.getEndDate()));
			}
			return restrictions;
		}, pageable);

		List<Lesson> lessonList = new ArrayList<>();
		for (LessonEntity eachLessonEntity : lessonEntityPage.getContent()) {
			lessonList.add(new Lesson(eachLessonEntity));
		}

		return new PageImpl<Lesson>(lessonList, pageable, lessonEntityPage.getTotalElements());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService#
	 * patchUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.
	 * Lesson)
	 */
	@Override
	public Lesson patchUpdate(Lesson lesson) {
		LessonEntity lessonEntity = lessonRepository.findOne(lesson.getId());
		updateProperties(lesson, lessonEntity, false);
		lessonEntity = lessonRepository.saveAndFlush(lessonEntity);
		return new Lesson(lessonEntity);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService#
	 * putUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Lesson)
	 */
	@Override
	public Lesson putUpdate(Lesson lesson) {
		LessonEntity lessonEntity = lessonRepository.findOne(lesson.getId());
		updateProperties(lesson, lessonEntity, true);
		lessonEntity = lessonRepository.saveAndFlush(lessonEntity);
		return new Lesson(lessonEntity);
	}

}
