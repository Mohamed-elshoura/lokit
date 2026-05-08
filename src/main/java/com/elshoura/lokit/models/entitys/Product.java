package com.elshoura.lokit.models.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 200)
   private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name ="brand_id",nullable = false)
    private Brand brand;

    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @ManyToOne(optional = false , fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Builder.Default
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductVariant> variants = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @Column(nullable = false)
    private Boolean active=true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.active ==null)
        {this.active=true;}
}

@PreUpdate
public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
}
}
