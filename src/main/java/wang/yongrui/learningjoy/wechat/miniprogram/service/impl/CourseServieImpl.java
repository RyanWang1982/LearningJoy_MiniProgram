/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import static org.springframework.beans.BeanUtils.*;
import static wang.yongrui.learningjoy.wechat.miniprogram.util.PatchBeanUtils.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.Attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.WeChatUser;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.CourseCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.CourseRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.WeChatUserRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class CourseServieImpl implements CourseService {

	@Autowired
	private WeChatUserRepository userRepository;

	@Autowired
	private CourseRepository courseRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#create(
	 * wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course)
	 */
	@Override
	public Course create(Course course) {
		CourseEntity courseEntity = new CourseEntity();
		copyProperties(course, courseEntity);

		Set<Long> ids = new HashSet<>();
		for (WeChatUser teacher : course.getTeacherSet()) {
			ids.add(teacher.getId());
		}
		Set<WeChatUserEntity> teacherEntitySet = new HashSet<>();
		teacherEntitySet.addAll(userRepository.findAll(ids));

		courseEntity.setTeacherEntitySet(teacherEntitySet);
		courseEntity = courseRepository.saveAndFlush(courseEntity);

		Set<Attribute<?, ?>> includedAttributeSet = new HashSet<>();
		includedAttributeSet.add(CourseEntity_.teacherEntitySet);
		return new Course(courseEntity);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * retrieve(java.lang.Long)
	 */
	@Override
	public Course retrieve(Long id) {
		return new Course(courseRepository.findOne(id));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * retrieveWithTeachers(java.lang.Long)
	 */
	@Override
	public Course retrieveWithTeachers(Long id) {
		CourseEntity courseEntity = courseRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(CourseEntity_.teacherEntitySet, JoinType.LEFT);
			return criteriaBuilder.equal(root.get(CourseEntity_.id), id);
		});

		Set<Attribute<?, ?>> includedAttributeSet = new HashSet<>();
		includedAttributeSet.add(CourseEntity_.teacherEntitySet);
		return new Course(courseEntity, includedAttributeSet);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * retrieveWithStudents(java.lang.Long)
	 */
	@Override
	public Course retrieveWithStudents(Long id) {
		CourseEntity courseEntity = courseRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(CourseEntity_.studentEntitySet, JoinType.LEFT);
			return criteriaBuilder.equal(root.get(CourseEntity_.id), id);
		});

		Set<Attribute<?, ?>> includedAttributeSet = new HashSet<>();
		includedAttributeSet.add(CourseEntity_.studentEntitySet);
		return new Course(courseEntity, includedAttributeSet);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * retrievePagination(wang.yongrui.learningjoy.wechat.miniprogram.entity.web
	 * .criteria.CourseCriteria, org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Course> retrievePagination(CourseCriteria courseCriteria, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * patchUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.
	 * Course)
	 */
	@Override
	public Course patchUpdate(Course course) {
		CourseEntity courseEntity = courseRepository.findOne(course.getId());
		updateProperties(course, courseEntity, false);
		courseEntity = courseRepository.saveAndFlush(courseEntity);
		return new Course(courseEntity);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * putUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course)
	 */
	@Override
	public Course putUpdate(Course course) {
		CourseEntity courseEntity = courseRepository.findOne(course.getId());
		updateProperties(course, courseEntity, true);
		courseEntity = courseRepository.saveAndFlush(courseEntity);
		return new Course(courseEntity);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * deleteRelationWithTeachers(java.lang.Long, java.util.Set)
	 */
	@Override
	public Course deleteRelationWithTeachers(Long id, Set<Long> teacherIdSet) {
		CourseEntity courseEntity = courseRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(CourseEntity_.teacherEntitySet, JoinType.LEFT);
			return criteriaBuilder.equal(root.get(CourseEntity_.id), id);
		});
		Set<WeChatUserEntity> teacherEntitySet = courseEntity.getTeacherEntitySet();
		for (WeChatUserEntity teacherEntity : teacherEntitySet) {
			if (teacherIdSet.contains(teacherEntity.getId())) {
				teacherEntitySet.remove(teacherEntity);
			}
		}
		courseEntity.setTeacherEntitySet(teacherEntitySet);
		courseRepository.saveAndFlush(courseEntity);

		Set<Attribute<?, ?>> includedAttributeSet = new HashSet<>();
		includedAttributeSet.add(CourseEntity_.teacherEntitySet);
		return new Course(courseEntity, includedAttributeSet);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * deleteRelationWithStudents(java.lang.Long, java.util.Set)
	 */
	@Override
	public Course deleteRelationWithStudents(Long id, Set<Long> studentIdSet) {
		CourseEntity courseEntity = courseRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(CourseEntity_.studentEntitySet, JoinType.LEFT);
			return criteriaBuilder.equal(root.get(CourseEntity_.id), id);
		});
		Set<WeChatUserEntity> studentEntitySet = courseEntity.getStudentEntitySet();
		for (WeChatUserEntity studentEntity : studentEntitySet) {
			if (studentIdSet.contains(studentEntity.getId())) {
				studentEntitySet.remove(studentEntity);
			}
		}
		courseEntity.setStudentEntitySet(studentEntitySet);
		courseRepository.saveAndFlush(courseEntity);

		Set<Attribute<?, ?>> includedAttributeSet = new HashSet<>();
		includedAttributeSet.add(CourseEntity_.studentEntitySet);
		return new Course(courseEntity, includedAttributeSet);
	}

}
