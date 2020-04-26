package org.hibernate.domainmodel.mappingblob;

import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.Clob;

public class Product {


    @Id
    private Integer id;

    private String name;

    @Lob
    private Clob warranty;

    //Getters and setters are omitted for brevity

}


