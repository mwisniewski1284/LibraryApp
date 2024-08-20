package pl.programistaodzera.LibraryApp.author;

public class AuthorDTO {
    public AuthorDTO() {
    }
    private String lastName;
    private String name;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
