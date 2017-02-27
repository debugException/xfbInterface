package cn.emagsoftware.frame.plugin;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 插件管理类,提供管理插件的功能
 */
public class PluginManager {

	public PluginManager() {

	}

	/** 对象实例 */
	private static PluginManager instance;

	/** 获取对象实例 */
	public static PluginManager getInstance() {
		return instance;
	}

	public static PluginManager getInstance(ApplicationContext ctx) {
		if (instance.ctx == null) {
			instance.ctx = ctx;
		}
		return instance;
	}

	/**
	 * 初始化对象实例
	 * 
	 * @throws Exception
	 */
	public synchronized static void init(String configFilename) throws Exception {
		if (instance != null)
			return;
		instance = new PluginManager(configFilename);
	}

	/**
	 * 初始化对象实例
	 * 
	 * @throws Exception
	 */
	public synchronized static void init(ApplicationContext ctx, String configFilename) throws Exception {
		if (instance != null)
			return;
		instance = new PluginManager(ctx, configFilename);
	}

	/** 配置文件名 */
	String configFilename;
	ApplicationContext ctx;

	/** 插件分组集合 */
	HashMap<String, List<PluginSlot>> pluginSlotGroupMap = new HashMap<String, List<PluginSlot>>();

	/**
	 * 对象创建
	 * 
	 * @param configFilename
	 *            配置文件名
	 */
	PluginManager(ApplicationContext ctx, String configFilename) throws Exception {
		this.configFilename = configFilename;
		this.ctx = ctx;
		loadConfig();
	}

	/**
	 * 对象创建
	 * 
	 * @param configFilename
	 *            配置文件名
	 */
	PluginManager(String configFilename) throws Exception {
		this.configFilename = configFilename;
		loadConfig();
	}

	/**
	 * 获取 有效插件
	 * 
	 * @param groupName
	 *            插件分组名
	 * @param pluginName
	 *            插件名
	 * @return
	 */
	public synchronized Plugin getPlugin(String groupName, String pluginName) {
		PluginSlot slot = getPluginSlot(groupName, pluginName);
		if (slot != null && slot.isEnable())
			return slot.getPlugin();
		else
			return null;
	}

	/**
	 * 获取 有效插件清单
	 * 
	 * @param groupName
	 *            插件分组名
	 * @return 有效插件清单
	 */
	public synchronized List<Plugin> getPluginList(String groupName) {
		List<Plugin> list = new ArrayList<Plugin>();
		List<PluginSlot> slotList = pluginSlotGroupMap.get(groupName);
		if (slotList != null)
			for (PluginSlot slot : slotList)
				if (slot.enable)
					list.add(slot.getPlugin());
		return list;
	}

	/**
	 * 获取 插座清单
	 * 
	 * @param groupName
	 *            插件分组名
	 * @param pluginName
	 *            插件名
	 * @return 插座清单
	 */
	public synchronized PluginSlot getPluginSlot(String groupName, String pluginName) {
		List<PluginSlot> list = getPluginSlotList(groupName);
		for (PluginSlot slot : list)
			if (pluginName == null || slot.getPluginName().equals(pluginName))
				return slot;
		return null;
	}

	/**
	 * 获取 插座清单
	 * 
	 * @return 插座清单
	 */
	public synchronized List<PluginSlot> getPluginSlotList() {
		List<PluginSlot> list = new ArrayList<PluginSlot>();
		//
		for (List<PluginSlot> pluginSlotList : pluginSlotGroupMap.values())
			for (PluginSlot slot : pluginSlotList)
				list.add(slot);
		//
		return list;
	}

	/**
	 * 获取 插座清单
	 * 
	 * @param groupName
	 *            插件分组名
	 * @return 插座清单
	 */
	public synchronized List<PluginSlot> getPluginSlotList(String groupName) {
		List<PluginSlot> list = new ArrayList<PluginSlot>();
		//
		List<PluginSlot> pluginSlotList = pluginSlotGroupMap.get(groupName);
		if (pluginSlotList == null)
			return list;
		//
		for (PluginSlot slot : pluginSlotList)
			list.add(slot);
		//
		return list;
	}

	@SuppressWarnings("unchecked")
	public synchronized void loadConfig() throws Exception {
		File file = new File(configFilename);
		if (!file.exists()) {
			saveConfig();
			return;
		}
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(file);
		Element rootElement = document.getRootElement();
		List<Element> groupElementList = rootElement.getChildren("group");
		for (Element groupElement : groupElementList) {
			List<Element> pluginElementList = groupElement.getChildren("plugin");
			String groupName = groupElement.getAttributeValue("name");
			String jar;
			String className;
			boolean enable;
			for (Element pluginElement : pluginElementList) {
				jar = pluginElement.getChildText("jar");
				className = pluginElement.getChildText("class");
				enable = "true".equals(pluginElement.getChildText("enable"));
				loadPlugin(groupName, jar, className, enable, false);
			}
		}
	}

	/**
	 * 加载插件
	 * 
	 * @param groupName
	 *            插件分组名
	 * @param jarFilename
	 *            插件JAR文件名
	 * @param className
	 *            插件类名
	 * @throws Exception
	 */
	public synchronized void loadPlugin(String groupName, String jarFilename, String className) throws Exception {
		loadPlugin(groupName, jarFilename, className, true);
	}

	/**
	 * 加载插件
	 * 
	 * @param groupName
	 *            插件分组名
	 * @param jarFilename
	 *            插件JAR文件名
	 * @param className
	 *            插件类名
	 * @param enable
	 *            是否有效
	 * @throws Exception
	 */
	public synchronized void loadPlugin(String groupName, String jarFilename, String className, boolean enable)
			throws Exception {
		loadPlugin(groupName, jarFilename, className, enable, true);
	}

	/**
	 * 加载插件
	 * 
	 * @param groupName
	 *            插件分组名
	 * @param jarFilename
	 *            插件JAR文件名
	 * @param className
	 *            插件类名
	 * @param enable
	 *            是否有效
	 * @param saveConfig
	 *            是否保存配置
	 * @throws Exception
	 */
	public synchronized void loadPlugin(String groupName, String jarFilename, String className, boolean enable,
			boolean saveConfig) throws Exception {
		//
		PluginSlot slot;
		slot = new PluginSlot(groupName, jarFilename, className);
		Plugin plugin = slot.getPlugin();
		//
		PluginSlot pluginSlotExists = getPluginSlot(groupName, plugin.getPluginName());
		if (pluginSlotExists == null) {
			if (enable) {
				slot.enable = true;
				plugin.startPlugin(ctx);
			}
			List<PluginSlot> pluginSlotList = pluginSlotGroupMap.get(groupName);
			if (pluginSlotList == null) {
				pluginSlotList = new ArrayList<PluginSlot>();
				pluginSlotGroupMap.put(groupName, pluginSlotList);
			}
			pluginSlotList.add(slot);
			if (saveConfig)
				saveConfig();
		} else {
			pluginSlotExists.reloadJar(ctx);
		}
	}

	/**
	 * 重新加载插件
	 * 
	 * @param groupName
	 *            插件分组名
	 * @param pluginName
	 *            插件名
	 * @throws Exception
	 */
	public synchronized void reloadPlugin(String groupName, String pluginName) throws Exception {
		List<PluginSlot> pluginSlotList = pluginSlotGroupMap.get(groupName);
		if (pluginSlotList == null)
			return;
		for (PluginSlot slot : pluginSlotList)
			if (slot.getPluginName().equals(pluginName)) {
				// slot.getPlugin().stopPlugin();
				slot.reloadJar(ctx);
				// slot.getPlugin().startPlugin(ctx);
			}
	}

	public synchronized void saveConfig() {
		File file = new File(configFilename);
		// 备份
		{
			File dir = new File(file.getParent() + "/backup");
			if (!dir.exists())
				dir.mkdirs();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd.HHmmss.SSS");
			File backup = new File(file.getParent() + "/backup/" + file.getName() + "." + dateFormat.format(new Date()));
			file.renameTo(backup);
		}
		// 保存
		{
			// root
			Element rootElement = new Element("plugin");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			rootElement.setAttribute("time", dateFormat.format(new Date()));
			//
			for (String groupName : pluginSlotGroupMap.keySet()) {
				Element groupElement = new Element("group");
				groupElement.setAttribute("name", groupName);
				rootElement.addContent(groupElement);
				List<PluginSlot> slotList = pluginSlotGroupMap.get(groupName);
				Element pluginElement;
				Element e;
				for (PluginSlot slot : slotList) {
					pluginElement = new Element("plugin");
					groupElement.addContent(pluginElement);
					e = new Element("jar");
					e.setText(slot.getJarFilename());
					pluginElement.addContent(e);
					e = new Element("class");
					e.setText(slot.getClassName());
					pluginElement.addContent(e);
					e = new Element("enable");
					e.setText(Boolean.toString(slot.isEnable()));
					pluginElement.addContent(e);
				}
			}
			//
			Format format = Format.getPrettyFormat();
			format.setEncoding("GBK");
			format.setIndent("    ");
			XMLOutputter outputter = new XMLOutputter(format);
			Document document = new Document(rootElement);
			try {
				FileOutputStream fos = new FileOutputStream(file);
				outputter.output(document, fos);
				fos.flush();
				fos.close();
			} catch (Exception ex) {
			}
		}
	}

	/**
	 * 设置 插件的状态
	 * 
	 * @param groupName
	 *            插件分组名
	 * @param pluginName
	 *            插件名
	 * @param enable
	 *            状态
	 * @throws Exception
	 */
	public synchronized void setEnable(String groupName, String pluginName, boolean enable) throws Exception {
		List<PluginSlot> pluginSlotList = pluginSlotGroupMap.get(groupName);
		if (pluginSlotList == null)
			return;
		for (PluginSlot slot : pluginSlotList)
			if (slot.getPluginName().equals(pluginName)) {
				if (enable)
					slot.resume(ctx);
				else
					slot.pause();
				saveConfig();
			}
	}

	/**
	 * 置插件顺序
	 * 
	 * @param groupName
	 *            插件分组名
	 * @param pluginName
	 *            插件名
	 * @param index
	 *            序号
	 */
	public synchronized void setPluginIndex(String groupName, String pluginName, int index) {
		PluginSlot slot = getPluginSlot(groupName, pluginName);
		if (slot != null) {
			List<PluginSlot> pluginSlotList = pluginSlotGroupMap.get(slot.getGroupName());
			if (pluginSlotList == null)
				return;
			pluginSlotList.remove(slot);
			if (index <= 0)
				pluginSlotList.add(0, slot);
			else if (index < pluginSlotList.size())
				pluginSlotList.add(index, slot);
			else
				pluginSlotList.add(slot);
			saveConfig();
		}
	}

	/**
	 * 卸载插件
	 * 
	 * @param groupName
	 *            插件分组名
	 * @param pluginName
	 *            插件名
	 */
	public synchronized void unloadPlugin(String groupName, String pluginName) {
		List<PluginSlot> pluginSlotList = pluginSlotGroupMap.get(groupName);
		if (pluginSlotList == null)
			return;
		PluginSlot slot;
		for (int i = pluginSlotList.size() - 1; i >= 0; i--) {
			slot = pluginSlotList.get(i);
			if (slot.getPluginName().equals(pluginName)) {
				pluginSlotList.remove(i);
				slot.getPlugin().stopPlugin();
			}
		}
		saveConfig();
	}
}
