package _ashok_mini_api.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import _ashok_mini_api.entity.Plan;

@Service

public interface PlanService {

	public Map<Integer, String> getPlanCategories();

	public boolean savePlan(Plan plan);

	public List<Plan> getPlans();

	public Plan getPlanById(Integer planId);

	public boolean deletePlan(Integer planId);

	public boolean updatePlan(Plan plan);

	public boolean planStatusChange(Integer planId, String status);

}
