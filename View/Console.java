import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Animals.Animal;
import Animals.Command;

public class Console implements View{
    private Scanner scanner;
    private AnimalShelter animalShelter;
    private boolean work;

    public Console(){
        scanner = new Scanner(System.in);
        animalShelter = new AnimalShelter();
        work = true;
    }
    
    @Override
    public void setAnimalShelter(AnimalShelter animalShelter){
        this.animalShelter = animalShelter;
    }

    @Override
    public void start(){
        while(work){
            printMenu();
            execute();
        }
    }

    private void printMenu(){
        System.out.println("Выберите действие:\n" + 
                            "1 - добавить животное\n" +
                            "2 - показать всех животных в питомнике\n" +
                            "3 - показать команды, которые знает животное\n" +
                            "4 - добавить команду для животного\n" +
                            "5 - завершить работу");
    }

    private void execute(){
        int numCommand = Integer.parseInt(scanner.nextLine());
        switch (numCommand){
            case 1: addAnimal();
                    break;
            case 2: getAllAnimals();
                    break;
            case 3: getCommands();
                    break;
            case 4: addCommand();
                    break;
            case 5: finish();
                    break;
            default: System.out.println("Команда не найдена, повторите ввод: ");
        }
    }

    @Override
    public void addAnimal(){
        printAnimalTypes();
        int animalType = Integer.parseInt(scanner.nextLine());
        if(animalType < 1 || animalType >6){
            System.out.println("Для указанного значения животное не определено");
        }else{
            System.out.println("Введите через пробел кличку животного " +
                                "и дату рождения в формате dd.mm.yyyy");
            String[] innerData = scanner.nextLine().split(" ");
            innerData = checkBirthdayFromInput(innerData);
            String[] birthday = innerData[1].split("\\.");
            System.out.println(birthday[0]+" "+birthday[1]+" "+birthday[2]);
            int dayOfBirth = Integer.parseInt(birthday[0]);
            int monthOfBirth = Integer.parseInt(birthday[1]);
            int yearyOfBirth = Integer.parseInt(birthday[2]);
            animalShelter.addAnimal(animalType, innerData[0], dayOfBirth, monthOfBirth, yearyOfBirth);
        }
    }

        private void printAnimalTypes(){
        System.out.println("Какое животное вы хотите добавить:\n" + 
                            "1 - собаку\n" +
                            "2 - кота\n" +
                            "3 - хомяка\n" +
                            "4 - лошадь\n" +
                            "5 - верблюда\n" +
                            "6 - осла\n");
    }

    private String[] checkBirthdayFromInput(String[] innerData){
        boolean birsdayIsFind = false;
        Date birthday = null;
        while(!birsdayIsFind){
            try{
                DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
                birthday = df.parse(innerData[1]);
                birsdayIsFind = true;
            }catch(ParseException e){
                System.out.println("Не найдены данные, соответствующие дате в формате dd.mm.yyyy\n" +
                "повторите, пожалуйста, ввод");
                innerData = scanner.nextLine().split(" ");
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Не найдены данные, соответствующие дате в формате dd.mm.yyyy\n" +
                "повторите, пожалуйста, ввод");
                innerData = scanner.nextLine().split(" ");
            }
        }
        return innerData;
    }

    @Override
    public void getAllAnimals(){
        System.out.println(animalShelter.getAllAnimals());
    }

    @Override
    public void getCommands(){
        Animal findedAnimal = findAnimal();
        if(findedAnimal!=null) {
            System.out.println("Выбранное вами животное знает команды: ");
            System.out.println(findedAnimal.getCommands());
        }
    }

    private Animal findAnimal(){
        System.out.println("Введите кличку животного для поиска: ");
        List<Animal> findedAnimals = animalShelter.getAnimalByName(scanner.nextLine().toLowerCase());
        if(findedAnimals.size()!=0) {
            return findedAnimals.get(0);
        }else{
            System.out.println("Животное с данной кличкой не найдено");
            return null;
        }
    }

    @Override
    public void addCommand(){
        Animal findedAnimal = findAnimal();
        if(findedAnimal!=null) {
                System.out.println("Выбранное вами животное уже знает команды: ");
                System.out.println(findedAnimal.getCommands());
                printAnimalCommands();
                findedAnimal.addCommand(changeCommand());
            }
    }

    private void printAnimalCommands(){
        System.out.println("Выберите команду, которую хотите добавить:\n" + 
                            "1 - идти\n" +
                            "2 - стоять\n" +
                            "3 - лежать\n" +
                            "4 - ко мне\n" + 
                            "5 - прыжок\n" + 
                            "6 - встать");
    }

    private Command changeCommand(){
        int command = Integer.parseInt(scanner.nextLine());
        switch(command){
            case 1: return Command.go;
            case 2: return Command.stop;
            case 3: return Command.lie;
            case 4: return Command.come_up;
            case 5: return Command.jump;
            case 6: return Command.stand_up;
            default: System.out.println("Такой команды не существует, повторите ввод:");
                return changeCommand();
        }
    }

    @Override
    public void finish(){
        work = false;
    }
}
