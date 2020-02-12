package cn.xls.icf.flowable.service;
import java.util.List;

import org.flowable.task.api.history.HistoricTaskInstance;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.springframework.stereotype.Component;

import cn.xls.icf.flowable.service.inf.IHistTaskQueryService;
import cn.xls.icf.flowable.service.inf.ServiceFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * 历史任务
 *
 * @author shen_antonio
 * @date 2019/08/30
 */
@Slf4j
@Component
public class HistTaskQueryServiceImpl extends ServiceFactory implements IHistTaskQueryService {

    @Override
    public HistoricTaskInstanceQuery createHistoricTaskInstanceQuery() {
        return historyService.createHistoricTaskInstanceQuery();
    }

    @Override
    public HistoricTaskInstance activeTask(String instanceId) {
        return createHistoricTaskInstanceQuery().processInstanceId(instanceId).unfinished().singleResult();
    }

    @Override
    public HistoricTaskInstance finishedTask(String taskId) {
        return createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
    }

    @Override
    public List<HistoricTaskInstance> listByInstanceId(String instanceId) {
        return createHistoricTaskInstanceQuery().processInstanceId(instanceId).orderByTaskCreateTime().desc().list();

    }

    @Override
    public List<HistoricTaskInstance> pageListByInstanceId(String instanceId, int start, int limit) {
        return createHistoricTaskInstanceQuery()
                .processInstanceId(instanceId)
                .orderByTaskCreateTime()
                .desc().listPage(start, limit);

    }

}