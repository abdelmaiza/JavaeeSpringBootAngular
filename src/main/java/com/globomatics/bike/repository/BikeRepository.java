package com.globomatics.bike.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globomatics.bike.model.Bike;

/**
 * Created by b010nsm on 19/03/2019.
 */
public interface BikeRepository extends JpaRepository<Bike, Long> {

}
