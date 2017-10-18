/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Lesson;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.LessonCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class LessonServiceImpl implements LessonService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService#create(
	 * wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Lesson)
	 */
	@Override
	public Lesson create(Lesson lesson) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService#
	 * retrieve(java.lang.Long)
	 */
	@Override
	public Lesson retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.LessonService#
	 * putUpdate(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Lesson)
	 */
	@Override
	public Lesson putUpdate(Lesson lesson) {
		// TODO Auto-generated method stub
		return null;
	}

}
