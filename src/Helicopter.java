import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Helicopter extends SuperDefence implements Observer {
    private JPanel helicopterPanel;
    private JLabel areaStateLabel;
    private JButton shootButton;
    private JButton missileOperationButton;
    private JButton laserOperationButton;
    private JTextArea msgArea;
    private JTextField msgTxtField;
    private JButton sendButton;
    private JSlider sliderFuel;
    private JLabel ammoCountLabel;
    private JLabel soldierCountLabel;
    private JCheckBox positionCheckBox;
    private JSpinner soldierCountSpinner;
    private JSpinner ammoAmountSpinner;

    public Helicopter(){
        setTitle("Helicopter");
        setSize(550,300);
        setContentPane(helicopterPanel);
        setVisible(true);
        shootButton.setEnabled(false);
        shootButton.setFocusable(false);
        missileOperationButton.setEnabled(false);
        missileOperationButton.setFocusable(false);
        laserOperationButton.setEnabled(false);
        laserOperationButton.setFocusable(false);
        sendButton.setFocusable(false);
        name = "Helicopter";
        positionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!positionCheckBox.isSelected()){
                    disableBtn(shootButton,missileOperationButton,laserOperationButton,null);
                }
            }
        });
        soldierCountSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                soldierCount = (int) soldierCountSpinner.getValue();
            }
        });
        ammoAmountSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ammoAmount = (int) ammoAmountSpinner.getValue();
            }
        });
        sliderFuel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fuelAmount = sliderFuel.getValue();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               defenceController.notifyObjects(Field.MSGSENT,"Helicopter : "+msgTxtField.getText());
            }
        });
    }
    @Override
    public void update(Field field,String value) {
        switch (field){
            case AREASTATE:
                setAreaState(areaStateLabel,value);
                if(value.equals("Area Clear")){disableBtn(shootButton,missileOperationButton,laserOperationButton,null);}
                break;
            case LOW:
                setBtn1Pattern(shootButton,missileOperationButton,laserOperationButton,null,positionCheckBox);
                break;
            case MEDIUM:
                setBtn2Pattern(shootButton,missileOperationButton,laserOperationButton,null,positionCheckBox);
                break;
            case HIGH:
                setBtn3Pattern(shootButton,missileOperationButton,laserOperationButton,null,positionCheckBox);
                break;
            case CLOSED:
                disableBtn(shootButton,missileOperationButton,laserOperationButton,null);
                break;
            case TEXTAREA:
                setTextArea(msgArea,value);
        }
    }
}
