package gohil.aru.noteroomdatabase.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gohil.aru.noteroomdatabase.R;
import gohil.aru.noteroomdatabase.modal.Note;
import gohil.aru.noteroomdatabase.ui.MainActivity;
public class TaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    public List<Note> mArray;
    View mView,mHeaderview;
    MainActivity multiClickListener;
    public boolean isFilter = false;
    public int VIEW_TYPE_HEADER = 0;
    public int VIEW_TYPE_LIST = 0;
     ArrayList<String> mArrayidentity =new ArrayList<>();
    public TaskAdapter(Context con,List<Note> notearray,boolean filter){
        this.mContext = con;
        this.mArray =notearray;
        this.isFilter = filter;
        this.multiClickListener = (MainActivity)con;
        for(Note t: mArray){
            if(!mArrayidentity.contains(t.getIdentity())){
                    mArrayidentity.add(t.getIdentity());
            }
        }
        Log.e("aaaaaa",new Gson().toJson(mArrayidentity));

    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvname)
        TextView tvname;
        @BindView(R.id.tvage)
        TextView tvage;
        @BindView(R.id.tvcity)
        TextView tvcity;
        @BindView(R.id.tvidentity)
        TextView tvidentity;
        @BindView(R.id.tvmarriedstatus)
        TextView tvmarriedstatus;
        @BindView(R.id.tvmobile)
        TextView tvmobile;
        @BindView(R.id.tvedit)
        TextView tvedit;
        @BindView(R.id.tvdelete)
        TextView tvdelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,mView);
        }

        public void bind(int position) {
            Note note = mArray.get(position);

        }
    }
    public class MyHaderViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvheader)
        TextView tvheader;
        public MyHaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(!isFilter){
            mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
            return new MyViewHolder(mView);
        }else{
            if(viewType == VIEW_TYPE_HEADER){
                mHeaderview = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_header, parent, false);
                return new MyHaderViewHolder(mHeaderview);
            }else if(viewType == VIEW_TYPE_LIST){
                mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
                return new MyViewHolder(mView);
            }else{
                return null;
            }

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Note note = mArray.get(position);
        if(holder instanceof MyHaderViewHolder){
            ((MyHaderViewHolder)holder).tvheader.setText(note.getIdentity());
        }else if(holder instanceof MyViewHolder){
            ((MyViewHolder)holder).tvname.setText("Name"+" : "+note.getFirstname()+" "+note.getLastname());
            ((MyViewHolder)holder).tvage.setText("Age"+" : "+note.getAge()+"   "+"Gender"+" : "+note.getGender());
            ((MyViewHolder)holder).tvcity.setText("City"+" "+note.getCity());
            ((MyViewHolder)holder).tvidentity.setText("Identity"+" : "+note.getIdentity());
            ((MyViewHolder)holder).tvmarriedstatus.setText("Married Status"+" : "+note.getMarriedStatus());
            ((MyViewHolder)holder).tvmobile.setText("Mobile"+" : "+note.getMobileNumber());
            ((MyViewHolder)holder).tvedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    multiClickListener.onMultiClick(0, note,"edit");
                }
            });
            ((MyViewHolder)holder).tvdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    multiClickListener.onMultiClick(0, note,"del");
                }
            });
        }
    }
    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        boolean is =true;
        /*if(isFilter){

        }else{
            return VIEW_TYPE_LIST = 2;
        }*/
        return VIEW_TYPE_LIST = 2;

    }
    @Override
    public int getItemCount() {
        return mArray.size();
    }
}
