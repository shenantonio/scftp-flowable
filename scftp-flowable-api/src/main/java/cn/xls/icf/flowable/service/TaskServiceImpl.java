package cn.xls.icf.flowable.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.xls.icf.flowable.common.utils.BeanUtils;
import cn.xls.icf.flowable.repository.model.TaskVO;
import cn.xls.icf.flowable.service.inf.ITaskQueryService;
import cn.xls.icf.flowable.service.inf.ITaskService;
import cn.xls.icf.flowable.service.inf.ServiceFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 流程任务
 *
 * @author shen_antonio 
 */
@Slf4j
@Component
public class TaskServiceImpl extends ServiceFactory implements ITaskService {
    @Autowired
    private ITaskQueryService taskQueryHandler;


    @Override
    public void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    @Override
    public void unclaim(String taskId) {
        taskService.unclaim(taskId);
    }

    @Override
    public void complete(String taskId) {

        this.complete(taskId, null);
        log.info("-----------任务ID：{},已完成-----------", taskId);
    }

    @Override
    public void complete(String taskId, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
    }

    @Override
    public Map<String, Object> complete(String taskId, Map<String, Object> variables, boolean localScope) {
        TaskVO finishTask = taskQueryHandler.queryTaskVOById(taskId);
        taskService.complete(taskId, variables, localScope);
        Task task = taskQueryHandler.processInstanceId(finishTask.getProcessInstanceId());
        TaskVO activeTask = BeanUtils.copyBean(task, TaskVO.class);
        Map<String, Object> map = new HashMap<>(16);
        map.put("finish", finishTask);
        map.put("active", activeTask);
        return map;
    }

    @Override
    public void delegate(String taskId, String userId) {
        taskService.delegateTask(taskId, userId);
    }


    @Override
    public void resolveTask(String taskId) {

        taskService.resolveTask(taskId);
    }


    @Override
    public void setAssignee(String taskId, String userId) {
        taskService.setAssignee(taskId, userId);
    }

    @Override
    public void setOwner(String taskId, String userId) {
        taskService.setOwner(taskId, userId);
    }

    @Override
    public void delete(String taskId) {
        taskService.deleteTask(taskId);
    }

    @Override
    public void deleteWithReason(String taskId, String reason) {
        taskService.deleteTask(taskId, reason);
    }

    @Override
    public void addCandidateUser(String taskId, String userId) {

        taskService.addCandidateUser(taskId, userId);
    }

    @Override
    public Comment addComment(String taskId, String processInstanceId, String message) {

        return taskService.addComment(taskId, processInstanceId, message);
    }

    @Override
    public List<Comment> getTaskComments(String taskId) {

        return taskService.getTaskComments(taskId);
    }

    @Override
    public void withdraw(String processInstanceId, String currentActivityId, String newActivityId) {
        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(processInstanceId)
                .moveActivityIdTo(currentActivityId, newActivityId)
                .changeState();
    }

    @Override
    public void setVariableLocal(String taskId, String variableName, Object value) {
        taskService.setVariableLocal(taskId, variableName, value);
    }

    @Override
    public void setVariablesLocal(String taskId, Map<String, ? extends Object> variables) {
        taskService.setVariablesLocal(taskId, variables);
    }

}
