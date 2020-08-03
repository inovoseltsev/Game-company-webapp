package com.inovoseltsev.lightdev.domain.entity;

import com.inovoseltsev.lightdev.domain.state.State;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@Data
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "creation_date")
    private Date created;

    @LastModifiedDate
    @Column(name = "update_date")
    private Date updated;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 25)
    private State state;
}
