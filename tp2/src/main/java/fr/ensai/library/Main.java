package fr.ensai.library;

public class Main {

    public static void main(String[] args) {

        Author tolkien = new Author("J.R.R. Tolkien", 81, "UK");

        Book fellowshipOfTheRing = new Book(
                "The Fellowship of the Ring",
                1954,
                423,
                "978-0-618-26025-6",
                tolkien);


        Magazine magazine1 = new Magazine(
                "National Geographic", 
                2025, 
                120, 
                "1234-5678", 
                101);
                
        Magazine magazine2 = new Magazine(
                "Time", 
                2025, 
                95, 
                "9876-5432", 
                102);

        System.out.println(fellowshipOfTheRing.toString());

        Library library = new Library("Ma Bibliothèque");
        library.addItem(magazine1);
        library.addItem(magazine2);
        library.loadBooksFromCSV("books.csv");
        library.displayItems();
    }
    }



