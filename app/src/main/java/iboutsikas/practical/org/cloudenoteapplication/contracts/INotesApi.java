package iboutsikas.practical.org.cloudenoteapplication.contracts;

import iboutsikas.practical.org.cloudenoteapplication.models.Note;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by Zinadore on 6/3/2017.
 */

public interface INotesApi {
    static final String BASE_URL = "http://notes-sdmdcity.rhcloud.com/rest/";
    static final String NOTES_URL = "notes";

    @GET(NOTES_URL)
    Call<List<Note>> getAll();

    @POST(NOTES_URL)
    Call<Void> create(@Body Note note);

    @DELETE(NOTES_URL + "/{id}")
    Call<Void> delete(@Path("id") int id);
}
