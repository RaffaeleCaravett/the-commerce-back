package com.example.thecommerce.acquisto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto,Long> {

List<Acquisto> findAllByUser_Id(long id);
}
