package org.hibernate.domainmodel.basicTypes;

import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.BitSet;


/**
 * @TypeDef用于注册一个自定义类型
 */
@Entity(name = "Product")
@TypeDef(
        name = "bitset",
        defaultForType = BitSet.class,
        typeClass = BitSetType.class)
public class Product4 {

    @Id
    private Integer id;

    private BitSet bitSet;

    public Integer getId() {
        return id;
    }

    //Getters and setters are omitted for brevity


    public Product4() {
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
