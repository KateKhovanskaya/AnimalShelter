import java.util.ArrayList;
import java.util.List;

import Animals.Animal;
import Animals.Camel;
import Animals.Dog;
import Animals.Donkey;
import Animals.Hamster;
import Animals.Horse;
import Animals.Cat;

public class AnimalShelter{
    private static int counter;
    private List<Animal> animals = new ArrayList<Animal>();
  
    public AnimalShelter(List<Animal> animals){
        this.animals = animals;
    } 
    public AnimalShelter(){
        this(new ArrayList<>());
    }
    public int getCounter(){
        return counter;
    }

    private void add(){
        counter +=1;
    }
    public List<Animal> getAllAnimals(){
        return animals;
    }

    public void addAnimal(Animal animal){
        animals.add(animal);
        add();
    }
    public void addAnimal (int type, String name, int dayOfBirth, int monthOfBirth, int yearOfBirth){
        switch(type){
            case 1: 
                addAnimal(new Dog(name, dayOfBirth, monthOfBirth, yearOfBirth));
                break;
            case 2: 
                addAnimal(new Cat(name, dayOfBirth, monthOfBirth, yearOfBirth));
                break;
            case 3: 
                addAnimal(new Hamster(name, dayOfBirth, monthOfBirth, yearOfBirth));
                break;
            case 4: 
                addAnimal(new Horse(name, dayOfBirth, monthOfBirth, yearOfBirth));
                break;
            case 5: 
                addAnimal(new Camel(name, dayOfBirth, monthOfBirth, yearOfBirth));
                break;
            case 6: 
                addAnimal(new Donkey(name, dayOfBirth, monthOfBirth, yearOfBirth));
                break;
            default: System.out.println("Для указанного значения животное не определено");
        }
    }

    public List<Animal> getAnimalByName(String name){
        List<Animal> res = new ArrayList<>();
        for(int i=0; i<animals.size(); i++){
            Animal animal = animals.get(i);
            if (animal.getName().toLowerCase().indexOf(name)>=0){
                res.add(animal);
            }
        }
        return res;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (Animal animal : animals) {
            result.append(animal);
            result.append("\n");
        }
        return result.toString();
    }
}