/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.humanbooster.examSpringMartinsAnthony.model;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author HB
 */
@Entity
@Table(name = "annonce")
public class Annonce {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "title", nullable = false, length = 250)
    @NotNull(message = "Le titre ne peut pas etre null")
    @NotBlank(message = "Le titre ne peut pas etre vide")
    private String title;

    @Basic
    @Column(name = "image", nullable = false, length = 250)
    @NotNull(message = "L'image ne peut pas etre null")
    @NotBlank(message = "L'image ne peut pas etre vide")
    private String image;

    @Basic
    @Column(name = "content", nullable = false, length = 250)
    @Length(min = 20, message = "Le contenu doit contenir au minimum 20 caractères")
    @NotNull(message = "Le contenu ne peut pas etre null")
    @NotBlank(message = "Le contenu ne peut pas etre vide")
    private String content;

    @Basic
    @Column(name = "date_publication", nullable = true)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePublication;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", title=" + title + ", image=" + image + ", content=" + content + ", datePublication=" + datePublication + '}';
    }

}
