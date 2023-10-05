package Animals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class Animal {
    private String name;
    private Calendar birthday = new GregorianCalendar();
    private List<Command> commands = new ArrayList<Command>();

    public Animal(String name, int dayOfBirth, int monthOfBirth, int yearOfBirth){
        this.name = name;
        this.birthday.set(Calendar.DAY_OF_MONTH, dayOfBirth);
        this.birthday.set(Calendar.MONTH, monthOfBirth);
        this.birthday.set(Calendar.YEAR, yearOfBirth);
    }
    
    public String getName(){
        return name;
    }
    
    public List<Command> getCommands(){
        return commands;
    }

    public void addCommand (Command command){
        if(commands.indexOf(command)!=-1){
            System.out.println("Указанная команда уже добавлена указанному животному");
        }else{
            commands.add(command);
        }
        
    }

    @Override
    public String toString(){
        return name + " " + birthday.get(Calendar.DAY_OF_MONTH)+"."+
        birthday.get(Calendar.MONTH)+"."+birthday.get(Calendar.YEAR)+" г.р.";
    }
}
