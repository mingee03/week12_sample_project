BookController.list()
//쿼리 파라미터를 객체로 받아서 service.findAll(pageable)을 호출하고, 조회된 Page<BookResponse>를 200로 반환한다.
public ResponseEntity<Page<BookResponse>> list(Pageable pageable) {
    return ResponseEntity.ok(service.findAll(pageable));
}

BookController.create()
// @RequestBody 로 요청을 DTO보 변환해 받고, @Valid로 입력값을 검증한다.
public ResponseEntity<BookResponse> create(@Valid @RequestBody BookRequest input) {
    //도서를 저장하고, 결과를 DTO로 받아 Response Entity를 통해 201과 Response Body에 담아 반환한다
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(input));
}

BookService.findAll()
public Page<BookResponse> findAll(Pageable pageable) {
    // Page<Book>을  조회, .map(BookResponse::from)를 통해 각 엔티티를 DTO로 변환하여 Page<BookResponse>로 반환한다.
    return repository.findAll(pageable).map(BookResponse::from);
}

BookService.findById()
public Book findById(Long id) {
    // 데이터 베이스에서 해당 ID의 도서를 optional 형태로 조회하고, 데이터가 존대하지 않으면 .orElseThrow()로 404처리
    return repository.findById(id).orElseThrow();
}
