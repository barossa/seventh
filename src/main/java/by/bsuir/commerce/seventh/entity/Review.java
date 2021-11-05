package by.bsuir.commerce.seventh.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User author;

    @ManyToOne
    private Good good;

    private int rate;

    private String body;

    private LocalDateTime publicationDate;

}
