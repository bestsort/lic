package cn.bestsort.lic.listener.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 缓存事件通知
 * @author bestsort
 * @version 1.0
 * @date 2020/3/17 上午9:52
 */

@Getter
public class CacheEvent extends ApplicationEvent {
    /**
     * 改动的key
     */
    private String key;

    /**
     * 改动后的值
     */
    private String value;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CacheEvent(Object source, String key, String value) {
        super(source);
        this.key = key;
        this.value = value;
    }
}
