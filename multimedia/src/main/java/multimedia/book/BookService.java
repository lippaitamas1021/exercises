package multimedia.book;

import lombok.AllArgsConstructor;
import multimedia.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    
    private BookRepository bookRepository;

    private ModelMapper modelMapper;


    public List<BookDTO> listBooks() {
        Type targetListType = new TypeToken<List<BookDTO>>(){}.getType();
        return modelMapper.map(bookRepository.findAll(), targetListType);}


    public BookDTO findBookById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Book"));
        return modelMapper.map(book, BookDTO.class);}


    public BookDTO saveBook(CreateBookCommand command) {
        Book book = new Book(command.getAuthor(), command.getTitle(), command.getDate());
        bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);}


    @Transactional
    public BookDTO updateBook(int id, UpdateBookCommand command) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Book"));
        book.setId(command.getId());
        book.setAuthor(command.getAuthor());
        book.setTitle(command.getTitle());
        book.setDate(command.getDate());
        bookRepository.save(book);
        return modelMapper.map(book, BookDTO.class);}


    public void deleteBook(int id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new NotFoundException(id, "Book");}}


    public void deleteAllTheBooks() {
        bookRepository.deleteAll();}}
