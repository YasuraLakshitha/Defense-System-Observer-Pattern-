import javax.swing.*;

public class SuperDefence extends JFrame {
    public static final int AREASTATE = 1;
    public static final int TEXTAREA = 7;
    public static final int MSGSENT = 8;
    public static Field updateField;
    protected String name;
    protected int soldierCount;
    protected int ammoAmount;
    protected double fuelAmount;
    protected String messageAreaRecived;
    protected boolean position;
    protected double strength;
    protected String areaState;
    protected String sentMessage;
    protected Controller defenceController;

    protected SuperDefence(){
        defenceController = Controller.getInstance();
    }

    protected void setAreaState(JLabel label,String value){
        areaState = value;
        label.setText(value);
    }

    protected void setBtn1Pattern(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JCheckBox position){
        if(position.isSelected() & (areaState!=null && !areaState.equals("Area Clear"))){
            btn1.setEnabled(true);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            if (btn4!=null) {btn4.setEnabled(false);}
        }
    }
    protected void setBtn2Pattern(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JCheckBox position){
        if(position.isSelected() & (areaState!=null && !areaState.equals("Area Clear"))){
            btn1.setEnabled(true);
            btn2.setEnabled(true);
            btn3.setEnabled(false);
            if (btn4!=null) {btn4.setEnabled(false);}
        }
    }
    protected void setBtn3Pattern(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JCheckBox position){
        if(position.isSelected() & (areaState!=null && !areaState.equals("Area Clear"))){
            btn1.setEnabled(true);
            btn2.setEnabled(true);
            btn3.setEnabled(true);
            if (btn4!=null) {btn4.setEnabled(false);}
        }
    }
    protected void setBtn4Pattern(JButton btn1, JButton btn2, JButton btn3, JButton btn4, JCheckBox position){
        if(position.isSelected() & (areaState!=null && !areaState.equals("Area Clear"))){
            btn1.setEnabled(true);
            btn2.setEnabled(true);
            btn3.setEnabled(true);
            if (btn4!=null) {btn4.setEnabled(true);}
        }
    }
    protected void disableBtn(JButton btn1, JButton btn2, JButton btn3, JButton btn4){
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        if (btn4!=null) {btn4.setEnabled(false);}
    }

    protected void setTextArea(JTextArea txtArea,String value){
        messageAreaRecived = value;
        txtArea.setText(messageAreaRecived);
    }
    public int getSoldierCount() {
        return soldierCount;
    }

    public int getAmmoAmount() {
        return ammoAmount;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

}
