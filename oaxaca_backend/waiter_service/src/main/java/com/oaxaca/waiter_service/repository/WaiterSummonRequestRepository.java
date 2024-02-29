package com.oaxaca.waiter_summon_service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterSummonRequestRepository extends CrudRepository<WaiterSummonRequest, Integer> {

}
