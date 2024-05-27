package com.berijalan.technicaltest.repository;

import com.berijalan.technicaltest.entity.Buah;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface BuahRepository extends JpaRepository<Buah, Integer> {

    @Query("SELECT b FROM Buah b WHERE b.isDeleted=false")
    List<Buah> findAllBuah();

    @Query("UPDATE Buah b SET b.isDeleted=true WHERE b.id=:id")
    @Modifying
    void deleteBuahById(Integer id);
}
