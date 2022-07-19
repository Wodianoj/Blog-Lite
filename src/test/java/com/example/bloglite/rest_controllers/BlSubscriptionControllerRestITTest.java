package com.example.bloglite.rest_controllers;

import com.example.bloglite.entities.BlSubscription;
import com.example.bloglite.entities.BlTag;
import com.example.bloglite.entities.BlUser;
import com.example.bloglite.pojo.BlSubscriptionPojo;
import com.example.bloglite.repositories.BlSubscriptionRepository;
import com.example.bloglite.repositories.BlTagRepository;
import com.example.bloglite.repositories.BlUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("dev")
class BlSubscriptionControllerRestITTest
{
    private static final long SUBSCRIBER_ID = 2L;
    private static final long TAG_ID = 4L;

    private static final String SUBSCRIPTION_URL = "/subscription";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BlSubscriptionRepository repository;

    @Autowired
    private BlUserRepository userRepository;

    @Autowired
    private BlTagRepository tagRepository;

    @BeforeEach
    void setUp()
    {
        repository.deleteAll();
    }

    @Nested
    class GetAll
    {
        @Test
        void getAll_shouldReturnSuccessStatusCode_inAllConditions()
        {
            // when
            final ResponseEntity<Object> response = restTemplate.getForEntity("/subscription", Object.class);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void getAll_shouldCorrectlyReturnPersistedEntities()
        {
            // given
            final BlSubscription persistedOne = createSubscriptionOne();
            final BlSubscription persistedTwo = createSubscriptionTwo();

            // when
            final ResponseEntity<BlSubscription[]> response = restTemplate.getForEntity("/subscription",
                    BlSubscription[].class);

            // then
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
            final List<BlSubscription> body = asList(response.getBody());
            assertThat(body).containsOnly(persistedOne, persistedTwo);
        }
    }

    @Nested
    class GetById
    {
        @Test
        void getById_shouldReturnSuccessStatusCode_whenTheEntityExists()
        {
            // given
            final BlSubscription persistedOne = createSubscriptionOne();
            final String url = String.format("/subscription/%s", persistedOne.getId());


            // when
            final ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void getById_shouldReturnFailureStatusCode_whenTheEntityDoesNotExist()
        {
            // when
            final ResponseEntity<Object> response = restTemplate.getForEntity("/subscription/100", Object.class);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

        @Test
        void getById_shouldCorrectlyReturnASinglePersistedEntity()
        {
            // given
            final BlSubscription persistedOne = createSubscriptionOne();
            final BlSubscription persistedTwo = createSubscriptionTwo();
            final String url = String.format("/subscription/%s", persistedOne.getId());

            // when
            final ResponseEntity<BlSubscription> response = restTemplate.getForEntity(url, BlSubscription.class);

            // then
            assertThat(response).isNotNull();
            assertThat(response.getBody()).isNotNull();
            final BlSubscription body = response.getBody();
            assertThat(body).isEqualTo(persistedOne).isNotEqualTo(persistedTwo);
        }
    }

    @Nested
    class Created
    {
        @Test
        void create_shouldReturnErrorCode_whenDuplicateIsToBeCreated()
        {
            // given
            createSubscriptionOne();

            final BlSubscriptionPojo pojo = new BlSubscriptionPojo();
            pojo.setSubscriberId(SUBSCRIBER_ID);
            pojo.setTagId(TAG_ID);

            // when
            final ResponseEntity<Object> responseTwo = restTemplate.postForEntity("/subscription", pojo, Object.class);

            // then
            assertThat(responseTwo.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void create_shouldReturnErrorCode_whenRequiredFieldsNotPresent()
        {
            // given
            final BlSubscriptionPojo pojo = new BlSubscriptionPojo();

            // when
            final ResponseEntity<Object> response = restTemplate.postForEntity("/subscription", pojo, Object.class);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void create_shouldReturnAppropriateResponseCodeOfCreated_whenAllOk()
        {
            // given
            final BlSubscriptionPojo pojo = new BlSubscriptionPojo();
            pojo.setSubscriberId(1L);
            pojo.setTagId(3L);

            // when
            final ResponseEntity<Object> response = restTemplate.postForEntity("/subscription", pojo, Object.class);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void create_shouldCorrectlyPersistAllInformationForSubscription_whenAllOk()
        {
            // given
            final BlSubscriptionPojo pojo = new BlSubscriptionPojo();
            pojo.setSubscriberId(SUBSCRIBER_ID);
            pojo.setTagId(TAG_ID);

            // when
            final ResponseEntity<BlSubscription> response = restTemplate.postForEntity("/subscription", pojo,
                    BlSubscription.class);

            // then
            final BlSubscription newSubscription = response.getBody();
            assertThat(newSubscription).isNotNull();
            assertThat(newSubscription.getId()).isNotNull();
            assertThat(newSubscription.getSubscriber().getId()).isEqualTo(SUBSCRIBER_ID);
            assertThat(newSubscription.getTag().getId()).isEqualTo(TAG_ID);
            assertThat(newSubscription.getStartDate()).isNotNull().isCloseTo(LocalDateTime.now(), within(10, SECONDS));
            assertThat(newSubscription.getEndDate()).isNull();
        }
    }

    @Nested
    class Deactivate
    {
        @Test
        void deactivate_shouldReturnOk_whenDeactivateSuccessful()
        {
            // given
            final BlSubscription persisted = createSubscriptionOne();
            final String url = String.format("/subscription/%s", persisted.getId());

            // when
            final ResponseEntity<Object> response = restTemplate.exchange(url, DELETE, null, Object.class);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

        @Test
        void deactivate_shouldReturnNotFound_whenTheSubscriptionToDeactivateDoesNotExist()
        {
            // given
            final String url = String.format("/subscription/%s", Integer.MAX_VALUE);

            // when
            final ResponseEntity<Object> response = restTemplate.exchange(url, DELETE, null, Object.class);

            // then
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }

    private BlSubscription createSubscriptionOne()
    {
        final BlSubscription subscriptionOne = new BlSubscription();
        final BlUser user = userRepository.findById(SUBSCRIBER_ID).orElse(null);
        subscriptionOne.setSubscriber(user);
        final LocalDateTime startDate = LocalDateTime.now().truncatedTo(SECONDS);
        subscriptionOne.setStartDate(startDate);
        final LocalDateTime endDate = LocalDateTime.now().truncatedTo(SECONDS);
        subscriptionOne.setEndDate(endDate);
        final BlTag tag = tagRepository.findById(TAG_ID).orElse(null);
        subscriptionOne.setTag(tag);
        return repository.save(subscriptionOne);
    }

    private BlSubscription createSubscriptionTwo()
    {
        final BlSubscription subscriptionTwo = new BlSubscription();
        final BlUser userTwo = userRepository.findById(2L).orElse(null);
        subscriptionTwo.setSubscriber(userTwo);
        final LocalDateTime startDateTwo = LocalDateTime.now().minusDays(1).truncatedTo(SECONDS);
        subscriptionTwo.setStartDate(startDateTwo);
        final LocalDateTime endDateTwo = LocalDateTime.now().truncatedTo(SECONDS);
        subscriptionTwo.setEndDate(endDateTwo);
        final BlTag tagTwo = tagRepository.findById(2L).orElse(null);
        subscriptionTwo.setTag(tagTwo);
        return repository.save(subscriptionTwo);
    }
}