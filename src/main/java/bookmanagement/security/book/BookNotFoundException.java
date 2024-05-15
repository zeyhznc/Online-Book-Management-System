package bookmanagement.security.book;

public class BookNotFoundException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(Long id) {
        super(id + " id'li kitap bulunamadı");
    }

}
