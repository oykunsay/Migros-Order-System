package com.store.migros.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "address_details")
	private String addressDetails;

	private String city;
	private String district;

	@Column(name = "address_type")
	private String addressType;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonBackReference
	private Customer customer;
}
