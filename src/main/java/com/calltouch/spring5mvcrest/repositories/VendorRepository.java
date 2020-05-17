package com.calltouch.spring5mvcrest.repositories;

import com.calltouch.spring5mvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByName(String name);
}
