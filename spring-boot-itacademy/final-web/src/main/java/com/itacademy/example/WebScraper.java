package com.itacademy.example;

import java.io.IOException;

import com.itacademy.example.entity.Book;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@Component
public class WebScraper {

    private final WebClient webclient = WebClientProvider.getDefaultWebClient();
    
    private static final String SEARCH_URL = "https://www.bookfinder.com/search/?author=&title=&lang=en&isbn=%s&new_used=*&destination=by&currency=USD&mode=basic&st=sr&ac=qr";
    private static final String IMAGE_URL = "https://pictures.abebooks.com/isbn/%s-us-300.jpg";

    public Book getBookDetailsFromWeb(String isbn) {
        Book book = new Book();
        book.setIsbn(isbn);
        try {
            String url = String.format(SEARCH_URL, isbn);
            HtmlPage bookPage = webclient.getPage(url);
            HtmlElement name = (HtmlElement) bookPage.getByXPath("//span[@id='describe-isbn-title']").get(0);
            HtmlElement author = (HtmlElement) bookPage.getByXPath("//span[@itemprop='author']").get(0);
            HtmlElement description = null;
            if (!bookPage.getByXPath("//div[@id='bookSummary']").isEmpty()) {
                description = (HtmlElement) bookPage.getByXPath("//div[@id='bookSummary']").get(0);
                book.setDescription(description.getTextContent());
            } else {
                book.setDescription(StringUtils.EMPTY);
            }
            book.setPicture(String.format(IMAGE_URL, isbn));
            book.setName(name.getTextContent());
            book.setAuthor(author.getTextContent());
            return book;
        } catch (FailingHttpStatusCodeException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
