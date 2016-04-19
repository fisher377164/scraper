package com.techmix.fisher.entity;

import lombok.Data;

import java.util.List;

/**
 * @author fisher
 * @since 4/17/16
 */

@Data
public class Categori {
    private String url;

    private List<String> subCategories;
 }
