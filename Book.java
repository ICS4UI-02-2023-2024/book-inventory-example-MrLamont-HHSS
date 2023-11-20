public class Book {
    private String title;
    private String author;
    private double rating;
    private long isbn;

    public Book(long isbn, String author, String title, double rating){
        this.isbn = isbn;
        this.author = author;
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public long getIsbn() {
        return isbn;
    }

    public String toString(){
        return this.isbn + "," + this.author + "," + this.title + "," + this.rating;
    }
    
}
