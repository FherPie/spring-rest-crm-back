package com.componente.factinven.security.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class IDedDTO extends AuditedDTO{

    private Integer id;
}
