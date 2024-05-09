import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainController extends SuperDefence implements Observer{
    private JComboBox defenceList;
    private JCheckBox clearAreaCheckBox;
    private JPanel mainPanel;
    private JButton btnCollectInfo;
    private JLabel soldierCountLabel;
    private JLabel soldierCount;
    private JLabel fuelAmountLabel;
    private JLabel ammoAmountLabel;
    private JLabel positionLabel;
    private JSlider sldrStrength;
    private JTextArea msgArea;
    private JRadioButton radioTank;
    private JRadioButton radioHelicopter;
    private JRadioButton radioSubmarine;
    private JButton btnSendMsg;
    private JButton btnClear;
    private JLabel ammoAmount;
    private JLabel fuelAmount;
    private JLabel oxygenLevelLabel;
    private JLabel oxygenLevel;
    private JLabel strengthLevelLabel;
    private JLabel strengthLevelValueLabel;

    public MainController() {
        defenceController.defenceArrayList = new ArrayList<>();
        setTitle("Main Controller");
        name = "Main Controller";
        setContentPane(mainPanel);
        setVisible(true);
        setSize(550,400);
        btnClear.setFocusable(false);
        btnCollectInfo.setFocusable(false);
        btnSendMsg.setFocusable(false);
        clearAreaCheckBox.setSelected(true);
        clearAreaCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Observer ob :defenceController.defenceArrayList){
                    String value;
                    if(clearAreaCheckBox.isSelected()){
                        value = "Area Clear";
                    }else{
                        value = "Area is not Clear";
                    }
                    ob.update(Field.AREASTATE,value);
                }
            }
        });
        btnCollectInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Observer unit=defenceController.getUnit(defenceList.getSelectedItem().toString());
              if(unit instanceof Helicopter){
                  Helicopter h = (Helicopter) unit;
                  fuelAmountLabel.setText("Fuel Amount");
                  oxygenLevelLabel.setText("");
                  oxygenLevel.setText("");
                  fuelAmount.setText(h.getFuelAmount()+"");
                  soldierCount.setText(h.getSoldierCount()+"");
                  ammoAmount.setText(h.getAmmoAmount()+"");
              }else if(unit instanceof Tank){
                  Tank t = (Tank)unit;
                  fuelAmountLabel.setText("Fuel Amount");
                  oxygenLevelLabel.setText("");
                  oxygenLevel.setText("");
                  soldierCount.setText(t.getSoldierCount()+"");
                  fuelAmount.setText(t.getFuelAmount()+"");
                  ammoAmount.setText(t.getAmmoAmount()+"");
              }else if(unit instanceof Submarine){
                  System.out.println(10);
                  Submarine s = (Submarine) unit;
                  soldierCount.setText(s.getSoldierCount()+"");
                  ammoAmount.setText(s.getAmmoAmount()+"");
                  fuelAmountLabel.setText("Energy Level");
                  fuelAmount.setText(s.getEnergyLevel()+"");
                  oxygenLevelLabel.setText("Oxygen Level");
                  oxygenLevel.setText(s.getOxygenLevel()+"");
              }
            }
        });

        sldrStrength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                defenceController.strengthOperation(sldrStrength);
            }
        });

        btnSendMsg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defenceController.sendMsg(radioHelicopter,radioTank,radioSubmarine,msgArea.getText());
                msgArea.setText("\nMessage sent...");
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                msgArea.setText("");
            }
        });
    }

    @Override
    public void update(Field field, String value) {
        switch (field) {
            case MSGSENT:
                msgArea.setText(value);
                break;
            case STRENGTHLEVEL:
                strengthLevelValueLabel.setText(value);
                break;
        }
    }
}
