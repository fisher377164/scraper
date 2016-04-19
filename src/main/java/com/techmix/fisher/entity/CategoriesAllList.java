package com.techmix.fisher.entity;

import lombok.Data;

import java.util.List;

/**
 * @author fisher
 * @since 4/17/16
 */
@Data
public class CategoriesAllList {

    private String title;

    private List<Categori> categories;
}
