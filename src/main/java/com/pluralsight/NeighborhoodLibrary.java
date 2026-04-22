package com.pluralsight;

import java.util.Scanner;

public class NeighborhoodLibrary {

    private static Book[] books = new Book[20];
    private static int numOfBooks = 10;

    public static void main(String[] args) {
        books[0] = new Book(0, "32123", "To Kill a Mocking Bird", false, "");
        books[1] = new Book(1, "32327", "48 Laws of Power", false, "");
        books[2] = new Book(2, "52129", "Junie B Jones", false, "");
        books[3] = new Book(3, "82122", "Twilight", false, "");
        books[4] = new Book(4, "32913", "The Hunger games", false, "");
        books[5] = new Book(5, "22552", "Harry Potter", false, "");
        books[6] = new Book(6, "23463", "Four Agreements", false, "");
        books[7] = new Book(7, "67573", "The Tipping Point", false, "");
        books[8] = new Book(8, "57343", "Between The World & Me", false, "");
        books[9] = new Book(9, "28357", "Assata", false, "");

        boolean quit = false;
        Scanner userInput = new Scanner(System.in);

        while (!quit) {
            System.out.println("1: Show all Books");
            System.out.println("2: Show Checked Out Books");
            System.out.println("3: Quit");
            System.out.print("Select an option: ");


            int userSelection = userInput.nextInt();
            userInput.nextLine();

            switch (userSelection) {
                case 1:
                    ListAllAvailableBooks(userInput);
                    break;
                case 2:
                    ListCheckedOutBooks(userInput);
                    break;
                case 3:
                    System.out.println("Thank you for coming to our Library! Have a great day");
                    quit = true;
                    break;
            }


        }
    }

    public static void ListAllAvailableBooks(Scanner userInput) {

        System.out.println("Current Books in Stock: ");
        boolean isFound = false;

        for (int i = 0; i < numOfBooks; i++) {
            if (!books[i].isCheckedOut()) {
                System.out.println(books[i]);
            }
        }

        System.out.print("Please select the book you'd like to checkout by ID (ex: 1) or select (-1) to exit:  ");
        int bookChoice = userInput.nextInt();
        userInput.nextLine();

        for (int i = 0; i < numOfBooks; i++) {
            if (bookChoice == -1) break;
            if (!books[i].isCheckedOut() && bookChoice == books[i].getId()) {
                System.out.print("Please enter your name:  ");
                String name = userInput.nextLine();
                System.out.println("Thank you, " + name + "! You checked out " + books[i].getTitle() + ".");
                books[i].checkOut(name);
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println("Entry unavailable try again. ");
        }
    }
    public static void ListCheckedOutBooks(Scanner userInput) {

        boolean found = false;
        Book selectedBook = new Book();

        for (int i = 0; i < numOfBooks; i++) {
            if (books[i].isCheckedOut()) {
                System.out.println(books[i]);
                found = true;
            }
        }

        if (found) {
            System.out.println("Enter (C) to Check In a Book, or (X) to return to home screen");

            String userSelection = userInput.nextLine();
            if (userSelection.equalsIgnoreCase("c")) {
                System.out.print("Enter ID of book you are returning: ");
                int bookID = userInput.nextInt();
                userInput.nextLine();
                selectedBook = books[bookID];
            } else if (userSelection.equalsIgnoreCase("x")) {
                System.out.println("Returning to main menu.");
            } else {
                System.out.println("Invalid selection.");
                return;
            }

            if (selectedBook.isCheckedOut()) {
                System.out.println("Thank you " + selectedBook.getCheckedOutTo() +  " for returning " + selectedBook.getTitle());
                selectedBook.checkIn();
            } else {
                System.out.println("This book isn't checked out. Select another book.");
            }

        } else {
            System.out.println("No current books checked out.");
        }
    }
}
