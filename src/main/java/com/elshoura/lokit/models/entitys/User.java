package com.elshoura.lokit.models.entitys;

import com.elshoura.lokit.utils.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role=Role.CUSTOMER;

    @Column(nullable = false)
    private Boolean enabled=true;

    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified=false;

    @Column(name = "created_at" , nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at" , nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if(this.role==null){
            this.role=Role.CUSTOMER;
        }
        if (this.enabled==null){
            this.enabled=false;
        }
        if (this.emailVerified==null){
            this.emailVerified=false;
        }
        if (this.email !=null){
            this.email=this.email.toLowerCase();
        }

        }
        @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        if (this.email != null) {
            this.email = this.email.toLowerCase();
        }
    }
    }


