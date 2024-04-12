package com.ll.demo03;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
}
