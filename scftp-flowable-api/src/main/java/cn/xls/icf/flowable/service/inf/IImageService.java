package cn.xls.icf.flowable.service.inf;

/**
 * 流程追踪图生成类
 *
 * @author shen_antonio
 */
public interface IImageService {

    /**
     * 根据流程实例标识，生成流程跟踪图示（高亮）
     *
     * @param procInstId 流程实例标识
     * @return
     * @throws Exception
     */
    byte[] generateImageByProcInstId(String procInstId) throws Exception;

}
