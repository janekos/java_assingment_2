package com.cgi.dentistapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cgi.dentistapp.dao.entity.DentistVisitEntity;

import java.util.List;

@Repository
public class DentistVisitDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(DentistVisitEntity visit) {
        entityManager.persist(visit);
    }
    
    public DentistVisitEntity find(long id) {
        return entityManager.find(DentistVisitEntity.class, id);
    }
    
    public void update(DentistVisitEntity visit) {
        entityManager.merge(visit);
    }
    
    public void delete(DentistVisitEntity visit) {
        entityManager.remove(visit);
    }
    
    public List<DentistVisitEntity> getAllVisits() {
        return entityManager.createQuery("SELECT e FROM DentistVisitEntity e").getResultList();
    }
}
