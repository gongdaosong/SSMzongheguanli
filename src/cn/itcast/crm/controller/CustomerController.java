package cn.itcast.crm.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.itcast.common.utils.Page;
import cn.itcast.crm.pojo.BaseDict;
import cn.itcast.crm.pojo.Customer;
import cn.itcast.crm.pojo.QueryVo;
import cn.itcast.crm.service.BaseDictService;
import cn.itcast.crm.service.CustomerService;

@Controller
@RequestMapping("customer")
public class CustomerController {
	// 客户来源 
	@Value("${CUSTOMER_FROM_TYPE}")
	private String CUSTOMER_FROM_TYPE;
	// 客户行业
	@Value("${CUSTOMER_INDUSTRY_TYPE}")
	private String CUSTOMER_INDUSTRY_TYPE;
	// 客户级别 
	@Value("${CUSTOMER_LEVEL_TYPE}")
	private String CUSTOMER_LEVEL_TYPE;
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BaseDictService baseDictService;
	/**
	 * 显示用户页面
	 */
	@RequestMapping("list")
	public String queryCustometrList(QueryVo queryVo, Model model) {
		try {
			// 解决get请求乱码问题
			if (StringUtils.isNotBlank(queryVo.getCustName())) {
			queryVo.setCustName(new String(queryVo.getCustName().getBytes("UTF-8"), "UTF-8"));
			}
			} catch (Exception e) {
			e.printStackTrace();
			}
		// 客户来源
		List<BaseDict> fromType = this.baseDictService.queryBaseDictByDictTypeCode(this.CUSTOMER_FROM_TYPE);
		// 所属行业
		List<BaseDict> industryType = this.baseDictService.queryBaseDictByDictTypeCode(this.CUSTOMER_INDUSTRY_TYPE);
		// 客户级别
		List<BaseDict> levelType = this.baseDictService.queryBaseDictByDictTypeCode(this.CUSTOMER_LEVEL_TYPE);
		// 把前端页面需要显示的数据放到模型中
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);
		
		// 分页查询数据
		Page<Customer> page = this.customerService.queryCustomerByQueryVo(queryVo);
		// 把分页查询的结果放到模型中
		model.addAttribute("page", page);
		// 数据回显
		model.addAttribute("custName", queryVo.getCustName());
		model.addAttribute("custSource", queryVo.getCustSource());
		model.addAttribute("custIndustry", queryVo.getCustIndustry());
		model.addAttribute("custLevel", queryVo.getCustLevel());
		return "customer";
	}
	
	/**
	* 根据id查询用户,返回json格式数据
	*
	* @param id
	* @return
	*/
	@RequestMapping("edit")
	@ResponseBody
	public Customer queryCustomerById(Long id) {
		Customer customer = this.customerService.queryCustomerById(id);
		return customer;
	}
	
	/**
	* 根据id查询用户,返回更新后客户的json格式数据
	*
	* @param id
	* @return
	*/
	@RequestMapping("update")
	@ResponseBody
	public String updateCustomerById(Customer customer) {
		Customer result = this.customerService.updateCustomerById(customer);
		return "OK";
	}
	
	/**
	* 删除用户
	*
	* @param id
	* @return
	*/
	@RequestMapping("delete")
	@ResponseBody
	public String deleteCustomerById(Long id) {
		this.customerService.deleteCustomerById(id);
		return "ok";
	}
}
