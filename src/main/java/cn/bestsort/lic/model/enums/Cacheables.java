package cn.bestsort.dubai.model.enums;

import java.util.LinkedList;

/**
 * 实现该接口则表示可以将其内容存储在缓存中和持久化
 * @author bestsort
 * @version 1.0
 * @date 3/14/20 9:03 AM
 */
public interface Cacheables<T> extends ValueEnum {
    /**
     * 返回该枚举类在缓存中包含的字段
     * @return list
     */
    LinkedList<T> getAllFields();
}
