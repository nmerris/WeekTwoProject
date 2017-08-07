package com.nmerris.weektwoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;

/**
 * Main application class for this app. This app supports Spanish language.  It will default to US English.
 * To switch, just type '?lang=es_US' (Spanish) or '?lang=en_US' (English)
 * at the end of any URL on any page , then reload the page.  The app will stay in a language until you manually
 * switch back, or until the app is restarted on the server.
 *
 * @author Nathan Merris
 */
// EnableAutoConfiguration and ComponentScan are needed for the locale stuff to work
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class WeektwoprojectApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(WeektwoprojectApplication.class, args);
	}

	// need a SessionLocaleResolver for the language stuff to work
	// set locale to default to US English
	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("en", "US"));
		return localeResolver;
	}

	// create a new LCI
	// added to the InterceptorRegistry below
	// this will all us to intercept the '?lang=' parameter in the page URLs
	// will work on ANY web page in this app
	// it only needs to be set once, then it will stay in that language until the user manually
	// switches back to the other supported language
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	// add our custom LCI to the registry
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

}
