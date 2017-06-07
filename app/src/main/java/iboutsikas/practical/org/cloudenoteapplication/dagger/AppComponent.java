package iboutsikas.practical.org.cloudenoteapplication.dagger;

import dagger.Component;
import iboutsikas.practical.org.cloudenoteapplication.activities.MainActivity;
import iboutsikas.practical.org.cloudenoteapplication.activities.NewNoteActivity;
import iboutsikas.practical.org.cloudenoteapplication.activities.NoteDetailsActivity;

import javax.inject.Singleton;

/**
 * Created by Zinadore on 6/3/2017.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(MainActivity activity);
    void inject(NewNoteActivity activity);
    void inject(NoteDetailsActivity activity);
}
