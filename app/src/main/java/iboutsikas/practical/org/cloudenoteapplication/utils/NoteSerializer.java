package iboutsikas.practical.org.cloudenoteapplication.utils;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import iboutsikas.practical.org.cloudenoteapplication.models.Note;

import java.lang.reflect.Type;


/**
 * Created by zinadore on 03/06/17.
 */

public class NoteSerializer implements JsonSerializer<Note> {
    @Override
    public JsonElement serialize(Note note, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonNote = new JsonObject();

        jsonNote.addProperty("content", note.Content());
        jsonNote.addProperty("date", String.valueOf(note.Date().getTime()));

        return jsonNote;
    }
}
