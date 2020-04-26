package com.downloadablezebras.marlos.data.statuscondition;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode
public class StatusCondition {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String condition_type;
    private String description;
}
