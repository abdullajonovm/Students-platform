package students.platform.andqxai.uz.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import students.platform.andqxai.uz.domain.*; // for static metamodels
import students.platform.andqxai.uz.domain.Apartment;
import students.platform.andqxai.uz.repository.ApartmentRepository;
import students.platform.andqxai.uz.service.criteria.ApartmentCriteria;
import students.platform.andqxai.uz.service.dto.ApartmentDTO;
import students.platform.andqxai.uz.service.mapper.ApartmentMapper;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Apartment} entities in the database.
 * The main input is a {@link ApartmentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApartmentDTO} or a {@link Page} of {@link ApartmentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApartmentQueryService extends QueryService<Apartment> {

    private final Logger log = LoggerFactory.getLogger(ApartmentQueryService.class);

    private final ApartmentRepository apartmentRepository;

    private final ApartmentMapper apartmentMapper;

    public ApartmentQueryService(ApartmentRepository apartmentRepository, ApartmentMapper apartmentMapper) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentMapper = apartmentMapper;
    }

    /**
     * Return a {@link List} of {@link ApartmentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApartmentDTO> findByCriteria(ApartmentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Apartment> specification = createSpecification(criteria);
        return apartmentMapper.toDto(apartmentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApartmentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApartmentDTO> findByCriteria(ApartmentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Apartment> specification = createSpecification(criteria);
        return apartmentRepository.findAll(specification, page).map(apartmentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApartmentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Apartment> specification = createSpecification(criteria);
        return apartmentRepository.count(specification);
    }

    /**
     * Function to convert {@link ApartmentCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Apartment> createSpecification(ApartmentCriteria criteria) {
        Specification<Apartment> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Apartment_.id));
            }
            if (criteria.getLocation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocation(), Apartment_.location));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), Apartment_.phone));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Apartment_.description));
            }
            if (criteria.getPrice() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPrice(), Apartment_.price));
            }
            if (criteria.getRequirements() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRequirements(), Apartment_.requirements));
            }
            if (criteria.getRoomFit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRoomFit(), Apartment_.roomFit));
            }
        }
        return specification;
    }
}
