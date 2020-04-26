package org.hibernate.domainmodel.mappingenums;

import javax.persistence.AttributeConverter;

public class MoneyConverter implements AttributeConverter<Money, Long> {

    /**
     * 到数据库
     *
     * @param attribute
     * @return
     */
    @Override
    public Long convertToDatabaseColumn(Money attribute) {
        return attribute == null ? null : attribute.getCents();
    }

    /**
     * 数据库到实体类
     *
     * @param dbData
     * @return
     */
    @Override
    public Money convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : new Money(dbData);
    }
}
