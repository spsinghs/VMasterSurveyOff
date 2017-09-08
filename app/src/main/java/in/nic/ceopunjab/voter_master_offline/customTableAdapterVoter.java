package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 05/15/2017.
 */

public class customTableAdapterVoter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private ArrayList<String> mData = new ArrayList<>();
    private static LayoutInflater inflater=null;

    //public ImageLoader imageLoader;

    public customTableAdapterVoter(Activity a,String[] st)
    {
        activity = a;

        //data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    @Override

    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        if(convertView==null)

            vi = inflater.inflate(R.layout.tabvoter, null);

        TextView epic = (TextView)vi.findViewById(R.id.VEpic);
        TextView name = (TextView)vi.findViewById(R.id.VName);
        TextView fname = (TextView)vi.findViewById(R.id.VFname);
        TextView hno = (TextView)vi.findViewById(R.id.VHno);

        EditText dob = (EditText)vi.findViewById(R.id.VDob);
        EditText cast = (EditText)vi.findViewById(R.id.VCast);
        EditText mobile = (EditText)vi.findViewById(R.id.Vmobile);
        EditText dom = (EditText)vi.findViewById(R.id.VDom);

        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        // Setting all values in listview

      //ListView listv=(ListView)this.activity.findViewById(R.id.lvElector);
        // lv.setAdapter(Tadapter);

        String[] str= CallSerch.stringBuilder.toString().split("\n");

       /* "EPIC:  "+ obj3.getProperty("EPIC").toString() +"\n"+
                "Voter Name:  "+ obj3.getProperty("VoterName").toString() +"\n"+
                "Rel Name:   "+ obj3.getProperty("FH_Name").toString() +"\n"+
                "HOUSE NO:  "+ obj3.getProperty("HOUSE_NO").toString() +"\n"+
                "DOB:  "+ obj3.getProperty("DOB").toString() +"\n"+
                "VCast:   "+ obj3.getProperty("Vcast").toString() +"\n"+
                "VMobile:  "+ obj3.getProperty("VMobile").toString() +"\n"+
                "VType:  "+*/


        epic.setText(str[0]);
        name.setText(str[1]);
        fname.setText(str[2]);
        hno.setText(str[3]);

        dob.setText(str[4]);
        cast.setText(str[5]);
        mobile.setText(str[6]);
        dom.setText(str[7]);
       // artist.setText(song.get(CustomizedListView.KEY_ARTIST));
        //duration.setText(song.get(CustomizedListView.KEY_DURATION));
        //imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
        return vi;
    }
}
