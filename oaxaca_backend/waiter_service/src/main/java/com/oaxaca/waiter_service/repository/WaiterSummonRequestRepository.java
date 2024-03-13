package com.oaxaca.waiter_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.oaxaca.waiter_service.model.WaiterSummonRequest;

@Repository
public interface WaiterSummonRequestRepository extends CrudRepository<WaiterSummonRequest, Integer> {

}
