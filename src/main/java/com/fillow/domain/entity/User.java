package com.fillow.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder //객체 안전하게 생성
@AllArgsConstructor //모든필드의생성자 자동생성
@NoArgsConstructor //기본생성자 자동생성
@Table(
        name = "user",
        uniqueConstraints = {
                //제약조건: login_id와 email은 중복불가 (Unique)
                @UniqueConstraint(name = "unique_login_id", columnNames = "login_id"),
                @UniqueConstraint(name = "unique_email", columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login_id", nullable = false, length = 255)
    private String loginId;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "password", nullable = false, length = 255)
    private String password; // 해시 저장

    //어케 작동?
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false) //변경시 자동갱신
    private LocalDateTime updatedAt;

    // User(1) : Product(N)
    @OneToMany(mappedBy = "user")
    private List<Product> product =new ArrayList<>();
}