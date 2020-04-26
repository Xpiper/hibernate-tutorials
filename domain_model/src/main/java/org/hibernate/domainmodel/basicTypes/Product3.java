package org.hibernate.domainmodel.basicTypes;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.BitSet;

@Entity(name = "Product")
public class Product3 {

    @Id
    private Integer id;

    @Type(type = "bitset")
    private BitSet bitSet;

    public Integer getId() {
        return id;
    }

    //Getters and setters are omitted for brevity


    public Product3() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    public void setBitSet(BitSet bitSet) {
        this.bitSet = bitSet;
    }
}
