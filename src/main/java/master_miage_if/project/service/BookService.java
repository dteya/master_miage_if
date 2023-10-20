package master_miage_if.project.service;

import master_miage_if.project.entity.BookEntity;

public interface BookService {

    public BookEntity findByName(String name) throws Exception;

    public BookEntity createBook(BookEntity book);

}
