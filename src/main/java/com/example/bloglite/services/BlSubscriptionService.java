package com.example.bloglite.services;

import com.example.bloglite.entities.BlSubscription;
import com.example.bloglite.entities.BlTag;
import com.example.bloglite.entities.BlUser;
import com.example.bloglite.pojo.BlSubscriptionPojo;
import com.example.bloglite.repositories.BlSubscriptionRepository;
import com.example.bloglite.repositories.BlTagRepository;
import com.example.bloglite.repositories.BlUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BlSubscriptionService
{
    private final BlSubscriptionRepository repository;
    private final BlUserRepository userRepository;
    private final BlTagRepository tagRepository;

    @Autowired
    BlSubscriptionService(final BlSubscriptionRepository repository,
                          final BlUserRepository userRepository,
                          final BlTagRepository tagRepository)
    {
        this.repository = repository;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public BlSubscription create(final BlSubscriptionPojo subscriptionPojo)
    {
        final BlSubscription subscription = new BlSubscription();
        final BlUser subscriber = userRepository.findById(subscriptionPojo.getSubscriberId()).orElseThrow();
        subscription.setSubscriber(subscriber);
        final BlTag tag = tagRepository.findById(subscriptionPojo.getTagId()).orElseThrow();
        subscription.setTag(tag);
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(null);

        return repository.save(subscription);
    }

    public void deactivate(final Long subscriptionId)
    {
        final BlSubscription subscription = repository.findById(subscriptionId)
                .orElseThrow();
        subscription.setEndDate(LocalDateTime.now());
        repository.save(subscription);
    }

    public boolean exists(final BlSubscriptionPojo subscriptionPojo)
    {
        return repository.existsBySubscriberIdAndTagId(subscriptionPojo.getSubscriberId(), subscriptionPojo.getTagId());
    }
}
