package iboutsikas.practical.org.cloudenoteapplication.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import iboutsikas.practical.org.cloudenoteapplication.models.Note;

import java.lang.reflect.Type;
import java.util.Date;


/**
 * Created by zinadore on 03/06/17.
 */

public class NoteDeserializer implements JsonDeserializer<Note> {
    @Override
    public Note deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jObject = json.getAsJsonObject();

        JsonElement element;
        int id;
        String content;
        Date date;

        element = jObject.get("id");
        if (element != null){
             id = element.getAsInt();
        }
        else {
            throw new JsonParseException("JSON had no ID");
        }

        element = jObject.get("content");
        if (element != null) {
            content = element.getAsString();
        }
        else {
            throw new JsonParseException("JSON had no content");
        }

        element = jObject.get("date");
        if (element != null) {
            String dateString = element.getAsString();
            long dateLong = Long.parseLong(dateString);
           date = new Date(dateLong);
        } else {
            throw new JsonParseException("JSON had no date");
        }
        return new Note(id, content, date);
    }
}
