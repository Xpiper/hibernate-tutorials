
package org.hibernate.domainmodel.mappingenums;

import junit.framework.TestCase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Illustrates use of Hibernate native APIs.
 *
 * @author Steve Ebersole
 */
public class MappingEnumsTest extends TestCase {
    private SessionFactory sessionFactory;

    @Override
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory =
                    new MetadataSources(registry).buildMetadata().buildSessionFactory();
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
    public void testMappingEnums() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Phone phone = new Phone();
        phone.setId(1L);
        phone.setNumber("123-456-78990");
        phone.setType(PhoneType.MOBILE);
        session.save(phone);
        session.getTransaction().commit();
        session.close();
    }

    @SuppressWarnings("unchecked")
    public void testMappingEnumsString() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Phone2 phone = new Phone2();
        phone.setId(1L);
        phone.setNumber("123-456-78990");
        phone.setType(PhoneType.MOBILE);
        session.save(phone);
        session.getTransaction().commit();
        session.close();
    }


    @SuppressWarnings("unchecked")
    public void testAttributeConverter() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Person person = new Person();
        person.setId(1L);
        person.setName("李鹏达");
        person.setGender(Gender.MALE);
        session.save(person);
        session.getTransaction().commit();
//        session.close();

        session.beginTransaction();
        Person person1 = session.get(Person.class, 1L);
        System.out.println(person1.gender);
        session.getTransaction().commit();
        session.close();
    }


    @SuppressWarnings("unchecked")
    public void testAttributeConverter2() {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Photo photo = new Photo();
        Caption caption = new Caption("Nicolae Grigorescu");
        photo.setId(1);
        photo.setName("李鹏达");
        photo.setCaption(caption);
        session.save(photo);
        session.getTransaction().commit();

        session.beginTransaction();
        Photo photo2 = session.createQuery(
                "select p " +
                        "from Photo p " +
                        "where upper(caption) = upper(:caption) ", Photo.class)
                .setParameter("caption", "Nicolae Grigorescu")
                .getSingleResult();
        System.out.println(photo2.getCaption());
        session.getTransaction().commit();

        session.close();
    }

    @SuppressWarnings("unchecked")
    public void testAttributeConverter3() {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        Account account = new Account();
        Money money = new Money(1000L);
        account.setId(1L);
        account.setOwner("李鹏达");
        account.setBalance(money);
        session.save(account);
        session.getTransaction().commit();

        session.beginTransaction();
        Account account2 = session.createQuery(
                "select a " +
                        "from Account a " +
                        "where balance = :balance ", Account.class)
                .setParameter("balance", new Money(1000L))
                .getSingleResult();
        System.out.println(account2.getBalance().getCents());
        session.getTransaction().commit();

        session.close();
    }


}
