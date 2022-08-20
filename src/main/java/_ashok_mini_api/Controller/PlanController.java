package _ashok_mini_api.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import _ashok_mini_api.entity.Plan;
import _ashok_mini_api.service.PlanServiceImpl;


@RestController
public class PlanController {

	@Autowired
	private PlanServiceImpl service;

	@GetMapping("/plancategories")
	public ResponseEntity<Map<Integer, String>> getPlanCategories() {
		Map<Integer, String> planCategories = service.getPlanCategories();
		return new ResponseEntity<>(planCategories, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> createPlan(@RequestBody Plan plan) {
		String response = "";
		boolean isSaved = service.savePlan(plan);
		if (isSaved) {
			response = "Plan is saved";
		} else {
			response = "Plan is not saved";
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> viewPlans() {

		List<Plan> plans = service.getPlans();
		return new ResponseEntity<>(plans, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		String response = "";
		boolean isUpdated = service.savePlan(plan);
		if (isUpdated) {
			response = "Plan is updated";
		} else {
			response = "Plan is not updated";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/plans/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan plan = service.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	@DeleteMapping("/deleteplan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		String response = "";
		boolean isDeleted = service.deletePlan(planId);
		if (isDeleted) {
			response = "Plan has been deleted successfully";
		} else {
			response = "Plan has not been deleted successfully";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> planStatusChange(@PathVariable Integer planId,  @PathVariable String status) {
		String response = "";
		boolean planStatusChange = service.planStatusChange(planId, status);
		if (planStatusChange) {
			response = "Status has been changed succesfully";
		} else {
			response = "Status has not been changed succesfully";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
