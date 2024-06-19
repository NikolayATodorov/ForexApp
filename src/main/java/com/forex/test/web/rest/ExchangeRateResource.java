package com.forex.test.web.rest;

import com.forex.test.repository.CurrentExchangeRatesJsonRequestRepository;
import com.forex.test.repository.ExchangeRateRepository;
import com.forex.test.repository.HistoryExchangeRatesJsonRequestRepository;
import com.forex.test.service.CurrentExchangeRatesJsonRequestService;
import com.forex.test.service.ExchangeRateService;
import com.forex.test.service.HistoryExchangeRatesJsonRequestService;
import com.forex.test.service.dto.CurrentExchangeRatesJsonRequestDTO;
import com.forex.test.service.dto.ExchangeRateDTO;
import com.forex.test.service.dto.HistoryExchangeRatesJsonRequestDTO;
import com.forex.test.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.forex.test.domain.ExchangeRate}.
 */
@RestController
//@RequestMapping("/api/exchange-rates")
@RequestMapping("/json_api")
public class ExchangeRateResource {

    private final Logger log = LoggerFactory.getLogger(ExchangeRateResource.class);

    private static final String ENTITY_NAME = "exchangeRate";
    private static final String ENTITY_NAME_CURR_EX_RATES_JSON_REQUEST = "currentExchangeRatesJsonRequest";
    private static final String ENTITY_NAME_HISTORY_EX_RATES_JSON_REQUEST = "historyExchangeRatesJsonRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExchangeRateService exchangeRateService;

    private final ExchangeRateRepository exchangeRateRepository;

    private final CurrentExchangeRatesJsonRequestService currentExchangeRatesJsonRequestService;
    private final CurrentExchangeRatesJsonRequestRepository currentExchangeRatesJsonRequestRepository;

    private final HistoryExchangeRatesJsonRequestService historyExchangeRatesJsonRequestService;
    private final HistoryExchangeRatesJsonRequestRepository historyExchangeRatesJsonRequestRepository;

    @Value(value = "${spring.exchange-rate-update-interval}")
    int updateInterval;

    public ExchangeRateResource(
        ExchangeRateService exchangeRateService,
        ExchangeRateRepository exchangeRateRepository,
        CurrentExchangeRatesJsonRequestService currentExchangeRatesJsonRequestService,
        CurrentExchangeRatesJsonRequestRepository currentExchangeRatesJsonRequestRepository,
        HistoryExchangeRatesJsonRequestService historyExchangeRatesJsonRequestService,
        HistoryExchangeRatesJsonRequestRepository historyExchangeRatesJsonRequestRepository
    ) {
        this.exchangeRateService = exchangeRateService;
        this.exchangeRateRepository = exchangeRateRepository;
        this.currentExchangeRatesJsonRequestService = currentExchangeRatesJsonRequestService;
        this.currentExchangeRatesJsonRequestRepository = currentExchangeRatesJsonRequestRepository;
        this.historyExchangeRatesJsonRequestService = historyExchangeRatesJsonRequestService;
        this.historyExchangeRatesJsonRequestRepository = historyExchangeRatesJsonRequestRepository;
    }

    /**
     * {@code POST /current} : Create a new currentExchangeRatesJsonRequest.
     *
     * @param currentExchangeRatesJsonRequestDTO the currentExchangeRatesJsonRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 200} and with body
     * the current ExchangeRateDTO, or with status {@code 400 (Bad Request)}
     * if the currentExchangeRatesJsonRequest has already an ID,
     * or if the provided requestId already exists,
     * or with status {@code 204} if there is no current data after the last update.

     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/current")
    public ResponseEntity<ExchangeRateDTO> createCurrentExchangeRatesJsonRequest(
        @Valid @RequestBody CurrentExchangeRatesJsonRequestDTO currentExchangeRatesJsonRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CurrentExchangeRatesJsonRequest : {}", currentExchangeRatesJsonRequestDTO);
        if (currentExchangeRatesJsonRequestDTO.getId() != null) {
            throw new BadRequestAlertException(
                "A new currentExchangeRatesJsonRequest cannot already have an ID",
                ENTITY_NAME_CURR_EX_RATES_JSON_REQUEST,
                "idexists"
            );
        }

        Optional<CurrentExchangeRatesJsonRequestDTO> existing = currentExchangeRatesJsonRequestService.findByRequestId(
            currentExchangeRatesJsonRequestDTO.getRequestId()
        );
        if (!existing.isEmpty()) {
            throw new BadRequestAlertException(
                "A currentExchangeRatesJsonRequest with the provided request id already exists",
                ENTITY_NAME_CURR_EX_RATES_JSON_REQUEST,
                "requestidexists"
            );
        }
        currentExchangeRatesJsonRequestService.save(currentExchangeRatesJsonRequestDTO);

        Instant now = Instant.now();
        Instant startOfPeriod = now.minus(updateInterval, ChronoUnit.MINUTES);

        List<ExchangeRateDTO> exchangeRates = exchangeRateService.findByBaseAndTimestampGreaterThanOrEqualOrderByTimestampDesc(
            currentExchangeRatesJsonRequestDTO.getCurrency(),
            startOfPeriod
        );

        ExchangeRateDTO exchangeRateDTO = null;
        if (exchangeRates != null && !exchangeRates.isEmpty() && exchangeRates.listIterator(0).hasNext()) {
            exchangeRateDTO = exchangeRates.listIterator(0).next();
        }

        if (exchangeRateDTO == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(exchangeRateDTO));
    }

    /**
     * {@code POST  /history} : Create a new historyExchangeRatesJsonRequest.
     *
     * @param historyExchangeRatesJsonRequestDTO the historyExchangeRatesJsonRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 200} and with body the list of ExchangeRateDTOs,
     * or with status {@code 400 (Bad Request)} if the historyExchangeRatesJsonRequest has already an ID,
     * or if the provided requestId already exists.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/history")
    public List<ExchangeRateDTO> createHistoryExchangeRatesJsonRequest(
        @Valid @RequestBody HistoryExchangeRatesJsonRequestDTO historyExchangeRatesJsonRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to save HistoryExchangeRatesJsonRequest : {}", historyExchangeRatesJsonRequestDTO);
        if (historyExchangeRatesJsonRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new historyExchangeRatesJsonRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }

        Optional<HistoryExchangeRatesJsonRequestDTO> existing = historyExchangeRatesJsonRequestService.findByRequestId(
            historyExchangeRatesJsonRequestDTO.getRequestId()
        );
        if (!existing.isEmpty()) {
            throw new BadRequestAlertException(
                "A currentExchangeRatesJsonRequest with the provided request id already exists",
                ENTITY_NAME_HISTORY_EX_RATES_JSON_REQUEST,
                "requestidexists"
            );
        }

        historyExchangeRatesJsonRequestService.save(historyExchangeRatesJsonRequestDTO);

        Instant now = Instant.now();
        Instant startOfPeriod = now.minus(historyExchangeRatesJsonRequestDTO.getPeriod(), ChronoUnit.HOURS);

        List<ExchangeRateDTO> exchangeRates = exchangeRateService.findByBaseAndTimestampGreaterThanOrEqualOrderByTimestampDesc(
            historyExchangeRatesJsonRequestDTO.getCurrency(),
            startOfPeriod
        );
        return exchangeRates;
    }

    /**
     * {@code POST  /exchange-rates} : Create a new exchangeRate.
     *
     * @param exchangeRateDTO the exchangeRateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new exchangeRateDTO, or with status {@code 400 (Bad Request)} if the exchangeRate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ExchangeRateDTO> createExchangeRate(@Valid @RequestBody ExchangeRateDTO exchangeRateDTO)
        throws URISyntaxException {
        log.debug("REST request to save ExchangeRate : {}", exchangeRateDTO);
        if (exchangeRateDTO.getId() != null) {
            throw new BadRequestAlertException("A new exchangeRate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        exchangeRateDTO = exchangeRateService.save(exchangeRateDTO);
        return ResponseEntity.created(new URI("/api/exchange-rates/" + exchangeRateDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, exchangeRateDTO.getId().toString()))
            .body(exchangeRateDTO);
    }

    /**
     * {@code PUT  /exchange-rates/:id} : Updates an existing exchangeRate.
     *
     * @param id the id of the exchangeRateDTO to save.
     * @param exchangeRateDTO the exchangeRateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exchangeRateDTO,
     * or with status {@code 400 (Bad Request)} if the exchangeRateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the exchangeRateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExchangeRateDTO> updateExchangeRate(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ExchangeRateDTO exchangeRateDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ExchangeRate : {}, {}", id, exchangeRateDTO);
        if (exchangeRateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, exchangeRateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exchangeRateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        exchangeRateDTO = exchangeRateService.update(exchangeRateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, exchangeRateDTO.getId().toString()))
            .body(exchangeRateDTO);
    }

    /**
     * {@code PATCH  /exchange-rates/:id} : Partial updates given fields of an existing exchangeRate, field will ignore if it is null
     *
     * @param id the id of the exchangeRateDTO to save.
     * @param exchangeRateDTO the exchangeRateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exchangeRateDTO,
     * or with status {@code 400 (Bad Request)} if the exchangeRateDTO is not valid,
     * or with status {@code 404 (Not Found)} if the exchangeRateDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the exchangeRateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ExchangeRateDTO> partialUpdateExchangeRate(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ExchangeRateDTO exchangeRateDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ExchangeRate partially : {}, {}", id, exchangeRateDTO);
        if (exchangeRateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, exchangeRateDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!exchangeRateRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ExchangeRateDTO> result = exchangeRateService.partialUpdate(exchangeRateDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, exchangeRateDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /exchange-rates} : get all the exchangeRates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of exchangeRates in body.
     */
    @GetMapping("")
    public List<ExchangeRateDTO> getAllExchangeRates() {
        log.debug("REST request to get all ExchangeRates");
        return exchangeRateService.findAll();
    }

    /**
     * {@code GET  /exchange-rates/:id} : get the "id" exchangeRate.
     *
     * @param id the id of the exchangeRateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the exchangeRateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExchangeRateDTO> getExchangeRate(@PathVariable("id") Long id) {
        log.debug("REST request to get ExchangeRate : {}", id);
        Optional<ExchangeRateDTO> exchangeRateDTO = exchangeRateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exchangeRateDTO);
    }

    /**
     * {@code DELETE  /exchange-rates/:id} : delete the "id" exchangeRate.
     *
     * @param id the id of the exchangeRateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExchangeRate(@PathVariable("id") Long id) {
        log.debug("REST request to delete ExchangeRate : {}", id);
        exchangeRateService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
