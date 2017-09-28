/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.SetJoin;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.WeChatUserBasic_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.CourseEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Course;
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
		return new Course(courseRepository.findOne(courseId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.CourseService#
	 * retrieveByTeacher(java.lang.String,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Course> retrieveByTeacher(String weChatUnionId, Pageable pageable) {
		Page<CourseEntity> courseEntityPage = courseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(CourseEntity_.teacherEntitySet, JoinType.LEFT);
			root.fetch(CourseEntity_.studentEntitySet, JoinType.LEFT);
			SetJoin<CourseEntity, WeChatUserEntity> teacherJoin = root.join(CourseEntity_.teacherEntitySet);
			return criteriaBuilder.and(criteriaBuilder
					.equal(teacherJoin.get(WeChatUserEntity_.weChatInfo).get(WeChatUserBasic_.unionId), weChatUnionId));
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
	 * retrieveByStudent(java.lang.String,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Course> retrieveByStudent(String weChatUnionId, Pageable pageable) {
		Page<CourseEntity> courseEntityPage = courseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(CourseEntity_.teacherEntitySet, JoinType.LEFT);
			root.fetch(CourseEntity_.studentEntitySet, JoinType.LEFT);
			SetJoin<CourseEntity, WeChatUserEntity> studentJoin = root.join(CourseEntity_.studentEntitySet);
			return criteriaBuilder.and(criteriaBuilder
					.equal(studentJoin.get(WeChatUserEntity_.weChatInfo).get(WeChatUserBasic_.unionId), weChatUnionId));
		}, pageable);

		List<Course> courseList = new ArrayList<>();
		for (CourseEntity eachCourseEntity : courseEntityPage.getContent()) {
			courseList.add(new Course(eachCourseEntity));
		}

		return new PageImpl<Course>(courseList, pageable, courseEntityPage.getTotalElements());
	}

}
