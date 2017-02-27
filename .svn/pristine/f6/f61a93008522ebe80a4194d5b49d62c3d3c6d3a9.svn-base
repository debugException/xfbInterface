package cn.emagsoftware.frame.listener;


import cn.emagsoftware.utils.ConfigCache;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * 读取读取预处理信息的监听.
 */
public class PrePreatmentListener implements ServletContextListener {



    /*
     * (non-Javadoc)
     *
     * @see
     * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
     * .ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent context) {
        // 加载配置文件
        ConfigCache.readConfigCache();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
     * ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent context) {
    }


}