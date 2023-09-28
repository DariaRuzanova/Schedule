package org.example;

import lombok.Data;

import java.time.Instant;

@Data
public class ResultSchedule {
    Instant startOperation;
    ResultOperation resultOperation;
}
