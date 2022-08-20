package _ashok_mini_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import _ashok_mini_api.entity.Plan;
import _ashok_mini_api.entity.PlanCategory;
import _ashok_mini_api.repo.PlanCategoryRepo;
import _ashok_mini_api.repo.PlanRepo;

@Service

public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planRepo;
	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {
		List<PlanCategory> categories = planCategoryRepo.findAll();
		Map<Integer, String> category = new HashMap<>();
		categories.forEach(e -> {
			category.put(e.getPlanCategoryId(), e.getPlancategoryName());
		});
		return category;
	}

	@Override
	public boolean savePlan(Plan plan) {
		this.planRepo.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public List<Plan> getPlans() {
		List<Plan> plans = planRepo.findAll();

		return plans;
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> plan = planRepo.findById(planId);
		if (plan.isPresent()) {
			return plan.get();
		}
		return null;
	}

	@Override
	public boolean deletePlan(Integer planId) {
		boolean isIdValid = false;
		try {
			planRepo.deleteById(planId);
			isIdValid = true;
			return isIdValid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isIdValid;

	}

	@Override
	public boolean updatePlan(Plan plan) {
		planRepo.save(plan);
		return plan.getPlanId() != null;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> plan = planRepo.findById(planId);
		if (plan.isPresent()) {
			Plan actualPlan = plan.get();
			actualPlan.setActive_sw(status);
			planRepo.save(actualPlan);
			return true;
		}
		return false;
	}

}
