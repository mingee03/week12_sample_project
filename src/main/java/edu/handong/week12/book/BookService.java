package edu.handong.week12.book;

import edu.handong.week12.book.dto.BookRequest;
import edu.handong.week12.book.dto.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Page<BookResponse> findAll(Pageable pageable) {
        // Page<Book>을  조회, .map(BookResponse::from)를 통해 각 엔티티를 DTO로 변환하여 Page<BookResponse>로 반환한다.
        return repository.findAll(pageable).map(BookResponse::from);
    }

    public Book findById(Long id) {
        // 데이터 베이스에서 해당 ID의 도서를 optional 형태로 조회하고, 데이터가 존재하지 않으면 .orElseThrow()로 404처리
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    public BookResponse create(BookRequest input) {
        Book book = new Book(input.title(), input.author(), input.price());
        return BookResponse.from(repository.save(book));
    }

    public BookResponse update(Long id, BookRequest input) {
        Book book = findById(id);
        book.update(input.title(), input.author(), input.price());
        return BookResponse.from(repository.save(book));
    }

    public void delete(Long id) {
        Book book = findById(id);
        repository.delete(book);
    }
}