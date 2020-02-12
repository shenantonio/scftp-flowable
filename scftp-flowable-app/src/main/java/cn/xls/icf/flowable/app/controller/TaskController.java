package cn.xls.icf.flowable.app.controller;


import java.util.Map;

import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xls.icf.flowable.app.response.ResponseData;
import cn.xls.icf.flowable.service.inf.ITaskQueryService;
import cn.xls.icf.flowable.service.inf.ITaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/**
 * <p>
 * 流程任务相关
 * </p>
 *
 * @author shen_antonio 
 */
@Slf4j
@RestController
@RequestMapping("api/flow/task")
@Api(value = "Task", tags = {"流程任务"})
public class TaskController {

    @Autowired
    private ITaskService taskService;
    @Autowired
    private ITaskQueryService taskQueryService;


    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "执行任务", notes = "部门领导审批：{\"days\":\"6\",\"check\":\"1\",\"actorIds\":[\"wangwu\",\"lifei\"]}                                             \n" +
            "分管领导审批：{\"check\":\"1\",\"actorIds\":[\"hr_li\",\"hr_zhang\"]}", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String")})
    public ResponseData executeTask(String taskId, @RequestBody Map<String, Object> variables) throws Exception {
        Map<String, Object> map = taskService.complete(taskId, variables, false);

        return ResponseData.success(map);
    }


    @RequestMapping(value = "/claim", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务签收", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String"),
    })
    public ResponseData claim(String taskId, String userId) throws Exception {
        taskService.claim(taskId, userId);
        return ResponseData.success("任务签收成功");
    }


    @RequestMapping(value = "/unclaim", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务反签收", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String")})
    public ResponseData unclaim(String taskId) throws Exception {
        taskService.unclaim(taskId);
        return ResponseData.success("任务反签收成功");
    }


    @RequestMapping(value = "/delegate", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务委派", notes = "【act_ru_task】委派人：owner，被委派人：assignee，委派任务：delegateTask，任务回到委派人：resolveTask", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String"),
    })
    public ResponseData delegate(String taskId, String userId) throws Exception {
        taskService.delegate(taskId, userId);
        return ResponseData.success("任务委派成功");
    }


    @RequestMapping(value = "/resolve", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务归还：被委派人完成任务之后，将任务归还委派人", notes = "", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),})
    public ResponseData<Task> reslove(String taskId) throws Exception {
        taskService.resolveTask(taskId);
        return ResponseData.success();
    }


    @RequestMapping(value = "/assignee ", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "任务转办", notes = "【act_ru_task】设置assignee为转办人", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String"),
    })
    public ResponseData assignee(String taskId, String userId) throws Exception {
        taskService.setAssignee(taskId, userId);
        return ResponseData.success("任务转办成功");
    }


    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加批注信息", notes = "批注信息：act_hi_comment", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "instanceId", value = "实例ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "message", value = "批注内容", required = true, dataType = "String")
    })
    public ResponseData<Comment> addComment(String taskId, String instanceId, String message) throws Exception {
        Comment comment = taskService.addComment(taskId, instanceId, message);
        return ResponseData.success(comment);
    }
}

