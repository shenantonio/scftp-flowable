package cn.xls.icf.flowable.app.boot;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author shen_antonio
 *
 */
//@ImportResource(locations = {"classpath:spring/*.xml"})
@Component("scftp-flowable-app")
@EnableAutoConfiguration
@ComponentScan( basePackages = {"cn.xls.icf.**.boot"})
@Slf4j
@Configuration
@SpringBootApplication
public class ApplicationBoot {

  @PostConstruct
  public void start() {
    log.info("scftp-flowable-app ApplicationBoot");
  }



}
