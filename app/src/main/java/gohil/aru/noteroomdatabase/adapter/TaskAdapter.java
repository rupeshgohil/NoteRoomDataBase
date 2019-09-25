package gohil.aru.noteroomdatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gohil.aru.noteroomdatabase.R;
import gohil.aru.noteroomdatabase.modal.Note;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder>{
    Context mContext;
    public List<Note> mArray;
    View mView;
    public TaskAdapter(Context con,List<Note> notearray){
        this.mContext = con;
        this.mArray =notearray;
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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,mView);
        }

        public void bind(int position) {
            Note note = mArray.get(position);
            tvname.setText("Name"+" : "+note.getFirstname()+" "+note.getLastname());
            tvage.setText("Age"+" : "+note.getAge()+"   "+"Gender"+" : "+note.getGender());
            tvcity.setText("City"+" "+note.getCity());
            tvidentity.setText("Identity"+" : "+note.getIdentity());
            tvmarriedstatus.setText("Married Status"+" : "+note.getMarriedStatus());
            tvmobile.setText("Mobile"+" : "+note.getMobileNumber());
        }
    }
    @NonNull
    @Override
    public TaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_list_item, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }
}
