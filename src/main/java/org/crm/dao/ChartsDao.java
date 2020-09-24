package org.crm.dao;

import java.util.List;
import java.util.Map;

public interface ChartsDao {
    /**
     * 字典值表与交易表进行连接查询 通过value字段分组进行记录的统计
     * @return
     */
    List<Map<String, Object>> queryTranCount();
}
