package cn.emagsoftware.frame.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * @Title: iBatis DAO基类
 */
public class BaseBillingDao extends SqlMapClientDaoSupport {

    /**
     * 注入SqlMapClient
     *
     * @param billingSqlMapClient
     */
    @Autowired
    public void setSqlMapClientFoJrAutowire(SqlMapClient billingSqlMapClient) {
        super.setSqlMapClient(billingSqlMapClient);
    }
}
