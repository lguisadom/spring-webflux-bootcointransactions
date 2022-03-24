package com.nttdata.lagm.bootcointx.model.movement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankingMovementType {
    private Integer id;
    private String description;
}
