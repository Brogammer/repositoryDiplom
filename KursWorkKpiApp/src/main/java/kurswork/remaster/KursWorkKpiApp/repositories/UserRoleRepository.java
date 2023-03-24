package kurswork.remaster.KursWorkKpiApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kurswork.remaster.KursWorkKpiApp.model.UserRole;
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

}
