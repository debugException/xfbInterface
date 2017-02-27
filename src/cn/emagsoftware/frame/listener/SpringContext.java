package cn.emagsoftware.frame.listener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;

public class SpringContext {

	private static ApplicationContext context;

	private static Scheduler scheduler;

	public static ApplicationContext getContext() {
		return context;
	}

	public static void setContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static Object getService(String name) {
		return getBean(name);
	}

	public static Scheduler getScheduler() {
		if (scheduler == null) {
			try {
				scheduler = StdSchedulerFactory.getDefaultScheduler();
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		return scheduler;
	}

	public static void setScheduler(Scheduler scheduler) {
		SpringContext.scheduler = scheduler;
	}
}