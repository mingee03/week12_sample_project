package edu.handong.week12.book;

import edu.handong.week12.book.dto.BookRequest;
import edu.handong.week12.book.dto.BookResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Book API", description = "도서 관리 API")
@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @Operation(summary = "도서 목록 조회")
    @GetMapping
    //쿼리 파라미터를 객체로 받아서 service.findAll(pageable)을 호출하고, 조회된 Page<BookResponse>를 200로 반환한다.
    public ResponseEntity<Page<BookResponse>> list(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(BookResponse.from(service.findById(id)));
    }

    @Operation(summary = "도서 등록")
    @PostMapping
    // @RequestBody 로 요청을 DTO보 변환해 받고, @Valid로 입력값을 검증한다.
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookRequest input) {
        //도서를 저장하고, 결과를 DTO로 받아 Response Entity를 통해 201과 Response Body에 담아 반환한다
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(input));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable Long id, @Valid @RequestBody BookRequest input) {
        return ResponseEntity.ok(service.update(id, input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}