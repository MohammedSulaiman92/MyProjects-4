import java.util.Scanner;
class Book 
{
    private String name;
    private String author;
    private boolean available;
    public Book(String name, String author)
    {
        this.name = name;
        this.author = author;
        this.available = true;
	}
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
class Patron 
{
    private String name;
    public Patron(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

class Transaction {
    private Patron patron;
    private Book book;
    private String date;

    public Transaction(Patron patron, Book book, String date) {
        this.patron = patron;
        this.book = book;
        this.date = date;
    }

    public Patron getPatron() {
        return patron;
    }

    public Book getBook() {
        return book;
    }

    public String getDate() {
        return date;
    }
}

class Library
{
    private Book[] books;
    private Transaction[] transactions;
    private int bookCount;
    private int transactionCount;
    public Library(int maxBooks, int maxTransactions)
    {
        this.books = new Book[maxBooks];
        this.transactions = new Transaction[maxTransactions];
        this.bookCount = 0;
        this.transactionCount = 0;
    }
    public void addBook(Book book)
    {
        if (bookCount < books.length)
        {
            books[bookCount++] = book;
        }
        else {
            System.out.println("Library is full. Cannot add more books.");
        }
    }
    public void displayBooks()
    {
        System.out.println("Available Books:");
        for (Book book : books)
        {
            if (book != null && book.isAvailable())
            {
                System.out.println(book.getName() + " by " + book.getAuthor());
            }
        }
    }
    public void borrowBook(Patron patron, String bookName, String bookAuthor, String date)
    {
        for (Book book : books)
        {
            if (book != null && book.getName().equalsIgnoreCase(bookName) && book.getAuthor().equalsIgnoreCase(bookAuthor) && book.isAvailable())
            {
                book.setAvailable(false);
                transactions[transactionCount++] = new Transaction(patron, book, date);
                System.out.println("Book borrowed successfully!");
                return;
            }
        }
        System.out.println("Book not available for borrowing.");
    }
    public void returnBook(Patron patron, String bookName, String bookAuthor, String date)
    {
        for (Transaction transaction : transactions)
        {
            if (transaction != null && transaction.getPatron().equals(patron) && transaction.getBook().getName().equalsIgnoreCase(bookName) && transaction.getBook().getAuthor().equalsIgnoreCase(bookAuthor) && transaction.getDate().equalsIgnoreCase(date))
            {
                transaction.getBook().setAvailable(true);
                System.out.println("Book returned successfully!");
                return;
            }
            System.out.println("Invalid return.");
        }
     }
     
}
public class LibraryManagementSystem 
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Library Management System!");
        System.out.print("Enter the maximum number of books the library can hold: ");
        int maxBooks = scanner.nextInt();
        System.out.print("Enter the maximum number of transactions the library can handle: ");
        int maxTransactions = scanner.nextInt();
        Library library = new Library(maxBooks, maxTransactions);
        library.addBook(new Book("Harry Potter", "J. K. Rowling"));
        library.addBook(new Book("The Golden Compass", "Philip Pullman"));
        library.addBook(new Book("The Magicians", "Lev Grossman"));
        library.addBook(new Book("The Lightning Thief", "Lev Grossman"));
        library.addBook(new Book("The Magicians", "Rick Riordan"));
        while (true) 
        {
            System.out.println("\nMenu:");
            System.out.println("1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice)
            {
                case 1:
                library.displayBooks();
                break;
                case 2:
                System.out.print("Enter your name: ");
                String patronName = scanner.next();
                Patron patron = new Patron(patronName);
                System.out.println("Enter the book name you want to borrow:");
                scanner.nextLine(); // Consume the newline character
                String borrowBookName = scanner.nextLine();
                System.out.print("Enter the author of the book: ");
                String borrowBookAuthor = scanner.nextLine();
                System.out.print("Enter the date you will return the book (dd-MMM-yyyy): ");
                String borrowDate = scanner.next();
                library.borrowBook(patron, borrowBookName, borrowBookAuthor, borrowDate);
                break;

                case 3:
                System.out.print("Enter your name: ");
                String returnPatronName = scanner.next();
                Patron returnPatron = new Patron(returnPatronName);
                System.out.println("Enter the book name you want to return:");
                scanner.nextLine(); 
                String returnBookName = scanner.nextLine();
                System.out.print("Enter the author of the book: ");
                String returnBookAuthor = scanner.nextLine();
                System.out.print("Enter the date you borrowed the book (dd-MMM-yyyy): ");
                String returnDate = scanner.next();
                library.returnBook(returnPatron, returnBookName, returnBookAuthor, returnDate);
                break;

                case 4:
                System.out.println("Exiting Library Management System. Goodbye!");
                scanner.close();
                System.exit(0);
                break;

                default:
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
