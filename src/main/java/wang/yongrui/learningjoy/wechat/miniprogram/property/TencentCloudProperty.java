/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;

/**
 * @author Wang Yongrui
 *
 */
@Configuration
@ConfigurationProperties
@PropertySource({ "classpath:tencentCloud.properties" })
@Getter
public class TencentCloudProperty {

	@Value("${cloudAPI.appId}")
	private Long cloudAPIAppId;

	@Value("${cloudAPI.secretId}")
	private String cloudAPISecretId;

	@Value("${cloudAPI.secretKey}")
	private String cloudAPISecretKey;

	@Value("${cloudAPI.cosUrl}")
	private String cloudAPICosUrl;

	@Value("${cloudAPI.cosBucket}")
	private String cloudAPICosBucket;

}
