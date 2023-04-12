package pro.sky.cw3.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import pro.sky.cw3.components.SizeConverter;

public class WebMvcConfigurationSupport {

    public void addFormatter(FormatterRegistry registry) {
        registry.addConverter((Converter<?, ?>) new SizeConverter());
    }
}
