package co.pragra.pragracoffetime.repo;

import co.pragra.pragracoffetime.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c from Customer c WHERE upper(c.lastName) = upper(:lastName)")

    //@Query(value = "SELECT * FROM TABLE_CUSTOMER WHERE UPPER(LASTNAME) = UPPER(:lastname)", nativeQuery = true)
    List<Customer> findAllByLastName(@Param("lastName") String lastName);
}

//  SELECT * FROM TABLE_CUSTOMER WHERE UPPER(LASTNAME) = UPPER(?)