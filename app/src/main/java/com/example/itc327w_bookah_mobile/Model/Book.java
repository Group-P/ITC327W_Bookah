package com.example.itc327w_bookah_mobile.Model;

public class Book {

    public String title, author, description, edition, ISBN, pictureURL, publisher;

    public Book() {
    }

    public Book(String title, String author, String description, String edition, String isbn, String pictureURL, String publisher) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.edition = edition;
        this.ISBN = isbn;
        this.pictureURL = pictureURL;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
