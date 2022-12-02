package com.camunda.example.CCMS.curd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.camunda.example.CCMS.fluxdata;

public interface curdopeartionCCSM extends JpaRepository<fluxdata, String> {
	
	fluxdata findBycourtid(String courtid);
	fluxdata findByadharno(String adharno);
	fluxdata findByname(String name);
	fluxdata findByemail(String email);
	
	
	@Modifying
	@Query("update fluxdata set checkerComment = ?1 where name = ?2")
	  void setcheckercomment(String checkercomment , String name);
	
	@Modifying
	@Query("update fluxdata set makercomment = ?1 where name = ?2")
	  void setmakercomment(String makercomment , String name);
}
