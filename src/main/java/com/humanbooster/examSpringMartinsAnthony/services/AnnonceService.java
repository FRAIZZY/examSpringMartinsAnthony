/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanbooster.examSpringMartinsAnthony.services;

import com.humanbooster.examSpringMartinsAnthony.model.Annonce;
import com.humanbooster.examSpringMartinsAnthony.repository.AnnonceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HB
 */
@Service
public class AnnonceService {
    
    @Autowired
    private AnnonceRepository annonceRepository;
    
    public AnnonceService(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }
    
    public List<Annonce> getAnnonces() {
        return this.annonceRepository.findAll();
    }
    
    public Annonce getAnnonce(Integer id) {
        return this.annonceRepository.findAnnonceById(id);
    }
    
    public void saveOrUpdate(Annonce annonce) {
        this.annonceRepository.save(annonce);
    }
    
    public void deleteAnnonce(Integer id) {
        this.annonceRepository.deleteById(id);
    }
    
}
