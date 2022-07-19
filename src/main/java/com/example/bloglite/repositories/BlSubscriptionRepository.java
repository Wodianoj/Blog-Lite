package com.example.bloglite.repositories;

import com.example.bloglite.entities.BlSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlSubscriptionRepository extends JpaRepository<BlSubscription, Long>
{
    boolean existsBySubscriberIdAndTagId(final Long subscriberId, final Long tagId);
}
