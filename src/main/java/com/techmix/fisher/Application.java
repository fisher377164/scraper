package com.techmix.fisher;

import com.techmix.fisher.scraper.impl.SouqScraper;

/**
 * @author fisher
 * @since 4/17/16
 */
public class Application {

    public static void main(String[] args) {
        System.out.println(new SouqScraper().getProducts());
    }
}
