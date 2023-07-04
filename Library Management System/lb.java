import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayBooks() {
        System.out.println("Library Books:");
        System.out.println("ID\tTitle\t\tAuthor\t\tAvailability");
        for (Book book : books) {
            System.out.println(
                book.getId() + "\t" +
                book.getTitle() + "\t\t" +
                book.getAuthor() + "\t\t" +
                (book.isAvailable() ? "Available" : "Not Available")
            );
        }
        System.out.println();
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}

 class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        // Adding some sample books
        library.addBook(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        library.addBook(new Book(2, "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book(3, "1984", "George Orwell"));

        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Library Management System");
            System.out.println("1. Display Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter the book ID to borrow: ");
                    int borrowId = scanner.nextInt();
                    Book borrowBook = library.findBookById(borrowId);
                    if (borrowBook != null && borrowBook.isAvailable()) {
                        borrowBook.setAvailable(false);
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Book not available for borrowing.");
                    }
                    break;
                case 3:
                    System.out.print("Enter the book ID to return: ");
                    int returnId = scanner.nextInt();
                    Book returnBook = library.findBookById(returnId);
                    if (returnBook != null && !returnBook.isAvailable()) {
                        returnBook.setAvailable(true);
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Invalid book ID or book already returned.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 4);

        scanner.close();
    }
}
