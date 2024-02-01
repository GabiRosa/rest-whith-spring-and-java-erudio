package br.com.erudio.data.vo.v1;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class BookVO extends RepresentationModel<BookVO> implements Serializable {

    private Long id;

    private String author;

    private LocalDateTime launchDate;

    private double price;

    private String title;

    public BookVO() {
    }

    public BookVO(Long id, String author, LocalDateTime launchDate, double price, String title) {
        this.id = id;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookVO bookVO = (BookVO) o;
        return Double.compare(price, bookVO.price) == 0 && Objects.equals(id, bookVO.id) && Objects.equals(author, bookVO.author) && Objects.equals(launchDate, bookVO.launchDate) && Objects.equals(title, bookVO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, launchDate, price, title);
    }
}
