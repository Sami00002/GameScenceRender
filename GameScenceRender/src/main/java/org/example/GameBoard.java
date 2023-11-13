package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GameBoard {
    int width;
    int length;
    List<House> houses = new ArrayList<>();
    List<Character> characters = new ArrayList<>();
    List<Group> groups = new ArrayList<>();

    public GameBoard(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public void addHouse(House house) {
        houses.add(house);
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public void render() {
        char[][] board = new char[length][width];

        // Inițializarea tabla cu spații goale
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = '.';
            }
        }

        // Adăugarea case la tablă
        for (House house : houses) {
            char houseTypeChar = house.type.charAt(0);
            for (int i = house.position.y; i < house.position.y + house.size.length && i < length; i++) {
                for (int j = house.position.x; j < house.position.x + house.size.width && j < width; j++) {
                    board[i][j] = houseTypeChar; // Reprezintarea casele cu primul caracter al tipului
                }
            }
        }

        // Adăugare personaje și grupuri pe tablă
        for (Character character : characters) {
            char characterTypeChar = character.type.charAt(0);
            if (character.position.y < length && character.position.x < width) {
                board[character.position.y][character.position.x] = characterTypeChar;
            }
        }

        for (Group group : groups) {
            char groupTypeChar = group.type.charAt(0);
            for (Character member : group.members) {
                if (member.position.y < length && member.position.x < width) {
                    board[member.position.y][member.position.x] = groupTypeChar;
                }
            }
        }

        // Print the board
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        // Print house, character, si informațiile grupei
        for (House house : houses) {
            System.out.println(house.type + " house made of " + house.material + " at (" +
                    house.position.x + ", " + house.position.y + ") - Size: (" +
                    house.size.width + "x" + house.size.length + ")");
        }

        for (Character character : characters) {
            System.out.println(character.type + " at (" + character.position.x + ", " +
                    character.position.y + ") - Age: " + character.age +
                    ", Size: (" + character.size.width + "x" + character.size.length +
                    "), Abilities: " + character.abilities);
        }

        for (Group group : groups) {
            System.out.println(group.type + " " + group.name + " at (" +
                    group.members.get(0).position.x + ", " + group.members.get(0).position.y + ")");
        }
    }

    public void initializeHouses(int numberOfHouses, int spaceBetweenHouses) {
        Random random = new Random();

        for (int i = 0; i < numberOfHouses; i++) {
            String[] houseTypes = {"European", "Asian", "African"};
            String houseType = houseTypes[random.nextInt(houseTypes.length)];

            // Generarea aleatorie a poziției casei cu spațiu între ele
            int x = random.nextInt(width - spaceBetweenHouses);
            int y = random.nextInt(length - spaceBetweenHouses);

            // Crearea un material de casă aleatoriu pentru simplitate
            String[] houseMaterials = {"concrete", "bamboo", "wail and cane"};
            String houseMaterial = houseMaterials[random.nextInt(houseMaterials.length)];

            House house = new House(houseType, houseMaterial, new Position(x, y), new Size(5, 5)); // Adjust size as needed
            houses.add(house);
        }
    }
}