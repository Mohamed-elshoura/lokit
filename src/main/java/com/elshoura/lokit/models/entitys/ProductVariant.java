package com.elshoura.lokit.models.entitys;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_variants")
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false ,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne(optional = false ,fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id",nullable = false)
    private Size size;

    @ManyToOne(optional = false ,fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id",nullable = false)
    private Color color;

    @Column( nullable = false)
    private String sku;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

}
