package in.nic.ceopunjab.voter_master_offline;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 05/12/2017.
 */




class customTableAdapter extends BaseAdapter implements View.OnClickListener {

    /*********** Declare Used Variables *********/

    private ArrayList data;
    private static LayoutInflater inflater=null;
    //public Resources res;

    int i=0;

    /*************  CustomAdapter Constructor *****************/
    customTableAdapter(Activity a, ArrayList d) {

        Activity activity;
        /********** Take passed values **********/
        activity = a;
        data=d;
        //res = resLocal;

        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {

        if(data.size()<=0)
            return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{

        public TextView Sno;
        public TextView Epic;
        public TextView Name;
        public TextView Fname;
        public TextView Hno;
        public TextView RelTy;
        public TextView dob;
        public TextView dom;
        public TextView mobile;
        public TextView cast;
        public TextView TRelg;
        public TextView THabit;
        public TextView Tvtype;
        public TextView Teco;
        public TextView Torg;
        public TextView TRem;
        public TextView Tprof;
       // public ImageView image;

    }

    /****** Depends upon data size called for each row , Create each ListView row *****/
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){

            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.tabitem, null);

            /****** View Holder Object to contain tabitem.xml file elements ******/

            holder = new ViewHolder();
            holder.Sno = (TextView) vi.findViewById(R.id.textSno);
            holder.Epic = (TextView) vi.findViewById(R.id.textEpice);
            holder.Name=(TextView)vi.findViewById(R.id.textName);
            holder.Fname=(TextView)vi.findViewById(R.id.textFname);
            holder.Hno=(TextView)vi.findViewById(R.id.textHno);
            holder.RelTy=(TextView)vi.findViewById(R.id.textRelType);
            holder.dob=(TextView)vi.findViewById(R.id.textDOB);
            holder.dom=(TextView)vi.findViewById(R.id.textDOM);
            holder.mobile=(TextView)vi.findViewById(R.id.textMobile);
            holder.cast=(TextView)vi.findViewById(R.id.textCast);
            holder.TRelg=(TextView)vi.findViewById(R.id.textRelg);
            holder.THabit=(TextView)vi.findViewById(R.id.textHabit);
            holder.Tvtype=(TextView)vi.findViewById(R.id.textVtype);
            holder.Teco=(TextView)vi.findViewById(R.id.textEco);
            holder.Torg=(TextView)vi.findViewById(R.id.textOrg);
            holder.TRem=(TextView)vi.findViewById(R.id.textRem);
            holder.Tprof=(TextView)vi.findViewById(R.id.textProf);

            //holder.image=(ImageView) vi.findViewById(R.id.imageView);


            //holder.image=(ImageView)vi.findViewById(R.id.image);

            /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();

        if(data.size()<=0)
        {
            holder.Epic.setText("No Data");

        }
        else
        {
            ListData tempValues;
            /***** Get each Model object from Arraylist ********/
            tempValues=null;
            tempValues = ( ListData ) data.get( position );

            /************  Set Model values in Holder elements ***********/
            holder.Sno.setText(tempValues.getField1());
            holder.Epic.setText(tempValues.getField2());
            holder.Name.setText( tempValues.getField3() );
            holder.Fname.setText( tempValues.getField4() );
            holder.Hno.setText( tempValues.getField5() );
            holder.RelTy.setText( tempValues.getField6() );
            holder.dob.setText( tempValues.getDob() );
            holder.dom.setText( tempValues.getDom() );
            holder.mobile.setText( tempValues.getMobt() );
            holder.cast.setText( tempValues.getCast() );

            holder.TRelg.setText( tempValues.getRelg() );
            holder.THabit.setText( tempValues.getHabit() );
            holder.Tvtype.setText( tempValues.getvType() );
            holder.Teco.setText( tempValues.geteco() );
            holder.Torg.setText( tempValues.getOrg() );
            holder.TRem.setText( tempValues.getRem() );

            holder.Tprof.setText( tempValues.getProf() );

            //holder.image.setI

            /******** Set Item Click Listner for LayoutInflater for each row *******/
            //vi.setOnClickListener(new OnItemClickListener( position ));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {
        Log.v("CustomAdapter", "=====Row button clicked=====");
    }

}
