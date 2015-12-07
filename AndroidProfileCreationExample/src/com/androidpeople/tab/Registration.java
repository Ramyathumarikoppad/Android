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
import android.widget.Spinner;
import android.widget.Toast;

public class Registration extends Activity {
	static final int READ_BLOCK_SIZE = 100;

	private String date;
    private Spinner RegCentreView;
    private Spinner sponsorNameView;
    private String regCentre;
    private String sponsorName;
    private EditText eventNameView;
    private String eventName;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);
	}

	@Override
	protected void onPause() {
		super.onPause();
		getRegistrationValues();
		JSONObject obj = getRegistrationJsonObj();
		WriteBtn(obj.toString());
		ReadBtn();
	};

	public void getRegistrationValues() {
        RegCentreView= (Spinner) findViewById(R.id.RegCentreView);
        regCentre = RegCentreView.getSelectedItem().toString();
        sponsorNameView= (Spinner) findViewById(R.id.sponsorNameView);
        sponsorName = sponsorNameView.getSelectedItem().toString();
        eventNameView= (EditText) findViewById(R.id.eventNameView);
        eventName = eventNameView.getText().toString();

    }

	public JSONObject getRegistrationJsonObj() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("Registration Date", date);
            obj.put("Registration Centre", regCentre);
            obj.put("Sponsor Name", sponsorName);
            obj.put("Event Name", eventName);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;

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
			date = day + "-" + month + "-" + year;
			// Do something with the date chosen by the user
		}
	}

	// write text to file
	public void WriteBtn(String obj) {
		// add-write text into file
		try {
			FileOutputStream fileout = openFileOutput("firstTab.txt",
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
			FileInputStream fileIn = openFileInput("firstTab.txt");
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