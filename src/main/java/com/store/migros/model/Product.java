package com.store.migros.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Double price;
	private Integer stock;
	
	private String imageUrl;
	private String category;

	@OneToMany(mappedBy = "product")
	private List<OrderDetails> orderDetails;
}
