package com.feriwinarta.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Data
@ToString
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "created_date", nullable = false, updatable = false)
    @JsonIgnore
    @CreatedDate
    private Date createdDate;

    @Column(name = "modified_date")
    @JsonIgnore
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(name = "created_by")
    @JsonIgnore
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @JsonIgnore
    @LastModifiedBy
    private String modifiedBy;
}
