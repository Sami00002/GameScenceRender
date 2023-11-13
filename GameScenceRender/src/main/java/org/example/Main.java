package org.example;

public class Main {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(15, 15);

        // Inițializarea caselor
        gameBoard.initializeHouses(3, 2);

        // Create characters
        Character human1 = new Character("Human", 25, new Position(0, 0), new Size(1, 1), "Strength");
        Character elf1 = new Character("Elf", 150, new Position(3, 3), new Size(1, 1), "Agility");
        Character human2 = new Character("Human", 30, new Position(8, 8), new Size(1, 1), "Endurance");
        Character human3 = new Character("Human", 25, new Position(6, 6), new Size(1, 1), "Strength");
        Character elf2 = new Character("Elf", 150, new Position(10, 10), new Size(1, 1), "Agility");


        gameBoard.addCharacter(human1);
        gameBoard.addCharacter(elf1);
        gameBoard.addCharacter(human2);
        gameBoard.addCharacter(human3);
        gameBoard.addCharacter(elf2);

        // Crearea grupuri
        Group army1 = new Group("Military", "Army 1");
        army1.addMember(human1);
        army1.addMember(elf1);

        Group army2 = new Group("Military", "Army 2");
        army2.addMember(human2);

        gameBoard.addGroup(army1);
        gameBoard.addGroup(army2);

        // Mutarea caracterelor și a grupurilor
        human1.moveTo(new Position(2, 2), gameBoard.houses);
        elf1.moveTo(new Position(7, 7), gameBoard.houses);
        army1.moveTo(new Position(10, 10), gameBoard.houses);
        army2.moveTo(new Position(5, 5), gameBoard.houses);
        human2.moveTo(new Position(12, 12), gameBoard.houses);

        // Redă tabla de joc
        gameBoard.render();
    }
}