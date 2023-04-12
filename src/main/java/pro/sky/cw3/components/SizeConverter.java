package pro.sky.cw3.components;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import pro.sky.cw3.model.Size;

public class SizeConverter implements Converter<String, Size> {

    @Override
    public Size convert(String sourch) {
        return Size.convertSize(Integer.parseInt(sourch));
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }
}
