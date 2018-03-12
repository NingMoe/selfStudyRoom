package cn.xdf.selfStudyRoom.domain.dao;

import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import cn.xdf.selfStudyRoom.domain.entity.Customer;

public interface CustomerDao extends ElasticsearchRepository<Customer, Long> {

	public Customer findByFirstName(String firstName);

	public List<Customer> findByLastName(String lastName);
}
