package com.store.migros.service;

import com.store.migros.model.Customer;
import com.store.migros.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getCustomerById(Long id) {
		Optional<Customer> customerOptional = customerRepository.findById(id);

		if (customerOptional.isPresent()) {
			return customerOptional.get();
		} else {
			throw new RuntimeException("Customer not found with id: " + id);
		}
	}

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Long id, Customer customer) {
		Optional<Customer> existingCustomerOptional = customerRepository.findById(id);

		if (existingCustomerOptional.isPresent()) {
			Customer existingCustomer = existingCustomerOptional.get();
			existingCustomer.setName(customer.getName());
			existingCustomer.setEmail(customer.getEmail());
			existingCustomer.setPhoneNumber(customer.getPhoneNumber());

			return customerRepository.save(existingCustomer);
		} else {
			throw new RuntimeException("Customer not found with id: " + id);
		}
	}

	@Override
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
}
