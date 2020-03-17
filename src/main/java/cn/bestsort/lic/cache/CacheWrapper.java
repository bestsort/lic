package cn.bestsort.lic.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 9:44 AM
 */

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CacheWrapper<V> implements Serializable {
    private V data;
    private Timestamp expireAt;
    private Timestamp createAt;
}
