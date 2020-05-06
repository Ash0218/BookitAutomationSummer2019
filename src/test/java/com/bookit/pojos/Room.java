package com.bookit.pojos; // 020620

/*
{
    "id": 112,
 *         "name": "harvard",
 *         "description": "veritas",
 *         "capacity": 6,
 *         "withTV": true,
 *         "withWhiteBoard": false
 *     },
 */

import java.util.Objects;

// 020720
public class Room {
    private int id; // 1
    private String name; // 2
    private String description; // 3
    private int capacity; // 4
    private boolean withTV; // 5
    private boolean withWhiteBoard; // 6

    public Room(){ // 9

    }


    // equals() and haseCode() from here (#11)
    // It checks if 2 room objects are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                Objects.equals(name, room.name);
    }

    // need to override if you are gonna use hash based data structures
    //  like HashMap, HashSet. Otherwise, HashSet/ HashMap will not contain
    //  only unique values
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    } // equals() and haseCode() till here (#11)


    // Constructor from here (#8)
    public Room(String name, String description, int capacity, boolean withTV, boolean withWhiteBoard) {
        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.withTV = withTV;
        this.withWhiteBoard = withWhiteBoard;
    } // constructor till here (#8)


    // Getters and setters from here #7
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isWithTV() {
        return withTV;
    }

    public void setWithTV(boolean withTV) {
        this.withTV = withTV;
    }

    public boolean isWithWhiteBoard() {
        return withWhiteBoard;
    }

    public void setWithWhiteBoard(boolean withWhiteBoard) {
        this.withWhiteBoard = withWhiteBoard;
    }
    // Getters and setters till here #7


    // toString method from here (#10)
    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", withTV=" + withTV +
                ", withWhiteBoard=" + withWhiteBoard +
                '}';
    } // toString method till here (#10)
}
