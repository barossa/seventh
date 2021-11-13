package by.bsuir.commerce.seventh.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User author;

    private int rate;

    private String body;

    private LocalDateTime publicationDate;

}
