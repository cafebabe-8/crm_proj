package org.crm.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 分页时映射的对象
@Data
@NoArgsConstructor
public class Page {
    private Integer currentPage = 1; // 当前页
    private Integer rowsPerPage = 10; // 每页条数
    private Integer maxRowsPerPage = 100; // 每页最多显示多少条
    private Integer totalRows; // 总记录数
    private Integer totalPages; // 总页数
    private Integer visiblePageLinks = 5; // 页面上显示的分页按钮数
    private List<?> list; // 当前页数据
}
