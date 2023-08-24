package com.example.miniking;


import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class SaveDataParser {
    private static final String TAG = "SaveDataParser";
    private final boolean validity;
    private final ResourceKeeper res;
    private final Questions q;
    private final File save;

    public SaveDataParser(Context context, ResourceKeeper res, Questions q, String saveSlot) {
        //read and write to a pre-existing save
        this.res = res;
        this.q = q;
        File directory = new File(context.getFilesDir(), "saves");
        if(!directory.exists()) {
            directory.mkdir();
        }
        save = new File(directory, saveSlot + ".json");
        validity = save.exists();

        Log.e(TAG, "Validity:" + validity + save);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void saveGame() {
        //save date to json file

        try {
            FileWriter fileWriter = new FileWriter(save);
            JSONObject saveJSON = new JSONObject();
            saveJSON.put("index", q.getIndex());
            saveJSON.put("time", res.getTime());
            saveJSON.put("order", res.getOrder());
            saveJSON.put("food", res.getFood());
            saveJSON.put("gold", res.getGold());
            saveJSON.put("might", res.getMight());
            saveJSON.put("seed", res.getSeed());
            saveJSON.put("religionFlag", q.getReligionFlag());
            saveJSON.put("magicFlag", q.getMagicFlag());
            saveJSON.put("plagueFlag", q.getPlagueFlag());
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
            Instant instant = Instant.now();
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
            String date = zdt.format(formatter.withLocale(Locale.CANADA));
            Log.v(TAG, date);
            saveJSON.put( "date", date);

            fileWriter.write(saveJSON.toString());
            fileWriter.flush();
        } catch (IOException | JSONException e) {
            Log.e(TAG, String.valueOf(e));
            throw new RuntimeException(e);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public JSONObject getJSON() {
        //get JSON file
        JSONObject saveJSON = null;
        if(validity){
            try {
                FileReader fileReader = new FileReader(save);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                if(stringBuilder.length() > 0)
                    saveJSON = (JSONObject) new JSONTokener(stringBuilder.toString()).nextValue();
            } catch (Exception e) {
                Log.e(TAG, String.valueOf(e));
                throw new RuntimeException(e);
            }
        }
        return saveJSON;
    }

    public boolean getValidity() {
        return validity;
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public String getDate() {
        String output = "Empty";
        JSONObject saveJSON = getJSON();

        if(saveJSON != null) {
            output = saveJSON.optString("date");
        }

        return output;
    }
}
