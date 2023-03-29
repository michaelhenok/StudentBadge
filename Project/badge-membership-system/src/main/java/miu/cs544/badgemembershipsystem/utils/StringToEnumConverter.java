package miu.cs544.badgemembershipsystem.utils;


import miu.cs544.badgemembershipsystem.utils.enums.MemberShipTypeEnum;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, MemberShipTypeEnum> {
    @Override
    public MemberShipTypeEnum convert(String source) {
        return MemberShipTypeEnum.valueOf(source.toUpperCase());
    }
}
