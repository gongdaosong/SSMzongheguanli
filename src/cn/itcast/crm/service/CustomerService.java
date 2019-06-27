package cn.itcast.crm.service;

import cn.itcast.common.utils.Page;
import cn.itcast.crm.pojo.Customer;
import cn.itcast.crm.pojo.QueryVo;

public interface CustomerService {
	/**
	* 根据条件分页查询客户
	*
	* @param queryVo
	* @return
	*/
	Page<Customer> queryCustomerByQueryVo(QueryVo queryVo);
	
	/**
	* 根据id查询数据
	*
	* @param id
	* @return
	*/
	Customer queryCustomerById(Long id);
	
	/**
	* 根据id编辑客户数据
	*
	* @param customer
	*/
	Customer updateCustomerById(Customer customer);
	
	/**
	* 根据id删除客户
	*
	* @param id
	*/
	void deleteCustomerById(Long id);
}
