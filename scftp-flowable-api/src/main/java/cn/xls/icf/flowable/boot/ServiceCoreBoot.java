package cn.xls.icf.flowable.boot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import cn.xls.icf.action.module.SpringModuleComponent;

/**
 * boot component 
 * @author shen_antonio
 *
 */
@Component("flowable.api")
@ComponentScan(basePackages="cn.xls.icf.flowable")
public class ServiceCoreBoot extends SpringModuleComponent{

	@Override
	protected String getComponentName() {
		return "flowable.api";
	}
	
}
