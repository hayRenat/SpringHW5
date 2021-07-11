package hibernateApp.crud_product;

import hibernateApp.PrepareData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CrudApp {
    private static SessionFactory factory;

    public static void init(){
        PrepareData.forcePrepareData();
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void showManyItems() {
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();

            List<Product> productList = session.createQuery("from Product").getResultList();
            System.out.println(productList);
        }
    }

    public static void createInstance(){
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product pants = new Product("pants", 24);
            session.saveOrUpdate(pants);
            session.getTransaction().commit();
        }
    }

    public static void readAndPrintInstance(){
        try (Session session = factory.getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, 10L);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public static void updateInstance() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 10L);
            product.setCost(666);
            session.update(product);
            session.getTransaction().commit();
        }
    }

        public static void deleteInstance() {
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                Product product = session.get(Product.class, 10L);
                session.delete(product);
                session.getTransaction().commit();
            }
        }
    public static void shutdown(){
        factory.close();
    }

    public static void main (String[] args) {
        try {
            init();
            createInstance();
            readAndPrintInstance();
            updateInstance();
            readAndPrintInstance();
            deleteInstance();
            showManyItems();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
}
