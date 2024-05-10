package com.example.diceroll;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textResult;
    private Spinner spinnerDiceFaces;
    private RadioGroup radioGroupDiceCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.textResult);
        spinnerDiceFaces = findViewById(R.id.spinnerDiceFaces);
        radioGroupDiceCount = findViewById(R.id.radioGroupDiceCount);
        Button buttonRoll = findViewById(R.id.buttonRoll);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dice_faces_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDiceFaces.setAdapter(adapter);

        buttonRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
    }

    private void rollDice() {
        Random random = new Random();
        String selectedDice = spinnerDiceFaces.getSelectedItem().toString();
        int diceFaces = mapDiceToValue(selectedDice);
        int diceCount = getSelectedDiceCount();

        StringBuilder result = new StringBuilder("Dice Rolled: ");
        for (int i = 0; i < diceCount; i++) {
            int diceValue = random.nextInt(diceFaces) + 1;
            result.append(diceValue);
            if (i < diceCount - 1) {
                result.append(", ");
            }
        }
        textResult.setText(result.toString());
    }

    private int getSelectedDiceCount() {
        int selectedRadioButtonId = radioGroupDiceCount.getCheckedRadioButtonId();
        View radioButton = radioGroupDiceCount.findViewById(selectedRadioButtonId);
        int index = radioGroupDiceCount.indexOfChild(radioButton);

        switch (index) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            default:
                return 1;
        }
    }

    private int mapDiceToValue(String selectedDice) {
        switch (selectedDice) {
            case "D4":
                return 4;
            case "D6":
                return 6;
            case "D8":
                return 8;
            case "D10":
                return 10;
            case "D12":
                return 12;
            case "D20":
                return 20;
            case "D100":
                return 100;
            default:
                return 6; // Valor padrão
        }
    }
}
