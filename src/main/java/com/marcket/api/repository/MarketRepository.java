package com.marcket.api.repository;

import com.marcket.api.model.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {

    @Query("SELECT m.country.name, m.code, SUM(s.share) as shares FROM Market AS m JOIN Share as s on m.id = s.market.id " +
            "GROUP BY m.code, m.country.name ORDER BY m.country.name, m.code DESC")
    List<Object[]> sumSharesPerMarketAndCountry();

}
