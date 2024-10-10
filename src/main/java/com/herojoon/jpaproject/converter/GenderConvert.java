package com.herojoon.jpaproject.converter;

import com.herojoon.jpaproject.constraint.Gender;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;

@Converter(autoApply = true)
public class GenderConvert implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return Optional.ofNullable(attribute).orElse(Gender.NULL).getValue();
    }

    @Override
    public Gender convertToEntityAttribute(String s) {
        if (StringUtils.isEmpty(s)) {
            return Gender.NULL;
        }
        return Gender.findOf(s);
    }
}
