package com.apidesign.customerapidemo2.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.apidesign.customerapidemo2.models.Customer;
import com.apidesign.customerapidemo2.repositories.CustomerRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.github.tennaito.rsql.jpa.JpaCriteriaQueryVisitor;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	

	
	@Autowired
	private EntityManager entityManager;
	
	//insert the customer
	public Customer saveCustomer(Customer customer)
	{
		return this.customerRepo.save(customer);
	}
	
	//findall
	public List<Customer> getAllCustomers()
	{
		return this.customerRepo.findAll();
	}
	
	
	public Customer getCustomerById(long customerId)
	{
		return this.customerRepo.findById(customerId).orElse(null);
	}
	//delete the customer
	//select where by customerId
		public void deleteCustomerById(long customerId)
		{
		     this.customerRepo.deleteById(customerId);
		}
	
		//update the customer
		public Customer updateCustomer(Customer customer)
		{
			return this.customerRepo.save(customer);
		}
		
		public List<Customer> findPaginated(int offset, int limit) {
			int pageNumber = (int) (Math.floor(offset / limit) + ( offset % limit ));    
	        Pageable paging = PageRequest.of(pageNumber,limit);
	        Page<Customer> pagedResult = customerRepo.findAll(paging);

	        return pagedResult.toList();
	    }
		
		public List<Customer> getAllCustomers(Integer pageNo, Integer pageSize, String sortBy)
	    {
	        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	 
	        Page<Customer> pagedResult = customerRepo.findAll(paging);
	         
	        if(pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Customer>();
	        }
	    }
		
		
		 
		    public Page<Customer> query(String condition, Pageable pageable){
		        // 1.Create the JPA Visitor
		        RSQLVisitor<CriteriaQuery<Customer>, EntityManager> visitor = new JpaCriteriaQueryVisitor<Customer>();
		        // 2.Parse a RSQL into a Node
		        Node rootNode = new RSQLParser().parse(condition);
		        // 3.Create CriteriaQuery
		        CriteriaQuery<Customer> criteriaQuery = rootNode.accept(visitor, entityManager);
		        List<Customer> total = entityManager.createQuery(criteriaQuery).getResultList();
		        List<Customer> resultList = entityManager.createQuery(criteriaQuery)
		                .setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		 
		        return new PageImpl<>(resultList,pageable, total.size());
		    }
}
