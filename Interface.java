import java.util.ArrayList;
import java.util.Scanner;

public class Interface{
    private Collection books;
    private Book currentBook;
    private int screen;

    private final int MAIN_SCREEN = 0;
    private final int ADD_SCREEN = 1;
    private final int BOOK_SCREEN = 2;
    private final int REMOVE_SCREEN = 3;
    private final int SEARCH_SCREEN = 4;

    private Scanner input;

    public Interface(){
        // making the collection
        books = new Collection("books.txt");
        // no current book selected
        currentBook = null;
        // start on the main screen
        screen = 0;
        // scanner for any input needed
        input = new Scanner(System.in);
    }


    public void run(){
        // main program loop based on the screen selected
        while(true){
            // run the proper section based in the screen
            if(screen == MAIN_SCREEN){
                mainScreen();
            }else if(screen == ADD_SCREEN){
                addBook();
            }else if(screen == BOOK_SCREEN){
                bookScreen();
            }else if(screen == SEARCH_SCREEN){
                search();
            }else if(screen == REMOVE_SCREEN){
                remove();
            }

        }
    }

    private void mainScreen(){
        // display the main menu
        System.out.println("Welcome to your Book Management System.");
        System.out.println("What would you like to do?");
        System.out.println("1 - search for a book");
        System.out.println("2 - add a new book");
        System.out.println("3 - Quit");
        
        // get the user's choice
        int selection = input.nextInt();
        // clear the enter key
        input.nextLine();

        // change the screen based on the selection
        if(selection == 1){
            screen = SEARCH_SCREEN;
        }else if(selection == 2){
            screen = ADD_SCREEN;
        }else if(selection == 3){
            input.close();
            // update file before quitting
            this.books.updateFile();
            System.exit(0);
        }else{
            System.out.println("Invalid choice");
        }
    }

    private void bookScreen(){
        // display the options
        System.out.println("Current Selected Book: " + currentBook.getTitle() + " by " + currentBook.getAuthor());
        System.out.println("What would you like to do?");
        System.out.println("1 - remove book");
        System.out.println("2 - go back to main menu");
        
        // get the user's choice
        int selection = input.nextInt();
        // clear the enter key
        input.nextLine();

        // change the screen based on the selection
        if(selection == 1){
            screen = REMOVE_SCREEN;
        }else if(selection == 2){
            System.out.println("Returning to main screen");
            screen = MAIN_SCREEN;
        }else{
            System.out.println("Invalid choice");
        }
    }

    private void search(){
        // display the options
        System.out.println("How would you like to search for your book?");
        System.out.println("1 - Search by ISBN");
        System.out.println("2 - Search by Title");
        System.out.println("3 - Search by Author");
        System.out.println("4 - Return to main menu");

        // get the user's choice
        int selection = input.nextInt();
        // clear the enter key
        input.nextLine();

        // change the screen based on the selection
        if(selection == 1){
            searchByISBN();
        }else if(selection == 2){
            searchByTitle();
        }else if(selection == 3){
            seachByAuthor();
        }else if(selection == 4){
            // return to main menu if invalid
            System.out.println("Returning to main menu");
            screen = MAIN_SCREEN;
        }else{
            System.out.println("Invalid selection");
        }
    }

    private void searchByISBN(){
        System.out.println("What is the ISBN you are looking for?");
        // get the user's input
        long isbn = input.nextLong();
        // clear the enter key
        input.nextLine();

        // try to find the book
        currentBook = this.books.search(isbn);
        // change to the book screen if we found a book
        if(currentBook != null){
            screen = BOOK_SCREEN;
        }else{
            // if no book was found
            System.out.println("No book by that ISBN was found");
        }
    }

    private void seachByAuthor(){
        System.out.println("What book author are looking for?");
        // get the user's input
        String name = input.nextLine();
        
        // try to find the book
        ArrayList<Book> foundBooks = this.books.search(name);
        // change to the book screen if we found a book
        if(foundBooks != null){
            bookListSelection(foundBooks, name);
        }else{
            // if no book was found
            System.out.println("No book by " + name + " were found");
        }
    }

    public void bookListSelection(ArrayList<Book> list, String author){
        System.out.println("Here are all the books by  " + author + ":");
        for(int i = 0; i < list.size(); i++){
            // get the book from the list
            Book b = list.get(i);
            // display it with a number beside it for selection
            System.out.println((i+1) + " - " + b.getTitle());
        }
        System.out.println((list.size() + 1) + " - Return to main menu");
        //get the users selection
        int selected = input.nextInt();
        // clear the enter key
        input.nextLine();

        // set the selected book and go to book screen
        if(selected <= list.size()){
            currentBook = list.get(selected - 1);
            screen = BOOK_SCREEN;
        }else{
            // return to main menu if invalid
            System.out.println("Returning to main menu");
            screen = MAIN_SCREEN;
        }

    }


    public void searchByTitle(){
        System.out.println("What book title are looking for?");
        // get the user's input
        String title = input.nextLine();

        // try to find the book
        currentBook = this.books.searchByTitle(title);
        // change to the book screen if we found a book
        if(currentBook != null){
            screen = BOOK_SCREEN;
        }else{
            // if no book was found
            System.out.println("No book by that title was found");
        }
    }

    private void remove(){
        if(currentBook != null){
            // remove the selected book
            this.books.removeBook(currentBook);
            // let them know what was removed
            System.out.println(this.currentBook.getTitle() + " removed from the collection");
            // update the file
            this.books.updateFile();
            // deselect the book
            currentBook = null;
            // go back to the main screen
            screen = MAIN_SCREEN;
        }
        
    }

    private void addBook(){
        System.out.println("Adding a new book to the collection");
        // get the info for the new book
        System.out.println("What is the ISBN?");
        long isbn = input.nextLong();
        input.nextLine();

        System.out.println("Who is the author?");
        String author = input.nextLine();

        System.out.println("What is the title?");
        String title = input.nextLine();

        System.out.println("What is your rating?");
        double rating = input.nextDouble();
        input.nextLine();

        // add the book to the system and update the file
        this.books.addBook(isbn, author, title, rating);
        this.books.updateFile();

        // show the stats
        System.out.println("Added " + title + " by " + author + " to the collection");
    }
}