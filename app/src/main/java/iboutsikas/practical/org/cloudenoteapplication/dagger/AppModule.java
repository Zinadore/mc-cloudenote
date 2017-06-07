package iboutsikas.practical.org.cloudenoteapplication.dagger;

import android.app.Application;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import iboutsikas.practical.org.cloudenoteapplication.contracts.INotesApi;
import iboutsikas.practical.org.cloudenoteapplication.contracts.IRepository;
import iboutsikas.practical.org.cloudenoteapplication.models.Note;
import iboutsikas.practical.org.cloudenoteapplication.repositories.NoteRepository;
import iboutsikas.practical.org.cloudenoteapplication.utils.NoteDeserializer;
import iboutsikas.practical.org.cloudenoteapplication.utils.NoteSerializer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

/**
 * Created by Zinadore on 6/3/2017.
 */

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return application;
    }

    @Provides
    @Singleton
    IRepository<Note> provideNoteRepository(INotesApi notesApi) {
        return new NoteRepository(notesApi);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(INotesApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    Gson provideGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Note.class, new NoteSerializer());
        gsonBuilder.registerTypeAdapter(Note.class, new NoteDeserializer());
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    INotesApi provideNotesApi(Retrofit retrofit){
        return retrofit.create(INotesApi.class);
    }

}
