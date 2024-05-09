import javax.swing.*;
import java.util.ArrayList;


public class Controller implements Observable {
    public static ArrayList<Observer> defenceArrayList;
    private static Controller instance;
    private Controller(){
        defenceArrayList = new ArrayList<>();
    }

    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    public void strengthOperation(JSlider sldrStrength){
        if(sldrStrength.getValue()>=80){
            notifyObjects(Field.STRONG,null);
            notifyObjects(Field.STRENGTHLEVEL,"STRONG");
        } else if (sldrStrength.getValue()>=60) {
            notifyObjects(Field.HIGH,null);
            notifyObjects(Field.STRENGTHLEVEL,"HIGH");
        } else if (sldrStrength.getValue()>=40) {
            notifyObjects(Field.MEDIUM,null);
            notifyObjects(Field.STRENGTHLEVEL,"MEDIUM");
        }else if(sldrStrength.getValue()>=20){
            notifyObjects(Field.LOW,null);
            notifyObjects(Field.STRENGTHLEVEL,"LOW");
        }else{
            notifyObjects(Field.STRENGTHLEVEL,"0");
            notifyObjects(Field.CLOSED,null);
        }
    }
    @Override
    public void notifyObjects(Field field,String value){
        defenceArrayList.forEach(unit->unit.update(field,value));
    }

    public static Observer getUnit(String unitName){
        for (Observer ob:defenceArrayList){
            if(unitName.equals(ob.getClass().getName())) {
                return ob;
            }
        }
        return null;
    }
    public void sendMsg(JRadioButton radioHelicopter,JRadioButton radioTank,JRadioButton radioSubmarine,String msgText){
        if(radioHelicopter.isSelected()&radioTank.isSelected()&radioSubmarine.isSelected()) {
            getUnit("Tank").update(Field.TEXTAREA,msgText);
            getUnit("Helicopter").update(Field.TEXTAREA,msgText);
            getUnit("Submarine").update(Field.TEXTAREA,msgText);
        }else if(radioHelicopter.isSelected()&radioTank.isSelected()){
            getUnit("Helicopter").update(Field.TEXTAREA,msgText);
            getUnit("Tank").update(Field.TEXTAREA,msgText);
        }else if(radioTank.isSelected()&radioSubmarine.isSelected()){
            getUnit("Tank").update(Field.TEXTAREA,msgText);
            getUnit("Submarine").update(Field.TEXTAREA,msgText);
        }else if(radioHelicopter.isSelected()&radioSubmarine.isSelected()){
            getUnit("Helicopter").update(Field.TEXTAREA,msgText);
            getUnit("Submarine").update(Field.TEXTAREA,msgText);
        }else if(radioHelicopter.isSelected()){
            getUnit("Helicopter").update(Field.TEXTAREA,msgText);
        }else if(radioTank.isSelected()){
            getUnit("Tank").update(Field.TEXTAREA,msgText);
        }else if(radioSubmarine.isSelected()){
            getUnit("Submarine").update(Field.TEXTAREA,msgText);
        }
    }

    public  void addDefence(Observer obj){
        defenceArrayList.add(obj);

    }
}
