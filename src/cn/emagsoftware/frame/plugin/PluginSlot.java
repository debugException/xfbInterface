package cn.emagsoftware.frame.plugin;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;

/**
 * 插座类,用于加载插件的接口
 */
public class PluginSlot {

	/** 插件类名 */
	String className;

	/** 插件状态 */
	boolean enable = false;

	/** 插件分组名 */
	String groupName;

	/** 插件JAR文件名 */
	String jarFilename;

	/** 插件加载时间 */
	long loadTime;

	/** 插件加载时间 字符串 */
	String loadTimeString;

	/** 插件实例 */
	Plugin plugin;

	/**
	 * 创建方法
	 * 
	 * @param groupName
	 *            插件分组名
	 * @param jarFilename
	 *            插件JAR文件名
	 * @param className
	 *            插件类名
	 * @throws Exception
	 */
	public PluginSlot(String groupName, String jarFilename, String className) throws Exception {
		super();
		this.jarFilename = jarFilename;
		this.className = className;
		this.groupName = groupName;
		loadJar();
	}

	/**
	 * 获取 插件类名
	 * 
	 * @return 插件类名
	 */
	public final String getClassName() {
		return className;
	}

	/**
	 * 获取 插件分组名
	 * 
	 * @return 插件分组名
	 */
	public final String getGroupName() {
		return groupName;
	}

	/**
	 * 获取 插件JAR文件名
	 * 
	 * @return 插件JAR文件名
	 */
	public final String getJarFilename() {
		return jarFilename;
	}

	/**
	 * 获取 插件加载时间
	 * 
	 * @return
	 */
	public final long getLoadTime() {
		return loadTime;
	}

	/**
	 * 获取 插件实例
	 * 
	 * @return 插件实例
	 */
	public final Plugin getPlugin() {
		return plugin;
	}

	/**
	 * 获取 插件说明
	 * 
	 * @return 插件说明
	 */
	public final String getPluginDescription() {
		return plugin.getPluginDescription();
	}

	/**
	 * 获取 插件名称
	 * 
	 * @return 插件名称
	 */
	public final String getPluginName() {
		return plugin.getPluginName();
	}

	/**
	 * 获取 获取插件版本
	 * 
	 * @return 获取插件版本
	 */
	public final String getPluginVersion() {
		return plugin.getPluginName();
	}

	/**
	 * 获取 插件状态
	 * 
	 * @return 插件状态
	 */
	public final boolean isEnable() {
		return enable;
	}

	/**
	 * 加载JAR
	 * 
	 * @throws Exception
	 */
	private final void loadJar() throws Exception {
		ClassLoader classLoader;
		boolean isURL = false;
		if (StringUtils.isNotBlank(jarFilename)) {
			URL url = null;
			try {
				url = new URL(jarFilename);
				isURL = true;
			} catch (Exception ex) {
				url = null;
			}
			if (url == null) {
				url = new File(jarFilename).toURI().toURL();
			}
			URL[] urls = new URL[] { url };
			classLoader = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
		} else {
			classLoader = Thread.currentThread().getContextClassLoader();
		}
		plugin = (Plugin) classLoader.loadClass(className).newInstance();
		if (jarFilename != null)
			if (!isURL)
				plugin.setPluginDir(new File(jarFilename).getParent());
		//
		this.loadTime = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		loadTimeString = dateFormat.format(loadTime);
	}

	/**
	 * 暂停插件
	 */
	protected final void pause() {
		if (this.enable) {
			this.plugin.stopPlugin();
			this.enable = false;
		}
	}

	/**
	 * 重新加载插件JAR
	 * 
	 * @throws Exception
	 */
	protected final void reloadJar(ApplicationContext ctx) throws Exception {
		if (enable)
			plugin.stopPlugin();
		//
		loadJar();
		//
		if (enable) {
			enable = false;
			plugin.startPlugin(ctx);
			enable = true;
		}
	}

	/**
	 * 恢复插件状态
	 * 
	 * @throws Exception
	 */
	protected final void resume(ApplicationContext ctx) throws Exception {
		if (this.enable == false) {
			this.plugin.startPlugin(ctx);
			this.enable = true;
		}
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder(500);
		buf.append("Group:").append(groupName).append("(@").append(Integer.toHexString(hashCode())).append("), ");
		buf.append("Time:").append(loadTimeString).append(", ");
		buf.append("Enable:").append(enable).append(", ");
		buf.append("Jar:").append(getJarFilename()).append(", ");
		buf.append("Plugin:(").append(plugin).append(")");
		return buf.toString();
	}
}
