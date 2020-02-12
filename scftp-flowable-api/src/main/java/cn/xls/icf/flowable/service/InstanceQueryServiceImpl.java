package cn.xls.icf.flowable.service;

import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Component;

import cn.xls.icf.flowable.service.inf.IInstanceQueryService;
import cn.xls.icf.flowable.service.inf.ServiceFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程实例查询
 * @author shen_antonio
 *
 */
@Slf4j
@Component
public class InstanceQueryServiceImpl extends ServiceFactory implements IInstanceQueryService {


    @Override
    public ProcessInstanceQuery createProcessInstanceQuery() {
        return runtimeService.createProcessInstanceQuery();
    }

    @Override
    public ProcessInstance processInstanceId(String processInstanceId) {

        return createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
    }

    @Override
    public ProcessInstance processInstanceBusinessKey(String processInstanceBusinessKey) {

        return createProcessInstanceQuery().processInstanceBusinessKey(processInstanceBusinessKey).singleResult();
    }

    @Override
    public boolean hasProcessInstanceFinished(String processInstanceId) {

        ProcessInstance pi = processInstanceId(processInstanceId);

        return pi == null;
    }

    @Override
    public ProcessInstance taskId(String taskId) {

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstId = task.getProcessInstanceId();
        return this.processInstanceId(processInstId);
    }

}
