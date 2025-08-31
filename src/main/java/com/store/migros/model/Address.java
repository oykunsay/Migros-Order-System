package com.store.migros.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

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

	public Address() {
	}

	public Address(String addressDetails, String city, String district, String addressType, Customer customer) {
		this.addressDetails = addressDetails;
		this.city = city;
		this.district = district;
		this.addressType = addressType;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
