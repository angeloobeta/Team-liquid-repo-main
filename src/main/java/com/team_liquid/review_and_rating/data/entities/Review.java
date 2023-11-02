package com.team_liquid.review_and_rating.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review_table")
public class Review {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @NonNull
    private String productId;

    @NonNull
    private String customerName;
    @NonNull
    private Long isHelpful;
    private Integer rating;

    @NonNull
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "review_id", referencedColumnName = "id")
    private Reply reply;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public void addReviewHelpful(){
        isHelpful++;
    }



}
