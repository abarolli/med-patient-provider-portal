package io.onicodes.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EnumDBFieldConverter<T extends Enum<T>> implements AttributeConverter<T, String> {

    private Class<T> enumType;

    public EnumDBFieldConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute != null ? attribute.name().toLowerCase() : null;
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        try {
            return dbData != null ? Enum.valueOf(enumType, dbData.toUpperCase()) : null;
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Unknown value for enum " + enumType.getSimpleName() + ": " + dbData);
        }
    }
}
