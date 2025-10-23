import java.util.*;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean issued;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.issued = false;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isIssued() { return issued; }

    public void issue() { issued = true; }
    public void returnBack() { issued = false; }
}

class User {
    private String name;
    private String id;
    private List<Book> books;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
        this.books = new ArrayList<>();
    }

    public void issueBook(Book b) {
        books.add(b);
        b.issue();
    }

    public void returnBook(Book b) {
        books.remove(b);
        b.returnBack();
    }

    public void showBooks() {
        System.out.println("Books with " + name + ":");
        for (Book b : books) {
            System.out.println(b.getTitle() + " by " + b.getAuthor());
        }
    }
}

class Library {
    private List<Book> list;

    public Library() {
        list = new ArrayList<>();
    }

    public void add(Book b) {
        list.add(b);
    }

    public Book search(String title) {
        for (Book b : list) {
            if (b.getTitle().equalsIgnoreCase(title)) return b;
        }
        return null;
    }

    public void showAvailable() {
        System.out.println("Available Books:");
        for (Book b : list) {
            if (!b.isIssued()) System.out.println(b.getTitle() + " by " + b.getAuthor());
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.add(new Book("1984", "George Orwell", "B1"));
        lib.add(new Book("The Alchemist", "Paulo Coelho", "B2"));
        User u = new User("Ravi", "U101");
        lib.showAvailable();
        Book b = lib.search("1984");
        if (b != null && !b.isIssued()) {
            u.issueBook(b);
            System.out.println("Book issued");
        }
        lib.showAvailable();
        u.showBooks();
        u.returnBook(b);
        System.out.println("Book returned");
        lib.showAvailable();
    }
}
