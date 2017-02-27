package cn.emagsoftware.frame.listener;

import cn.emagsoftware.utils.BaseUtils;
import cn.emagsoftware.utils.Constant;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

@Configuration
public class RequestListener implements ServletRequestListener {
    Logger logger = Logger.getLogger(RequestListener.class.getName());

    public void requestInitialized(ServletRequestEvent arg0) {
        // 日志编号
        MDC.put(Constant.LOG_ID, BaseUtils.getLogId());
        // 记录时间
        MDC.put(Constant.TIMESTAMP, System.currentTimeMillis());
    }

    public void requestDestroyed(ServletRequestEvent event) {
        // 记录未被程序捕获，但被容器捕获的异常信息
        ServletRequest request = event.getServletRequest();
        if (request != null) {
            Object object = request.getAttribute(Constant.UNCAUGHT_EXCEPTION);
            if (object != null && object instanceof Throwable) {
                logger.error(Constant.UNCAUGHT_EXCEPTION, (Throwable) object);
            }
        }

        logger.info("耗时:" + (System.currentTimeMillis() - ((Long) MDC.get(Constant.TIMESTAMP)) + "毫秒"));

        // 重置日志唯一标识
        MDC.remove(Constant.LOG_ID);
        MDC.remove(Constant.TIMESTAMP);
    }
}
