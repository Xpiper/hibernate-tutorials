package org.hibernate.domainmodel.basicTypes;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 隐式声明
 */
@Entity(name = "Product2")
public class Product2 {

    @Id
    private Integer id;


    private String sku;


    private String name;


    private String description;
}
