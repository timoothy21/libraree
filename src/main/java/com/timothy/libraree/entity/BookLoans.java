package com.timothy.libraree.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookLoans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book")
    private Book book;
    private LocalDateTime borrowedTime;
    private LocalDateTime actualReturnTime;
    private LocalDateTime expectedReturnTime;
}
