/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.SetJoin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.enumeration.CourseStatus;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.WeChatUser;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.CourseRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class CourseServieImpl implements CourseService {

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
		BeanUtils.copyProperties(course, courseEntity);
		Set<WeChatUserEntity> teacherEntitySet = new LinkedHashSet<>();
		for (WeChatUser teacher : course.getTeacherSet()) {
			WeChatUserEntity teacherEntity = new WeChatUserEntity();
			BeanUtils.copyProperties(teacher, teacherEntity);
			teacherEntitySet.add(teacherEntity);
		}
		courseEntity.setTeacherEntitySet(teacherEntitySet);
		courseEntity = courseRepository.saveAndFlush(courseEntity);

		return new Course(courseEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * retrieveOne(java.lang.Long)
	 */
	@Override
	public Course retrieveOne(Long courseId) {
		CourseEntity courseEntity = courseRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(CourseEntity_.teacherEntitySet, JoinType.LEFT);
			root.fetch(CourseEntity_.studentEntitySet, JoinType.LEFT);
			root.fetch(CourseEntity_.lessonEntitySet, JoinType.LEFT);
			return criteriaBuilder.equal(root.get(CourseEntity_.id), courseId);
		});

		return new Course(courseEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * retrieveByTeacher(java.lang.Long,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Course> retrieveByTeacher(Long teacherId, Pageable pageable) {
		Page<CourseEntity> courseEntityPage = courseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(CourseEntity_.teacherEntitySet, JoinType.LEFT);
			root.fetch(CourseEntity_.studentEntitySet, JoinType.LEFT);
			SetJoin<CourseEntity, WeChatUserEntity> teacherJoin = root.join(CourseEntity_.teacherEntitySet);
			return criteriaBuilder.and(criteriaBuilder.equal(teacherJoin.get(WeChatUserEntity_.id), teacherId));
		}, pageable);

		List<Course> courseList = new ArrayList<>();
		for (CourseEntity eachCourseEntity : courseEntityPage.getContent()) {
			courseList.add(new Course(eachCourseEntity));
		}

		return new PageImpl<Course>(courseList, pageable, courseEntityPage.getTotalElements());
	}

	@Override
	public Page<Course> retrieveByStudent(Long studentId, Pageable pageable) {
		Page<CourseEntity> courseEntityPage = courseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(CourseEntity_.teacherEntitySet, JoinType.LEFT);
			root.fetch(CourseEntity_.studentEntitySet, JoinType.LEFT);
			SetJoin<CourseEntity, WeChatUserEntity> studentJoin = root.join(CourseEntity_.studentEntitySet);
			return criteriaBuilder.and(criteriaBuilder.equal(studentJoin.get(WeChatUserEntity_.id), studentId));
		}, pageable);

		List<Course> courseList = new ArrayList<>();
		for (CourseEntity eachCourseEntity : courseEntityPage.getContent()) {
			courseList.add(new Course(eachCourseEntity));
		}

		return new PageImpl<Course>(courseList, pageable, courseEntityPage.getTotalElements());
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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * putUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course)
	 */
	@Override
	public Course putUpdate(Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#close(
	 * java.lang.Long)
	 */
	@Override
	public Course close(Long courseId) {
		CourseEntity courseEntity = courseRepository.findOne(courseId);
		courseEntity.setStatus(CourseStatus.Closed);
		courseEntity = courseRepository.saveAndFlush(courseEntity);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * deleteRelationOfTeachers(java.util.Set)
	 */
	@Override
	public Course deleteRelationOfTeachers(Set<Long> teacherIdSet) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * deleteRelationOfStudents(java.util.Set)
	 */
	@Override
	public Course deleteRelationOfStudents(Set<Long> studentIdSet) {
		// TODO Auto-generated method stub
		return null;
	}

}
