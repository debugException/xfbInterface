package cn.emagsoftware.frame.plugin;

import cn.emagsoftware.frame.listener.SpringContext;
import cn.emagsoftware.utils.BaseUtils;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * 插件类<br>
 * 提供动态加载的接口<br>
 */
public abstract class Plugin {

    /**
     * 插件JAR存放目录
     */
    private String pluginDir = "./";

    /**
     * 日志ID
     */
    protected long logId = 0;

    protected Exception exception = null;

    /**
     * 获取 插件说明
     *
     * @return 插件说明
     */
    abstract public String getPluginDescription();

    /**
     * 获取 插件JAR存放目录
     *
     * @return 插件JAR存放目录
     */
    public String getPluginDir() {
        return pluginDir;
    }

    /**
     * 获取 插件名称(英文,数字,_)
     *
     * @return 插件名称
     */
    abstract public String getPluginName();

    /**
     * 获取 插件版本号
     *
     * @return 插件版本号
     */
    abstract public String getPluginVersion();

    abstract public String getPluginActName();

    /**
     * 设置 插件JAR存放目录
     *
     * @param dir 插件JAR存放目录
     */
    public void setPluginDir(String dir) {
        if (dir == null)
            this.pluginDir = "./";
        else
            this.pluginDir = dir;
    }

    /**
     * 启动插件启动
     */
    abstract public void startPlugin(ApplicationContext ctx);

    /**
     * 停止插件功能
     */
    abstract public void stopPlugin();

    /**
     * 重启插件功能
     */
    abstract public void restartPlugin(ApplicationContext ctx);

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(500);
        buf.append("插件:").append(getPluginName()).append("(@").append(Integer.toHexString(hashCode())).append("), ");
        buf.append("版本:").append(getPluginVersion()).append(", ");
        buf.append("描述:").append(getPluginDescription()).append(", ");
        buf.append("JAR:").append(getPluginDir());
        return buf.toString();
    }

    /**
     * 获取数据连接
     *
     * @param dataSourceName 数据源名称
     * @return
     */
    private Connection getConnection(String dataSourceName) {
        DataSource dataSource = (DataSource) SpringContext.getContext().getBean(dataSourceName);
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取数据连接
     *
     * @return
     */
    private Connection getConnection() {
        return getConnection("dataSource");
    }

    /**
     * 取得SQL返回的单条数据
     *
     * @param sql
     * @return
     */
    public Map<String, String> getItemNameMap(String sql, List<Object> params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map<String, String> hm = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            int pIndex = 1;
            if (params != null) {
                for (Object param : params) {
                    pstmt.setObject(pIndex++, param);
                }
            }

            rs = pstmt.executeQuery();
            ResultSetMetaData rmeta = rs.getMetaData();
            int numColumns = rmeta.getColumnCount();
            if (rs.next()) {
                hm = new HashMap<String, String>();
                for (int j = 1; j <= numColumns; ++j) {
                    hm.put(rmeta.getColumnName(j).toUpperCase(), BaseUtils.getString(rs.getString(j)));
                }
            }
            rs.close();
            pstmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            exception = ex;
        } finally {
            dispose(pstmt, rs);
            closeConn(conn);
        }
        return hm;
    }

    /**
     * 销毁数据库操作对象
     *
     * @param pstmt
     * @param rs
     */
    public void dispose(PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (Exception ex) {
            // logger.error("dispose()", ex);
        }
        try {
            if (pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
        } catch (Exception ex) {
            // logger.error("dispose()", ex);
        }
    }

    /**
     * 释放数据库连接
     *
     * @param conn
     */
    public static void closeConn(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚数据库连接
     *
     * @param conn
     */
    public static void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量更新
     *
     * @param sql
     * @param params
     */
    public void updateItemBatch(String sql, List<String[]> params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            int cnt = 0;
            int pIndex = 1;
            if (params != null) {
                for (String[] strs : params) {
                    pIndex = 1;
                    for (String str : strs) {
                        pstmt.setObject(pIndex++, str);
                    }
                    pstmt.addBatch();

                    cnt++;
                    if (cnt == 200) {
                        pstmt.executeBatch();
                        cnt = 0;
                    }
                }
            }

            pstmt.executeBatch();
            dispose(pstmt, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            exception = ex;
            // logger.error("updateItemBatch()", ex);
        } finally {
            dispose(pstmt, null);
            closeConn(conn);
        }
    }

    /**
     * 更新单个对象
     *
     * @param sql
     * @param params
     * @return
     */
    public int updateItem(String sql, List<Object> params) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int res = 0;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            int pIndex = 1;
            if (params != null) {
                for (Object param : params) {
                    pstmt.setObject(pIndex++, param);
                }
            }

            res = pstmt.executeUpdate();
            dispose(pstmt, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            // logger.error("updateItem()", ex);
        } finally {
            dispose(pstmt, null);
            closeConn(conn);
        }
        return res;
    }

    /**
     * 预处理执行sql，返回所有列表数据
     *
     * @param sql
     * @param params
     * @return
     */
    public List<Map<String, String>> getAllNameMapList(String sql, List<Object> params) {
        List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
        ResultSet rs = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            int pIndex = 1;
            if (params != null) {
                for (Object obj : params) {
                    pstmt.setObject(pIndex++, obj);
                }
            }
            rs = pstmt.executeQuery();
            ResultSetMetaData rmeta = rs.getMetaData();
            int numColumns = rmeta.getColumnCount();
            while (rs.next()) {
                Map<String, String> hm = new LinkedHashMap<String, String>();
                for (int j = 1; j <= numColumns; ++j) {
                    hm.put(rmeta.getColumnName(j).toUpperCase(), BaseUtils.getString(rs.getString(j)));
                }
                dataList.add(hm);
            }
            dispose(pstmt, rs);
        } catch (Exception ex) {
            ex.printStackTrace();
            exception = ex;
        } finally {
            dispose(pstmt, rs);
            closeConn(conn);
        }
        return dataList;
    }

}
