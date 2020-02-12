package cn.xls.icf.flowable.app.controller;


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


/**
 * <p>
 * 流程回滚
 * </p>
 *
 * @author shen_antonio 
 */
@RestController
@RequestMapping("api/flow/rollback")
@Api(value = "rollback", tags = {"任务回滚"})
public class BackController {
    @Autowired
    private ITaskService taskHandler;

    @ResponseBody
    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    @ApiOperation(value = "任务撤回",notes = "注意：当前与目标定义Key为设计模板时任务对应的ID,而非数据主键ID",produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "currentTaskKey", value = "当前任务定义Key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "targetTaskKey", value = "目标任务定义Key", required = true, dataType = "String")
    })
    public ResponseData withdraw(String processInstanceId, String currentTaskKey, String targetTaskKey) {
        taskHandler.withdraw(processInstanceId, currentTaskKey, targetTaskKey);
        return ResponseData.success("任务撤回成功");
    }

}

