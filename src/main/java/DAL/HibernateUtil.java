package DAL;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author phanq
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
//        try {
//            return new Configuration().configure().buildSessionFactory();
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//            return null;
//        }
        try {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure().build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder()
                    .build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void close() {
        getSessionFactory().close();
    }
}
