package com.elshoura.lokit.models.entitys;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    private ProductSpecification() {
    }

    public static Specification<Product> search(
            String keyword,
            Long brandId,
            Long categoryId,
            Long colorId,
            Long sizeId,
            BigDecimal minPrice,
            BigDecimal maxPrice
    ) {
        return (root, query, criteriaBuilder) -> {

            query.distinct(true);

            var predicate = criteriaBuilder.conjunction();

            if (keyword != null && !keyword.isBlank()) {
                String likeKeyword = "%" + keyword.toLowerCase() + "%";

                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.or(
                                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likeKeyword),
                                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), likeKeyword)
                        )
                );
            }

            if (brandId != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("brand").get("id"), brandId)
                );
            }

            if (categoryId != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("category").get("id"), categoryId)
                );
            }

            boolean needsVariantJoin =
                    colorId != null ||
                            sizeId != null ||
                            minPrice != null ||
                            maxPrice != null;

            if (needsVariantJoin) {
                Join<Product, ProductVariant> variantJoin = root.join("variants", JoinType.INNER);

                if (colorId != null) {
                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.equal(variantJoin.get("color").get("id"), colorId)
                    );
                }

                if (sizeId != null) {
                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.equal(variantJoin.get("size").get("id"), sizeId)
                    );
                }

                if (minPrice != null) {
                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.greaterThanOrEqualTo(variantJoin.get("price"), minPrice)
                    );
                }

                if (maxPrice != null) {
                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.lessThanOrEqualTo(variantJoin.get("price"), maxPrice)
                    );
                }
            }

            return predicate;
        };
    }
}
