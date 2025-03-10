package fr.ensai.library;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private String name;
    private List<Item> items;
    private List<Loan> activeLoans;
    private List<Loan> completedLoans;
    
    public Library(String name) {
        this.name = name;
        this.items = new ArrayList<>();
        this.activeLoans = new ArrayList<>();
        this.completedLoans = new ArrayList<>();
    }

    public void displayActiveLoans() {
        if (activeLoans.isEmpty()) {
            System.out.println("Aucun prêt actif.");
        } else {
            for (Loan loan : activeLoans) {
                System.out.println(loan.toString());
            }
        }
    }

    public Loan findActiveLoanForItem(Item item) {
        for (Loan loan : activeLoans) {
            if (loan.getItem().equals(item)) {
                return loan;
            }
        }
        return null;
    }

    public ArrayList<Book> getBooksByAuthor(Author author) {
        ArrayList<Book> booksByAuthor = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Book) {
                Book book = (Book) item;
                if (book.getAuthor().equals(author)) {
                    booksByAuthor.add(book);
                }
            }
        }
        return booksByAuthor;
    }

    public boolean loanItem(Item item, Student student) {
        if (findActiveLoanForItem(item) != null) {
            System.out.println("L'item est déjà prêté.");
            return false;
        }
        Loan loan = new Loan(item, student, LocalDate.now());
        activeLoans.add(loan);
        System.out.println("Item prêté : " + item.getTitle() + " à " + student.getName());
        return true;
    }

    public boolean renderItem(Item item) {
        Loan loan = findActiveLoanForItem(item);
        if (loan == null) {
            System.out.println("L'item n'est pas actuellement prêté.");
            return false;
        }

        // Marquer le prêt comme terminé
        loan.completeLoan(LocalDate.now());
        activeLoans.remove(loan);
        completedLoans.add(loan);
        System.out.println("Item rendu : " + item.getTitle());
        return true;
    }

    public void displayCompletedLoans() {
        if (completedLoans.isEmpty()) {
            System.out.println("Aucun prêt complété.");
        } else {
            for (Loan loan : completedLoans) {
                System.out.println(loan.toString());
            }
        }
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void displayItems(){
        if (items.isEmpty()) {
            System.out.println("La bibliothèque est vide.");
        }
        else{
            System.out.println("Livres dans la bibliothèque \"" + name + "\" :");
            for (Item item : items) {
                System.out.println(item.toString()); 
            }
        }
    }

    /**
     * Loads books from a CSV file and adds them to the library.
     * 
     * @param filePath The path to the CSV file containing book data.
     * @throws IOException If there is an error reading the file, an
     *                     {@link IOException} will be thrown.
     */
    public void loadBooksFromCSV(String filePath) {

        URL url = getClass().getClassLoader().getResource(filePath);

        try (BufferedReader br = new BufferedReader(new FileReader(url.getFile()))) {
            Map<String, Author> authors = new HashMap<>();
            String line;
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 5) {
                    String isbn = data[0].trim();
                    String title = data[1].trim();
                    String authorName = data[2].trim();
                    int year = Integer.parseInt(data[3].trim());
                    int pageCount = Integer.parseInt(data[4].trim());

                    // Check if author already exists in the map
                    Author author = authors.get(authorName);
                    if (author == null) {
                        author = new Author(authorName);
                        authors.put(authorName, author);
                        System.out.println(author.toString());
                    }
                    Book book = new Book(title, year, pageCount, isbn, author);

                    this.addItem(book);
                }
            }
        } catch (

        IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
