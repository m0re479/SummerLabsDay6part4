package ru.app.summerlabsday6part3;


import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import ru.app.summerlabsday6part3.Variant14.Dachshund;
import ru.app.summerlabsday6part3.Variant14.Dog;
import ru.app.summerlabsday6part3.Variant14.Hound;
import ru.app.summerlabsday6part3.Variant14.Pastoral;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private RadioGroup radioGroupBreed;
    private EditText editTextAdditionalField;
    private CheckBox checkBoxCollar, checkBoxMuzzle, checkBoxToy;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        radioGroupBreed = findViewById(R.id.radioGroupBreed);
        editTextAdditionalField = findViewById(R.id.editTextAdditionalField);
        checkBoxCollar = findViewById(R.id.checkBoxCollar);
        checkBoxMuzzle = findViewById(R.id.checkBoxMuzzle);
        checkBoxToy = findViewById(R.id.checkBoxToy);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        textViewResult = findViewById(R.id.textViewResult);

        textViewResult.setMovementMethod(new ScrollingMovementMethod());

        radioGroupBreed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Проверьте, что checkedId не равен -1, что означает, что ни один RadioButton не выбран.
                if (checkedId != -1) {
                    RadioButton selectedRadioButton = findViewById(checkedId);
                    String hint = "";
                    // Убедитесь, что selectedRadioButton не равен null
                    if (selectedRadioButton != null) {
                        String radioButtonText = selectedRadioButton.getText().toString();
                        if (radioButtonText.equals(getString(R.string.pastoral))) {
                            hint = "Название стада";
                        } else if (radioButtonText.equals(getString(R.string.dachshund))) {
                            hint = "Окрас";
                        } else if (radioButtonText.equals(getString(R.string.hound))) {
                            hint = "Количество охот, в которых участвовала";
                        }
                        editTextAdditionalField.setHint(hint);
                    }
                }
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDog();
            }
        });
    }

    private void createDog() {
        try {
            String name = editTextName.getText().toString().trim();
            String additionalField = editTextAdditionalField.getText().toString().trim();

            if (name.isEmpty() || additionalField.isEmpty()) {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                return;
            }

            ArrayList<String> accessories = new ArrayList<>();
            if (checkBoxCollar.isChecked()) accessories.add("Ошейник");
            if (checkBoxMuzzle.isChecked()) accessories.add("Намордник");
            if (checkBoxToy.isChecked()) accessories.add("Игрушка");

            int selectedRadioButtonId = radioGroupBreed.getCheckedRadioButtonId();
            if (selectedRadioButtonId == -1) {
                Toast.makeText(this, "Пожалуйста, выберите породу", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            if (selectedRadioButton == null) {
                Toast.makeText(this, "Ошибка при выборе породы", Toast.LENGTH_SHORT).show();
                return;
            }

            String breed = selectedRadioButton.getText().toString();

            Dog dog;
            if (breed.equals("Пастушья")) {
                dog = new Pastoral(name, accessories, additionalField);
            } else if (breed.equals("Такса")) {
                dog = new Dachshund(name, accessories, additionalField);
            } else {
                dog = new Hound(name, accessories, Integer.parseInt(additionalField));
            }

            String result = "Новая собака:\n" +
                    "Кличка: " + dog.getName() + "\n" +
                    "Порода: " + breed + "\n" +
                    "Дополнительные данные: " + additionalField + "\n" +
                    "Аксессуары: " + TextUtils.join(", ", accessories) + "\n\n";

            textViewResult.setText(result);

            // Clear input fields
            editTextName.setText("");
            radioGroupBreed.clearCheck();
            editTextAdditionalField.setText("Some note");
            checkBoxCollar.setChecked(false);
            checkBoxMuzzle.setChecked(false);
            checkBoxToy.setChecked(false);

            Toast.makeText(this, "Собака успешно добавлена", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Произошла ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            textViewResult.setText("Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}