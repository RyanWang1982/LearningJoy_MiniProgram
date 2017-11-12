/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Homework;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.HomeworkCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.service.HomeworkService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class HomeworkServiceImpl implements HomeworkService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.HomeworkService#
	 * create(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.Homework)
	 */
	@Override
	public Homework create(Homework homework) {
		// TODO Auto-generated method stub
		return null;
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
