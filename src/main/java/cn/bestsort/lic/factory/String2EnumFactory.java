package cn.bestsort.lic.factory;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * @author bestsort
 * @version 1.0
 * @date 3/12/20 9:12 AM
 */

@Component
public class String2EnumFactory implements ConverterFactory<String, Enum> {
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToEnumConverter(targetType);
    }

    private static class StringToEnumConverter<T extends Enum>
            implements Converter<String, T> {

        private Class<T> enumType;

        private StringToEnumConverter(Class<T> enumType) {
            this.enumType = enumType;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T convert(String source) {
            return (T) Enum.valueOf(this.enumType, source.toUpperCase());
        }
    }

}