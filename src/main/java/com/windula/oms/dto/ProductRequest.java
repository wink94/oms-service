package com.windula.oms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    @Size(min = 1, message = "one or more products are required to be added")
    List<@Valid ProductDTO> productList;

}
