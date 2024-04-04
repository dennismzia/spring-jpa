package com.example.demo;


import com.example.demo.book.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@Entity(name = "student")
@Table(
        name = "student",
uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames ="email")
}
)
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
           generator = "student_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(
            name = "age",
            nullable = false
    )
    private Integer age;

    @OneToOne(mappedBy = "student",cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    private StudentIdCard studentIdCard;

    @OneToMany(mappedBy = "student",cascade = {CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    @OneToMany(
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE}
    )
    private List<Enrolment> enrolments = new ArrayList<>();

    public Student( String firstName, String lastName, String email,Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;

    }

    public void addBook(Book book){
        if (!this.books.contains(book)){
            this.books.add(book);
//            below "this" is bidirectional book is setting student and student is setting book
            book.setStudent(this);
        }
    }

    public void removeBook(Book book){
        if (this.books.contains(book)){
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    public void addEnrolment(Enrolment enrolment){
        if(!this.enrolments.contains(enrolment)){
            enrolments.add(enrolment);
        }
    }

    public void removeEnrolment(Enrolment enrolment){
        this.enrolments.remove(enrolment);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
