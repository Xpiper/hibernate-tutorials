package org.hibernate.domainmodel.mappingytpes;


import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;

/**
 * create table Contact (
 * id integer not null,
 * first varchar(255),
 * last varchar(255),
 * middle varchar(255),
 * notes varchar(255),
 * starred boolean not null,
 * website varchar(255),
 * primary key (id)
 * )
 */


@Entity(name = "Contact")
public class Contact {

    @Id
    private Integer id;

    private Name name;

    private String notes;

    private URL website;

    private boolean starred;

    //Getters and setters are omitted for brevity


    @Embeddable
    public class Name {

        private String first;

        private String middle;

        private String last;

        // getters and setters omitted
    }

}


