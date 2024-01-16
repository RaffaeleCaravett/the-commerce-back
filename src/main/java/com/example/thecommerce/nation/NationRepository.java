package com.example.thecommerce.nation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationRepository extends JpaRepository<Nation,Long> {
    Nation findByCity_Id(long cityId);
}
