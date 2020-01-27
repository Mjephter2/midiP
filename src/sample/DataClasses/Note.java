package sample.DataClasses;

import java.util.LinkedList;
import java.util.List;

public class Note {
    private String name;
    public LinkedList<String> noteNames = new LinkedList(List.of("C", "Db","D", "Eb", "E",
                                                                "F", "Gb","G", "Ab", "A", "Bb", "B"));


    public static void main(String[] args) {
        Note note1 = new Note("G");
        System.out.println(note1);
        note1=note1.sharp();
        System.out.println(note1);
        note1=note1.sharp(4);
        System.out.println(note1);
        note1=note1.sharp(7);
        System.out.println(note1);
        note1=note1.flat();
        System.out.println(note1);
        note1=note1.flat(5);
        System.out.println(note1);
        note1=note1.flat(6);
        System.out.println(note1);
        note1=note1.sharp(12);
        System.out.println(note1);
        note1=note1.flat(12);
        System.out.println(note1);
    }

    public Note(){
        name = "C";
    }

    public Note(String name){
        if(!noteNames.contains(name)){
            System.out.println("Wrong Note Name!!!");
            System.exit(0);
        }
        this.name = name;
    }
    public Note(Note otherNote){
        name = otherNote.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public String toString(){
        return name;
    }

    private Note sharp(){
        int index = noteNames.indexOf(name);
        Note result = new Note(noteNames.get((index + 1) % 12));
        return result;
    }
    public Note sharp(int n){
        if(n >= 12) n = n % 12;
        Note result = this;
        for(int i =1; i<=n; i++) result = result.sharp();
        return result;
    }
    private Note flat(){
        int index = noteNames.indexOf(name);
        Note result = new Note(noteNames.get((index - 1 + 12) % 12));
        return result;
    }
    public Note flat(int n){
        if(n >= 12) n = n % 12;
        Note result = this;
        for(int i = 1; i <= n; i++) result = result.flat();
        return result;
    }
}
