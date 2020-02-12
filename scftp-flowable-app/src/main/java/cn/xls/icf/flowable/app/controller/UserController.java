package cn.xls.icf.flowable.app.controller;


import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.xls.icf.flowable.app.response.ResponseData;
import cn.xls.icf.flowable.service.inf.ITaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;


/**
 * <p>
 * 任务处理人相关
 * </p>
 *
 * @author shen_antonio 
 */
@Slf4j
@ApiIgnore
@RestController
@RequestMapping("api/flow/user")
@Api(value = "User", tags = {"任务处理人"})
public class UserController {

    @Autowired
    private ITaskService taskService;

    /**
     * 执行流程任务
     */
    @RequestMapping(value = "/transferTask", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "任务移交", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户ID（接受移交的用户）", required = true, dataType = "String")
    })
    public ResponseData<Task> transferTask(String taskId, String userId) throws Exception {
        taskService.setAssignee(taskId,userId);
        return ResponseData.success();
    }
}

