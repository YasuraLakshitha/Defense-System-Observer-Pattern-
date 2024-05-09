import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tank extends SuperDefence implements Observer {

    private JPanel tankPanel;
    private JLabel areaStateLabel;
    private JButton shootButton;
    private JButton missileOperationButton;
    private JButton redarOperationButton;
    private JButton rotateShootingButton;
    private JTextArea msgTextArea;
    private JTextField msgTextField;
    private JButton sendButton;
    private JLabel soldierCountLabel;
    private JLabel ammoCountLabel;
    private JCheckBox positionCheckBox;
    private JSpinner solderCountSpinner;
    private JSpinner ammoCountSpinner;
    private JSlider fuelSlider;

    public Tank(){
        super.name = "Tank";
        setTitle("Tank");
        setContentPane(tankPanel);
        setSize(550,300);
        setVisible(true);
        shootButton.setEnabled(false);
        missileOperationButton.setEnabled(false);
        redarOperationButton.setEnabled(false);
        rotateShootingButton.setEnabled(false);
        positionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!positionCheckBox.isSelected()){
                    disableBtn(shootButton,missileOperationButton,redarOperationButton,rotateShootingButton);
                }
            }
        });

        solderCountSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                soldierCount = (int)solderCountSpinner.getValue();
            }
        });

        ammoCountSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ammoAmount = (int)ammoCountSpinner.getValue();
            }
        });

        fuelSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fuelAmount = fuelSlider.getValue();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               defenceController.notifyObjects(Field.MSGSENT,"Tank : "+msgTextField.getText());
            }
        });
    }
    @Override
    public void update(Field field, String value) {
        switch (field){
            case AREASTATE:
                setAreaState(areaStateLabel,value);
                if(value.equals("Area Clear")){disableBtn(shootButton,missileOperationButton,redarOperationButton,rotateShootingButton);}
                break;
            case LOW:
                setBtn1Pattern(shootButton,missileOperationButton,redarOperationButton,rotateShootingButton,positionCheckBox);
                break;
            case MEDIUM:
                setBtn2Pattern(shootButton,missileOperationButton,redarOperationButton,rotateShootingButton,positionCheckBox);
                break;
            case HIGH:
                setBtn3Pattern(shootButton,missileOperationButton,redarOperationButton,rotateShootingButton,positionCheckBox);
                break;
            case STRONG:
                setBtn4Pattern(shootButton,missileOperationButton,redarOperationButton,rotateShootingButton,positionCheckBox);
                break;
            case CLOSED:
                disableBtn(shootButton,missileOperationButton,redarOperationButton,rotateShootingButton);
                break;
            case TEXTAREA:
                setTextArea(msgTextArea,value);
        }
    }
}
