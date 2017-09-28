/**
 *
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.basic.WeChatUserBasic_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.persistence.WeChatUserEntity_;
import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.WeChatUser;
import wang.yongrui.learningjoy.wechat.miniprogram.repository.WeChatUserRepository;
import wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class WeChatUserServiceImpl implements WeChatUserService {

	@Autowired
	private WeChatUserRepository userRepository;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService#
	 * create(wang.yongrui.learningjoy.wechat.miniprogram.entity.web.WeChatUser)
	 */
	@Override
	public WeChatUser create(WeChatUser user) {
		WeChatUserEntity userEntity = new WeChatUserEntity();
		BeanUtils.copyProperties(user, userEntity);
		userEntity = userRepository.saveAndFlush(userEntity);

		return new WeChatUser(userEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.learningjoy.wechat.miniprogram.service.WeChatUserService#
	 * retrieveBasicInfo(java.lang.String)
	 */
	@Override
	public WeChatUser retrieveBasicInfo(String weChatUnionId) {
		WeChatUserEntity userEntity = userRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			return criteriaBuilder.equal(root.get(WeChatUserEntity_.weChatInfo).get(WeChatUserBasic_.unionId),
					weChatUnionId);
		});

		return new WeChatUser(userEntity);
	}

}
