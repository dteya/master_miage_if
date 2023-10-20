package master_miage_if.project.controller;

import lombok.AllArgsConstructor;
import master_miage_if.project.entity.BookEntity;
import master_miage_if.project.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{name}")
    public BookEntity getBookByName(@PathVariable String name) throws Exception {
        return bookService.findByName(name);
    }

    @PostMapping("/add")
    public BookEntity saveBook(@RequestBody BookEntity book) {
        BookEntity savedBook = bookService.createBook(book);
        return savedBook;
    }
}
