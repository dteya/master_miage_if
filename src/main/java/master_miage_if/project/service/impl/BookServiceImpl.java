package master_miage_if.project.service.impl;

import lombok.AllArgsConstructor;
import master_miage_if.project.entity.BookEntity;
import master_miage_if.project.repository.BookRepository;
import master_miage_if.project.service.BookService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookEntity findByName(String name) throws Exception {

        BookEntity book = bookRepository.findByName(name);
        if (book != null)
            return book;
        else
            throw new Exception("Book by that name not found");

    }

    @Override
    public BookEntity createBook(BookEntity book) {
        BookEntity savedBook = bookRepository.save(book);
        return savedBook;
    }
}
