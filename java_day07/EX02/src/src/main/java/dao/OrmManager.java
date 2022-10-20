package dao;

import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public class OrmManager {

    public OrmManager() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM " + User.class.getSimpleName()).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction t1 = session.beginTransaction();
        session.save(user);
        t1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction t1 = session.beginTransaction();
        session.update(user);
        t1.commit();
        session.close();
    }

    public User findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }
}
