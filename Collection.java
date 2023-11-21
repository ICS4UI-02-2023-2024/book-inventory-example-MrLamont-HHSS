import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Collection {
    private ArrayList<Book> books;
    private String bookFile;

    public Collection(String bookFile){
        // initialize the book arraylist
        this.books = new ArrayList<>();
        // store the bookfile name to update it later
        this.bookFile = bookFile;
        
        // reading in the text file
        Scanner input = null;
        // open the file with the scanner
        try{
            input = new Scanner(new File(bookFile));
        }catch(Exception e){
            // if errors, print them out
            e.printStackTrace();
        }
        // get rid of the header in the file
        input.nextLine();
        // loop until we run out of books
        while(input.hasNext()){
            // scan an entire book line
            String bookLine = input.nextLine();
            // break up the info
            String[] bookInfo = bookLine.split(",");
            long isbn = Long.parseLong(bookInfo[0]);
            String author = bookInfo[1];
            String title = bookInfo[2];
            double rating = Double.parseDouble(bookInfo[3]);
            // make the book
            Book newBook = new Book(isbn, author, title, rating);
            // add book to arraylist
            this.books.add(newBook);
        }
        // close off the Scanner
        input.close();
    }

    public void addBook(long isbn, String author, String title, double rating){
        // make the book
        Book newBook = new Book(isbn, author, title, rating);
        // add book to arraylist
        this.books.add(newBook);
    }

    public void removeBook(Book toRemove){
        // ask the list to remove that book
        this.books.remove(toRemove);
    }

    public Book search(long isbn){
        // go through all books
        for(Book b: this.books){
            // do the isbn's match?
            if(b.getIsbn() == isbn){
                return b;
            }
        }
        // no book found
        return null;
    }

    public Book searchByTitle(String title){
        // go through all books
        for(Book b: this.books){
            // do the isbn's match?
            if(b.getTitle().equals(title)){
                return b;
            }
        }
        // no book found
        return null;
    }

    public ArrayList<Book> search(String author){
        // create the initial blank list
        ArrayList<Book> bookList = new ArrayList<>();
        // go through each book in our current list
        for(Book b: this.books){
            // do the authors match
            if(b.getAuthor().equals(author)){
                // add book to the bookList
                bookList.add(b);
            }
        }
        // check to see if we have books
        if(bookList.isEmpty()){
            return null;
        }else{
            return bookList;
        }
    }

    public void updateFile(){
        // create the print writer to write the file
        PrintWriter output = null;
        try{
            output = new PrintWriter(new File(this.bookFile));
        }catch(Exception e){
            e.printStackTrace();
        }
        // output the header
        output.println("isbn13,authors,title,average_rating");
        // go through each book
        for(Book b: this.books){
            // write the book to the file
            output.println(b);
        }
        // close off the write to finalize the file
        output.close();
    }
}
