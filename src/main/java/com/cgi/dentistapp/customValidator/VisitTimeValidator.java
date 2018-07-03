package com.cgi.dentistapp.customValidator;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.cgi.dentistapp.dao.entity.DentistVisitEntity;
import com.cgi.dentistapp.service.DentistVisitService;

public class VisitTimeValidator implements ConstraintValidator<VisitTimeConstraint, Object> {
	
	@Autowired
    private DentistVisitService dentistVisitService;
	private String field;
	private String fieldMatch;
	
	@Override
	public void initialize(VisitTimeConstraint vtc) {
		this.field = vtc.field();
        this.fieldMatch = vtc.fieldMatch();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext arg1) {
		LocalDateTime checkableVisitTime;
		Object dentistName = new BeanWrapperImpl(value).getPropertyValue(field);
		LocalDateTime visitTime = (LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
		
		for(DentistVisitEntity visit : dentistVisitService.listVisits()) {
			if(visit.getDentistName().equals(dentistName)) {
				checkableVisitTime = visit.getVisitTime();
				if(visitTime.isEqual(checkableVisitTime) || (visitTime.isAfter(checkableVisitTime) && visitTime.isBefore(checkableVisitTime.plusMinutes(30)))) {
					return false;
				}
			}
		}
		
		return true;
	}

}
