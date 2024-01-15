package com.example.thecommerce.società;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietàRepository extends JpaRepository<Società,Long> {
}
