package iboutsikas.practical.org.cloudenoteapplication;

import android.content.ClipData;
import android.content.Context;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import iboutsikas.practical.org.cloudenoteapplication.models.Note;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Zinadore on 6/3/2017.
 */

public abstract class ReactiveListAdapter<T> extends BaseAdapter {

    private List<T> mItemsInSource;
    private Disposable mSub;
    protected Context mContext;

    public ReactiveListAdapter(Context context, Observable<List<T>> source) {
        super();
        this.mContext = context;
        this.mSub = source
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes -> {
                    this.mItemsInSource = notes;
                    this.notifyDataSetChanged();
                });
    }

    @Override
    public int getCount() {
        if(this.mItemsInSource == null){
            return 0;
        }
        return this.mItemsInSource.size();
    }

    @Override
    public T getItem(int position) {
        if (this.mItemsInSource != null)
            return this.mItemsInSource.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void cleanUp() {
     this.mSub.dispose();
    }
}
