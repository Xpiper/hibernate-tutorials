package org.hibernate.domainmodel.basicTypes;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * 明确声明
 * <p>
 * Java基本类型（boolean，int，等）
 * <p>
 * 包装的原始类型（java.lang.Boolean，java.lang.Integer等）
 * <p>
 * java.lang.String
 * <p>
 * java.math.BigInteger
 * <p>
 * java.math.BigDecimal
 * <p>
 * java.util.Date
 * <p>
 * java.util.Calendar
 * <p>
 * java.sql.Date
 * <p>
 * java.sql.Time
 * <p>
 * java.sql.Timestamp
 * <p>
 * byte[] 要么 Byte[]
 * <p>
 * char[] 要么 Character[]
 * <p>
 * enums
 * 实现的任何其他类型Serializable
 */
@Entity(name = "Product")
public class Product {

    @Id
    @Basic
    private Integer id;

    /**
     * optional -布尔值（默认为true）定义此属性是否允许空值。
     * 只要类型不是原始类型，Hibernate就会将此表示基础列应为NULLABLE。
     * <p>
     * fetch -FetchType（默认为EAGER）定义此属性是应立即获取还是延迟获取。
     * 在JPA中，EAGER是提供程序（休眠）的一项要求，即在提取所有者时应提取值，而LAZY只是暗示在访问属性时要提取值。
     */
    @Basic(optional = true, fetch = FetchType.EAGER)
    private String sku;

    @Basic
    @Type(type = "nstirng")
    private String name;

    @Basic
    @Column(name = "NOTES")
    @Type(type = "materialized_nclob")
    private String description;
}
