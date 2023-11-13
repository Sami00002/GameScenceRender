package org.example;
import java.util.List;

class Character {
    String type; // Tipul personajului
    int age; // Vârsta personajului
    Position position; // Poziția personajului
    Size size; // Dimensiunea personajului
    String abilities; // Abilitățile personajului
    Group group; // Grupul personajului

    public Character(String type, int age, Position position, Size size, String abilities) {
        this.type = type;
        this.age = age;
        this.position = position;
        this.size = size;
        this.abilities = abilities;
    }

    public void moveTo(Position newPosition, List<House> houses) {
        // Validează dacă noua poziție este validă
        if (isValidPosition(newPosition, houses)) {
            this.position = newPosition;
            System.out.println(type + " s-a mutat la: (" + newPosition.x + ", " + newPosition.y + ")");
        } else {
            System.out.println("Mutare invalidă pentru " + type + " la: (" + newPosition.x + ", " + newPosition.y + ")");
        }
    }

    private boolean isValidPosition(Position newPosition, List<House> houses) {
        // Verifică dacă noua poziție se află în limitele tablei
        if (newPosition.x < 0 || newPosition.y < 0 || newPosition.x + size.width > 15 || newPosition.y + size.length > 15) {
            return false;
        }

        // Verifică dacă noua poziție se suprapune cu vreo casă
        for (House house : houses) {
            if (newPosition.x < house.position.x + house.size.width &&
                    newPosition.x + size.width > house.position.x &&
                    newPosition.y < house.position.y + house.size.length &&
                    newPosition.y + size.length > house.position.y) {
                return false;
            }
        }

        return true;
    }
}