package cn.xls.icf.flowable.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.xls.icf.flowable.app.boot.ApplicationBoot;

/**
 * 
 * @author shen_antonio
 *
 */
@SpringBootApplication
public class FlowApplication{

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBoot.class, args);
	}

}
