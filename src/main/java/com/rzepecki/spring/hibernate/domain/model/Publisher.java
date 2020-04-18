/**
 * 1. Utwórz encje o nazwie `Publisher`.
 * 2. Ustal nazwę tabeli bazy danych dla tej encji na `publishers`.
 * 3. Encja ma zawierać następujące pola:
 * - id
 * - name
 *
 * 4. Utwórz klasę `PublisherDao` - służącą do operacji na tej encji.
 * 5. Utwórz kontroler, realizujący operacje CRUD (create, read, update, delete).
 */
package com.rzepecki.spring.hibernate.domain.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(id, publisher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
