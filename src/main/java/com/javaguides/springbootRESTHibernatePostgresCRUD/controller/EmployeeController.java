package com.javaguides.springbootRESTHibernatePostgresCRUD.controller;

import com.javaguides.springbootRESTHibernatePostgresCRUD.exception.ResourceNotFoundException;
import com.javaguides.springbootRESTHibernatePostgresCRUD.model.Employee;
import com.javaguides.springbootRESTHibernatePostgresCRUD.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "employee",description = "Employee CRUD API")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Operation(summary = "Find All Employees",description = "Returns List Of All Employees",tags = {"employee"})
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description  = "Successful Operation",
					content = @Content(
							array = @ArraySchema(schema = @Schema(implementation = Employee.class)),
							mediaType = MediaType.APPLICATION_JSON_VALUE)

			),@ApiResponse(
					responseCode = "500",
	                description = "Internal Server Error")
	})
	@GetMapping(value = "/employees",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}


    @Operation(summary = "Get Employee By ID",description = "Returns Single Employee Details",tags = {"employee"})
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Successful Operation",
					content = @Content (
							schema = @Schema(implementation = Employee.class),
							mediaType = MediaType.APPLICATION_JSON_VALUE)
			),
			@ApiResponse(
					responseCode = "404",
					description = "Employee Not Found"
			),
			@ApiResponse(
			        responseCode = "500",
			        description = "Internal Server Error")
	})
	@GetMapping(value = "/employees/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@Operation(summary = "Create New Employee",description = "Add New Employee",tags = {"employee"})
	@ApiResponses(value={
			@ApiResponse(
					responseCode = "201",
					description = "Record Created Successfully",
					content = @Content(
							array = @ArraySchema(schema = @Schema(implementation = Employee.class)),
							mediaType = MediaType.APPLICATION_JSON_VALUE)
			),
			@ApiResponse(
					responseCode = "400",
					description = "Invalid Input"
			),
			@ApiResponse(
					responseCode = "409",
					description = "Record Already Exist"
			),
			@ApiResponse(
			        responseCode = "500",
			        description = "Internal Server Error")

	})
	@PostMapping(value = "/employees" ,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee ) {
		return ResponseEntity.created(ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(employeeRepository.save(employee).getId()).toUri())
				.build();
	}

	@Operation(summary = "Update Existing Record",description = "Updating Existing Employee Record",tags = {"employee"})
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "202",
					description = "Record Updated Successfully",
					content = @Content(
							array = @ArraySchema(schema = @Schema(implementation = Employee.class)),
							mediaType = MediaType.APPLICATION_JSON_VALUE)

			),
			@ApiResponse(
					responseCode = "400",
					description = "Invalid ID supplied"
			),
			@ApiResponse(
					responseCode = "404",
					description = "Employee Not Found"
			),
			@ApiResponse(
			      responseCode = "405",
			      description ="Validation Exception"
			),
			@ApiResponse(
			responseCode = "500",
			description = "Internal Server Error")
	})
	@PutMapping(value = "/employees/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmail(employeeDetails.getEmail());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setProfession(employeeDetails.getProfession());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return new ResponseEntity<>(updatedEmployee, HttpStatus.ACCEPTED);
	}

	@Operation(summary = "Delete Existing Record",description = "Deletion Of Existing Employee Record",
			tags = {"employee"})
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Record Deleted Successfully"
			),
			@ApiResponse(
					responseCode = "404",
					description = "Record Not Found"
			),
			@ApiResponse(
			responseCode = "500",
			description = "Internal Server Error")
	})
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
