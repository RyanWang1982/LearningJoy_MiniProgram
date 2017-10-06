/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.service;

import wang.yongrui.learningjoy.wechat.miniprogram.entity.web.WeChatUser;
import wang.yongrui.wechat.fundamental.service.UserService;

/**
 * @author Wang Yongrui
 *
 */
public interface WeChatUserService extends UserService {

	/**
	 * @param user
	 * @return
	 */
	public WeChatUser create(WeChatUser user);

	/**
	 * @param weChatUnionId
	 * @return
	 */
	public WeChatUser retrieveBasicInfo(String weChatUnionId);

	/**
	 * @param userId
	 * @return
	 */
	public WeChatUser retrieveBasicInfo(Long userId);

}
