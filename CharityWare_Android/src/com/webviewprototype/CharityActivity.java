package com.webviewprototype;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint; 
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.webviewprototype.facade.NetworkServiceFacade;
import com.webviewprototype.facade.RestServiceFacade;
import com.webviewprototype.facade.impl.DBManager;
import com.webviewprototype.facade.impl.NetworkFacadeImpl;
import com.webviewprototype.facade.impl.RestServiceFacadeImpl;
import com.webviewprototype.service.test.TestActivity;

import env.Entities.DataBean;
import env.Entities.FieldType;
import env.Entities.Form;
import env.Entities.FormFields;
import env.Entities.FormType;

import android.net.NetworkInfo;

public class CharityActivity extends ListActivity {
	
	
	NetworkServiceFacade networkFacade = new NetworkFacadeImpl();
	static List<String> FORM_TITLES = new LinkedList<String>();
	LinearLayout layout ;
	ArrayList<Form> forms;
	NetworkPollTask task;
	static String name; 
	 DBManager manager;
	 Integer userid;
	 DataBean bean= DataBean.getDataBean();
	 PopupWindow popUp;
	 LinearLayout popUpLayout;
	    LayoutParams params;

	 
	@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FORM_TITLES = new LinkedList<String>();
		forms = new ArrayList<Form>();
		this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FORM_TITLES));
		setContentView(R.layout.activity_charity);
	if (!bean.getCreated())	{
		manager = new DBManager(this);
		bean =DataBean.getDataBean();
		bean.setManager(manager);
		bean.setCreated(true);
	}
		// Show the Up button in the action bar.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1){
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		Intent intent = getIntent();
		name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	
		configureListTitles();
		}
	
	private void configureListTitles() {
		ListView listView = (ListView) findViewById(android.R.id.list);
		layout= (LinearLayout) findViewById(R.layout.activity_charity);
		listView.setTextFilterEnabled(true);
		ConnectivityManager connectivityManager 
        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
			if (networkFacade.hasActiveInternetConnection(activeNetworkInfo,this)){
				RestServiceFacade RestServiceFacade = new RestServiceFacadeImpl();
				Map<Integer,Map<Integer,List<String>>> form_map = RestServiceFacade.getFormEntities(name);
				Iterator<Entry<Integer,Map<Integer,List<String>>>> results_iterator = form_map.entrySet().iterator();
				while(results_iterator.hasNext()){
					Entry<Integer,Map<Integer,List<String>>> entry = results_iterator.next();
					Map<Integer,List<String>> values_map = (Map<Integer, List<String>>) entry.getValue().keySet();
					Iterator<Entry<Integer,List<String>>> values_map_iter = values_map.entrySet().iterator();
					Entry<Integer,List<String>> values_map_entry = values_map_iter.next();
					List<String> string_values = values_map_entry.getValue();
					FORM_TITLES.add(string_values.get(0));
					Form form_tmp = new Form();
					form_tmp.setFormName(FORM_TITLES.get(0));
					forms.add(form_tmp);
					
				}
				/*if (forms!=null) {
					for (Form f:forms) {
							FORM_TITLES.add("FORM "+f.getFormId());
							Set<FormFields> fields = f.getFields();
							Iterator<FormFields> it = fields.iterator();
							while (it.hasNext()){
								FormFields field = it.next();
//								FORM_CONTENT.put(field.getField_label(), field.getField_type_id().get)
								
							}
					}
				}*/
				//test
				/*FORM_TITLES.add("FORM 1");
				Form dummy = new Form();
				dummy.setFormName(FORM_TITLES.get(0));
				FORM_TITLES.add("FORM 2");
				Form dummy2 = new Form();
				dummy2.setFormName(FORM_TITLES.get(1));
				forms.add(dummy);
				forms.add(dummy2);*/
				this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FORM_TITLES));

				listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int arg2,
							long arg3) {
						String title = ((TextView) view).getText().toString();
						
						
						Form fr = getFormByName(title);
						if (fr!=null) {
							bean.setForm(fr);
//							bean.setFform();
						}
			              // Launching new Activity on selecting single List Item
			              Intent i = new Intent(getApplicationContext(), FormActivity.class);
			              // sending data to new activity
			              Bundle bundle = new Bundle();
			              userid =4;
			              bundle.putInt("Userid", userid.intValue());
			              bundle.putString("Username",name);
			             // bundle allocation
			              i.putExtras(bundle);
			              startActivity(i);
					}
				});
			}else{
				TextView view = (TextView) findViewById(R.id.textView1);
				view.setTextSize(20);
				view.setText("No Forms available");
				layout.addView(view);
			}
			
			task = new NetworkPollTask(activeNetworkInfo,networkFacade);
			task.execute(this);
			

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_charity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:

			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void synchronize(View view) {
		
	}
	
	public void synchronize() {
		
	}
	
	public void saveToSqlLite(View view) {

	}
	
	public void saveToSqLite() {
		
	}
	
	
	private Form getFormByName(String name) {
		for (Form f: forms){
			if (f.getFormName().equals(name)) {
				return f;
			}
		}
		
		return null;
	}	
	
	private class NetworkPollTask extends AsyncTask<Activity, Void, Void> {

		private NetworkServiceFacade nfacade;
		private NetworkInfo ninfo;
		
		@Override
		protected Void doInBackground(Activity... params) {
				nfacade.poll(params[0]);
				return null;
		}
		
		public NetworkPollTask(NetworkInfo info, NetworkServiceFacade facade) {
			this.ninfo = info;
			this.nfacade = facade;
		}
		
	}
		
	public void infoPopup (View view) {
		 popUp.showAtLocation(popUpLayout, Gravity.BOTTOM, 10, 10);
         popUp.update(50, 50, 300, 80);
         popUpLayout = new LinearLayout(this);
         params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                 LayoutParams.WRAP_CONTENT);
         popUpLayout.setOrientation(LinearLayout.VERTICAL);
         
         TextView tx = new TextView(this);
         popUpLayout.addView(tx, params);
         popUp.setContentView(popUpLayout);
         

	}
	
	
	

}
