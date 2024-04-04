package com.example.demo.book;

import com.example.demo.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "student_id",referencedColumnName = "id"
            ,foreignKey = @ForeignKey(name = "student_book_fk"))
    private Student student;

    @Column(
            name = "book_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String bookName;

    @Column(
            name = "created_at",
            nullable = false
    )
    private LocalDateTime created_at;

    public Book(String bookName, LocalDateTime created_at) {
        this.bookName = bookName;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", student_id=" + student +
                ", bookName='" + bookName + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
