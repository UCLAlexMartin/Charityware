package env.Entities;

import java.util.List;

import com.webviewprototype.R;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class FormListAdapter extends ArrayAdapter<AndroidField> {

	
	 private Context mContext;
	 private AndroidField[] fields;
	 private int id;

	public FormListAdapter(Context c, AndroidField[] fs, int pos) {
		super(c, pos, fs);
			this.mContext =c;
			this.fields =fs;
			this.id = pos;
	}

	public Context getmContext() {
		return mContext;
	}

	public void setmContext(Context mContext) {
		this.mContext = mContext;
	}

	public AndroidField[] getFields() {
		return fields;
	}

	public void setFields(AndroidField[] fields) {
		this.fields = fields;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        FieldHolder holder =null;
        // inflate new layout if null
                if(v == null) {
                    LayoutInflater inflater = LayoutInflater.from(mContext);
                    v = inflater.inflate(R.layout.listview_layout_form, null);
                    holder = new FieldHolder();
                    holder.textView=(TextView) v.findViewById(R.id.label);
                    holder.edit=(EditText) v.findViewById(R.id.fname);
                    v.setTag(holder);
                }else{
                	holder= (FieldHolder) v.getTag();
                }

             // load controls from layout resources
                
		AndroidField an = fields[position];
		holder.textView.setText(an.getLabel());
		holder.edit.setText(an.getText());
		
		
		return v;
	}
	
	
	static class FieldHolder {
		
		TextView textView;
		EditText edit;
		
	}

}
