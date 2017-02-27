package cn.emagsoftware.xfb.job;

import cn.emagsoftware.utils.json.JsonUtils;
import cn.emagsoftware.xfb.constants.OrderConstant;
import cn.emagsoftware.xfb.pojo.StageModel;
import cn.emagsoftware.xfb.service.StageModelService;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.emagsoftware.frame.log4j.ILog;
import cn.emagsoftware.frame.log4j.Logger;
import cn.emagsoftware.utils.BaseUtils;
import cn.emagsoftware.utils.Constant;
import cn.emagsoftware.utils.ConfigCache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Quartz定时调度任务.
 */
@Component
public class LoadCacheJob {

    @Logger
    private static ILog log;

    /**
     * JOB运行状态： 0 - 未启动 1 - 运行中
     */
    public static int runstate = 0;

    @Autowired
    private StageModelService stageModelService;




    public LoadCacheJob() {
    }

    /**
     * 每30分钟刷新一次缓存，重新加载properties文件
     */
    //@Scheduled(cron = "0 0/30 * * * ?")
    public void reloadProperties() {
        // 日志编号
        MDC.put(Constant.LOG_ID, BaseUtils.getLogId());

        // 如果JOB正在运行，则退出
        if (runstate == 1) {
            log.info("LoadCacheJob is running!");
            return;
        }else{
            log.info("LoadCacheJob start running!");
            // 设置JOB运行状态为， 1：运行中
            runstate = 1;
        }



        log.info("启动，读取配置文件参数!");
        // 加载properties配置文件
        ConfigCache.readConfigCache();
        log.info("LoadCacheJob 配置文件完成！");



        // 重置日志唯一标识
        MDC.remove(Constant.LOG_ID);
        try{
        List<StageModel> stageList = stageModelService.getStageList();
        if(stageList!=null&&stageList.size()>0){
            log.debug("费率模型为==="+ JsonUtils.getJSONString(stageList));
            Map<Integer,StageModel> map = new HashMap<Integer,StageModel>();

            for(int i=0;i<stageList.size();i++){
                StageModel StageModel = stageList.get(i);
                map.put(StageModel.getStageNum(),StageModel);
            }
            OrderConstant.ORDER_STAGE_RATE = map;
        }
        }catch (Exception ex){
            log.error("获取费率模型错误",ex);
        }
        // 设置JOB运行状态为， 0：未启动
        runstate = 0;

    }
}