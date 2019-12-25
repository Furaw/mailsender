package com.yukon.ita.recipient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipientRepository extends JpaRepository<Recipient,Long> {
    @Query("from Recipient r" + "where ")
    List<Recipient> findbyId(@Param("recipientId") Long recipientId);
}
