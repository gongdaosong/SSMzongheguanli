package cn.itcast.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.common.utils.Page;
import cn.itcast.crm.mapper.CustomerMapper;
import cn.itcast.crm.pojo.Customer;
import cn.itcast.crm.pojo.QueryVo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public Page<Customer> queryCustomerByQueryVo(QueryVo queryVo) {
		// 设置查询条件,从哪一条数据开始查
		queryVo.setStart((queryVo.getPage() - 1) * queryVo.getRows());
		
		// 查询数据结果集
		List<Customer> list = this.customerMapper.queryCustomerByQueryVo(queryVo);
		// 查询到的数据总条数
		int total = this.customerMapper.queryCountByQueryVo(queryVo);
		// 封装返回的page对象
		Page<Customer> page = new Page<>(total, queryVo.getPage(), queryVo.getRows(), list);
		return page;
	}
	//通过ID查询
	@Override
	public Customer queryCustomerById(Long id) {
		Customer customer = this.customerMapper.queryCustomerById(id);
		return customer;
	}
	//通过id更新
	@Override
	public Customer updateCustomerById(Customer customer) {
		return this.customerMapper.updateCustomerById(customer);
	}
	//通过id删除
	@Override
	public void deleteCustomerById(Long id) {
		this.customerMapper.deleteCustomerById(id);
	}

}
