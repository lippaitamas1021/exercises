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
        return modelMapper.map(findBook(id), BookDTO.class);}


    public Book findBook(int id) {
        return bookRepository.findById(id).orElseThrow(()-> new NotFoundException(id, "Book"));}


    public BookDTO saveBook(CreateBookCommand command) {
        Book book = bookRepository.save(new Book(command.getAuthor(), command.getTitle()));
        return modelMapper.map(book, BookDTO.class);}


    @Transactional
    public BookDTO updateBook(int id, UpdateBookCommand command) {
        Book book = findBook(id);
        book.setAuthor(command.getAuthor());
        book.setTitle(command.getTitle());
        return modelMapper.map(book, BookDTO.class);}


    public void deleteBook(int id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new NotFoundException(id, "Book");}}


    public void deleteAllTheBooks() {
        bookRepository.deleteAll();}}
