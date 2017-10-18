/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.criteria.JoinType;

import org.springframework.beans.BeanUtils;
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
	 * retrieve(java.lang.Long)
	 */
	@Override
	public Course retrieve(Long id) {
		CourseEntity courseEntity = courseRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(CourseEntity_.teacherEntitySet, JoinType.LEFT);
			root.fetch(CourseEntity_.studentEntitySet, JoinType.LEFT);
			root.fetch(CourseEntity_.lessonEntitySet, JoinType.LEFT);
			return criteriaBuilder.equal(root.get(CourseEntity_.id), id);
		});

		return new Course(courseEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * retrieveWithTeachers(java.lang.Long)
	 */
	@Override
	public Course retrieveWithTeachers(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * retrieveWithStudents(java.lang.Long)
	 */
	@Override
	public Course retrieveWithStudents(Long id) {
		// TODO Auto-generated method stub
		return null;
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
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * deleteRelationWithTeachers(java.util.Set)
	 */
	@Override
	public Course deleteRelationWithTeachers(Set<Long> teacherIdSet) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * deleteRelationWithStudents(java.util.Set)
	 */
	@Override
	public Course deleteRelationWithStudents(Set<Long> studentIdSet) {
		// TODO Auto-generated method stub
		return null;
	}

}
