package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import com.cgi.dentistapp.customValidator.VisitTimeConstraint;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;

/**
 * Created by serkp on 2.03.2017.
 */

@VisitTimeConstraint(field = "dentistName", fieldMatch = "visitTime")
public class DentistVisitDTO {
	
    @Size(min = 1, max = 50)
    String dentistName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime visitTime;   
    
    public DentistVisitDTO() {
    }

    public DentistVisitDTO(String dentistName, LocalDateTime  visitTime) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;
    }
    
    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public LocalDateTime  getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime  visitTime) {
        this.visitTime = visitTime;
    }
}
