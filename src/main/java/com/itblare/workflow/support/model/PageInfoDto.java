package com.itblare.workflow.support.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * 自定义分页结果
 *
 * @author Blare
 * @version 1.0.0
 * @since 2021/7/14 10:35
 */
public class PageInfoDto<T> implements Serializable {

    /**
     * 总数
     */
    private long total = 0;

    /**
     * 每页显示条数，默认 10
     */
    private int size = 10;

    /**
     * 当前页
     */
    private int current = 1;

    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();

    /**
     * 排序信息
     */
    private List<Order> orders;

    public PageInfoDto(long total, int size, int current, List<T> records, List<Order> orders) {
        this.total = total;
        this.size = size;
        this.current = current;
        this.records = records;
        this.orders = orders;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public static class Order implements Serializable {

        /**
         * 排序字段
         */
        private String property;

        /**
         * 排序方向
         */
        private Direction direction;

        public Order(String property, Direction direction) {
            super();
            this.property = property;
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }
    }

    public enum Direction {

        ASC, DESC;

        public boolean isAscending() {
            return this.equals(ASC);
        }

        public boolean isDescending() {
            return this.equals(DESC);
        }

        public static Direction fromString(String value) {

            try {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format("给定排序方向的值“%s”无效！ 必须是“desc”或“asc”（不区分大小写）！", value), e);
            }
        }

        public static Optional<Direction> fromOptionalString(String value) {
            try {
                return Optional.of(fromString(value));
            } catch (IllegalArgumentException e) {
                return Optional.empty();
            }
        }
    }
}