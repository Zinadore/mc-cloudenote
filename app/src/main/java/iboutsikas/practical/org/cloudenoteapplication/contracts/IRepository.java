package iboutsikas.practical.org.cloudenoteapplication.contracts;

import android.content.Context;
import io.reactivex.Observable;

import java.util.List;

/**
 * Created by Zinadore on 6/2/2017.
 */

public interface IRepository<T> {
    public Observable<List<T>> Items();

    public List<T> GetAll();

    public T Get(int id);

    public void FetchAll(Context context);

    public void Create(Context context, T newEntity);

    public void Delete(Context context, int id);
}
