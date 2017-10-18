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
	public WeChatUser retrieveBasicInfoByWeChatUnionId(String weChatUnionId);

	/**
	 * @param id
	 * @return
	 */
	public WeChatUser retrieveBasicInfo(Long id);

	/**
	 * @param id
	 * @return
	 */
	public WeChatUser retrieveWithSetting(Long id);

	/**
	 * @param id
	 * @return
	 */
	public WeChatUser retrieveWithChildren(Long id);

	/**
	 * @param user
	 * @return
	 */
	public WeChatUser patchUpdate(WeChatUser user);

	/**
	 * @param user
	 * @return
	 */
	public WeChatUser putUpdate(WeChatUser user);

}
