package com.elshoura.lokit.models.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name="created_at",nullable = false)
    private LocalDateTime createAt;

    @PrePersist
    public void onCreate(){
        this.createAt = LocalDateTime.now();
    }
}
