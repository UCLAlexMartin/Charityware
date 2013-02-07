package com.webviewprototype;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.webviewprototype.facade.impl.DBManager;

import env.Entities.AndroidField;
import env.Entities.DataBean;
import env.Entities.FilledForm;
import env.Entities.Form;
import env.Entities.FormFields;
import env.Entities.FormListAdapter;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Note;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.text.Layout;

@SuppressLint("NewApi")
public class FormActivity extends ListActivity {

	FilledForm filledForm = new FilledForm();
	Form form = new Form();
	DBManager manager;
	Integer userid;
	String username;
	LinearLayout flayout;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		DataBean bean = DataBean.getDataBean();
		manager = bean.getManager();		
		form = bean.getForm();
//		filledForm = bean.getFform();
		Intent intent = getIntent();
		userid = intent.getExtras().getInt("Userid");
		username = intent.getExtras().getString("Username");
		flayout = (LinearLayout) findViewById(R.layout.activity_form);
		ListView listView = (ListView) findViewById(android.R.id.list);
		AndroidField[] fields = new AndroidField[3];
		AndroidField field = new AndroidField();
		field.setLabel("Name");
		field.setText("Beleq");
		fields[0] = field;	
		AndroidField field2 = new AndroidField();
		field2.setLabel("Surname");
		field2.setText("Uthizaar");
		fields[1 ] = field2;
		AndroidField field3 = new AndroidField();
		field3.setLabel("Location");
		field3.setText("Prospero");
		fields[2] = field3;
		
		FormListAdapter formAdapter = new FormListAdapter(this, fields, R.layout.listview_layout_form);
		listView.setAdapter(formAdapter);
		TextView header = (TextView) findViewById(R.id.fname);
		header.setText(form.getFormName());
		
		//  creation of UI components here
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_form, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onStop() {
	    super.onStop(); 
	    ContentValues values = new ContentValues();
	    this.form = null;
	    this.filledForm=null;
//	    if (!form.isEmpty()){
//	    	Set<String> labels = form.keySet();
//	    	List<String> vls = (List<String>) form.values();
//	    	Iterator<String> it = labels.iterator();
//	    	for ( int i=0;i<labels.size();i++){
//	    		values.put(it.next(), vls.get(i));
//	    	}
//	    }
	   /* getContentResolver().update(
	            mUri,    // The URI for the note to update.
	            values,  // The map of column names and new values to apply to them.
	            null,    // No SELECT criteria are used.
	            null     // No WHERE columns are used.
	            );*/
	}
	
	@Override 
	public void onRestart() {
		super.onRestart();
	}
	
	
	
	private void saveToSqlLite(View view) {
//			manager.createFilledForm(filledForm.getFilled_form_id(), form.getFormId(), Integer.parseInt(nrs.get(i).getText().toString()), editTexts.get(i).getText().toString(), userid, filledForm.getRecord_id(), true);
		
	
	}
	
	
	private void saveToSqlLite() {
		
	}

}
