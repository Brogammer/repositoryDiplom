package kurswork.remaster.KursWorkKpiApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import kurswork.remaster.KursWorkKpiApp.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByLogin(String employee_login);
}
