package cn.bestsort.lic.utils;

import cn.bestsort.lic.model.enums.propertys.AliOssProperties;
import cn.bestsort.lic.model.enums.propertys.PropertyEnum;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2020/3/17 上午9:39
 */

public class EnumUtil {

    public static Map<String, PropertyEnum> getValuePropertyEnumMap(){
        // Get all properties
        List<Class<? extends PropertyEnum>> propertyEnumClasses = new LinkedList<>();
        propertyEnumClasses.add(AliOssProperties.class);

        Map<String, PropertyEnum> result = new HashMap<>();

        propertyEnumClasses.forEach(propertyEnumClass -> {
            PropertyEnum[] propertyEnums = propertyEnumClass.getEnumConstants();

            for (PropertyEnum propertyEnum : propertyEnums) {
                result.put(propertyEnum.getValue(), propertyEnum);
            }
        });
        return result;
    }
}
