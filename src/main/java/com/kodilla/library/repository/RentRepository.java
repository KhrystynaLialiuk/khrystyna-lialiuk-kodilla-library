package com.kodilla.library.repository;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long> {

    @Override
    List<Rent> findAll();

    @Override
    Optional<Rent> findById(Long id);

    Optional<Rent> findByCopy(Copy copy);
}
