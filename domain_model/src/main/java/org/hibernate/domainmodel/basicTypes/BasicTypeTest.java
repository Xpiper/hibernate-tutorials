
package org.hibernate.domainmodel.basicTypes;

import java.util.BitSet;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import junit.framework.TestCase;

/**
 * Illustrates use of Hibernate native APIs.
 *
 * @author Steve Ebersole
 */
public class BasicTypeTest extends TestCase {
    private SessionFactory sessionFactory;

    @Override
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            MetadataSources sources = new MetadataSources(registry);
            MetadataBuilder metadataBuilder = sources.getMetadataBuilder();
            System.out.println(BitSetType.INSTANCE.getName());
            metadataBuilder.applyBasicType(BitSetType.INSTANCE);
            Metadata metadata = metadataBuilder.build();
            sessionFactory = metadata.buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    @SuppressWarnings("unchecked")
    public void testBasicUsage() {
        // create a couple of events...

        BitSet bitSet = BitSet.valueOf(new long[]{1, 2, 3});

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Product3 product = new Product3();
        product.setId(1);
        product.setBitSet(bitSet);
        session.save(product);
        session.getTransaction().commit();
        session.close();

        // now lets pull events from the database and list them
        session = sessionFactory.openSession();
        session.beginTransaction();
        Product3 product3 = session.get(Product3.class, 1);
        assertEquals(bitSet, product3.getBitSet());
        session.getTransaction().commit();
        session.close();
    }
}
