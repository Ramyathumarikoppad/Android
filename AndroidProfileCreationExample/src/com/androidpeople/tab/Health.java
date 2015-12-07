package com.androidpeople.tab;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Health extends Activity {
	
	static final int READ_BLOCK_SIZE = 100;
	
	private EditText heightET;
	private String height;

	private EditText weightET;
	private String weight;

	private EditText pulseWaveSymET;
	private String pulseWaveSym;

	private EditText pulseWaveDiaET;
	private String pulseWaveDia;

	private EditText pulseWavePRAET;
	private String pulseWavePRA;

	private EditText pulseWavePRVET;
	private String pulseWavePRV;

	private EditText avgBreathingRateET;
	private String avgBreathingRate;

	private EditText anomolyET;
	private String anomoly;

	private EditText oximeterET;
	private String oximeter;

	private EditText bloodSugerET;
	private String bloodSuger;

	private EditText bloodGroupET;
	private String bloodGroup;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.health);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		getHealthValues();
		JSONObject obj = getHealthJsonObj();
		WriteBtn(obj.toString());
		ReadBtn();
		
	}

	public JSONObject getHealthJsonObj() {
		JSONObject obj = new JSONObject();
				try {
					obj.put("Height", height);
					obj.put("Weight", weight);
					obj.put("PulseWave Symbolic(mmHg)", pulseWaveSym);
					obj.put("PulseWave Diastolic(mmHg)", pulseWaveDia);
					obj.put("PulseWave PRA(/min)", pulseWavePRA);
					obj.put("PulseWave PRV(%)", pulseWavePRV);
					obj.put("Average Breathing Rate(/min)", avgBreathingRate);
					obj.put("Total Anomoly Score(%)", anomoly);
					obj.put("Oximeter Oxygen Saturator SPO2", oximeter);
					obj.put("Blood Suger(mg/dL)", bloodSuger);
					obj.put("Blood Group", bloodGroup);


				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return obj;

		}

public void getHealthValues(){
		heightET = (EditText) findViewById(R.id.heightET);
				height = heightET.getText().toString();

				weightET = (EditText) findViewById(R.id.weightET);
				weight = weightET.getText().toString();

				pulseWaveSymET = (EditText) findViewById(R.id.pulseWaveSymET);
				pulseWaveSym = pulseWaveSymET.getText().toString();

				pulseWaveDiaET = (EditText) findViewById(R.id.pulseWaveDiaET);
				pulseWaveDia = pulseWaveDiaET.getText().toString();

				pulseWavePRAET = (EditText) findViewById(R.id.pulseWavePRAET);
				pulseWavePRA = pulseWavePRAET.getText().toString();

				pulseWavePRVET = (EditText) findViewById(R.id.pulseWavePRVET);
				pulseWavePRV = pulseWavePRVET.getText().toString();

				avgBreathingRateET = (EditText) findViewById(R.id.avgBreathingRateET);
				avgBreathingRate = avgBreathingRateET.getText().toString();

				anomolyET = (EditText) findViewById(R.id.anomolyET);
				anomoly = anomolyET.getText().toString();

				oximeterET= (EditText) findViewById(R.id.oximeterET);
				oximeter = oximeterET.getText().toString();

				bloodSugerET = (EditText) findViewById(R.id.bloodSugerET);
				bloodSuger = bloodSugerET.getText().toString();

				bloodGroupET = (EditText) findViewById(R.id.bloodGroupET);
				bloodGroup = bloodGroupET.getText().toString();
				}
	// write text to file
		public void WriteBtn(String obj) {
			// add-write text into file
			try {
				FileOutputStream fileout = openFileOutput("fourthTab.txt",
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
				FileInputStream fileIn = openFileInput("fourthTab.txt");
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
