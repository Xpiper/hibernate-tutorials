package org.hibernate.domainmodel.mappingenums;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Photo")
public class Photo {


    @Id
    private Integer id;

    private String name;

    @Convert(converter = CaptionConverter.class)
    private Caption caption;

    //Getters and setters are omitted for brevity


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }
}
