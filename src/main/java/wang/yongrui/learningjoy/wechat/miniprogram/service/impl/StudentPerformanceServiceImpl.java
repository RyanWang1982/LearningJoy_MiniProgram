/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.StudentPerformance;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.criteria.StudentPerformanceCriteria;
import wang.yongrui.learningjoy.wechat.miniprogram.service.StudentPerformanceService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class StudentPerformanceServiceImpl implements StudentPerformanceService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.
	 * StudentPerformanceService#create(wang.yongrui.learningjoy.wechat.
	 * miniprogram.entity.web.StudentPerformance)
	 */
	@Override
	public StudentPerformance create(StudentPerformance studentPerformance) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.learningjoy.wechat.miniprogram.service.
	 * StudentPerformanceService#retrieve(java.lang.Long)
	 */
	@Override
	public StudentPerformance retrieve(Long id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
