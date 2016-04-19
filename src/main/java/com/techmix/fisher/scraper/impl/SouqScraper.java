package com.techmix.fisher.scraper.impl;

import com.techmix.fisher.entity.Product;
import com.techmix.fisher.scraper.AbstractScraper;
import com.techmix.fisher.scraper.BasicCategoriesCrawler;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * scraper for http://uae.souq.com/
 *
 * @author fisher
 * @since 4/18/16
 */
public class SouqScraper extends AbstractScraper {

    //TODO: insert  url to test but do not forget "?page=%d" !!!
    private static final String EXAMPLE_CATEGORY_URL = "http://uae.souq.com/ae-en/dental-care/l/?page=%d";

    private static final String URL = "http://uae.souq.com/ae-en/shop-all-categories/c/";

    private static final String CAT_URL_SELECTOR = ".side-nav a";

    private static final String PRODUCT_SELECTOR = ".placard";

    private static final String SHOW_MORE_BUTTON_SELECTOR = ".show-more-result";

    private static final String PRODUCT_URL_SELECTOR = ".row > a";
    private static final String IMG_SRC_SELECTOR = ".align-image-vertically img[src]";
    private static final String PRICE_SELECTOR = ".price-block";
    private static final String TITLE_SELECTOR = ".result-item-title";

    @Override
    public Collection getProducts() {
        final List<String> categories = new BasicCategoriesCrawler()
                .getCategories(URL, CAT_URL_SELECTOR).stream()
                .map((category) -> category + "?page=%d").collect(Collectors.toList());

        System.out.println("categories.size()" + categories.size());
        System.out.println("categories" + categories);

        //TODO: here i remove all categories and add one to test
        categories.clear();
        categories.add(EXAMPLE_CATEGORY_URL);

        List<Product> allProducts = new ArrayList<>();

        //TODO: here will be multithreading
        for (String category : categories) {
            for (int i = 0; i < 1000; i++) {
                final String pageOfProducts = String.format(category, i + 1);
                System.out.println(pageOfProducts);
                final List<Element> productsDocs = selectProduct(pageOfProducts);
                if (productsDocs.isEmpty()) {
                    break;
                }
                List<Product> products = productsDocs.stream()
                        .map(element -> {
                            Product product = new Product();
                            String url = getLink(element.select(PRODUCT_URL_SELECTOR).first());
                            String name = getText(element.select(TITLE_SELECTOR).first());
                            String img = element.select(IMG_SRC_SELECTOR).first().toString();
                            String price = getText(element.select(PRICE_SELECTOR).first());
                            product.setUrl(url);
                            product.setName(name);
                            product.setImage(img);
                            product.setPrice(price);
                            return product;
                        }).collect(Collectors.toList());


                System.out.println("products.size()" + products.size());
                System.out.println("i = " + i);
                allProducts.addAll(products);
            }
        }

        return allProducts;
    }


    private List<Element> selectProduct(String pageOfProducts) {

        final Document document = getDocument(pageOfProducts);

        if ("".equals(document.select(SHOW_MORE_BUTTON_SELECTOR).toString())) {
            return Collections.emptyList();
        }
        return document.select(PRODUCT_SELECTOR)
                .stream()
                .collect(Collectors.toList());
    }


}
