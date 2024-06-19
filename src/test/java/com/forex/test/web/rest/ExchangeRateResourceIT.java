package com.forex.test.web.rest;

import static com.forex.test.domain.ExchangeRateAsserts.*;
import static com.forex.test.web.rest.TestUtil.createUpdateProxyForBean;
import static com.forex.test.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forex.test.IntegrationTest;
import com.forex.test.domain.ExchangeRate;
import com.forex.test.repository.ExchangeRateRepository;
import com.forex.test.service.dto.ExchangeRateDTO;
import com.forex.test.service.mapper.ExchangeRateMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ExchangeRateResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ExchangeRateResourceIT {

    private static final String DEFAULT_BASE = "AAAAAAAAAA";
    private static final String UPDATED_BASE = "BBBBBBBBBB";

    private static final Instant DEFAULT_TIMESTAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TIMESTAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final BigDecimal DEFAULT_USD = new BigDecimal(1);
    private static final BigDecimal UPDATED_USD = new BigDecimal(2);

    private static final BigDecimal DEFAULT_GBP = new BigDecimal(1);
    private static final BigDecimal UPDATED_GBP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CHF = new BigDecimal(1);
    private static final BigDecimal UPDATED_CHF = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/exchange-rates";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private ExchangeRateMapper exchangeRateMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExchangeRateMockMvc;

    private ExchangeRate exchangeRate;

    private ExchangeRate insertedExchangeRate;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExchangeRate createEntity(EntityManager em) {
        ExchangeRate exchangeRate = new ExchangeRate()
            .base(DEFAULT_BASE)
            .timestamp(DEFAULT_TIMESTAMP)
            .usd(DEFAULT_USD)
            .gbp(DEFAULT_GBP)
            .chf(DEFAULT_CHF);
        return exchangeRate;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ExchangeRate createUpdatedEntity(EntityManager em) {
        ExchangeRate exchangeRate = new ExchangeRate()
            .base(UPDATED_BASE)
            .timestamp(UPDATED_TIMESTAMP)
            .usd(UPDATED_USD)
            .gbp(UPDATED_GBP)
            .chf(UPDATED_CHF);
        return exchangeRate;
    }

    @BeforeEach
    public void initTest() {
        exchangeRate = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedExchangeRate != null) {
            exchangeRateRepository.delete(insertedExchangeRate);
            insertedExchangeRate = null;
        }
    }

    @Test
    @Transactional
    void createExchangeRate() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ExchangeRate
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(exchangeRate);
        var returnedExchangeRateDTO = om.readValue(
            restExchangeRateMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(exchangeRateDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ExchangeRateDTO.class
        );

        // Validate the ExchangeRate in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedExchangeRate = exchangeRateMapper.toEntity(returnedExchangeRateDTO);
        assertExchangeRateUpdatableFieldsEquals(returnedExchangeRate, getPersistedExchangeRate(returnedExchangeRate));

        insertedExchangeRate = returnedExchangeRate;
    }

    @Test
    @Transactional
    void createExchangeRateWithExistingId() throws Exception {
        // Create the ExchangeRate with an existing ID
        exchangeRate.setId(1L);
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(exchangeRate);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restExchangeRateMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(exchangeRateDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ExchangeRate in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkBaseIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        exchangeRate.setBase(null);

        // Create the ExchangeRate, which fails.
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(exchangeRate);

        restExchangeRateMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(exchangeRateDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTimestampIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        exchangeRate.setTimestamp(null);

        // Create the ExchangeRate, which fails.
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(exchangeRate);

        restExchangeRateMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(exchangeRateDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllExchangeRates() throws Exception {
        // Initialize the database
        insertedExchangeRate = exchangeRateRepository.saveAndFlush(exchangeRate);

        // Get all the exchangeRateList
        restExchangeRateMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(exchangeRate.getId().intValue())))
            .andExpect(jsonPath("$.[*].base").value(hasItem(DEFAULT_BASE)))
            .andExpect(jsonPath("$.[*].timestamp").value(hasItem(DEFAULT_TIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].usd").value(hasItem(sameNumber(DEFAULT_USD))))
            .andExpect(jsonPath("$.[*].gbp").value(hasItem(sameNumber(DEFAULT_GBP))))
            .andExpect(jsonPath("$.[*].chf").value(hasItem(sameNumber(DEFAULT_CHF))));
    }

    @Test
    @Transactional
    void getExchangeRate() throws Exception {
        // Initialize the database
        insertedExchangeRate = exchangeRateRepository.saveAndFlush(exchangeRate);

        // Get the exchangeRate
        restExchangeRateMockMvc
            .perform(get(ENTITY_API_URL_ID, exchangeRate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(exchangeRate.getId().intValue()))
            .andExpect(jsonPath("$.base").value(DEFAULT_BASE))
            .andExpect(jsonPath("$.timestamp").value(DEFAULT_TIMESTAMP.toString()))
            .andExpect(jsonPath("$.usd").value(sameNumber(DEFAULT_USD)))
            .andExpect(jsonPath("$.gbp").value(sameNumber(DEFAULT_GBP)))
            .andExpect(jsonPath("$.chf").value(sameNumber(DEFAULT_CHF)));
    }

    @Test
    @Transactional
    void getNonExistingExchangeRate() throws Exception {
        // Get the exchangeRate
        restExchangeRateMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingExchangeRate() throws Exception {
        // Initialize the database
        insertedExchangeRate = exchangeRateRepository.saveAndFlush(exchangeRate);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the exchangeRate
        ExchangeRate updatedExchangeRate = exchangeRateRepository.findById(exchangeRate.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedExchangeRate are not directly saved in db
        em.detach(updatedExchangeRate);
        updatedExchangeRate.base(UPDATED_BASE).timestamp(UPDATED_TIMESTAMP).usd(UPDATED_USD).gbp(UPDATED_GBP).chf(UPDATED_CHF);
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(updatedExchangeRate);

        restExchangeRateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, exchangeRateDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(exchangeRateDTO))
            )
            .andExpect(status().isOk());

        // Validate the ExchangeRate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedExchangeRateToMatchAllProperties(updatedExchangeRate);
    }

    @Test
    @Transactional
    void putNonExistingExchangeRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        exchangeRate.setId(longCount.incrementAndGet());

        // Create the ExchangeRate
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(exchangeRate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExchangeRateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, exchangeRateDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(exchangeRateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExchangeRate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchExchangeRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        exchangeRate.setId(longCount.incrementAndGet());

        // Create the ExchangeRate
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(exchangeRate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExchangeRateMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(exchangeRateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExchangeRate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamExchangeRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        exchangeRate.setId(longCount.incrementAndGet());

        // Create the ExchangeRate
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(exchangeRate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExchangeRateMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(exchangeRateDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExchangeRate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateExchangeRateWithPatch() throws Exception {
        // Initialize the database
        insertedExchangeRate = exchangeRateRepository.saveAndFlush(exchangeRate);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the exchangeRate using partial update
        ExchangeRate partialUpdatedExchangeRate = new ExchangeRate();
        partialUpdatedExchangeRate.setId(exchangeRate.getId());

        partialUpdatedExchangeRate.base(UPDATED_BASE).gbp(UPDATED_GBP).chf(UPDATED_CHF);

        restExchangeRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExchangeRate.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedExchangeRate))
            )
            .andExpect(status().isOk());

        // Validate the ExchangeRate in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertExchangeRateUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedExchangeRate, exchangeRate),
            getPersistedExchangeRate(exchangeRate)
        );
    }

    @Test
    @Transactional
    void fullUpdateExchangeRateWithPatch() throws Exception {
        // Initialize the database
        insertedExchangeRate = exchangeRateRepository.saveAndFlush(exchangeRate);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the exchangeRate using partial update
        ExchangeRate partialUpdatedExchangeRate = new ExchangeRate();
        partialUpdatedExchangeRate.setId(exchangeRate.getId());

        partialUpdatedExchangeRate.base(UPDATED_BASE).timestamp(UPDATED_TIMESTAMP).usd(UPDATED_USD).gbp(UPDATED_GBP).chf(UPDATED_CHF);

        restExchangeRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExchangeRate.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedExchangeRate))
            )
            .andExpect(status().isOk());

        // Validate the ExchangeRate in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertExchangeRateUpdatableFieldsEquals(partialUpdatedExchangeRate, getPersistedExchangeRate(partialUpdatedExchangeRate));
    }

    @Test
    @Transactional
    void patchNonExistingExchangeRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        exchangeRate.setId(longCount.incrementAndGet());

        // Create the ExchangeRate
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(exchangeRate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExchangeRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, exchangeRateDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(exchangeRateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExchangeRate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchExchangeRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        exchangeRate.setId(longCount.incrementAndGet());

        // Create the ExchangeRate
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(exchangeRate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExchangeRateMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(exchangeRateDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ExchangeRate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamExchangeRate() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        exchangeRate.setId(longCount.incrementAndGet());

        // Create the ExchangeRate
        ExchangeRateDTO exchangeRateDTO = exchangeRateMapper.toDto(exchangeRate);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExchangeRateMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(exchangeRateDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ExchangeRate in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteExchangeRate() throws Exception {
        // Initialize the database
        insertedExchangeRate = exchangeRateRepository.saveAndFlush(exchangeRate);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the exchangeRate
        restExchangeRateMockMvc
            .perform(delete(ENTITY_API_URL_ID, exchangeRate.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return exchangeRateRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected ExchangeRate getPersistedExchangeRate(ExchangeRate exchangeRate) {
        return exchangeRateRepository.findById(exchangeRate.getId()).orElseThrow();
    }

    protected void assertPersistedExchangeRateToMatchAllProperties(ExchangeRate expectedExchangeRate) {
        assertExchangeRateAllPropertiesEquals(expectedExchangeRate, getPersistedExchangeRate(expectedExchangeRate));
    }

    protected void assertPersistedExchangeRateToMatchUpdatableProperties(ExchangeRate expectedExchangeRate) {
        assertExchangeRateAllUpdatablePropertiesEquals(expectedExchangeRate, getPersistedExchangeRate(expectedExchangeRate));
    }
}
