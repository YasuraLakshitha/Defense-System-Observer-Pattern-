import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Submarine extends SuperDefence implements Observer {
    private int energyLevel;
    private int oxygenLevel;
    private JLabel areaStateLabel;
    private JButton shootButton;
    private JButton sonarOperationButton;
    private JButton tomahawkMissileButton;
    private JButton trident2MissileButton;
    private JTextArea msgTextArea;
    private JTextField msgTextField;
    private JButton sendButton;
    private JLabel soldierCountLabel;
    private JLabel ammoCountLabel;
    private JSlider energySlider;
    private JSlider oxygenSlider;
    private JCheckBox positionCheckBox;
    private JPanel submarinePanel;
    private JSpinner soldierCountSpinner;
    private JSpinner ammoCountSpinner;

    public Submarine(){
        name = "Submarine";
        setTitle("Submarine");
        setContentPane(submarinePanel);
        setSize(550,300);
        setVisible(true);
        shootButton.setEnabled(false);
        shootButton.setFocusable(false);
        sonarOperationButton.setEnabled(false);
        sonarOperationButton.setFocusable(false);
        tomahawkMissileButton.setEnabled(false);
        tomahawkMissileButton.setFocusable(false);
        trident2MissileButton.setEnabled(false);
        trident2MissileButton.setFocusable(false);
        sendButton.setFocusable(false);
        positionCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!positionCheckBox.isSelected()){
                    disableBtn(shootButton,sonarOperationButton,tomahawkMissileButton,trident2MissileButton);
                }
            }
        });
        soldierCountSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                soldierCount = (int)soldierCountSpinner.getValue();
            }
        });
        ammoCountSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ammoAmount = (int)ammoCountSpinner.getValue();
            }
        });
        energySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                energyLevel = energySlider.getValue();
            }
        });
        oxygenSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                oxygenLevel = oxygenSlider.getValue();
            }
        });
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                defenceController.notifyObjects(Field.MSGSENT,"Submarine : "+msgTextField.getText());
            }
        });
    }
    @Override
    public void update(Field field, String value) {
        switch (field){
            case AREASTATE:
                setAreaState(areaStateLabel,value);
                if(value.equals("Area Clear")){disableBtn(shootButton,sonarOperationButton,tomahawkMissileButton,trident2MissileButton);}
                break;
            case LOW:
                setBtn1Pattern(shootButton,sonarOperationButton,tomahawkMissileButton,trident2MissileButton,positionCheckBox);
                break;
            case MEDIUM:
                setBtn2Pattern(shootButton,sonarOperationButton,tomahawkMissileButton,trident2MissileButton,positionCheckBox);
                break;
            case HIGH:
                setBtn3Pattern(shootButton,sonarOperationButton,tomahawkMissileButton,trident2MissileButton,positionCheckBox);
                break;
            case STRONG:
                setBtn4Pattern(shootButton,sonarOperationButton,tomahawkMissileButton,trident2MissileButton,positionCheckBox);
                break;
            case CLOSED:
                disableBtn(shootButton,sonarOperationButton,tomahawkMissileButton,trident2MissileButton);
                break;
            case TEXTAREA:
                setTextArea(msgTextArea,value);
                break;
        }
    }
    public int getEnergyLevel() {
        return energyLevel;
    }

    public int getOxygenLevel() {
        return oxygenLevel;
    }
}
