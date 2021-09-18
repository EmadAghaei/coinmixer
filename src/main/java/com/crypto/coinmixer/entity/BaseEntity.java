package com.crypto.coinmixer.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    protected Long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    protected Instant createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    protected Instant modifiedAt;

    @Column
    @Version
    protected int version;

}
