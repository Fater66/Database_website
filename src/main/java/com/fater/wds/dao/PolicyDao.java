package com.fater.wds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fater.wds.entity.Policy;

public interface PolicyDao {
	
	/*
	 * 返回querypolicylist总数
	 */
	int queryPolicyCount(@Param("policyCondition")Policy policyCondition);
	
	List<Policy> queryAllPolicy();
	/*
	 * 因为参数不止一个所以要加@Param
	 * @param policyCondition
	 * @param rowIndex 从第几行开始取
	 * @param pageSize 返回的条数
	 * @return
	 */
//	List<Policy> queryPolicyList(@Param("policyCondition")Policy policyCondition,@Param("rowIndex")int rowIndex,@Param("pageSize") int pageSize);
	List<Policy> queryPolicyList(@Param("policyCondition") Policy policyCondition);
	/*
	 * 通过ID查找policy
	 * @param policyId
	 * @return
	 */
	Policy queryByPolicyId(long policyId);
	
	/*
	 * 新增policy
	 * @param 
	 * @return
	 */
	int insertPolicy(Policy policy);
	
	/*
	 * 更新policy
	 * @param policy
	 * @return
	 */
	int updatePolicy(Policy policy);
}
