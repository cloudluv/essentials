package com.example.essentials;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.List;

@SpringBootApplication
public class EssentialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EssentialsApplication.class, args);
    }

}

@Entity
@Table(name = "cats")
@Getter
@NoArgsConstructor
@ToString
class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}

@Repository
interface CatRepository extends JpaRepository<Cat, Long> {
    List<Cat> findCatByName(String name);
}

@RestController
@RequestMapping("/api")
class CatController {
    private final CatRepository catRepository;

    CatController(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @GetMapping("/health")
    public String health() {
        return "Hello world!";
    }

    @GetMapping("/cats")
    public List<Cat> getAll() {
        return catRepository.findAll();
    }

    @GetMapping("/cats/{name}")
    public List<Cat> getByName(@PathVariable String name) {
        return catRepository.findCatByName(name);
    }

    @PostMapping("/cats")
    public Cat saveCat(@RequestBody Cat cat) {
        return catRepository.save(cat);
    }
}