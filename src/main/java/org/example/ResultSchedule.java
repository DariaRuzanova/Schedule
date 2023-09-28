package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ResultSchedule {
    Instant startOperation;
    ResultOperation resultOperation;
}
