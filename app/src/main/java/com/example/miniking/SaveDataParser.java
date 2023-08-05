package com.example.miniking;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

public class SaveDataParser {
    private static final String TAG = "SaveDataParser";
    private Context context;
    private boolean validity;
    private ResourceKeeper res;
    private Questions q;
    private FileWriter fileWriter;
    private FileReader fileReader;

    public SaveDataParser(Context context, String seed, ResourceKeeper res, Questions q) {
        //read and write to a pre-existing save
        this.context = context;
        this.res = res;
        this.q = q;
        File directory = new File(context.getFilesDir(), "saves");
        if(!directory.exists()) {
            directory.mkdir();
        }
        File save = new File(directory, seed + ".json");
        try {
            fileWriter = new FileWriter(save);
            fileReader = new FileReader(save);
            validity = true;
        } catch (IOException e) {
            validity = false;
            Log.e(TAG, String.valueOf(e));
            throw new RuntimeException(e);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void saveGame() {
        //save date to json file
        try {
            JSONObject saveJSON = new JSONObject();
            saveJSON.append("index", q.getIndex());
            saveJSON.append("time", res.getTime());
            saveJSON.append("order", res.getOrder());
            saveJSON.append("food", res.getFood());
            saveJSON.append("gold", res.getGold());
            saveJSON.append("might", res.getMight());
            saveJSON.append("seed", res.getSeed());
            saveJSON.append("religionFlag", q.getReligionFlag());
            saveJSON.append("magicFlag", q.getMagicFlag());
            saveJSON.append("plagueFlag", q.getPlagueFlag());

            fileWriter.write(saveJSON.toString());
            fileWriter.flush();
        } catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
            throw new RuntimeException(e);
        } catch (JSONException e) {
            Log.e(TAG, String.valueOf(e));
            throw new RuntimeException(e);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void openGame() {
        //get data from json file
        try {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONObject saveJSON = (JSONObject) new JSONTokener(stringBuilder.toString()).nextValue();
            System.out.println(saveJSON);
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, String.valueOf(e));
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            Log.e(TAG, String.valueOf(e));
            throw new RuntimeException(e);
        }
        catch (Exception e) {
            Log.e(TAG, String.valueOf(e));
            throw new RuntimeException(e);
        }
    }

    public boolean getValidity() {
        return validity;
    }
}
