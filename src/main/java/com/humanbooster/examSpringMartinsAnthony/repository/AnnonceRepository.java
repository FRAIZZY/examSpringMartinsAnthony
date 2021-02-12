/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanbooster.examSpringMartinsAnthony.repository;

import com.humanbooster.examSpringMartinsAnthony.model.Annonce;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HB
 */
@Repository
public interface AnnonceRepository extends CrudRepository<Annonce, Integer>{
    List<Annonce> findAll();
    Annonce findAnnonceById(Integer id);
}
