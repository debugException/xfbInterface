package cn.emagsoftware.frame.log4j;

import org.apache.log4j.Logger;

public class ILogImpl implements ILog {

    private Logger logger;

    public ILogImpl(Class<?> clazz) {
        logger = Logger.getLogger(clazz);
    }

    public void info(Object message) {
        logger.info(message);
    }

    public void info(long id, Object message) {
        logger.info(id + "|" + message);
    }

    public void info(Object message, Throwable t) {
        logger.info(message, t);
    }

    public void info(long id, Object message, Throwable t) {
        logger.info(id + "|" + message, t);
    }

    public void error(Object message) {
        logger.error(message);
    }

    public void error(long id, Object message) {
        logger.error(id + "|" + message);
    }

    public void error(Object message, Throwable t) {
        logger.error(message, t);
    }

    public void error(long id, Object message, Throwable t) {
        logger.error(id + "|" + message, t);
    }

    public void warn(Object message) {
        logger.warn(message);
    }

    public void warn(long id, Object message) {
        logger.warn(id + "|" + message);
    }

    public void warn(Object message, Throwable t) {
        logger.warn(message, t);
    }

    public void warn(long id, Object message, Throwable t) {
        logger.warn(id + "|" + message, t);
    }

    public void debug(Object message) {
        logger.info(message);
    }

    public void debug(long id, Object message) {
        logger.debug(id + "|" + message);
    }

    public void debug(Object message, Throwable t) {
        logger.debug(message, t);
    }

    public void debug(long id, Object message, Throwable t) {
        logger.debug(id + "|" + message, t);
    }
}