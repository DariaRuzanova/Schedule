package org.example.model;

import lombok.Data;
import lombok.Getter;

import java.time.Instant;

@Data
public class Period {

    private Instant startDate;
    private Instant endDate;

}
