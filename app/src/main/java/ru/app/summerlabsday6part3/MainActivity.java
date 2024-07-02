package ru.app.summerlabsday6part3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ru.app.projectday6summerlabs.domain.Admin;
import ru.app.projectday6summerlabs.domain.Director;
import ru.app.projectday6summerlabs.domain.Employee;
import ru.app.projectday6summerlabs.domain.Engineer;
import ru.app.projectday6summerlabs.domain.Manager;

public class MainActivity extends AppCompatActivity {
    EditText editTextId, editTextName, editTextSoc_sec, editTextSalary;
    String idStr, nameStr, soc_secStr, salaryStr;
    TextView result;

    private Spinner spinnerPosition;
    private LinearLayout additionalFieldsContainer;
    private Map<String, List<String>> additionalFieldsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button buttonAdd = findViewById(R.id.buttonAdd);
        spinnerPosition = findViewById(R.id.spinnerPosition);
        additionalFieldsContainer = findViewById(R.id.additional_fields_container);

        additionalFieldsMap = new HashMap<>();
        additionalFieldsMap.put("Manager", Arrays.asList("deptName"));
        additionalFieldsMap.put("Director", Arrays.asList("deptName", "budget"));
        additionalFieldsMap.put("Engineer", Collections.emptyList());
        additionalFieldsMap.put("Admin", Collections.emptyList());
        additionalFieldsMap.put("Default", Collections.emptyList());

        spinnerPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = parent.getItemAtPosition(position).toString();
                List<String> additionalFields = additionalFieldsMap.getOrDefault(selectedType, Collections.emptyList());

                additionalFieldsContainer.removeAllViews();
                for (String field : additionalFields) {
                    EditText editText = new EditText(MainActivity.this);
                    editText.setHint(field);
                    additionalFieldsContainer.addView(editText);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewNewEmployee();
            }
        });
    }

    private void viewNewEmployee() {
        editTextId = findViewById(R.id.editTextId);
        idStr = editTextId.getText().toString();

        editTextName = findViewById(R.id.editTextName);
        nameStr = editTextName.getText().toString();

        editTextSoc_sec = findViewById(R.id.editTextSoc_sec);
        soc_secStr = editTextSoc_sec.getText().toString();

        editTextSalary = findViewById(R.id.editTextSalary);
        salaryStr = editTextSalary.getText().toString();

        result = findViewById(R.id.textViewResult);

        String position = spinnerPosition.getSelectedItem().toString();

        if (idStr.isEmpty() || nameStr.isEmpty() || soc_secStr.isEmpty() || salaryStr.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, введите все необходимые данные", Toast.LENGTH_SHORT).show();
        }
        else {
            int id = Integer.parseInt(idStr);
            double salary = Double.parseDouble(salaryStr);
            Employee employee;
            String str = "";
            switch (position) {
                case "Manager":
                    employee = new Manager(id, nameStr, soc_secStr, salary, getDeptName());
                    str = "\nDept: " + getDeptName();
                    break;
                case "Director":

                    employee = new Director(id, nameStr, soc_secStr, salary, getDeptName(), getBudget());
                    str = "\nDept: " + getDeptName() + "\nBudget: " + getBudget();
                    break;
                case "Engineer":
                    employee = new Engineer(id, nameStr, soc_secStr, salary);
                    break;
                case "Admin":
                    employee = new Admin(id, nameStr, soc_secStr, salary);
                    break;
                default:
                    Toast.makeText(this, "Пожалуйста, выберите тип сотрудника", Toast.LENGTH_SHORT).show();
                    employee = null;
            }
            result.setText("Employee ID: " + employee.getEmpId() + "\nEmployee Name: " + employee.getName() +
                    "\nEmployee Soc Sec #" + employee.getSsn() + "\nEmployee salary: " + employee.getSalary() + str);


        }
    }
}