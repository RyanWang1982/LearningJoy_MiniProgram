/**
 * 
 */
package wang.yongrui.learningjoy.wechat.miniprogram.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Wang Yongrui
 *
 */
@Configuration
public class ApplicationConfiguration {

	/**
	 * @return
	 * @throws Exception
	 */
	@Bean
	public Validator validator() throws Exception {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource());

		return validator;
	}

	/**
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
		messageBundle.setBasenames("classpath:message/messages", "classpath:message/errors");
		messageBundle.setDefaultEncoding("UTF-8");

		return messageBundle;
	}

}
