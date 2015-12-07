package com.androidpeople.tab;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;

public class Personal extends Activity {
	/** Called when the activity is first created. */
	
	static final int READ_BLOCK_SIZE = 100;
	private String date;
	private EditText regIdET;
	private String regId;

	private EditText fNameET;
	private String fName;

	private EditText lNameET;
	private String lName;

	private RadioGroup roleRG;
	private EditText roleET;
	private String role;

	private RadioGroup maritalStatusRG;
	private RadioButton maritalStatusRB;
	private String maritalStatus;

	private EditText pNoET;
	private String pNo;

	private RadioGroup insuranceRGr;
	private RadioButton insuranceRB;
	private String insurance;

	private Spinner qualificationS;
	private String qualification;

	private RadioGroup languageRG;
	private EditText langET;
	private String lang;

	private EditText cityET;
	private String city;

	private EditText stateET;
	private String state;

	private EditText dependentMembersET;
	private String dependentMembers;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal);
		getPersonalValues();
	
		
		
	}
	
	@Override
	public void onPause() {
		super.onPause();
		getPersonalValues();
		JSONObject obj = getPersonalJsonObj();
		WriteBtn(obj.toString());
		ReadBtn();
	}

	
	public JSONObject getPersonalJsonObj() {
		JSONObject obj = new JSONObject();
			try {
				obj.put("Registration Id", regId);
				obj.put("First Name", fName);
				obj.put("Last Name", lName);
				obj.put("Role", role);
				obj.put("Date of Birth", date);
				obj.put("Marital Status", maritalStatus);
				obj.put("Phone Number", pNo);
				obj.put("Has Insurance?", insurance);
				obj.put("Qualification", qualification);
				obj.put("Languages Known", lang);
				obj.put("Address : City/Village", city);
				obj.put("Address : State", state);
				obj.put("How many dependent Family Members", dependentMembers);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return obj;
		}
	private void getPersonalValues() {
		// TODO Auto-generated method stub
		regIdET= (EditText) findViewById(R.id.regIdET);
		regId = regIdET.getText().toString();

		fNameET = (EditText) findViewById(R.id.fNameET);
		fName = fNameET.getText().toString();

		lNameET = (EditText) findViewById(R.id.lNameET);
		lName = lNameET.getText().toString();

		roleRG= (RadioGroup) findViewById(R.id.roleRG);
		roleET= (EditText) findViewById(R.id.roleET);
		roleET.setVisibility(View.INVISIBLE);
		roleRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.radio2) {
					roleET.setVisibility(View.VISIBLE);
					role = roleET.getText().toString();
				} else if ((checkedId == R.id.radio0)
						|| (checkedId == R.id.radio1)) {
					roleET.setVisibility(View.INVISIBLE);
					role = "Driver";
				} else {
					roleET.setVisibility(View.INVISIBLE);
					role = "Cleaner";
				}
			}
		});

		maritalStatusRG= (RadioGroup) findViewById(R.id.maritalStatusRG);
		int selectedId1 = maritalStatusRG.getCheckedRadioButtonId();
		maritalStatusRB= (RadioButton) findViewById(selectedId1);
		maritalStatus=maritalStatusRB.getText().toString();

		pNoET = (EditText) findViewById(R.id.pNoET);
		pNo = pNoET.getText().toString();

		insuranceRGr = (RadioGroup) findViewById(R.id.insuranceRGr);
		int selectedId2 = insuranceRGr.getCheckedRadioButtonId();
		insuranceRB = (RadioButton) findViewById(selectedId2);
		insurance=insuranceRB.getText().toString();

		qualificationS = (Spinner) findViewById(R.id.qualificationS);
		qualification = qualificationS.getSelectedItem().toString();

		languageRG = (RadioGroup) findViewById(R.id.languageRG);
		langET = (EditText) findViewById(R.id.langET);
		langET.setVisibility(View.INVISIBLE);
		languageRG.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == R.id.radio9) {
					langET.setVisibility(View.VISIBLE);
					role = langET.getText().toString();
				} else if ((checkedId == R.id.radio7)
						|| (checkedId == R.id.radio8)) {
					langET.setVisibility(View.INVISIBLE);
					lang = "Hindi";
				} else {
					lang = "English";
				}
			}
		});


		cityET = (EditText) findViewById(R.id.cityET);
		city = cityET.getText().toString();

		stateET = (EditText) findViewById(R.id.stateET);
		state = stateET.getText().toString();

		dependentMembersET = (EditText) findViewById(R.id.dependentMembersET);
		dependentMembers = dependentMembersET.getText().toString();

	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}

	private class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			date = day + "-" + month + "-" + year;
		}
	}
	
	// write text to file
		public void WriteBtn(String obj) {
			// add-write text into file
			try {
				FileOutputStream fileout = openFileOutput("secondTab.txt",
						MODE_PRIVATE);
				OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
				outputWriter.write(obj);
				outputWriter.close();

				// display file saved message
				Toast.makeText(getBaseContext(), "File saved successfully!",
						Toast.LENGTH_SHORT).show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Read text from file
		public void ReadBtn() {
			// reading text from file
			try {
				FileInputStream fileIn = openFileInput("secondTab.txt");
				InputStreamReader InputRead = new InputStreamReader(fileIn);

				char[] inputBuffer = new char[READ_BLOCK_SIZE];
				String s = "";
				int charRead;

				while ((charRead = InputRead.read(inputBuffer)) > 0) {
					// char to string conversion
					String readstring = String
							.copyValueOf(inputBuffer, 0, charRead);
					s += readstring;
				}
				InputRead.close();
				System.out.println(s + "json obj while storing");
				Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
