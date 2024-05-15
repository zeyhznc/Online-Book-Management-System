package bookmanagement.security.book;

import java.util.Set;

public class BookUnSupportedFieldPatchException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<String> fields;

    public BookUnSupportedFieldPatchException(Set<String> fields) {
        super("Günleme işlemi için desteklenmeyen alanlar : " + fields);
        this.fields = fields;
    }

    public Set<String> getFields() {
        return fields;
    }

}
