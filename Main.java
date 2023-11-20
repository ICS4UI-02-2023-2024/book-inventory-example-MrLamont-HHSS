import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {
        // making the collection
        Collection books = new Collection("books.txt");

        // find a book by ISBN
        Book b = books.search(9780439554930l);
        System.out.println(b);

        books.removeBook(b);
        books.updateFile();


        ArrayList<Book> harryPotter = books.search("J.K. Rowling");
        for(Book b2: harryPotter){
            System.out.println(b2);
        }


    }
}