package com.marcket.api.repository;

import com.marcket.api.model.Commiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommiterRepository extends JpaRepository<Commiter, Long> {
}
