package org.example;
import java.util.ArrayList;
import java.util.List;

class Group {
    String type;
    String name;
    List<Character> members;

    public Group(String type, String name) {
        this.type = type;
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(Character character) {
        members.add(character);
        character.group = this;
    }

    public void moveTo(Position newPosition, List<House> houses) {
        // Calcularea dimensiunea totală a grupului
        int totalWidth = 0;
        int totalLength = 0;
        for (Character member : members) {
            totalWidth += member.size.width;
            totalLength += member.size.length;
        }

        // Crearea un obiect de mărime pentru grup
        Size groupSize = new Size(totalWidth, totalLength);

        // Se validează dacă noua poziție este valabilă pentru grup
        if (isValidPosition(newPosition, groupSize, houses)) {
            // Move each member of the group
            for (Character member : members) {
                member.position = newPosition;
            }
            System.out.println("Group " + name + " moved to: (" + newPosition.x + ", " + newPosition.y + ")");
        } else {
            System.out.println("Invalid move for group " + name + " to: (" + newPosition.x + ", " + newPosition.y + ")");
        }
    }

    private boolean isValidPosition(Position newPosition, Size groupSize, List<House> houses) {
        // Verificați dacă noua poziție se încadrează în limitele bordului
        if (newPosition.x < 0 || newPosition.y < 0 || newPosition.x + groupSize.width > 15 || newPosition.y + groupSize.length > 15) {
            return false;
        }

        // Verifică dacă noua poziție se suprapune cu vreo casă
        for (House house : houses) {
            if (newPosition.x < house.position.x + house.size.width &&
                    newPosition.x + groupSize.width > house.position.x &&
                    newPosition.y < house.position.y + house.size.length &&
                    newPosition.y + groupSize.length > house.position.y) {
                return false;
            }
        }

        return true;
    }
}