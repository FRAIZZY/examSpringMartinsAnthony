/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanbooster.examSpringMartinsAnthony.controller;

import com.humanbooster.examSpringMartinsAnthony.model.Annonce;
import com.humanbooster.examSpringMartinsAnthony.services.AnnonceService;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author HB
 */
@RestController
@RequestMapping(path = "/")
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;

    @RequestMapping(value = "list_annonce", method = RequestMethod.GET)
    public ModelAndView listAnnonce() {

        ModelAndView mv = new ModelAndView("listAnnonce");
        List<Annonce> annonces = this.annonceService.getAnnonces();
        mv.addObject("annonces", annonces);

        return mv;
    }

    @RequestMapping(value = "/show_annonce/{id}", method = RequestMethod.GET)
    public ModelAndView detailAnnonce(@PathVariable(name = "id", required = true) Integer id) {

        ModelAndView mv = new ModelAndView("showAnnonce");
        Annonce annonce = this.annonceService.getAnnonce(id);
        mv.addObject("annonce", annonce);

        if (annonce == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce Not Found");
        }

        return mv;
    }
    
    @RequestMapping(value = "/create_annonce", method = RequestMethod.GET)
    public ModelAndView createAnnonce() {

        Annonce annonce = new Annonce();
        ModelAndView mv = new ModelAndView("createAnnonce");
        mv.addObject("annonce", annonce);

        return mv;
    }

    @RequestMapping(value = "/create_annonce", method = RequestMethod.POST)
    public ModelAndView submitCreateAnnonce (@Valid Annonce annonce, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("createAnnonce");
        } else {
            Date datePublication = new Date();
            annonce.setDatePublication(datePublication);
            this.annonceService.saveOrUpdate(annonce);
            return new ModelAndView("redirect:/list_annonce");
        }
    }
    
    @RequestMapping(value = "/edit_annonce/{id}", method = RequestMethod.GET)
    public ModelAndView editAnnonce(@PathVariable(name = "id", required = true) Integer id) {
        ModelAndView mv = new ModelAndView("createAnnonce");

        Annonce annonce = this.annonceService.getAnnonce(id);
        mv.addObject("annonce", annonce);

        if (annonce == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce Not Found");
        }

        return mv;
    }

    @RequestMapping(value = "/edit_annonce/{id}", method = RequestMethod.POST)
    public ModelAndView submitEditAnnonce(@Valid Annonce annonce, BindingResult bindingResult) {

        if (annonce == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Annonce Not Found");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("createAnnonce");
        } else {
            this.annonceService.saveOrUpdate(annonce);
            return new ModelAndView("redirect:/list_annonce");
        }
    }
    
    @RequestMapping(value = "/delete_annonce/{id}", method = RequestMethod.GET)
    public ModelAndView deleteAnnonce(@PathVariable(name = "id", required = true) Integer id) {
        if (id == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Annonce Not Found"
            );
        } else {
            this.annonceService.deleteAnnonce(id);
            return new ModelAndView("redirect:/list_annonce");
        }
    }
}
