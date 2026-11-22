package edu.handong.week12.book;

import edu.handong.week12.book.dto.BookRequest;
import edu.handong.week12.book.dto.BookResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> list() {
        return service.findAll();
    } // TODO: Pageable

    @GetMapping("/{id}")
    public Book get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Book create(@RequestBody Book input) {
        return service.create(input);
    } // TODO: @Valid, ResponseEntity 201

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book input) {
        return service.update(id, input);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    } // TODO: 204
    @PostMapping("/books")
    public BookResponse create(
            @RequestBody BookRequest request) {
        Book saved = bookService
    }
}
