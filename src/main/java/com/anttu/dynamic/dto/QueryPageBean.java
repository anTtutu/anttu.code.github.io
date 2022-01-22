package com.anttu.dynamic.dto;

import lombok.Data;

/**
 * 描述
 *
 * @ClassName：QueryPageBean
 * @Description：分页查询 bean
 * @author：Anttu
 * @Date：6/11/2021 00:29
 */
@Data
public class QueryPageBean {
    /**
     * 开始展示条数
     */
    private int num;
    /**
     * 每页展示条数
     */
    private int size;
}
