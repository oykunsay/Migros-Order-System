package com.store.migros.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

	private Long id;

	private Long customerId;

	private LocalDate orderDate;
	private Double totalPrice;

	private List<OrderDetailsDto> orderDetails;
	private List<ProductDto> products;

}
