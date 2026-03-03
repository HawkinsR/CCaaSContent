package com.example.demoapp.service;

import com.example.demoapp.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service demonstrating Criteria API, Native SQL, and Named Queries.
 * Note: We inject the EntityManager, but unwrap it to a Hibernate Session
 * if we need Hibernate-specific features (though native JPA works fine here
 * too).
 */
@Service
@Transactional(readOnly = true)
public class QueryDemoService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 1. Criteria API
     * Object-oriented, type-safe query building.
     */
    public List<Book> findBooksByAuthorCriteria(String authorName) {
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);

        query.select(root)
                .where(cb.equal(root.get("author"), authorName))
                .orderBy(cb.asc(root.get("title")));

        return session.createQuery(query).getResultList();
    }

    /**
     * 2. Native SQL
     * Database-specific SQL string.
     */
    public List<Book> findExpensiveBooksNative(double minPrice) {
        Session session = entityManager.unwrap(Session.class);
        return session.createNativeQuery(
                "SELECT * FROM books WHERE price > :minParam", Book.class)
                .setParameter("minParam", minPrice)
                .getResultList();
    }

    /**
     * 3. Named Query
     * Executes the query pre-defined on the Book entity.
     */
    public List<Book> findByTitleNamed(String keyword) {
        Session session = entityManager.unwrap(Session.class);
        return session.createNamedQuery("Book.findByTitleKeyword", Book.class)
                .setParameter("keyword", "%" + keyword + "%")
                .getResultList();
    }
}
