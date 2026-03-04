package com.revature.nosql.demo.repository;

import com.revature.nosql.demo.model.Product;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

// We extend CassandraRepository instead of JpaRepository.
// We get all the standard save(), findById(), and delete() methods for free.
@Repository
public interface ProductRepository extends CassandraRepository<Product, UUID> {
    
    // We can also use Spring Data's magic query derivation techniques!
    // However, REMEMBER CASSANDRA'S RESTRICTIONS: 'name' must be an indexed column
    // or this will crash the application trying to do a full table scan.
    // List<Product> findByName(String name);
}
